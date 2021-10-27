package com.example.quitsmoking.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.quitsmoking.fragments.MainFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CigaretteViewModel(application: Application): AndroidViewModel(application) {

    private val readAllData: LiveData<List<Cigarette>>
    private val repository: CigaretteRepository

    init {
        val cigaretteDao = CigaretteDatabase.getDatabase(MainFragment()).cigaretteDao()
        repository = CigaretteRepository(cigaretteDao)
        readAllData = repository.readAllData
    }

    fun addCigarette(cigarette: Cigarette){
        viewModelScope.launch(Dispatchers.IO){
            repository.addCigarette(cigarette)
        }
    }
}