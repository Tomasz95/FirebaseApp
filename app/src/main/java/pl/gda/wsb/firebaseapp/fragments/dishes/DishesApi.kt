package pl.gda.wsb.firebaseapp.fragments.dishes

import retrofit2.http.GET
import retrofit2.http.Headers

interface DishesApi {

    @Headers(
        value =
        ["X-RapidAPI-Key: 05fefddc5amshe82caa1862a2f8cp15fb97jsn1ee9dd9eb288",
            "X-RapidAPI-Host: burgers1.p.rapidapi.com"]
    )
    @GET("burgers")
    suspend fun getBurgers(): List<DishResponse>

}