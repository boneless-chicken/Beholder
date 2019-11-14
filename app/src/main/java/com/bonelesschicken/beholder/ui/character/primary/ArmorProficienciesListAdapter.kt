package com.bonelesschicken.beholder.ui.character.primary

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bonelesschicken.beholder.R

class ArmorProficienciesListAdapter (context: Context, private var mProficienciesList: List<String>?)
    : RecyclerView.Adapter<ArmorProficienciesListAdapter.ProficienciesViewHolder>() {
    private val mInflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProficienciesViewHolder {
        val view = mInflater.inflate(R.layout.item_armor_proficiency, parent, false)
        return ProficienciesViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProficienciesViewHolder, position: Int) {
        val proficiency = mProficienciesList!![position]
        holder.mTextProficiency.text = proficiency
    }

    override fun getItemCount(): Int {
        return mProficienciesList!!.size
    }

    fun setData(data: List<String>) {
        mProficienciesList = data
        notifyDataSetChanged()
    }

    inner class ProficienciesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mTextProficiency: TextView = itemView.findViewById(R.id.text_item_list)
    }
}