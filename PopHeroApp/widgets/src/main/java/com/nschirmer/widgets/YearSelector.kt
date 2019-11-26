package com.nschirmer.widgets

import android.content.Context
import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.year_selector.view.*


/**
 * Listener for when a button is pressed.
 * [position] inidicates the position in the list [yearLabels].
 * **/
typealias YearSelectorListener = (position: Int) -> Unit


class YearSelector @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0): FrameLayout(context, attrs, defStyle) {


    companion object {
        const val ALPHA_DISABLED = 0.3f
        const val ALPHA_ENABLED = 1f
    }


    /**
     * Change the text of the year label.
     * **/
    var yearLabel : String?
        set(text){
            year_label.text = text

        } get() = year_label.text.toString()

    /**
     * Change the button corner radius rect.
     * [corners] is a dimension (not a dimension resource).
     */
    var corners: Float = resources.getDimension(R.dimen.default_card_corner)


    /**
     * Change the left icon that is used as previous action.
     * **/
    var leftIcon: Drawable? = ContextCompat.getDrawable(context, R.drawable.ic_arrow_left)
        set(icon) {
            field = icon
            if(!previous_button.isVisible) previous_button.visibility = View.VISIBLE
            previous_button.setImageDrawable(icon)
        }


    /**
     * Change the left icon that is used as forward action.
     * **/
    var rightIcon: Drawable? = ContextCompat.getDrawable(context, R.drawable.ic_arrow_right)
        set(icon) {
            field = icon
            if(!forward_button.isVisible) forward_button.visibility = View.VISIBLE
            forward_button.setImageDrawable(icon)
        }


    /**
     * Set the array of labels.
     * **/
    private var yearLabelsList = listOf<String>()
        set(labels) {
            field = labels
            position = 0
        }


    /**
     * Set the position of the array and the label into the years label if that position exists.
     * **/
    var position: Int = 0
        set(pos){
            field = pos
            if(pos < yearLabelsList.size) yearLabel = yearLabelsList[position]
            forward_button.isClickable = pos < yearLabelsList.size -1
            previous_button.isClickable = pos > 0
            forward_button.alpha =
                if(yearLabelsList.isNotEmpty() && pos < yearLabelsList.size -1) ALPHA_ENABLED
                else ALPHA_DISABLED
            previous_button.alpha =
                if(yearLabelsList.isNotEmpty() && pos > 0) ALPHA_ENABLED
                else ALPHA_DISABLED
        }


    init {
        LayoutInflater.from(context).inflate(R.layout.year_selector, this, true)
        setAttributes(context.obtainStyledAttributes(attrs, R.styleable.YearSelector))
    }


    private fun setAttributes(typedArray: TypedArray){
        typedArray.run {
            try {
                yearLabel = this.getString(R.styleable.YearSelector_placeholderText) ?: yearLabel
                leftIcon = this.getDrawable(R.styleable.YearSelector_leftIcon) ?: leftIcon
                rightIcon = this.getDrawable(R.styleable.YearSelector_rightIcon) ?: rightIcon
                corners = this.getDimension(R.styleable.YearSelector_cardRadius, corners)

            } finally {
                this.recycle()
            }
        }
    }


    /**
     * @param labels list of years labels to show in the [yearLabel]
     * @param listener listens the position array changes.
     * **/
    fun setYearLabels(labels: List<String>, listener: YearSelectorListener){
        yearLabelsList = labels
        listener(position)
        previous_button.setOnClickListener {
            position --
            listener(position)
        }
        forward_button.setOnClickListener {
            position ++
            listener(position)
        }
    }


    /**
     * Clear out everything in the year selector.
     * **/
    fun clearEverything(){
        position = 0
        yearLabelsList = listOf()
        yearLabel = ""
        leftIcon = null
        rightIcon = null
        forward_button.visibility = View.GONE
        previous_button.visibility = View.GONE
    }


}
