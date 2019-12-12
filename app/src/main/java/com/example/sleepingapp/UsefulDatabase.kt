package com.example.sleepingapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.security.AccessControlContext

@Database(entities = [Useful::class], version = 1)
abstract class UsefulDatabase : RoomDatabase(){
    abstract fun usefulDao() : UsefulDao

    companion object{
        //Singleton prevents multiple instances of the database
        //opening at the same time
        @Volatile
        private var INSTANCE : UsefulDatabase? = null

        fun getDatabase(context: Context): UsefulDatabase{
            val tempDb = INSTANCE
            if(tempDb != null){
                return tempDb
            }

            //create an instance of the database
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context,
                    UsefulDatabase::class.java,
                    "useful_db"
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}