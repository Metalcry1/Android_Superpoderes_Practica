package com.example.android_superpoderes_practica.data.mappers


import com.example.android_superpoderes_practica.Domain.Model.Comics
import com.example.android_superpoderes_practica.Domain.Model.Hero
import com.example.android_superpoderes_practica.Domain.Model.HeroLocal
import com.example.android_superpoderes_practica.Domain.Model.HeroUI
import com.example.android_superpoderes_practica.Domain.Model.Stories
import com.example.android_superpoderes_practica.Domain.Model.Thumbnail
import com.example.android_superpoderes_practica.Domain.Model.URL
import com.squareup.moshi.Json
import javax.inject.Inject

class LocalToUIMapper @Inject constructor() {

    fun map(localHeros: List<HeroLocal>): List<HeroUI>{
        return localHeros.map {
            HeroUI(
                it.id,
                it.name,
                it.description,
                it.thumbnail
                ) }
    }
}
