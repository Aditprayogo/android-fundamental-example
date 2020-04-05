package com.aditprayogo.mysharedpreferences

import android.content.Context

internal class UserPreference(context: Context) {
    companion object {
        private const val PREFS_NAME = "user_pref"
        private const val NAME = "name"
        private const val EMAIL  = "email"
        private const val AGE = "age"
        private const val PHONE_NUMER = "phone"
        private const val LOVE_MU = "isLove"
    }

    private val preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun setUser(value: UserModel) {
        val editor = preferences.edit()
        editor.putString(NAME, value.name)
        editor.putString(EMAIL, value.email)
        editor.putInt(AGE, value.age)
        editor.putString(PHONE_NUMER, value.phoneNumber)
        editor.putBoolean(LOVE_MU, value.isLove)
        //apply secara async
        editor.apply()
    }

    fun getUser() : UserModel {

        val model = UserModel()
        model.name = preferences.getString(NAME, "")
        model.email = preferences.getString(EMAIL, "")
        model.age = preferences.getInt(AGE, 0)
        model.phoneNumber = preferences.getString(PHONE_NUMER, "")
        model.isLove = preferences.getBoolean(LOVE_MU, false)

        return model
    }
}