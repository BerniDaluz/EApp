package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

//appViewModels -> manges state of the app
//keeps track pf screen current being displayed
//manage data beening shared between screens

class AppViewModel : ViewModel(){
    //tracks where screen is being displayed
    var currentScreen by mutableStateOf("Dashboard")
        private set //only this view model can chnage it

    //stores currently selected env reading
    var selectedReading by mutableStateOf<String?>(null)
        private set

    //tracks if data is currently being load
    var isLoading by mutableStateOf(false)
        private set

    //stores an erro message
    var errorMessage by mutableStateOf<String?>(null)
        private set


    //changes the current screen
    fun navigateTo(screenName: String){
        //update cureentscreen to new screen  name
        currentScreen = screenName

        //clear any error message when nav
        errorMessage = null
    }

    //selcts a reading(atores which one was clicked)
    fun selectReading(readingId: String) {
        //stores select reading id
        selectedReading = readingId
    }
    //deselects the cureent reading
    fun clearSelectedReading(){
        //set selected reading back to null
        selectedReading = null
    }
    //shows or hides the loading spinner
    fun setLoading(loading: Boolean){
        //updates isloading state
        isLoading = loading
    }
    //shows an error message to the user
    fun setError(message: String?){
        //update error message
        errorMessage = message
    }


}

