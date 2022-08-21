package pl.gda.wsb.firebaseapp.fragments.home.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import pl.gda.wsb.firebaseapp.base.database.Converters

@TypeConverters(Converters::class)
@Entity(tableName = "dishesroom")
data class DishResponseRoom (
    @PrimaryKey (autoGenerate = true) val id: Int,
//    val addresses: List<AddressesResponse>,
    val description: String,
    val ingredients: List<String>,
    val name: String,
    val restaurant: String,
    val web: String

        )

