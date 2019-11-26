package com.nschirmer.poplist

import android.graphics.PorterDuff
import android.os.Build
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import kotlinx.android.synthetic.main.hero_list.*
import kotlinx.android.synthetic.main.loading.*


internal class LoadingScreenHandler(private val activity: PopListActivity) {

    init {
        setProgressColor(ContextCompat.getColor(activity, com.nschirmer.coreui.R.color.colorPrimaryDark))
    }

    fun showLoading(show: Boolean){
        activity.let {
            it.runOnUiThread {
                it.loading_screen.visibility = if (show) View.VISIBLE else View.GONE
//            it.hero_list.visibility = if(show) View.GONE else View.VISIBLE
            }
        }
    }


    /**
     * This method will change the progress color in any android version.
     * setColorFilter is deprecated after API 29.
     *
     * @param color is a Color object.
     * **/
    private fun setProgressColor(color: Int) {
        activity.progress.run {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                DrawableCompat.wrap(this.indeterminateDrawable).let {
                    DrawableCompat.setTint(it, color)
                    this.indeterminateDrawable = DrawableCompat.unwrap(it)
                }

            } else {
                this.indeterminateDrawable.setColorFilter(color, PorterDuff.Mode.SRC_IN)
            }
        }
    }
}