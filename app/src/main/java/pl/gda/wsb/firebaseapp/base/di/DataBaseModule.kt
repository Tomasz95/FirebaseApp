package pl.gda.wsb.firebaseapp.base.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.realm.Realm
import io.realm.Realm.getApplicationContext
import io.realm.RealmConfiguration
import pl.gda.wsb.firebaseapp.base.database.AppDatabase
import pl.gda.wsb.firebaseapp.fragments.dao.DishDao
import javax.inject.Singleton

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

        @Provides
        @Singleton
        fun providesRealmDatabase(
            @ApplicationContext context: Context
        ): Realm {
            Realm.init(context)
            val realmConfiguration = RealmConfiguration
                .Builder()
                .allowWritesOnUiThread(true)
                .name("Project")
                .build()
            Realm.setDefaultConfiguration(realmConfiguration)
            return Realm.getDefaultInstance()
        }
}

//    @Singleton
//    @Provides
//    fun provideRealm(@ApplicationContext context: Context): Realm =
//        try {
//            Realm.init(context)
//            Realm.getDefaultInstance()
//        } catch (e: Exception) {
//            Realm.getInstance(provideRealmConfig())
//        }
//
//    private fun provideRealmConfig(): RealmConfiguration = RealmConfiguration.Builder().allowWritesOnUiThread(true).build()
//    }
