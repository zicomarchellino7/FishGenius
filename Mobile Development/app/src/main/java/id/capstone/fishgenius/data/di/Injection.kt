package id.capstone.fishgenius.data.di

import android.content.Context
import id.capstone.fishgenius.data.FishGeniusRepository
import id.capstone.fishgenius.data.local.database.FishGeniusRoomDatabase
import id.capstone.fishgenius.data.remote.retrofit.ConfigAPI

object Injection {
    fun provideRepository(context: Context, token: String): FishGeniusRepository {
        val database = FishGeniusRoomDatabase.getDatabase(context)
        val apiService = ConfigAPI.getApiService(token)
        return FishGeniusRepository(database, apiService)
    }
}
