package com.bonelesschicken.beholder.ui.character.inventory

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bonelesschicken.beholder.R
import com.bonelesschicken.beholder.data.model.equipment.Armor
import com.google.android.material.chip.Chip

class ArmorListAdapter (val context: Context, private var mArmorsList: List<Armor>?)
    : RecyclerView.Adapter<ArmorListAdapter.ArmorViewHolder>() {
    private val mInflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArmorViewHolder {
        val view = mInflater.inflate(R.layout.item_armor, parent, false)
        return ArmorViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArmorViewHolder, position: Int) {
        val armor = mArmorsList!![position]
        holder.mTextArmorName.text = armor.name
        holder.mTextArmorDescription.text = armor.description
        setPrimaryScoreViewValues(holder.mArmorCard1, "Min. Strength", armor.strengthNeeded.toString())
        setPrimaryScoreViewValues(holder.mArmorCard2, "Dex. Advantage", armor.dexterityDisadvantage.toString())
        setPrimaryScoreViewValues(holder.mArmorCard3, "Weight", armor.weight.toString())
        setPrimaryScoreViewValues(holder.mArmorCard4, "Armor Class", armor.armorClass.toString())

        holder.mChipArmorType.text = armor.armorType

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            holder.mChipArmorType.chipBackgroundColor = context.getColorStateList(R.color.colorAccentLight)
        }

    }

    override fun getItemCount(): Int {
        return mArmorsList!!.size
    }

    fun setData(data: List<Armor>) {
        mArmorsList = data
        notifyDataSetChanged()
    }

    private fun setPrimaryScoreViewValues(scoreView: View, scoreName: String, scoreValue: String) {
        scoreView.findViewById<TextView>(R.id.text_generic_card_title).text = scoreName
        scoreView.findViewById<TextView>(R.id.text_generic_card_value).text = scoreValue
    }

    inner class ArmorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mChipArmorType: Chip = itemView.findViewById(R.id.chip_armor_type)

        val mTextArmorName: TextView = itemView.findViewById(R.id.text_armor_name)
        val mTextArmorDescription: TextView = itemView.findViewById(R.id.text_armor_description)

        val mArmorCard1: View = itemView.findViewById(R.id.card_armor_card_1)
        val mArmorCard2: View = itemView.findViewById(R.id.card_armor_card_2)
        val mArmorCard3: View = itemView.findViewById(R.id.card_armor_card_3)
        val mArmorCard4: View = itemView.findViewById(R.id.card_armor_card_4)
    }
}