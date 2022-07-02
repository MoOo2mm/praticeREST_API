package com.example.dologinout

interface LogInView {
    fun onLogInSuccess(code : Int, result: Result)
    fun onLogInFailure()
}