package com.example.a2dayzfact.data.model

import com.google.gson.annotations.SerializedName

data class WikiFactListEntity(
    @SerializedName("events")
    val facts: List<WikiFactEntity>
)

data class WikiFactEntity(
    @SerializedName("text")
    val title: String,
    @SerializedName("year")
    val year: Int,
    @SerializedName("pages")
    val pages: List<WikiPage>
)

data class WikiPage(
    @SerializedName("thumbnail")
    val thumbnail: WikiThumbnail,
    @SerializedName("extract")
    val content: String,
    @SerializedName("content_urls")
    val urls: WikiPageSources
)

data class WikiPageSources(
    @SerializedName("mobile")
    val mobile: WikiPageSource
)

data class WikiPageSource(
    @SerializedName("page")
    val source: String
)

data class WikiThumbnail(
    @SerializedName("source")
    val source: String
)
