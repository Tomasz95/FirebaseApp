package pl.gda.wsb.firebaseapp.fragments.task

import android.util.Log
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import pl.gda.wsb.firebaseapp.base.ui.BaseViewModel
import pl.gda.wsb.firebaseapp.fragments.dishes.DishResponse
import pl.gda.wsb.firebaseapp.fragments.dishes.usecases.GetBurgersUseCase
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val getBurgersUseCase: GetBurgersUseCase
) : BaseViewModel() {
    val burgersList = MutableStateFlow<List<DishResponse>>(emptyList())

    fun getCurrentBurgers() {
        viewModelScope.launch {
            getBurgersUseCase.create(Unit).collect {
                burgersList.value = it
                Log.d("burgerslist", "Nasza lista: $it"
                )
            }
        }
    }
}

