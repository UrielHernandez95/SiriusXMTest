package com.urielhernandez.siriusxmtest.ui.book

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.urielhernandez.siriusxmtest.R
import com.urielhernandez.siriusxmtest.databinding.ItemBookBinding
import com.urielhernandez.siriusxmtest.model.Item

class BookListAdapter : RecyclerView.Adapter<BookListAdapter.ViewHolder>() {

    private var bookList: List<Item> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val bindingAdapter: ItemBookBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_book,
            parent,
            false
        )
        return ViewHolder(bindingAdapter)
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(bookList[position])
    }

    fun updateBookList(bookList: List<Item>) {
        this.bookList = bookList
        notifyDataSetChanged()

    }

    class ViewHolder(private val bindingAdapter: ItemBookBinding) :
        RecyclerView.ViewHolder(bindingAdapter.root) {

        private val bookViewModel = BookViewModel()

        fun bind(book: Item) {
            bookViewModel.bind(book)
            bindingAdapter.bookViewModel = bookViewModel

        }
    }


}