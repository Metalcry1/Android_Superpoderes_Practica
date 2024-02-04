package com.example.android_superpoderes_practica.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.android_superpoderes_practica.Domain.Model.HeroLocal


@Dao
interface HeroDAO {

    @Query("SELECT * FROM heros")
    fun getAll(): List<HeroLocal>

    @Insert
    fun insertAll(heros: List<HeroLocal>)

    @Transaction
    fun updateHeroes(heros: List<HeroLocal>) {
        deleteAllHeroes()
        insertAll(heros)
    }

    @Query("DELETE FROM heros")
    fun deleteAllHeroes()
}
