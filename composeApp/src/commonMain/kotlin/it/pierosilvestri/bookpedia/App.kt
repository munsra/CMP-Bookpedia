package it.pierosilvestri.bookpedia


import androidx.compose.runtime.*
import io.ktor.client.engine.HttpClientEngine
import it.pierosilvestri.bookpedia.book.data.network.KtorRemoteBookDataSource
import it.pierosilvestri.bookpedia.book.data.repository.DefaultBookRepository
import it.pierosilvestri.bookpedia.book.presentation.book_list.BookListScreenRoot
import it.pierosilvestri.bookpedia.book.presentation.book_list.BookListViewModel
import it.pierosilvestri.bookpedia.core.data.HttpClientFactory
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {
    val viewModel = koinViewModel<BookListViewModel>()
    BookListScreenRoot(
        viewModel = viewModel,
        onBookClick = {

        }
    )
}