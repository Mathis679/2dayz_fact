package com.mathislaurent.a2dayzfact.data.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class WikiFactListEntity(
    @SerializedName("events")
    val facts: List<WikiFactEntity>
)

@Keep
data class WikiFactEntity(
    @SerializedName("text")
    val title: String,
    @SerializedName("year")
    val year: Int,
    @SerializedName("pages")
    val pages: List<WikiPage>
)

@Keep
data class WikiPage(
    @SerializedName("thumbnail")
    val thumbnail: WikiThumbnail,
    @SerializedName("extract")
    val content: String,
    @SerializedName("content_urls")
    val urls: WikiPageSources
)

@Keep
data class WikiPageSources(
    @SerializedName("mobile")
    val mobile: WikiPageSource
)

@Keep
data class WikiPageSource(
    @SerializedName("page")
    val source: String
)

@Keep
data class WikiThumbnail(
    @SerializedName("source")
    val source: String
)
