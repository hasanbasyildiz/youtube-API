package com.example.youtubeapikotlin.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class SnippetYt(
    @SerializedName("title")
    val title  : String,
    @SerializedName("description")
    val description: String,

    @SerializedName("customUrl")
    val customUrl: String,

    @SerializedName("publishedAt")
    val publishedAt: String,

    @SerializedName("thumbnails")
    val thumbnails: thumbnailsYt,

    @SerializedName("country")
    val country: String,


) {
}