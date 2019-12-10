package com.jangkriek.ridwanharts.football.Model

import com.google.gson.annotations.SerializedName

data class DetailMatch(

    @SerializedName("idEvent")
    val eventId: String? = null,

    @SerializedName("strEvent")
    val eventStr: String? = null,

    @SerializedName("idLeague")
    val leagueId: String? = null,

    @SerializedName("dateEvent")
    val eventDate: String? = null,

    @SerializedName("strDate")
    val dateStr: String? = null,

    @SerializedName("strSport")
    val sportStr: String? = null,

    @SerializedName("strCountry")
    val countryStr: Any? = null,

    @SerializedName("idSoccerXML")
    val soccerXMLId: String? = null,

    @SerializedName("strTVStation")
    val tVStationStr: Any? = null,

    @SerializedName("strThumb")
    val thumbStr: Any? = null,

    @SerializedName("strLeague")
    val leagueStr: String? = null,

    @SerializedName("strCity")
    val cityStr: Any? = null,

    @SerializedName("strPoster")
    val posterStr: Any? = null,

    @SerializedName("intRound")
    val roundInt: String? = null,

    @SerializedName("strMap")
    val mapStr: Any? = null,

    @SerializedName("strBanner")
    val bannerStr: Any? = null,

    @SerializedName("strFanart")
    val fanartStr: Any? = null,

    @SerializedName("strDescriptionEN")
    val descriptionENStr: Any? = null,

    @SerializedName("strResult")
    val resultStr: Any? = null,

    @SerializedName("strCircuit")
    val circuitStr: Any? = null,

    @SerializedName("strFilename")
    val fileNameStr: String? = null,

    @SerializedName("strTime")
    val timeStr: String? = null,

    @SerializedName("strLocked")
    val lockedStr: String? = null,

    @SerializedName("strSeason")
    val seasonStr: String? = null,

    @SerializedName("intSpectators")
    val spectatorsInt: Any? = null,




    @SerializedName("idHomeTeam")
    val homeTeamId: String? = null,

    @SerializedName("strHomeTeam")
    val homeTeamStr: String? = null,

    @SerializedName("strHomeFormation")
    val homeFormationStr: String? = null,

    @SerializedName("intHomeScore")
    val homeScoreInt: String? = null,

    @SerializedName("strHomeGoalDetails")
    val homeGoalDetailsStr: String? = null,

    @SerializedName("intHomeShots")
    val homeShotsInt: String? = null,

    @SerializedName("strHomeLineupGoalkeeper")
    val homeLineupGoalKeeperStr: String? = null,

    @SerializedName("strHomeLineupDefense")
    val homeLineupDefenseStr: String? = null,

    @SerializedName("strHomeLineupMidfield")
    val homeLineupMidfieldStr: String? = null,

    @SerializedName("strHomeLineupForward")
    val homeLineupForwardStr: String? = null,

    @SerializedName("strHomeLineupSubstitutes")
    val homeLineupSubstitutesStr: String? = null,

    @SerializedName("strHomeYellowCards")
    val homeYellowCardsStr: String? = null,

    @SerializedName("strHomeRedCards")
    val homeRedCardsStr: String? = null,




    @SerializedName("idAwayTeam")
    val awayTeamId: String? = null,

    @SerializedName("strAwayTeam")
    val awayTeamStr: String? = null,

    @SerializedName("strAwayFormation")
    val awayFormationStr: String? = null,

    @SerializedName("intAwayScore")
    val awayScoreInt: String? = null,

    @SerializedName("strAwayGoalDetails")
    val awayGoalDetailsStr: String? = null,

    @SerializedName("intAwayShots")
    val awayShotsInt: String? = null,

    @SerializedName("strAwayLineupGoalkeeper")
    val awayLineupGoalKeeperStr: String? = null,

    @SerializedName("strAwayLineupDefense")
    val awayLineupDefenseStr: String? = null,

    @SerializedName("strAwayLineupMidfield")
    val awayLineupMidfieldStr: String? = null,

    @SerializedName("strAwayLineupForward")
    val awayLineupForwardStr: String? = null,

    @SerializedName("strAwayLineupSubstitutes")
    val awayLineupSubstitutesStr: String? = null,

    @SerializedName("strAwayYellowCards")
    val awayYellowCardsStr: String? = null,

    @SerializedName("strAwayRedCards")
    val awayRedCardsStr: String? = null

    )