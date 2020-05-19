package com.example.testnewsapp.AddressSearch.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SearchResponse(
    var requestId: String,
    var data: ResultData
) : Parcelable

@Parcelize
data class ResultData(
    var autoCompleteRequestString: String?,
    var addressList: ArrayList<AddressDetail>? = null
) : Parcelable

@Parcelize
data class AddressDetail(
    var id: String,
    var city: String,
    var addressString: String?,
    var pinCode: String?
) : Parcelable