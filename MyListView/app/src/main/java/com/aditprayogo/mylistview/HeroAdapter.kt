package com.aditprayogo.mylistview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.item_hero.view.*

class HeroAdapter internal constructor(private val context: Context) : BaseAdapter(){

    //bertugas menampung data dari activity
    internal var heroes = arrayListOf<Hero>()

    override fun getView(position: Int, view: View?, viewGroup: ViewGroup?): View {

        var itemView = view

        if (itemView == null) {

            itemView = LayoutInflater.from(context)
                .inflate(R.layout.item_hero, viewGroup, false)

        }

        val viewHolder = ViewHolder(itemView as View)

        val hero = getItem(position) as Hero

        viewHolder.bind(hero)

        return itemView

    }

//    private inner class ViewHolder internal constructor(view: View) {
//
//        private val txtName: TextView = view.findViewById(R.id.txt_name)
//        private val txtDesc: TextView = view.findViewById(R.id.txt_description)
//        private val imgHero: CircleImageView = view.findViewById(R.id.img_photo)
//
//        internal fun bind(hero: Hero){
//            txtName.text = hero.name
//            txtDesc.text = hero.description
//            imgHero.setImageResource(hero.photo)
//        }
//    }

    inner class ViewHolder constructor(private val view: View) {

        fun bind(hero: Hero) {
            with(view){
                txt_name.text = hero.name
                txt_description.text = hero.description
                img_photo.setImageResource(hero.photo)
            }

        }
    }

    override fun getItem(i: Int): Any = heroes[i]

    override fun getItemId(i: Int): Long = i.toLong()

    override fun getCount(): Int = heroes.size
}