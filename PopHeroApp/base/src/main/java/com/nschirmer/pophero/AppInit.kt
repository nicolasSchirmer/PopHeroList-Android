package com.nschirmer.pophero

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco

class AppInit: Application() {

    override fun onCreate() {
        super.onCreate()

        Fresco.initialize(this)
    }

}