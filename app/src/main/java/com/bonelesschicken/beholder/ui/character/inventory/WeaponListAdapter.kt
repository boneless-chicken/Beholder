package com.bonelesschicken.beholder.ui.character.inventory

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bonelesschicken.beholder.R
import com.bonelesschicken.beholder.data.model.equipment.Weapon
import com.google.android.material.chip.Chip

class WeaponListAdapter (val context: Context, private var mWeaponsList: List<Weapon>?)
    : RecyclerView.Adapter<WeaponListAdapter.WeaponViewHolder>() {
    private val mInflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeaponViewHolder {
        val view = mInflater.inflate(R.layout.item_weapon, parent, false)
        return WeaponViewHolder(view)
    }

    override fun onBindViewHolder(holder: WeaponViewHolder, position: Int) {
        val weapon = mWeaponsList!![position]
        holder.mTextWeaponName.text = weapon.name
        val description = "${weapon.description}\n${weapon.properties}"
        holder.mTextWeaponDescription.text = description
        val damage = "Damage with ${weapon.damage.diceRoll} d${weapon.damage.diceValue}"
        holder.mTextWeaponProperties.text = damage
        setPrimaryScoreViewValues(holder.mWeaponCard1, "D. Type", weapon.damageType)
        setPrimaryScoreViewValues(holder.mWeaponCard2, "Weight", weapon.weight.toString())
        setPrimaryScoreViewValues(holder.mWeaponCard3, "Min. Range", weapon.minimumRange.toString())
        setPrimaryScoreViewValues(holder.mWeaponCard4, "Max. Range", weapon.maximumRange.toString())

        holder.mChipWeaponType.text = weapon.weaponType
        holder.mChipWeaponRange.text = weapon.weaponRange

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            holder.mChipWeaponType.chipBackgroundColor = context.getColorStateList(R.color.colorAccentLight)
            holder.mChipWeaponRange.chipBackgroundColor = context.getColorStateList(R.color.colorAccentDark)
        }

    }

    override fun getItemCount(): Int {
        return mWeaponsList!!.size
    }

    fun setData(data: List<Weapon>) {
        mWeaponsList = data
        notifyDataSetChanged()
    }

    private fun setPrimaryScoreViewValues(scoreView: View, scoreName: String, scoreValue: String) {
        scoreView.findViewById<TextView>(R.id.text_generic_card_title).text = scoreName
        scoreView.findViewById<TextView>(R.id.text_generic_card_value).text = scoreValue
    }

    inner class WeaponViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mChipWeaponType: Chip = itemView.findViewById(R.id.chip_weapon_type)
        val mChipWeaponRange: Chip = itemView.findViewById(R.id.chip_weapon_range)

        val mTextWeaponName: TextView = itemView.findViewById(R.id.text_weapon_name)

        val mWeaponCard1: View = itemView.findViewById(R.id.card_weapon_card_1)
        val mWeaponCard2: View = itemView.findViewById(R.id.card_weapon_card_2)
        val mWeaponCard3: View = itemView.findViewById(R.id.card_weapon_card_3)
        val mWeaponCard4: View = itemView.findViewById(R.id.card_weapon_card_4)

        val mTextWeaponDescription: TextView = itemView.findViewById(R.id.text_weapon_description)
        val mTextWeaponProperties: TextView = itemView.findViewById(R.id.text_weapon_properties)
    }
}