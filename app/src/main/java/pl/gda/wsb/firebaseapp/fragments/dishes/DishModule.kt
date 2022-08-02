package pl.gda.wsb.firebaseapp.fragments.dishes

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
abstract class DishModule {

        companion object {

            @Provides
            fun provideDishesApi(retrofit: Retrofit): DishesApi =
                retrofit.create(DishesApi::class.java)

        }
    }




