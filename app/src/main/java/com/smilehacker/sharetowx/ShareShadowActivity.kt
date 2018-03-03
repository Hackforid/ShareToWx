package com.smilehacker.sharetowx

import android.app.Activity
import android.content.Intent
import android.os.Bundle

/**
 * Created by quan.zhou on 02/03/2018.
 */
class ShareShadowActivity: Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleIntent()
    }

    private fun handleIntent() {
        val action = intent.action
        val type = intent.type
        if (action != Intent.ACTION_SEND) {

        }
    }
}