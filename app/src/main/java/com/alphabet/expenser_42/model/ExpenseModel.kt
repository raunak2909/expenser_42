package com.alphabet.expenser_42.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.alphabet.expenser_42.screen.home.MainActivity

@Entity(tableName = "expense")
class ExpenseModel(
    @PrimaryKey(autoGenerate = true) val id : Int,
    @ColumnInfo(name = "title") val title : String,
    @ColumnInfo(name = "desc") val desc : String,
    @ColumnInfo(name = "amt") val amount : Double,
    @ColumnInfo(name = "bal") val balance : Double,
    @ColumnInfo(name = "type") val type : Int,
    @ColumnInfo(name = "cat_type") val catType : Int,
    @ColumnInfo(name = "date") val date : String,
){
    fun getImage(catId: Int): Int{
        for (cat in MainActivity.arrCat){
            if(cat.id==catId){
                return cat.imgPath
            }
        }
        return 0
    }
}