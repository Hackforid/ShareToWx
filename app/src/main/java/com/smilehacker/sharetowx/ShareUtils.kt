package com.smilehacker.sharetowx

import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.Uri

/**
 * Created by zhouquan on 18/3/3.
 */
object ShareUtils {
    fun redirectIntent(activity: Activity, originIntent: Intent) {
        val intent = Intent()
        if (originIntent.type.startsWith("image/")) {
            intent.component = ComponentName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareToTimeLineUI")
            intent.action = Intent.ACTION_SEND
            intent.type = "image/*"
            intent.putExtra("Kdescription",originIntent.getStringExtra(Intent.EXTRA_TEXT))
            intent.putExtra(Intent.EXTRA_STREAM, originIntent.getParcelableExtra<Uri>(Intent.EXTRA_STREAM))
        }
        activity.startActivity(intent)
    }

    fun shareToWxCircle(context: Context, content: String? = null, images: ArrayList<Uri>? = null) {
        val intent = Intent()
        intent.component = ComponentName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareToTimeLineUI")
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        intent.putExtra("Kdescription", content)
        if (images != null && images.isNotEmpty()) {
            if (images.size > 1) {
                intent.action = Intent.ACTION_SEND_MULTIPLE
                intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, images)
            } else {
                intent.action = Intent.ACTION_SEND
                intent.putExtra(Intent.EXTRA_STREAM, images[0])
            }
            intent.type = "image/*"
        } else {
            intent.action = Intent.ACTION_SEND
            intent.type = "image/*"
            intent.putExtra(Intent.EXTRA_STREAM, Uri.parse(""))
        }

        context.startActivity(intent)
    }
}