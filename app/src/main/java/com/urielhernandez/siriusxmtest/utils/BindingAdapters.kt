package com.urielhernandez.siriusxmtest.utils

import android.content.Context
import android.view.View

import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.urielhernandez.siriusxmtest.R

@BindingAdapter("visibility")
fun setVisibility(view: View, visibility: MutableLiveData<Int>?) {

    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && visibility != null) {
        visibility.observe(
            parentActivity,
            Observer { value -> view.visibility = value ?: View.VISIBLE })
    }

}

@BindingAdapter("title")
fun setMutableText(view: TextView, text: MutableLiveData<String>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && text != null) {
        text.observe(parentActivity, Observer { value ->
            view.text = value ?: ""
        })
    }
}

@BindingAdapter("authorList")
fun setAuthorList(view: TextView, authorList: MutableLiveData<List<String>>) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null)
        authorList.observe(
            parentActivity,
            Observer { value -> view.text = value?.joinToString(", ") ?: NO_AUTHOR_FOUND })
}


@BindingAdapter("adapter")
fun setAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    view.adapter = adapter
}

@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
    val context: Context = view.context
    if (imageUrl == null) {
        view.setImageDrawable(context.getDrawable(R.mipmap.image_not_found))
    } else {
        Glide.with(context)
            .load(imageUrl)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)

    }
}

@BindingAdapter("ratingBar")
fun setRating(view: RatingBar, rating: Float) {
    view.rating = rating
}
