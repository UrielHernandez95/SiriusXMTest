package com.urielhernandez.siriusxmtest.model

import com.google.gson.annotations.SerializedName

data class ImageLinks(
    @SerializedName("thumbnail") val thumbnail: String?
)