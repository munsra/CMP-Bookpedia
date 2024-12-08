package it.pierosilvestri.bookpedia.book.presentation.book_list

import it.bookpedia.bookpedia.core.presentation.UiText
import it.pierosilvestri.bookpedia.book.domain.Book

data class BookListState(
    val searchQuery: String = "Kotlin",
    val searchResults: List<Book> = emptyList(),
    val favoriteBooks: List<Book> = emptyList(),
    val isLoading: Boolean = true,
    val selectedTabIndex: Int = 0,
    val errorMessage: UiText? = null
)
