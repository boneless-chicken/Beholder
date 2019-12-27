package com.bonelesschicken.beholder.ui.character.inventory


import android.os.AsyncTask
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.bonelesschicken.beholder.R
import com.bonelesschicken.beholder.data.model.equipment.Armor
import com.bonelesschicken.beholder.data.model.equipment.Weapon
import com.bonelesschicken.beholder.ui.character.CharacterViewModel
import com.bonelesschicken.beholder.ui.character.CharacterViewModelFactory
import com.bonelesschicken.beholder.ui.character.spells.SpellListAdapter

/**
 * A simple [Fragment] subclass.
 */
class InventoryCharacterFragment : Fragment() {

    private lateinit var mRecyclerPreparedWeapons: RecyclerView
    private lateinit var mWeaponsPreparedListAdapter: WeaponListAdapter

    private lateinit var mRecyclerPreparedArmors: RecyclerView
    private lateinit var mArmorsPreparedListAdapter: ArmorListAdapter

    private lateinit var characterViewModel: CharacterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_inventory_character, container, false)
        mRecyclerPreparedWeapons = v.findViewById(R.id.recycler_weapons_prepared)
        mRecyclerPreparedArmors = v.findViewById(R.id.recycler_armors_prepared)

        context?.let {
            mWeaponsPreparedListAdapter = WeaponListAdapter(it, ArrayList())

            mRecyclerPreparedWeapons.layoutManager = LinearLayoutManager(it)
            mRecyclerPreparedWeapons.adapter = mWeaponsPreparedListAdapter

            mArmorsPreparedListAdapter = ArmorListAdapter(it, ArrayList())

            mRecyclerPreparedArmors.layoutManager = LinearLayoutManager(it)
            mRecyclerPreparedArmors.adapter = mArmorsPreparedListAdapter
        }
        return v
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context?.let { it ->

            characterViewModel = ViewModelProvider(this, CharacterViewModelFactory(it))
                .get(CharacterViewModel::class.java)

            characterViewModel.getCharacter(arguments!!.getString("KEY_CHARACTER_ID")!!)
                .observe(this, Observer { character ->
                    val weaponsPrepared = ArrayList<Weapon>()
                    val armorsPrepared = ArrayList<Armor>()

                    AsyncTask.execute {
                        character.characterEquipment.weaponsEquipped.forEach { weaponId ->
                            weaponsPrepared.add(characterViewModel.getWeapon(weaponId))
                        }

                        character.characterEquipment.armorEquipped.forEach { armorId ->
                            armorsPrepared.add(characterViewModel.getArmor(armorId))
                        }

                        /*val spellsKnownOrdered = spellsKnown.sortedWith(compareBy<Spell> { it.requiredLevel }.thenBy { it.name })*/

                        activity?.runOnUiThread {
                            mWeaponsPreparedListAdapter.setData(weaponsPrepared)
                            mArmorsPreparedListAdapter.setData(armorsPrepared)
                        }
                    }
                })
        }
    }

}
