package pl.gda.wsb.firebaseapp.fragments.dao

import androidx.room.*
import pl.gda.wsb.firebaseapp.fragments.home.entities.DishResponseRoom

@Dao
interface DishDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addAllDishes(dishesList: List<DishResponseRoom>)

//    @Delete
//    suspend fun deleteAll(dishesList: List<DishResponseRoom>)

    @Query("SELECT * FROM dishesroom")
    suspend fun getAllDishes(): List<DishResponseRoom>
}