package com.issuesolver.presentation.profile.my_account


sealed class MyAccountEvent {
    data class FullNameChanged(val fullName: String) : MyAccountEvent()


    object Submit : MyAccountEvent()


}