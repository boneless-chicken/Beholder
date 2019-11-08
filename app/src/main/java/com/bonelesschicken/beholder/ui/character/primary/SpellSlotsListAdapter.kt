package com.bonelesschicken.beholder.ui.character.primary

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.recyclerview.widget.RecyclerView
import com.bonelesschicken.beholder.R
import com.bonelesschicken.beholder.data.model.spells.SpellSlot

class SpellSlotsListAdapter (private val context: Context, private var mSpellList: List<SpellSlot>?)
    : RecyclerView.Adapter<SpellSlotsListAdapter.SpellSlotsViewHolder>() {
    private val mInflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpellSlotsViewHolder {
        val view = mInflater.inflate(R.layout.item_spell_slot, parent, false)
        return SpellSlotsViewHolder(view)
    }

    override fun onBindViewHolder(holder: SpellSlotsViewHolder, position: Int) {
        val spellSlots = mSpellList!![position]
        val spellSlotsText = "Level " + spellSlots.level.toString()
        holder.mTextSpellSlotsLevel.text = spellSlotsText
        holder.mChipGroupSpellSlots.removeAllViews()
        for (x in 0 until spellSlots.quantity) {
            holder.mChipGroupSpellSlots.addView(createCheckBox(context))
        }
    }

    override fun getItemCount(): Int {
        return mSpellList!!.size
    }

    fun setData(data: List<SpellSlot>) {
        mSpellList = data
        notifyDataSetChanged()
    }
    private fun createCheckBox(context: Context): CheckBox {
        val checkBox = CheckBox(context)
        checkBox.setButtonDrawable(R.drawable.checkbox_style)
        return checkBox
    }

    inner class SpellSlotsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mTextSpellSlotsLevel: TextView = itemView.findViewById(R.id.text_spell_slot_level)
        val mChipGroupSpellSlots: LinearLayoutCompat = itemView.findViewById(R.id.chip_group_spell_slots)
    }
}