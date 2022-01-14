package com.helpapp.quitsmoking.data

import androidx.lifecycle.LiveData

class CigaretteRepository(private val cigaretteDao: CigaretteDao) {
    val readAllData: LiveData<List<Cigarette>> = cigaretteDao.readAllData()
    suspend fun addCigarette(cigarette:Cigarette){
        cigaretteDao.addCigarette(cigarette)
    }
    suspend fun deleteCigarette(cigarette: Cigarette){
        cigaretteDao.deleteCigarette(cigarette)
    }
}