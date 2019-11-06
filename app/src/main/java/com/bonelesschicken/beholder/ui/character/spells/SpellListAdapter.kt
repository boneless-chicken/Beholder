package com.bonelesschicken.beholder.ui.character.spells

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bonelesschicken.beholder.R
import com.bonelesschicken.beholder.data.model.spells.Spell

class SpellListAdapter (private val context: Context, private var mSpellList: List<Spell>?)
    : RecyclerView.Adapter<SpellListAdapter.SpellViewHolder>() {
    private val mInflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpellViewHolder {
        val view = mInflater.inflate(R.layout.item_spell, parent, false)
        return SpellViewHolder(view)
    }

    override fun onBindViewHolder(holder: SpellViewHolder, position: Int) {
        val spell = mSpellList!![position]
        holder.mTextSpellName.text = spell.name
        holder.mTextSpellDescription.text = spell.description
        holder.mTextSpellHigher.text = spell.atHigherLevels
    }

    override fun getItemCount(): Int {
        return mSpellList!!.size
    }

    fun setData(data: List<Spell>) {
        mSpellList = data
        notifyDataSetChanged()
    }

    inner class SpellViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mTextSpellName: TextView = itemView.findViewById(R.id.text_spell_name)
        var mTextSpellDescription: TextView = itemView.findViewById(R.id.text_spell_description)
        var mTextSpellHigher: TextView = itemView.findViewById(R.id.text_spell_higher_levels)
    }
}