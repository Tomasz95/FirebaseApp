package pl.gda.wsb.firebaseapp.base.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import pl.gda.wsb.firebaseapp.fragments.dao.DishDao
import pl.gda.wsb.firebaseapp.fragments.home.entities.DishResponseRoom


@Database(entities = [DishResponseRoom::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): DishDao
}