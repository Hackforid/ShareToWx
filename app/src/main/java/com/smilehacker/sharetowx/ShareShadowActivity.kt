package com.smilehacker.sharetowx

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle

/**
 * Created by quan.zhou on 02/03/2018.
 */
class ShareShadowActivity: Activity() {

    private val TAG = ShareShadowActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleIntent()
        finish()
    }

    private fun handleIntent() {
        val action = intent.action
        val type = intent.type
        if (action != Intent.ACTION_SEND && action != Intent.ACTION_SEND_MULTIPLE) {
            return
        }

        val title = intent.getStringExtra(Intent.EXTRA_TITLE)
        val text = intent.getStringExtra(Intent.EXTRA_TEXT)
        val content = if (title.isNullOrEmpty()) text else "$title\n$text"
        if (type.startsWith("text/")) {
            ShareUtils.shareToWxCircle(this, content, null)
        } else if (type.startsWith("image/")) {
            if (action == Intent.ACTION_SEND) {
                ShareUtils.shareToWxCircle(this, content, arrayListOf(intent.getParcelableExtra<Uri>(Intent.EXTRA_STREAM)))
            } else {
                ShareUtils.shareToWxCircle(this, content, intent.getParcelableArrayListExtra<Uri>(Intent.EXTRA_STREAM))
            }
        }

        //ShareUtils.redirectIntent(this, intent)
    }
}