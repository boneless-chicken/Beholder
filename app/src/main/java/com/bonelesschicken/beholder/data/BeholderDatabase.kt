package com.bonelesschicken.beholder.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bonelesschicken.beholder.data.daos.CharacterDao
import com.bonelesschicken.beholder.data.model.Character
import com.bonelesschicken.beholder.data.model.CharacterStats
import com.bonelesschicken.beholder.data.model.TemporaryHitPoints


@Database(entities = [Character::class], version = 1)
@TypeConverters(Converters::class)
abstract class BeholderDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao

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