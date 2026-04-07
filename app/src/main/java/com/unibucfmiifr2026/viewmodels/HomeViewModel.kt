package com.unibucfmiifr2026.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unibucfmiifr2026.data.AppDatabase
import com.unibucfmiifr2026.data.entities.UserEntity
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel(){
    private val userDAO = AppDatabase.getDatabase().userDAO()

    fun addUser(firstName: String, lastName: String){
        viewModelScope.launch {
            userDAO.insert(UserEntity(firstName = firstName, lastName = lastName))
        }
    }
}