package com.angelo.crud_room_mvvm.ui.fragments.details

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.angelo.crud_room_mvvm.R
import com.angelo.crud_room_mvvm.data.local.db.UserDataBase
import com.angelo.crud_room_mvvm.data.local.source.LocalDataSourceImpl
import com.angelo.crud_room_mvvm.data.model.UserEntity
import com.angelo.crud_room_mvvm.domain.UserRepositoryImpl
import com.angelo.crud_room_mvvm.ui.viewmodels.details.DetailsViewModel
import com.angelo.crud_room_mvvm.ui.viewmodels.main.MainViewModel
import com.angelo.crud_room_mvvm.ui.viewmodels.main.MainViewModelFactory
import com.angelo.crud_room_mvvm.utils.toast
import kotlinx.android.synthetic.main.fragment_details.*

class DetailsFragment : Fragment() {

    private lateinit var user: UserEntity

    private val viewModel by activityViewModels<DetailsViewModel> {
        MainViewModelFactory(
            UserRepositoryImpl(
                LocalDataSourceImpl(UserDataBase.getInstance(requireContext().applicationContext))
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        requireArguments().let {
                user = it.getParcelable<UserEntity>("contact") as UserEntity
            }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        txt_user_name_details.text = user.username
        txt_user_email_details.text = user.email
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_details,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.delete_contact ->{
                viewModel.deleteUser(user)
                findNavController().navigate(R.id.action_detailsFragment_to_mainFragment)
                toast("Usuario eliminado")
            }
            R.id.update_contact ->{
                val data = Bundle()
                data.putParcelable("contact",user)
                findNavController().navigate(R.id.action_detailsFragment_to_mainFragment,data)
            }
        }
        return super.onOptionsItemSelected(item)
    }


}