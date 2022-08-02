package pl.gda.wsb.firebaseapp.fragments.task

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import pl.gda.wsb.firebaseapp.base.ui.BaseFragment
import pl.gda.wsb.firebaseapp.databinding.FragmentTaskBinding
import pl.gda.wsb.firebaseapp.fragments.ViewPagerViewModel
import pl.gda.wsb.firebaseapp.fragments.dishes.DishResponse
import javax.inject.Inject

@AndroidEntryPoint
class TaskFragment @Inject constructor() : BaseFragment<FragmentTaskBinding, TaskViewModel>(
    FragmentTaskBinding::inflate
) {
    override val vm: TaskViewModel by viewModels()
    var burgersList = emptyList<DishResponse>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getBurgers()
    }

    // TODO
    // change vm.viewModelScope na CoroutineScope np.
    private fun getBurgers() {
        vm.getCurrentBurgers()
//        CoroutineScope(Dispatchers.Main).launch {
//            vm.burgersList.collectLatest {
//                Log.d("burgerslist", "LOgujemy we fragmencie: $it")
//            }
//        }
    }

//    override fun onDestroyView() {
//        super.onDestroyView()
//        // Log.d("tag", List<DishResponse>())
//        binding = null
//    }

}