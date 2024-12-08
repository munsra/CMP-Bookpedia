package it.pierosilvestri.bookpedia.book.domain

import it.bookpedia.bookpedia.core.domain.EmptyResult
import it.bookpedia.bookpedia.core.domain.Result
import it.pierosilvestri.bookpedia.core.domain.DataError
import kotlinx.coroutines.flow.Flow

interface BookRepository {
    suspend fun searchBooks(query: String): Result<List<Book>, DataError.Remote>
    suspend fun getBookDescription(bookId: String): Result<String?, DataError>

    fun getFavoriteBooks(): Flow<List<Book>>
    fun isBookFavorite(id: String): Flow<Boolean>
    suspend fun markAsFavorite(book: Book): EmptyResult<DataError.Local>
    suspend fun deleteFromFavorites(id: String)
}