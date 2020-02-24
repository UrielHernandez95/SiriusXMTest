package com.urielhernandez.siriusxmtest.model

import com.google.gson.annotations.SerializedName

data class Book(
    @SerializedName("items") val items: List<Item>,
    @SerializedName("kind") val kind: String?
)