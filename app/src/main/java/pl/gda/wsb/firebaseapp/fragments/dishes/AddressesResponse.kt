package pl.gda.wsb.firebaseapp.fragments.dishes

data class AddressesResponse(
    val addressId: Int,
    val country: String,
    val line1: String,
    val line2: String,
    val number: String,
    val postcode: String
)