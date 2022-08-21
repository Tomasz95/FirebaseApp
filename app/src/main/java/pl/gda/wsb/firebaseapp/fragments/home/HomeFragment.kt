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
import pl.gda.wsb.firebaseapp.fragments.auth.AuthFragment


@AndroidEntryPoint
class HomeFragment @Inject constructor() :
    BaseFragment<FragmentHomeBinding, HomeViewModel>(FragmentHomeBinding::inflate) {
    override val vm: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        layout.tvMyName.text = AuthFragment.userEmail
        Log.d("test123", AuthFragment.userEmail)

        vm.insertDataToRoomRealmFirebase()
    }
}