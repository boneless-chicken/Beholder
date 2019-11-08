package com.bonelesschicken.beholder.ui.character.spells


import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bonelesschicken.beholder.R
import com.bonelesschicken.beholder.data.model.spells.Spell
import com.bonelesschicken.beholder.ui.character.CharacterViewModel
import com.bonelesschicken.beholder.ui.character.CharacterViewModelFactory

/**
 * A simple [Fragment] subclass.
 */
class SpellsCharacterFragment : Fragment() {

    private lateinit var mRecyclerPreparedSpells: RecyclerView
    private lateinit var mRecyclerKnownSpells: RecyclerView

    private lateinit var mSpellsPreparedListAdapter: SpellListAdapter
    private lateinit var mSpellsKnownListAdapter: SpellListAdapter

    private lateinit var characterViewModel: CharacterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_spells_character, container, false)
        mRecyclerPreparedSpells = v.findViewById(R.id.recycler_spells_prepared)
        mRecyclerKnownSpells = v.findViewById(R.id.recycler_spells_known)

        context?.let {
            mSpellsPreparedListAdapter = SpellListAdapter(it, ArrayList())
            mSpellsKnownListAdapter = SpellListAdapter(it, ArrayList())

            mRecyclerPreparedSpells.layoutManager = LinearLayoutManager(it)
            mRecyclerPreparedSpells.adapter = mSpellsPreparedListAdapter

            mRecyclerKnownSpells.layoutManager = LinearLayoutManager(it)
            mRecyclerKnownSpells.adapter = mSpellsKnownListAdapter
        }
        return  v
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context?.let { it ->

            characterViewModel = ViewModelProvider(this, CharacterViewModelFactory(it))
                .get(CharacterViewModel::class.java)

            characterViewModel.getCharacter(arguments!!.getString("KEY_CHARACTER_ID")!!)
                .observe(this, Observer { character ->
                    val spellsPrepared = ArrayList<Spell>()
                    val spellsKnown = ArrayList<Spell>()

                    AsyncTask.execute {
                        character.characterSpells.spellsPrepared.forEach { spellId ->
                            spellsPrepared.add(characterViewModel.getSpell(spellId))
                        }

                        character.characterSpells.spellsKnown.forEach { spellId ->
                            spellsKnown.add(characterViewModel.getSpell(spellId))
                        }

                        spellsPrepared.sortBy { spellPrepared ->
                            spellPrepared.requiredLevel
                        }

                        val spellsPreparedOrdered = spellsPrepared.sortedWith(compareBy<Spell> { it.requiredLevel }.thenBy { it.name })
                        val spellsKnownOrdered = spellsKnown.sortedWith(compareBy<Spell> { it.requiredLevel }.thenBy { it.name })

                        activity?.runOnUiThread {
                            mSpellsPreparedListAdapter.setData(spellsPreparedOrdered)
                            mSpellsKnownListAdapter.setData(spellsKnownOrdered)
                        }
                    }
                })
        }
    }
}
