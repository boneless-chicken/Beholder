package com.bonelesschicken.beholder.ui.character


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.bonelesschicken.beholder.R

/**
 * A simple [Fragment] subclass.
 */
class SpellsCharacterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_spells_character, container, false)
    }


}
