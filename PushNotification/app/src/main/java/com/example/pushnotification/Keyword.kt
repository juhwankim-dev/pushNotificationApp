package com.example.pushnotification

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Keyword (
    var keyword: String
){
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}