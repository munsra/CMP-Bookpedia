package it.pierosilvestri.bookpedia.book.presentation.book_detail

import it.pierosilvestri.bookpedia.book.domain.Book

sealed interface BookDetailAction {
    data object OnBackClick: BookDetailAction
    data object OnFavoriteClick: BookDetailAction
    data class OnSelectedBookChange(val book: Book): BookDetailAction
}