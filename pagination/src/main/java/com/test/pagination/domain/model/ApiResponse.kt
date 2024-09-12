package com.test.pagination.domain.model


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class ApiResponse(
    @SerializedName("page")
    var page: Int? = null,
    @SerializedName("per_page")
    var perPage: Int? = null,
    @SerializedName("total")
    var total: Int? = null,
    @SerializedName("total_pages")
    var totalPages: Int? = null,
    @SerializedName("data")
    var `data`: List<Data>,
    @SerializedName("support")
    var support: Support? = null
) : Parcelable