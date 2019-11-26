package com.nschirmer.poplist.herolist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nschirmer.responseobjects.Hero


internal class HeroPopListAdapter(private val heroList: ArrayList<Hero>): RecyclerView.Adapter<HeroPopViewHolder>() {

    private companion object {
        const val ITEM_COUNT = 3
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroPopViewHolder {
        return HeroPopViewHolder(LayoutInflater.from(parent.context), parent)
    }


    override fun getItemCount(): Int {
        return ITEM_COUNT
    }


    override fun onBindViewHolder(holder: HeroPopViewHolder, position: Int) {
        if(heroList.isNotEmpty()) holder.bindView(position, heroList[position])
    }

}