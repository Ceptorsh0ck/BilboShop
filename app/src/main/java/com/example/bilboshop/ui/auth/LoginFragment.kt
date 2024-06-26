package com.example.bilboshop.ui.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bilboshop.MyApp
import com.example.bilboshop.R
import com.example.bilboshop.data.User
import com.example.bilboshop.databinding.FragmentLoginBinding
import com.example.bilboshop.ui.MainActivity

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var loginBinding: FragmentLoginBinding

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
        loginBinding = FragmentLoginBinding.inflate(layoutInflater, container, false)

        loginBinding.loginButton.setOnClickListener() {
            var user = User(loginBinding.inputLogin.text.toString(),loginBinding.inputPassword.text.toString())
            Log.i("GORKA","Antes de Guardar")
            MyApp.userPreferences.saveLoggedUser(user)

            val intent = Intent(activity, MainActivity::class.java)
            Log.i("GORKA","Antes de Intent")
            startActivity(intent)
            Log.i("GORKA","Antes de Finish")
            activity?.finish()
        }

        loginBinding.registerButton.setOnClickListener() {
            //TODO Realizar recordar contraseña
            val newFragment = RegisterFragment()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.AuthFragmentContainerView, newFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        return loginBinding.root

        //return inflater.inflate(R.layout.fragment_login, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LoginFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}