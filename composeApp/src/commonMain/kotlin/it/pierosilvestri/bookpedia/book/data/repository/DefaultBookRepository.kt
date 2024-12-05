package it.pierosilvestri.bookpedia.book.data.repository

import androidx.room.Query
import it.bookpedia.bookpedia.core.domain.Result
import it.bookpedia.bookpedia.core.domain.map
import it.pierosilvestri.bookpedia.book.data.mappers.toBook
import it.pierosilvestri.bookpedia.book.data.network.RemoteBookDataSource
import it.pierosilvestri.bookpedia.book.domain.Book
import it.pierosilvestri.bookpedia.book.domain.BookRepository
import it.pierosilvestri.bookpedia.core.domain.DataError

class DefaultBookRepository(
    private val remoteBookDataSource: RemoteBookDataSource
): BookRepository {

    override suspend fun searchBooks(
        query: String
    ): Result<List<Book>, DataError.Remote> {
        return remoteBookDataSource
            .searchBooks(query)
            .map { dto ->
                dto.results.map { it.toBook() }
            }
    }

    override suspend fun getBookDescription(bookId: String): Result<String?, DataError> {
        return remoteBookDataSource
            .getBookDetails(bookId)
            .map {
                it.description
            }
    }
}