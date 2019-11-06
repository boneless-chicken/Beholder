package com.bonelesschicken.beholder.ui.character

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.bonelesschicken.beholder.ui.character.inventory.InventoryCharacterFragment
import com.bonelesschicken.beholder.ui.character.primary.PrimaryCharacterFragment
import com.bonelesschicken.beholder.ui.character.spells.SpellsCharacterFragment

class CharacterFragmentPagerAdapter(fragmentManager: FragmentManager, private val characterId: String) :
    FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val _count = 3

    // 2
    override fun getItem(position: Int): Fragment {
        val fragment = when (position) {
            0 -> PrimaryCharacterFragment()
            1 -> SpellsCharacterFragment()
            2 -> InventoryCharacterFragment()
            else -> PrimaryCharacterFragment()
        }

        val bundle = Bundle()
        bundle.putString("KEY_CHARACTER_ID", characterId)
        fragment.arguments = bundle

        return fragment
    }

    // 3
    override fun getCount(): Int {
        return _count
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Primary"
            1 -> "Spells"
            2 -> "Inventory"
            else -> "Primary"
        }
    }

}