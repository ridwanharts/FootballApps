package com.jangkriek.ridwanharts.football

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")

fun toSimpleString(date: Date?): String? = with(date?: Date()){

    SimpleDateFormat("EEE, dd MMM yyy").format(this)
}