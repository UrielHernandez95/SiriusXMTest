package com.urielhernandez.siriusxmtest.model

import com.google.gson.annotations.SerializedName

data class VolumeInfo(
    @SerializedName("authors") val authors: List<String>?,
    @SerializedName("imageLinks") val imageLinks: ImageLinks?,
    @SerializedName("title") val title: String?,
    @SerializedName("averageRating") val rating: Float?
)