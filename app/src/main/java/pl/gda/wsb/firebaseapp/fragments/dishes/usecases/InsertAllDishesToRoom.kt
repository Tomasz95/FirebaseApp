package pl.gda.wsb.firebaseapp.fragments.dishes.usecases

import pl.gda.wsb.firebaseapp.base.usecases.BaseFlowUseCase
import pl.gda.wsb.firebaseapp.base.usecases.BaseUseCase
import pl.gda.wsb.firebaseapp.fragments.dao.DishDao
import pl.gda.wsb.firebaseapp.fragments.dishes.DishResponse
import pl.gda.wsb.firebaseapp.fragments.dishes.DishesApi
import pl.gda.wsb.firebaseapp.fragments.home.entities.DishResponseRoom
import javax.inject.Inject

class InsertAllDishesToRoom @Inject constructor(private val dishDao: DishDao) :
    BaseUseCase<List<DishResponse>, Unit>() {
    override suspend fun create(params: List<DishResponse>) {

        // kasujemy wszystko
//        dishDao.deleteAll()

        // mapuje z response do responseRoom
        val mappedRoomElement = from(params)

        // wprowadanie z DAO do bazy danych
        dishDao.addAllDishes(mappedRoomElement)
    }

    private fun from(form: List<DishResponse>): List<DishResponseRoom> {
        val dishResponseRoomList = arrayListOf<DishResponseRoom>()
        if (form.isNotEmpty()) {
            form.forEach { it ->
                dishResponseRoomList.add(
                    DishResponseRoom(
                        0,
                        it.description,
                        it.ingredients,
                        it.name,
                        it.restaurant,
                        it.web
                    )
                )
            }
        }
        return dishResponseRoomList
    }
}