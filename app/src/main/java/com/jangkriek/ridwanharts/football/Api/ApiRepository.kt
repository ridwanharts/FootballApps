package com.jangkriek.ridwanharts.football.Api

import java.net.URL

class ApiRepository {
    fun doRequest(url: String): String{
        return URL(url).readText()
    }
}