package com.bugrahankaramollaoglu.tasty.data

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore


val Context.dataStore by preferencesDataStore(name = "favourites_prefs")

