package pl.gda.wsb.firebaseapp.fragments.dishes.usecases

import android.graphics.Insets.add
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import pl.gda.wsb.firebaseapp.base.usecases.BaseUseCase
import pl.gda.wsb.firebaseapp.fragments.dao.DishDao
import pl.gda.wsb.firebaseapp.fragments.dishes.DishResponse
import java.util.*
import javax.inject.Inject
import kotlin.collections.HashMap

class InsertAllDishesAllDishesToUseCase @Inject constructor(private val provideFirestore: FirebaseFirestore) :
    BaseUseCase<List<DishResponse>, Unit>() {
    override suspend fun create(dishes: List<DishResponse>) {

        // val dishesList<String>


        dishes.forEach {
            provideFirestore.collection("dishes")
                .document(UUID.randomUUID().toString())
                .set(it)
                .await()

        }
    }
//        fun saveFireStore(
//            inspirationTitle: String,
//            description: String,
//            data: String,
//            author: String,
//            quote: String,
//            localization: String,
//            activity: FragmentActivity
//        ) {
//            val inspiration: MutableMap<String, Any> = HashMap()
//            inspiration["inspirationTitle"] = inspirationTitle
//            inspiration["description"] = description
//            inspiration["author"] = author
//            inspiration["quote"] = quote
//            inspiration["imagePath"] = imgUrl
//            inspiration["data"] = data
//            inspiration["localization"] = localization
//}}
}