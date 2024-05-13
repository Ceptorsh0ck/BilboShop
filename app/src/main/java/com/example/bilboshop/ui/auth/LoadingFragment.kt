package com.example.bilboshop.ui.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.lifecycle.lifecycleScope
import com.example.bilboshop.MyApp
import com.example.bilboshop.ui.MainActivity
import com.example.bilboshop.R
import com.example.bilboshop.databinding.FragmentLoadingBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoadingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoadingFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var loadingBinding : FragmentLoadingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        loadingBinding = FragmentLoadingBinding.inflate(layoutInflater,container, false)

        return loadingBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val loadingBar: ProgressBar = loadingBinding.AuthLoadingBar
        lifecycleScope.launch {
            while (loadingBar.progress < loadingBar.max)
                initLoading(loadingBar)
        }
    }

    private suspend fun initLoading(
        progressBar: ProgressBar
    ) {
        delay(2000)
        progressBar.isIndeterminate = false
        progress(progressBar)

    }

    private suspend fun progress(progressBar: ProgressBar) {
        while (progressBar.progress < progressBar.max) {
            delay(300)
            progressBar.incrementProgressBy(PROGRESS_INCREMENT)
        }
        Log.i("GORKA","Antes de")
        if(isUserLogged()){
            Log.i("GORKA","Entra")
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }else {
            Log.i("GORKA","Else")
            val newFragment = LoginFragment()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.AuthFragmentContainerView, newFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

    private fun isUserLogged() : Boolean {
        Log.i("GORKA","Antes de llamar")
        val loggedUser = MyApp.userPreferences.getLoggedUser()
        Log.i("GORKA","Despues")
        if (loggedUser != null) {
            return true
        }
        return false
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LoadingFragment.
         */

        const val PROGRESS_INCREMENT = 50

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoadingFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}