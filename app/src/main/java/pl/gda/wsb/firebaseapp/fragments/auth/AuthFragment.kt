package pl.gda.wsb.firebaseapp.fragments.auth

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import pl.gda.wsb.firebaseapp.base.ui.BaseFragment
import pl.gda.wsb.firebaseapp.databinding.FragmentAuthBinding

import javax.inject.Inject


@AndroidEntryPoint
class AuthFragment @Inject constructor() :
    BaseFragment<FragmentAuthBinding, AuthViewModel>(FragmentAuthBinding::inflate) {
    override val vm: AuthViewModel by viewModels()

    private lateinit var auth: FirebaseAuth


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFirebase()
        registerUser()
        loginUser()
    }

    private fun registerUser() {
        layout.btnRegister.setOnClickListener {
            val email = layout.etEmailRegister.text.toString()
            val password = layout.etPasswordRegister.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty()) {
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        //tworzenie za pomocą funkcji z FireBase
                        auth.createUserWithEmailAndPassword(email, password).await()
                        withContext(Dispatchers.Main) {
                            checkLoggedInState()
                        }
                    } catch (e: Exception) {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(requireContext(), e.message, Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }
    }

    private fun loginUser() {
        layout.btnLogin.setOnClickListener {
            val email = layout.etEmailLogin.text.toString()
            val password = layout.etPasswordLogin.text.toString()
            userEmail = email

            if (email.isNotEmpty() && password.isNotEmpty()) {
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        //auth.signInWithEmailAndPassword(email, password).await()

                        auth.signInWithEmailAndPassword(email, password).await()
                        withContext(Dispatchers.Main) {

                                // TODO
                                findNavController().navigate(
                                    AuthFragmentDirections.actionAuthFragmentToViewPagerFragment(
                                    )
                                )
                                Log.d("test123", "zalogowany")
                                userEmail = email
                                Log.d("test123", userEmail)

                                checkLoggedInState()


                        }
                    } catch (e: Exception) {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(requireContext(), e.message, Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }
    }

    private fun checkLoggedInState() {
        if (auth.currentUser == null) { // not logged in
            layout.tvLoggedIn.text = "You are not logged in"
        } else {
//            layout.tvLoggedIn.text = "You are logged in!"
//            findNavController().navigate(AuthFragmentDirections.actionAuthFragmentToViewPagerFragment())
        }
    }

    override fun onStart() {
        super.onStart()
        checkLoggedInState()
    }


    private fun initFirebase() {
        auth = FirebaseAuth.getInstance()

    }

    override fun onPause() {
        super.onPause()
//        auth.signOut()
    }


    override fun onResume() {
        super.onResume()
//        auth.signOut()
    }

    companion object {
        var userEmail = ""
    }
}

