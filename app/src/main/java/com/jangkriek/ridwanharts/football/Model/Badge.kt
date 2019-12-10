package com.jangkriek.ridwanharts.football.Model

import com.google.gson.annotations.SerializedName

data class Badge(
    @SerializedName("strTeamBadge")
    val badgeTeam: String? = ""
)