package it.pierosilvestri.bookpedia.book.presentation.book_detail

import it.pierosilvestri.bookpedia.book.domain.Book

data class BookDetailState(
    val isLoading: Boolean = true,
    val isFavorite: Boolean = false,
    val book: Book? = null,
)
