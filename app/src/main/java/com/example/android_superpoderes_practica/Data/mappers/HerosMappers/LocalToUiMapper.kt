package com.example.android_superpoderes_practica.Data.mappers.HerosMappers


import com.example.android_superpoderes_practica.Domain.Model.HeroLocal
import com.example.android_superpoderes_practica.Domain.Model.HeroUI
import javax.inject.Inject

class LocalToUIMapper @Inject constructor() {

    fun map(localHeros: List<HeroLocal>): List<HeroUI>{
        return localHeros.map {
            HeroUI(
                it.id,
                it.name,
                it.thumbnail
                ) }
    }
}
