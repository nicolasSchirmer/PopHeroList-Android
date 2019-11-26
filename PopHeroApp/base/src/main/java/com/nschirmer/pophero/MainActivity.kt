package com.nschirmer.pophero

import android.os.Bundle
import com.nschirmer.pophero.util.BaseActivity
import com.nschirmer.pophero.util.ModuleHelper

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        openModule(ModuleHelper.HERO_POP_LIST)
        finish()
    }
}
