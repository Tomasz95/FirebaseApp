package pl.gda.wsb.firebaseapp.base.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import pl.gda.wsb.firebaseapp.base.database.AppDatabase
import pl.gda.wsb.firebaseapp.fragments.dao.DishDao

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun providesAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            DATABASE_NAME
        ).build()

    private const val DATABASE_NAME = "app_database"

    @Provides
    fun provideDishDatabase(appDatabase: AppDatabase): DishDao {
        return appDatabase.userDao()
    }

}