package com.bonelesschicken.beholder.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bonelesschicken.beholder.data.daos.ArmorDao
import com.bonelesschicken.beholder.data.daos.CharacterDao
import com.bonelesschicken.beholder.data.daos.SpellDao
import com.bonelesschicken.beholder.data.daos.WeaponDao
import com.bonelesschicken.beholder.data.model.character.Character
import com.bonelesschicken.beholder.data.model.equipment.Armor
import com.bonelesschicken.beholder.data.model.equipment.Weapon
import com.bonelesschicken.beholder.data.model.spells.Spell


@Database(entities = [Character::class, Spell::class, Weapon::class, Armor::class], version = 1)
@TypeConverters(Converters::class)
abstract class BeholderDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
    abstract fun spellDao(): SpellDao
    abstract fun armorDao(): ArmorDao
    abstract fun weaponDao(): WeaponDao

    companion object {
        @Volatile private var instance: BeholderDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context)= instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also { instance = it}
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(context,
            BeholderDatabase::class.java, "beholder.db")
            .build()
    }
}