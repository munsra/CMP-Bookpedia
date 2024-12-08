package it.pierosilvestri.bookpedia.book.data.repository

import androidx.room.Query
import androidx.sqlite.SQLiteException
import it.bookpedia.bookpedia.core.domain.EmptyResult
import it.bookpedia.bookpedia.core.domain.Result
import it.bookpedia.bookpedia.core.domain.map
import it.pierosilvestri.bookpedia.book.data.database.FavoriteBookDao
import it.pierosilvestri.bookpedia.book.data.mappers.toBook
import it.pierosilvestri.bookpedia.book.data.mappers.toBookEntity
import it.pierosilvestri.bookpedia.book.data.network.RemoteBookDataSource
import it.pierosilvestri.bookpedia.book.domain.Book
import it.pierosilvestri.bookpedia.book.domain.BookRepository
import it.pierosilvestri.bookpedia.core.domain.DataError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DefaultBookRepository(
    private val remoteBookDataSource: RemoteBookDataSource,
    private val favoriteBookDao: FavoriteBookDao
): BookRepository {
    override suspend fun searchBooks(query: String): Result<List<Book>, DataError.Remote> {
        return remoteBookDataSource
            .searchBooks(query)
            .map { dto ->
                dto.results.map { it.toBook() }
            }
    }

    override suspend fun getBookDescription(bookId: String): Result<String?, DataError> {
        val localResult = favoriteBookDao.getFavoriteBook(bookId)

        return if(localResult == null) {
            remoteBookDataSource
                .getBookDetails(bookId)
                .map { it.description }
        } else {
            Result.Success(localResult.description)
        }
    }

    override fun getFavoriteBooks(): Flow<List<Book>> {
        return favoriteBookDao
            .getFavoriteBooks()
            .map { bookEntities ->
                bookEntities.map { it.toBook() }
            }
    }

    override fun isBookFavorite(id: String): Flow<Boolean> {
        return favoriteBookDao
            .getFavoriteBooks()
            .map { bookEntities ->
                bookEntities.any { it.id == id }
            }
    }

    override suspend fun markAsFavorite(book: Book): EmptyResult<DataError.Local> {
        return try {
            favoriteBookDao.upsert(book.toBookEntity())
            Result.Success(Unit)
        } catch(e: SQLiteException) {
            Result.Error(DataError.Local.DISK_FULL)
        }
    }

    override suspend fun deleteFromFavorites(id: String) {
        favoriteBookDao.deleteFromFavorites(id)
    }
}