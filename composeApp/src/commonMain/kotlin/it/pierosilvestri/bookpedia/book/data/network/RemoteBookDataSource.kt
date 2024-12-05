package it.pierosilvestri.bookpedia.book.data.network

import it.bookpedia.bookpedia.core.domain.Result
import it.pierosilvestri.bookpedia.book.data.dto.BookWorkDto
import it.pierosilvestri.bookpedia.book.data.dto.SearchResponseDto
import it.pierosilvestri.bookpedia.core.domain.DataError

interface RemoteBookDataSource {
    suspend fun searchBooks(
        query: String,
        resultLimit: Int? = null
    ): Result<SearchResponseDto, DataError.Remote>

    suspend fun getBookDetails(bookWorkId: String): Result<BookWorkDto, DataError.Remote>
}