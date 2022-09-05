package pl.gda.wsb.firebaseapp.fragments.home

import android.util.Log
import androidx.lifecycle.viewModelScope
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import pl.gda.wsb.firebaseapp.base.ui.BaseViewModel
import pl.gda.wsb.firebaseapp.fragments.dao.DishDao
import pl.gda.wsb.firebaseapp.fragments.dishes.DishResponse
import pl.gda.wsb.firebaseapp.fragments.dishes.usecases.GetBurgersUseCase
import pl.gda.wsb.firebaseapp.fragments.dishes.usecases.InsertAllDishesAllDishesToUseCase
import pl.gda.wsb.firebaseapp.fragments.dishes.usecases.InsertAllDishesToRoomUseCase
import pl.gda.wsb.firebaseapp.fragments.dishes.usecases.InsertToTheRealm
import pl.gda.wsb.firebaseapp.fragments.home.entities.DishResponseRoom
import pl.gda.wsb.firebaseapp.fragments.home.usecases.GetAllDishesFromRoomUseCase
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getBurgersUseCase: GetBurgersUseCase,
    private val insertAllDishesToRoomUseCase: InsertAllDishesToRoomUseCase,
    private val insertAllDishesToTheRealm: InsertToTheRealm,
    private val insertAllDishesAllDishesToUseCase: InsertAllDishesAllDishesToUseCase,
    private val getAllDishesFromRoomUseCase: GetAllDishesFromRoomUseCase

) : BaseViewModel() {

     val flowListRoom = MutableStateFlow<List<DishResponseRoom>>(emptyList())

    // Metoda, która insertuje(wprowadza) dane do poszczegolnych baz danych, odpowiednio ROOM(SQL), REALM(NoSQL), Firebase(Firestore)
    fun insertDataToRoomRealmFirebase() {
        viewModelScope.launch {
            getBurgersUseCase.create(Unit).collect {
                insertAllDishesToRoomUseCase.build(it)
                insertAllDishesToTheRealm.build(it)
                insertAllDishesAllDishesToUseCase.build(it)
                Log.d(
                    "Burgers", "Nasza lista: $it"
                    // TODO 1.przypisanie wartości do Rooma
                    // TODO 2.zapis danych na firestorereate()
                    // TODO 3. zapis danych na realmie
                    // TODO 4. mapownie do dataclass

                )
            }
        }
    }

//  a najpierw pobieram dane z ROOMA,
//  stosujac useCase'a, ktory uzywa metody z DAO

    fun loadDataFromRoom() {
        viewModelScope.launch {
            getAllDishesFromRoomUseCase.create(Unit).collect { listRoom ->
                flowListRoom.value = listRoom

            }
        }
    }
}

