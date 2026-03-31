package com.unibucfmiifr2026.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class AuthState(
    val isLoading: Boolean = false,
    val error: String? = null
)

class AuthViewModel : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    val isLoggedIn: Boolean = auth.currentUser != null
    private val _authState = MutableStateFlow(AuthState())
    val authState: StateFlow<AuthState> = _authState

    fun register(email: String, password: String, onSuccess: () -> Unit) {
        _authState.value = AuthState(isLoading = true)
        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                // Registration successful
                _authState.value = AuthState()
                onSuccess()
            }
            .addOnFailureListener { error ->
                // Registration failed
                _authState.value = AuthState(error = error.message)
            }
    }

    fun login(email: String, password: String, onSuccess: () -> Unit) {
        _authState.value = AuthState(isLoading = true)
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                // Login successful
                Log.e("TAG", "login:Success")
                _authState.value = AuthState()
                onSuccess()
            }
            .addOnFailureListener {
                // Login failed
                Log.e("TAG", "login:Failed")
                _authState.value = AuthState(error = it.message)
            }
    }

    fun logout(){
        auth.signOut()
    }

}