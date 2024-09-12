package com.test.pagination.domain.model


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class Support(
    @SerializedName("url")
    var url: String? = null,
    @SerializedName("text")
    var text: String? = null
) : Parcelable