package com.jangkriek.ridwanharts.football.Database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class FavouriteDBHelper(ctx: Context): ManagedSQLiteOpenHelper(ctx, "Favourite.db", null, 1){

    companion object {
        private var instance: FavouriteDBHelper?=null

        fun getInstance(ctx: Context): FavouriteDBHelper {
            if(instance ==null){
                instance =
                        FavouriteDBHelper(ctx.applicationContext)
            }
            return instance as FavouriteDBHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        if (db != null) {
            db.createTable(
                FavouriteMatch.TABLE_FAVOURITE,true,
                FavouriteMatch.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                FavouriteMatch.ID_EVENT to TEXT + UNIQUE,
                FavouriteMatch.DATE_EVENT to TEXT,
                FavouriteMatch.HOME_TEAM to TEXT,
                FavouriteMatch.HOME_SCORE to TEXT,
                FavouriteMatch.HOME_GOALS to TEXT,
                FavouriteMatch.AWAY_TEAM to TEXT,
                FavouriteMatch.AWAY_SCORE to TEXT,
                FavouriteMatch.AWAY_GOALS to TEXT)

            db.createTable(
                FavouriteTeam.TABLE_FAVOURITE_TEAM, true,
                FavouriteTeam.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                FavouriteTeam.ID_TEAM to TEXT + UNIQUE,
                FavouriteTeam.NAME_TEAM to TEXT,
                FavouriteTeam.IMAGE_TEAM to TEXT,
                FavouriteTeam.YEAR_TEAM to TEXT,
                FavouriteTeam.STADIUM_TEAM to TEXT,
                FavouriteTeam.DESC_TEAM to TEXT)
        }


    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (db != null) {
            db.dropTable(FavouriteMatch.TABLE_FAVOURITE, true)
            db.dropTable(FavouriteTeam.TABLE_FAVOURITE_TEAM, true)
        }
    }
}

val Context.database: FavouriteDBHelper get() = FavouriteDBHelper.getInstance(applicationContext)