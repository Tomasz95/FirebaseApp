package pl.gda.wsb.firebaseapp.fragments.home

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.viewModelScope
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import pl.gda.wsb.firebaseapp.base.ui.BaseViewModel
import pl.gda.wsb.firebaseapp.fragments.dishes.DishResponse
import pl.gda.wsb.firebaseapp.fragments.dishes.usecases.GetBurgersUseCase
import pl.gda.wsb.firebaseapp.fragments.dishes.usecases.InsertAllDishesToRoom
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getBurgersUseCase: GetBurgersUseCase,
    private val insertAllDishesToRoom: InsertAllDishesToRoom
) : BaseViewModel() {

    fun insertDataToRoomRealmFirebase() {
        viewModelScope.launch {
            getBurgersUseCase.create(Unit).collect {
                insertAllDishesToRoom.build(it)

                Log.d("Burgers", "Nasza lista: $it"
                    // TODO 1.przypisanie wartości do Rooma
                    // TODO 2.zapis danych na firestore
                    // TODO 3. zapis danych na realmie
                    // TODO 4. mapownie do dataclass

                )
            }
        }
    }
}

@Entity
data class User(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "first_name") val firstName: String?,
    @ColumnInfo(name = "last_name") val lastName: String?
)
