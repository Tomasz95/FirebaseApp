package pl.gda.wsb.firebaseapp.fragments.dishes.usecases

import io.realm.Realm
import pl.gda.wsb.firebaseapp.base.usecases.BaseUseCase
import pl.gda.wsb.firebaseapp.fragments.dishes.DishResponse
import pl.gda.wsb.firebaseapp.fragments.home.entities.DishResponseRealm
import java.util.*
import javax.inject.Inject

class InsertToTheRealm @Inject constructor(private val realm: Realm) :
    BaseUseCase<List<DishResponse>, Unit>() {
    override suspend fun create(params: List<DishResponse>) {

        // mapuje z response do responseRealm dodaje pojedynczo obiekt z petli i wykonuje insertOrUpdate
        mapAndAddToRealm(params)
    }

    private fun mapAndAddToRealm(form: List<DishResponse>) {
        if (form.isNotEmpty()) {
            realm.executeTransaction { r: Realm ->
                form.forEach { it ->
                    val realmObject =
                        r.createObject(DishResponseRealm::class.java, UUID.randomUUID().toString())
                    realmObject.description = it.description
                    realmObject.ingredients.addAll(it.ingredients)
                    realmObject.name = it.name
                    realmObject.restaurant = it.restaurant
                    realmObject.web = it.web
                    realm.insertOrUpdate(realmObject)
                }
            }
        }
    }
}