package com.nschirmer.poplist.herolist

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.nschirmer.poplist.R
import com.nschirmer.responseobjects.Hero
import kotlinx.android.synthetic.main.hero_list_card.view.*


internal class HeroPopViewHolder (inflater: LayoutInflater, private val parent: ViewGroup):
    RecyclerView.ViewHolder(inflater.inflate(R.layout.hero_list_card, parent, false)) {

    fun bindView(position: Int, hero: Hero){
        itemView.let {
            setPicture(hero.pictureUrl, it)
            setName(hero.name, it)
            setScore(hero.score, position, it)
            setCardSize(position, it)
        }
    }


    private fun setName(name: String, view: View){
        view.herocard_name.text = name
    }


    private fun setPicture(url: String, view: View){
        view.herocard_picture.setImageURI(Uri.parse(url), parent.context)
    }


    private fun setScore(score: String, position: Int, view: View){
        view.herocard_score.text = parent.context.getString(R.string.list_score_label, score)
        setColorBadge(position, view)
    }


    private fun setColorBadge(position: Int, view: View){
        view.herocard_score.setBackgroundResource( when (position) {
            0 -> com.nschirmer.coreui.R.color.medal_gold
            1 -> com.nschirmer.coreui.R.color.medal_silver
            2 -> com.nschirmer.coreui.R.color.medal_bronze
            else -> com.nschirmer.coreui.R.color.gray_light
        } )
    }


    private fun setCardSize(position: Int, view: View){
        (view.herocard_guideline.layoutParams as ConstraintLayout.LayoutParams).let {
            it.guidePercent = when (position) {
                0 -> 0.78f
                1 -> 0.75f
                2 -> 0.715f
                else -> 0.6f
            }
            view.herocard_guideline.layoutParams = it
        }
    }

}