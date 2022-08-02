package pl.gda.wsb.firebaseapp.fragments.dishes.usecases

import com.google.android.gms.common.api.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import pl.gda.wsb.firebaseapp.base.usecases.BaseFlowUseCase
import pl.gda.wsb.firebaseapp.fragments.dishes.DishResponse
import pl.gda.wsb.firebaseapp.fragments.dishes.DishesApi
import javax.inject.Inject

class GetBurgersUseCase @Inject constructor(private val dishesApi: DishesApi) :
    BaseFlowUseCase<Unit, List<DishResponse>>() {
    public override fun create(params: Unit): Flow<List<DishResponse>> = flow {
        dishesApi.getBurgers()
    }
}
