package com.example.android_superpoderes_practica.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.android_superpoderes_practica.Domain.Model.HeroLocal

@Database(entities = [HeroLocal::class], version = 1)
abstract class HeroDatabase : RoomDatabase() {
    abstract fun heroDao(): HeroDAO
}