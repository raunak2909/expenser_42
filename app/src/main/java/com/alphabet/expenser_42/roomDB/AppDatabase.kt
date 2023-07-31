package com.alphabet.expenser_42.roomDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.alphabet.expenser_42.model.ExpenseModel

@Database(entities = [ExpenseModel::class], exportSchema = false, version = 1)
abstract class AppDatabase : RoomDatabase(){
    companion object{

        private val DB_NAME = "expense_db"
        private var DB_INSTANCE : AppDatabase? = null

        @Synchronized
        fun getDatabase(context: Context) : AppDatabase{
            if(DB_INSTANCE==null){
                DB_INSTANCE = Room
                    .databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        DB_NAME)
                    .allowMainThreadQueries()
                    .build()
            }

            return DB_INSTANCE!!
        }




    }

    abstract fun ExpenseDAO() : ExpenseDAO

}