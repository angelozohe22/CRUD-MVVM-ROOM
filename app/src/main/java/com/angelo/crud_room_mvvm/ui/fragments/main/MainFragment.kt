package com.angelo.crud_room_mvvm.ui.fragments.main

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.angelo.crud_room_mvvm.R
import com.angelo.crud_room_mvvm.data.local.db.UserDataBase
import com.angelo.crud_room_mvvm.data.local.source.LocalDataSourceImpl
import com.angelo.crud_room_mvvm.data.model.UserEntity
import com.angelo.crud_room_mvvm.domain.UserRepositoryImpl
import com.angelo.crud_room_mvvm.ui.viewmodels.main.MainViewModel
import com.angelo.crud_room_mvvm.ui.viewmodels.main.MainViewModelFactory
import com.angelo.crud_room_mvvm.utils.hideKeyboard
import com.angelo.crud_room_mvvm.utils.toast
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment(), MainAdapter.OnContactClickListener {

    private val viewModel by activityViewModels<MainViewModel> {
        MainViewModelFactory(
                UserRepositoryImpl(
                        LocalDataSourceImpl(UserDataBase.getInstance(requireContext().applicationContext))
                )
        )
    }

    private val mAdapter by lazy { MainAdapter(this) }
    private var contact: UserEntity ?= null
    private var idContact: Int? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { data->
            contact = data.getParcelable<UserEntity>("contact") as UserEntity
            idContact = contact?.idUser
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        updateUser(contact)
        setUpObservers()
        onSaveClicked()
        onCleanClicked()
    }

    private fun setUpRecyclerView(){
        rv_contacts.apply {
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
            adapter = mAdapter
        }
    }

    private fun setUpObservers(){
        viewModel.fetchUserList.observe(viewLifecycleOwner, Observer {
            it?.let { userList ->
                if (userList.isNullOrEmpty()) {
                    mAdapter.setData(emptyList())
                    toast("Lista Vacia")
                }else{
                    mAdapter.setData(userList)
                }
            }
        })
    }

    private fun onSaveClicked(){
        btn_save_contact.setOnClickListener {
            val name  = et_user_name.text.toString().trim()
            val email = et_user_email.text.toString().trim()

            if(validateFields(name, email)){
                val user = UserEntity(0, name, email)

                if(contact != null){
                    user.idUser = idContact.toString().toInt()
                    viewModel.updateUser(user)
                    btn_save_contact.text = getString(R.string.save_contact)
                    cleanFields()
                    toast("Contacto editado")
                    contact = null
                }else{
                    viewModel.insertUser(user)
                    cleanFields()
                    toast("Contacto guardado")
                }
            }else{
                toast("1 o dos campos vacios")
            }
        }
    }

    private fun onCleanClicked(){
        btn_clear_contacts.setOnClickListener {
            viewModel.deleteAll()
        }
    }

    private fun validateFields(name: String, email: String): Boolean{
        return !TextUtils.isEmpty(name) && !TextUtils.isEmpty(email)
    }

    private fun cleanFields(){
        et_user_name.text.clear()
        et_user_email.text.clear()
        requireContext().hideKeyboard(et_user_email)
    }

    override fun onItemClicked(user: UserEntity) {
        val data = Bundle()
        data.putParcelable("contact", user)
        findNavController().navigate(R.id.action_mainFragment_to_detailsFragment, data)
    }

    private fun updateUser(contact: UserEntity?){
        if(contact != null){
            et_user_name.setText(contact.username)
            et_user_email.setText(contact.email)
            btn_save_contact.text = getString(R.string.update_contact)
        }
    }


}