package com.bonelesschicken.beholder.ui.main

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bonelesschicken.beholder.R
import com.bonelesschicken.beholder.data.model.Character
import com.bonelesschicken.beholder.ui.character.CharacterActivity
import com.google.android.material.card.MaterialCardView

class CharacterListAdapter(private val context: Context, private var mCharacterList: List<Character>?)
    : RecyclerView.Adapter<CharacterListAdapter.CharacterViewHolder>() {
    private val mInflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): CharacterViewHolder {
        val view = mInflater.inflate(R.layout.item_character, parent, false)
        return CharacterViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = mCharacterList!![position]
        holder.mTextCharacterName.text = character.name
        holder.mTextCharacterClass.text = character.className
        holder.mTextCharacterLvl.text = character.level.toString()
        holder.mCardCharacter.setOnClickListener {
            val intent = Intent(context, CharacterActivity::class.java)
            intent.putExtra(CharacterActivity.CHARACTER_ID, character.id)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return mCharacterList!!.size
    }

    fun setData(data: List<Character>) {
        mCharacterList = data
        notifyDataSetChanged()
    }

    inner class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mCardCharacter: MaterialCardView = itemView.findViewById(R.id.card_character_container)
        var mTextCharacterName: TextView = itemView.findViewById(R.id.text_character_name)
        var mTextCharacterClass: TextView = itemView.findViewById(R.id.text_character_class)
        var mTextCharacterLvl: TextView = itemView.findViewById(R.id.text_character_lvl)
    }
}