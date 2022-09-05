package pl.gda.wsb.firebaseapp.fragments.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import pl.gda.wsb.firebaseapp.base.ui.BaseFragment
import pl.gda.wsb.firebaseapp.databinding.FragmentHomeBinding
import javax.inject.Inject
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.flow.collectLatest
import pl.gda.wsb.firebaseapp.fragments.auth.AuthFragment
import pl.gda.wsb.firebaseapp.fragments.dishes.adapters.TaskAdapter
import pl.gda.wsb.firebaseapp.fragments.home.adapter.HomeAdapter
import pl.gda.wsb.firebaseapp.fragments.home.usecases.GetAllDishesFromRoomUseCase


@AndroidEntryPoint
class HomeFragment @Inject constructor() :
    BaseFragment<FragmentHomeBinding, HomeViewModel>(FragmentHomeBinding::inflate) {
    override val vm: HomeViewModel by viewModels()
    private val homeAdapter: HomeAdapter by initAdapter()

    private fun initRecyclerView() {
        layout.rvDataDatabases.layoutManager = LinearLayoutManager(requireContext())
        layout.rvDataDatabases.adapter = homeAdapter
    }

    private fun initAdapter() = lazy {
        HomeAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("test123", AuthFragment.userEmail)

        vm.insertDataToRoomRealmFirebase()
        initRecyclerView()

//        val adapter = DrawerAdapter(itemClickListener())
//        mRecyclerView.setAdapter(adapter)
        layout.btnRoom.setOnClickListener{
            //TODO implementacja usecase, który odniesie się do Dao(Room) używając operacji get(select)
// UZYWAMY METODY GETALLDISHESROOM~
            vm.loadDataFromRoom()

        // uzycie FLOW stworzonego w ViewModelu i nasluchiwanie flow.collectLatest {}
      //      getAllDishesFromRoomUseCase.create(<Dish)
            // wylogowal te dane Log.d("tag", "$tekst")
            lifecycleScope.launchWhenCreated {
                vm.flowListRoom.collectLatest {
                    Log.d("TAG","$it" )
                }
            }

        }
        layout.btnRealm.setOnClickListener{

        }
        layout.btnFirebase.setOnClickListener{

        }
    }
}
