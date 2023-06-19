package com.jux.composeplayground

import android.app.Application
import com.airbnb.mvrx.Mavericks

class ComposePlaygroundApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        Mavericks.initialize(this)
    }
}