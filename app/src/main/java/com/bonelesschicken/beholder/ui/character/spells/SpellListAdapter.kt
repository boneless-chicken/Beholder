package com.bonelesschicken.beholder.ui.character.spells

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bonelesschicken.beholder.R
import com.bonelesschicken.beholder.data.model.spells.Spell
import com.google.android.material.button.MaterialButton
import com.google.android.material.chip.Chip

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
        val spellLevelText = if (spell.requiredLevel > 0) {
            "Spell lvl " + spell.requiredLevel
        } else {
            "Cantrip"
        }

        val spellCastingText = spell.spellCasting.castingAmount.toString() + " " + spell.spellCasting.castingType
        val spellRangeText = spell.spellRange.range.toString() + " feet"
        var spellComponentsText = ""
        if (spell.spellComponents.material) {
            spellComponentsText += "M. "
        }
        if (spell.spellComponents.somantic) {
            spellComponentsText += "S. "
        }
        if (spell.spellComponents.verbal) {
            spellComponentsText += "V. "
        }
        val spellDuration = spell.spellDuration.timeAmount.toString() + " " + spell.spellDuration.timeUnit
        val spellAreaText = spell.spellRange.area.toString() + " " + spell.spellRange.aoeType
        setPrimaryScoreViewValues(holder.mSpellCard1, "Casting time", spellCastingText)
        setPrimaryScoreViewValues(holder.mSpellCard2, "Range", spellRangeText)
        setPrimaryScoreViewValues(holder.mSpellCard3, "Area", spellAreaText)
        setPrimaryScoreViewValues(holder.mSpellCard4, "Components", spellComponentsText)
        setPrimaryScoreViewValues(holder.mSpellCard5, "Duration", spellDuration)
        setPrimaryScoreViewValues(holder.mSpellCard6, "Saving throw", spell.savingThrows)

        holder.mChipSpellType.text = spellLevelText
        if (spell.spellType == "Cantrip") {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                holder.mChipSpellType.chipBackgroundColor = context.getColorStateList(R.color.colorAccentLight)
            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                holder.mChipSpellType.chipBackgroundColor = context.getColorStateList(R.color.colorPrimaryLight)
            }
        }

        holder.mButtonShowMore.setOnClickListener {
            if (holder.mTextSpellHigher.visibility == View.VISIBLE) {
                holder.mButtonShowMore.text = "Show More"
                holder.mLabelSpellDescription.visibility = View.GONE
                holder.mLabelSpellHigher.visibility = View.GONE
                holder.mTextSpellDescription.visibility = View.GONE
                holder.mTextSpellHigher.visibility = View.GONE
            } else {
                holder.mButtonShowMore.text = "Show Less"
                holder.mLabelSpellDescription.visibility = View.VISIBLE
                holder.mLabelSpellHigher.visibility = View.VISIBLE
                holder.mTextSpellDescription.visibility = View.VISIBLE
                holder.mTextSpellHigher.visibility = View.VISIBLE
            }
        }
    }

    override fun getItemCount(): Int {
        return mSpellList!!.size
    }

    fun setData(data: List<Spell>) {
        mSpellList = data
        notifyDataSetChanged()
    }

    private fun setPrimaryScoreViewValues(scoreView: View, scoreName: String, scoreValue: String) {
        scoreView.findViewById<TextView>(R.id.text_generic_card_title).text = scoreName
        scoreView.findViewById<TextView>(R.id.text_generic_card_value).text = scoreValue
    }

    inner class SpellViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mChipSpellType: Chip = itemView.findViewById(R.id.chip_spell_type)
        val mTextSpellName: TextView = itemView.findViewById(R.id.text_spell_name)

        val mSpellCard1: View = itemView.findViewById(R.id.card_spell_card_1)
        val mSpellCard2: View = itemView.findViewById(R.id.card_spell_card_2)
        val mSpellCard3: View = itemView.findViewById(R.id.card_spell_card_3)
        val mSpellCard4: View = itemView.findViewById(R.id.card_spell_card_4)
        val mSpellCard5: View = itemView.findViewById(R.id.card_spell_card_5)
        val mSpellCard6: View = itemView.findViewById(R.id.card_spell_card_6)

        val mLabelSpellDescription: TextView = itemView.findViewById(R.id.label_spell_description)
        val mLabelSpellHigher: TextView = itemView.findViewById(R.id.label_spell_higher)
        val mTextSpellDescription: TextView = itemView.findViewById(R.id.text_spell_description)
        val mTextSpellHigher: TextView = itemView.findViewById(R.id.text_spell_higher_levels)

        val mButtonShowMore: MaterialButton = itemView.findViewById(R.id.button_spell_show_more)
    }
}