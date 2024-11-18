package it.pierosilvestri.bookpedia


import androidx.compose.runtime.*
import it.pierosilvestri.bookpedia.book.presentation.book_list.BookListScreenRoot
import it.pierosilvestri.bookpedia.book.presentation.book_list.BookListViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
@Composable
@Preview
fun App() {
    BookListScreenRoot(
        viewModel = remember { BookListViewModel() },
        onBookClick = {

        }
    )
}