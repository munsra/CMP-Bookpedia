package it.pierosilvestri.bookpedia.book.domain

import it.bookpedia.bookpedia.core.domain.Result
import it.pierosilvestri.bookpedia.core.domain.DataError

interface BookRepository {
    suspend fun searchBooks(query: String): Result<List<Book>, DataError.Remote>
    suspend fun getBookDescription(bookId: String): Result<String?, DataError>
}