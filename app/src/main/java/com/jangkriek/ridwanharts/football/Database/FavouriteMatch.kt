package com.jangkriek.ridwanharts.football.Database

data class FavouriteMatch(val id: Long?,
                          val idEvent: String?,
                          val dateEvent: String?,
                          val homeTeam: String?,
                          val homeScore: String?,
                          val homeGoals: String?,
                          val awayTeam: String?,
                          val awayScore: String?,
                          val awayGoals: String?
                          ){

    companion object {
        const val TABLE_FAVOURITE: String = "TABLE_FAVOURITE"
        const val ID: String = "ID_"
        const val ID_EVENT: String = "ID_EVENT"
        const val DATE_EVENT: String = "DATE_EVENT"
        const val HOME_TEAM: String = "HOME_TEAM"
        const val HOME_SCORE: String = "HOME_SCORE"
        const val HOME_GOALS: String = "HOME_GOALS"
        const val AWAY_TEAM: String = "AWAY_TEAM"
        const val AWAY_SCORE: String = "AWAY_SCORE"
        const val AWAY_GOALS: String = "AWAY_GOALS"

    }

}