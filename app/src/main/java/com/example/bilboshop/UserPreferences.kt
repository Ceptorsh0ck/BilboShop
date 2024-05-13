package com.example.bilboshop

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.example.bilboshop.data.User
import com.google.gson.Gson

class UserPreferences {

    private val sharedPreferences: SharedPreferences by lazy {
        MyApp.context.getSharedPreferences(MyApp.context.getString(R.string.app_name), Context.MODE_PRIVATE)
    }

    companion object {
        const val LOGGED_USER = "logged_user"
    }


    fun saveLoggedUser(user: User) {
        val editor = sharedPreferences.edit()

        val userJson = Gson().toJson(user)
        editor.putString(LOGGED_USER, userJson)
        editor.apply()
    }

    fun unLogUser() {
        val editor = sharedPreferences.edit()
        editor.remove(LOGGED_USER)
        editor.apply()
    }

    fun getLoggedUser(): User? {
        Log.i("USERPREFERENCES","GetLoggedUser")
        val userJson = sharedPreferences.getString(LOGGED_USER, null)
        return if (userJson != null) {
            // Aquí, utilizamos Gson para convertir el JSON almacenado de nuevo a un objeto User.
            Gson().fromJson(userJson, User::class.java)
        } else {
            null
        }
    }

}