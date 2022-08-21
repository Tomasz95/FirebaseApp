package pl.gda.wsb.firebaseapp.fragments.task

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import pl.gda.wsb.firebaseapp.base.ui.BaseFragment
import pl.gda.wsb.firebaseapp.databinding.FragmentTaskBinding
import pl.gda.wsb.firebaseapp.fragments.ViewPagerViewModel
import pl.gda.wsb.firebaseapp.fragments.dishes.DishResponse
import pl.gda.wsb.firebaseapp.fragments.dishes.adapters.TaskAdapter
import javax.inject.Inject

@AndroidEntryPoint
class TaskFragment @Inject constructor() : BaseFragment<FragmentTaskBinding, TaskViewModel>(
    FragmentTaskBinding::inflate
) {
    override val vm: TaskViewModel by viewModels()
    private val taskAdapter: TaskAdapter by initAdapter()
    var burgersList = emptyList<DishResponse>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        vm.getCurrentBurgers()
        getBurgers()
    }

    private fun initRecyclerView() {
        layout.rvContacts.layoutManager = LinearLayoutManager(requireContext())
        layout.rvContacts.adapter = taskAdapter
    }

    private fun initAdapter() = lazy {
        TaskAdapter()
    }

    // TODO
    // change vm.viewModelScope na CoroutineScope np.
    private fun getBurgers() {
        lifecycleScope.launch {
            vm.burgersList.collectLatest {
                if(it.isNotEmpty()) {

                    // jedne dane zapisac do ROOM SQLITE
                    // jedne dane zapisac do REALM NOSQL MONGO DB
                    // jedne dane zapisac do FireBase remote database chyba tez NOSQL
                    taskAdapter.items = it
                    Log.d("valueTasksIt", "${taskAdapter.items}")
                    taskAdapter.notifyDataSetChanged()
                }
            }
        }
    }

//    override fun onDestroyView() {
//        super.onDestroyView()
//        // Log.d("tag", List<DishResponse>())
//        binding = null
//    }

}