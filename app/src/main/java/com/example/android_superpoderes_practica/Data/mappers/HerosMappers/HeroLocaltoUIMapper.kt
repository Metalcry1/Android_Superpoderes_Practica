package com.example.android_superpoderes_practica.Data.mappers.HerosMappers

import com.example.android_superpoderes_practica.Domain.Model.HeroRemote
import com.example.android_superpoderes_practica.Domain.Model.HeroUI

import javax.inject.Inject

class HeroRemoteConvertToUIClass @Inject constructor() {

    fun map(remoteHeros: List<HeroRemote>): List<HeroUI>{
        return remoteHeros.map {
            HeroUI(
                it.id,
                it.name,
                it.convertThumbnailToString
            ) }
    }
    }

