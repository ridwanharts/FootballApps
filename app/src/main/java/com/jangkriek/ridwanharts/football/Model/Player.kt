package com.jangkriek.ridwanharts.football.Model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Player(

    @SerializedName("idTeam")
    var teamId: String?=null,

    @SerializedName("strPlayer")
    var strPlayer: String?=null,

    @SerializedName("strDescriptionEN")
    var strDescription: String?=null,

    @SerializedName("strPosition")
    var strPosition: String?=null,

    @SerializedName("strWeight")
    var strWeight: String?=null,

    @SerializedName("strHeight")
    var strHeight: String?=null,

    @SerializedName("strCutout")
    var strCutout: String?=null,

    @SerializedName("strFanart1")
    var strFanart1: String?=null



):Parcelable