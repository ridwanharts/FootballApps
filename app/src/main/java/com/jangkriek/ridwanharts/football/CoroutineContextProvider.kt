package com.jangkriek.ridwanharts.football

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlin.coroutines.CoroutineContext

open class CoroutineContextProvider{
    open val main: CoroutineContext by lazy { Dispatchers.Main }
}