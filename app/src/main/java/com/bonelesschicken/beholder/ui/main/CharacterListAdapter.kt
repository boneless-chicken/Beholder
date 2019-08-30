package com.bonelesschicken.beholder.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bonelesschicken.beholder.R
import com.bonelesschicken.beholder.data.model.Character

class CharacterListAdapter(context: Context, private var mCharacterList: List<Character>?)
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
        holder.mTextCharacterClass.text = character.classType
        holder.mTextCharacterLvl.text = character.level.toString()
    }

    override fun getItemCount(): Int {
        return mCharacterList!!.size
    }

    fun setData(data: List<Character>) {
        mCharacterList = data
        notifyDataSetChanged()
    }

    inner class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mTextCharacterName: TextView = itemView.findViewById(R.id.text_character_name)
        var mTextCharacterClass: TextView = itemView.findViewById(R.id.text_character_class)
        var mTextCharacterLvl: TextView = itemView.findViewById(R.id.text_character_lvl)
    }
}