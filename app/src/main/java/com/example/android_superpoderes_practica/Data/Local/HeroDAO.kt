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

    @Query("UPDATE heros SET favourite = :isFavorite WHERE id = :heroId")
    fun updateFavoriteStatus(heroId: Long, isFavorite: Boolean)

    @Query("SELECT * FROM heros WHERE id = :heroId")
    fun getHeroById(heroId: Long): HeroLocal

    @Query("DELETE FROM heros")
    fun deleteAllHeroes()

    @Query("SELECT favourite FROM heros WHERE id = :heroId")
    fun getHeroStatusFavourite(heroId: Long): Boolean
}
