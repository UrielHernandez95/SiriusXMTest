package com.urielhernandez.siriusxmtest.ui.book

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.urielhernandez.siriusxmtest.R
import com.urielhernandez.siriusxmtest.base.BaseViewModel
import com.urielhernandez.siriusxmtest.model.Book
import com.urielhernandez.siriusxmtest.networking.BooksApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class BookListViewModel : BaseViewModel() {

    @Inject
    lateinit var booksApi: BooksApi

    private lateinit var subscription: Disposable

    var loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadBooks(parameterName.value) }
    val bookListAdapter: BookListAdapter = BookListAdapter()
    var parameterName: MutableLiveData<String> = MutableLiveData()


    init {
        loadBooks(DEFAULT_QUERY)
    }

    fun loadBooks(query: String?) {
        subscription = booksApi.getBooks(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrieveBookListStart() }
            .doOnTerminate { onRetrieveBookListFinish() }
            .subscribe(
                //Adding result
                { result -> onRetrieveBookListSuccess(result) },
                { onRetrieveBookListError() })
    }


    private fun onRetrieveBookListStart() {
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrieveBookListFinish() {
        loadingVisibility.value = View.GONE
    }

    private fun onRetrieveBookListSuccess(bookResult: Book) {
        bookListAdapter.updateBookList(bookResult.items)
    }

    private fun onRetrieveBookListError() {
        errorMessage.value = R.string.book_error
    }


    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    private companion object {
        private const val DEFAULT_QUERY: String = "Game of Thrones"
    }

}
