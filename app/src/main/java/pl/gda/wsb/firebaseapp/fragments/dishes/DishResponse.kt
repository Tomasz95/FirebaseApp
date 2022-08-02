package pl.gda.wsb.firebaseapp.fragments.dishes

import com.google.gson.annotations.JsonAdapter
import pl.gda.wsb.firebaseapp.fragments.dishes.adapters.DishResponseAdapter

@JsonAdapter(DishResponseAdapter::class)
data class DishResponse(
    val id: Int,
    val addresses: List<AddressesResponse>,
    val description: String,
    val ingredients: List<String>,
    val name: String,
    val restaurant: String,
    val web: String
)


