package pl.gda.wsb.firebaseapp.fragments.dishes

data class DishResponse(
    val id: Int,
    val addresses: List<AddressesResponse>,
    val description: String,
    val ingredients: List<String>,
    val name: String,
    val restaurant: String,
    val web: String
)


