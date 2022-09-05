package pl.gda.wsb.firebaseapp.fragments.home.usecases

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import pl.gda.wsb.firebaseapp.base.usecases.BaseFlowUseCase
import pl.gda.wsb.firebaseapp.fragments.dao.DishDao
import pl.gda.wsb.firebaseapp.fragments.home.entities.DishResponseRoom
import javax.inject.Inject

class GetAllDishesFromRoomUseCase @Inject constructor(private val dishDao: DishDao) :
    BaseFlowUseCase<Unit, List<DishResponseRoom>>() {
    public override fun create(params: Unit): Flow<List<DishResponseRoom>> = flow {
        emit(dishDao.getAllDishes())
    }
}
