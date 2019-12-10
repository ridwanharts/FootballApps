package com.jangkriek.ridwanharts.football.Model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Event(

    @SerializedName("idEvent")
    var eventId: String? = null,

    @SerializedName("dateEvent")
    var matchDate: String? = null,

    @SerializedName("strHomeTeam")
    var teamHome: String? = null,

    @SerializedName("intHomeScore")
    var scoreHome: String? = null,

    @SerializedName("strHomeGoalDetails")
    var detailGoalsHome: String? = null,

    @SerializedName("strAwayTeam")
    var teamAway: String? = null,

    @SerializedName("intAwayScore")
    var scoreAway: String? = null,

    @SerializedName("strAwayGoalDetails")
    var detailGoalsAway: String? = null,

    @SerializedName("strTime")
    var time: String? = null


): Parcelable