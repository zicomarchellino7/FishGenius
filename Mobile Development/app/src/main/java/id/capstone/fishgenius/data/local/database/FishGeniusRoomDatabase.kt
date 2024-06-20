package id.capstone.fishgenius.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Classes::class,Moduls::class,Informations::class],version=1)
abstract class FishGeniusRoomDatabase : RoomDatabase() {
    abstract fun fishgeniusDao(): FishgeniusDao
    companion object {
        @Volatile
        private var INSTANCE: FishGeniusRoomDatabase? = null
        @JvmStatic
        fun getDatabase(context: Context): FishGeniusRoomDatabase {
            if (INSTANCE == null) {
                synchronized(FishGeniusRoomDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        FishGeniusRoomDatabase::class.java, "fishgenius_db").fallbackToDestructiveMigration().build()
                }
            }
            return INSTANCE as FishGeniusRoomDatabase
        }
    }
}