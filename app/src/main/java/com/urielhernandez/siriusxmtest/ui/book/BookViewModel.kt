package com.urielhernandez.siriusxmtest.ui.book

import androidx.lifecycle.MutableLiveData
import com.urielhernandez.siriusxmtest.base.BaseViewModel
import com.urielhernandez.siriusxmtest.model.Item

class BookViewModel : BaseViewModel() {

    private val authorList = MutableLiveData<List<String?>>()

    private val bookTitle = MutableLiveData<String?>()

    private val thumbnailUrl = MutableLiveData<String?>()

    private val rating = MutableLiveData<Float?>()


    fun bind(book: Item) {
        authorList.value = book.volumeInfo?.authors
        bookTitle.value = book.volumeInfo?.title
        thumbnailUrl.value = book.volumeInfo?.imageLinks?.thumbnail
        rating.value = book.volumeInfo?.rating
    }

    fun getBookTitle(): MutableLiveData<String?> {
        return bookTitle
    }

    fun getThumbnailUrl(): MutableLiveData<String?> {
        return thumbnailUrl
    }

    fun getAuthors(): MutableLiveData<List<String?>> {
        return authorList
    }

    fun gerRate(): MutableLiveData<Float?> {
        return rating
    }

}