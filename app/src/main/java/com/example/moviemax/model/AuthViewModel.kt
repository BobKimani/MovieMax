package com.example.moviemax.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.userProfileChangeRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class AuthViewModel : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _user = MutableStateFlow<FirebaseUser?>(auth.currentUser)
    val user: StateFlow<FirebaseUser?> = _user

    private val _isUserAuthenticated = MutableStateFlow(auth.currentUser != null)
    val isUserAuthenticated: StateFlow<Boolean> = _isUserAuthenticated

    init {
        auth.signOut()
        _user.value = null
        _isUserAuthenticated.value = false
    }

    // SignIn method
    fun signIn(email: String, password: String, onSuccess: () -> Unit, onError: (Exception) -> Unit) {
        viewModelScope.launch {
            try {
                auth.signInWithEmailAndPassword(email, password).await()
                _user.value = auth.currentUser
                _isUserAuthenticated.value = true
                onSuccess()
            } catch (e: Exception) {
                e.printStackTrace()
                onError(e) // Handle errors properly
            }
        }
    }

    // SignUp method
    fun signUp(email: String, password: String, username: String, onSuccess: () -> Unit, onError: (Exception) -> Unit) {
        viewModelScope.launch {
            try {
                val result = auth.createUserWithEmailAndPassword(email, password).await()
                val user = result.user
                user?.updateProfile(userProfileChangeRequest {
                    displayName = username
                })?.await() // Wait for Firebase to update the profile

                _user.value = auth.currentUser // Refresh user state
                _isUserAuthenticated.value = true
                onSuccess()
            } catch (e: Exception) {
                onError(e)
            }
        }
    }

    // SignOut method
    fun signOut(onSuccess: () -> Unit) {
        auth.signOut()
        _user.value = null
        _isUserAuthenticated.value = false
        onSuccess()
    }
}