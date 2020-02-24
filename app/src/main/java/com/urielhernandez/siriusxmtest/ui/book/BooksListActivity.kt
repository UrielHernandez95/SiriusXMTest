package com.urielhernandez.siriusxmtest.ui.book

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.urielhernandez.siriusxmtest.R
import com.urielhernandez.siriusxmtest.databinding.ActivityBookListBinding

class BooksListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBookListBinding
    private lateinit var viewModel: BookListViewModel
    private var errorSnackBar: Snackbar? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_book_list)


        viewModel = ViewModelProvider(this).get(BookListViewModel::class.java)


        viewModel.errorMessage.observe(this, Observer { errorMessage ->
            if (errorMessage != null) showError(errorMessage) else hideError()
        })

        viewModel.parameterName.observe(this, Observer {
            sendQuery()
        })

        binding.lifecycleOwner = this

        binding.bookListViewModel = viewModel


    }

    private fun initSearchListener(search: SearchView) {

        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.parameterName.value = query
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return true
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        val menuItem = menu.findItem(R.id.search)
        val search = menuItem.actionView as SearchView
        initSearchListener(search)
        super.onPrepareOptionsMenu(menu)
        return true
    }

    private fun hideError() {
        errorSnackBar?.dismiss()
    }

    private fun showError(errorMessage: Int) {
        errorSnackBar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackBar?.setAction(R.string.retry, viewModel.errorClickListener)
        errorSnackBar?.show()
    }

    private fun sendQuery() {
        viewModel.loadBooks(viewModel.parameterName.value)
    }

}