package com.juhwan.anyang_yi.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.juhwan.anyang_yi.present.config.Constants.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class KeywordEntity (
    @ColumnInfo(name = "keyword") var keyword: String
){
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}