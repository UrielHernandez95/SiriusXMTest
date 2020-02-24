package com.urielhernandez.siriusxmtest.model

import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("kind") val kind: String?,
    @SerializedName("volumeInfo") val volumeInfo: VolumeInfo?
)