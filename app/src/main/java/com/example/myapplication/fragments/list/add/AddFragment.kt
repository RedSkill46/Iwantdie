package com.example.myapplication.fragments.list.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.myapplication.data.User
import com.example.myapplication.data.UserViewModel
import com.example.tasks_application.R
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*


class AddFragment : Fragment() {

    private lateinit var mUserViewModel: UserViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       val view= inflater.inflate(R.layout.fragment_add, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.add_but.setOnClickListener{
            insertDataToDatabase()
        }
        return view
    }

    private fun insertDataToDatabase() {
        val firstName = AddFrirsName.text.toString()
        val lastName = AddLAstName.text.toString()
        val age= AddAge.text

        if (inputCheck(firstName,lastName,age)){
            //Create User
            val user = User(0,firstName,lastName,Integer.parseInt(age.toString()))
            // Add Data
            mUserViewModel.addUser(user)
            Toast.makeText(requireContext(), "Successfully added",Toast.LENGTH_LONG).show()
            //Navigate
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(), "Please fill out all fields",Toast.LENGTH_LONG).show()
        }

    }
    private fun inputCheck(firstName: String, lastName: String, age:Editable): Boolean{
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }
}