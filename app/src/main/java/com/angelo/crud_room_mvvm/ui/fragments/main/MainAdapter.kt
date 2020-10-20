package com.angelo.crud_room_mvvm.ui.fragments.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.angelo.crud_room_mvvm.R
import com.angelo.crud_room_mvvm.data.model.UserEntity
import kotlinx.android.synthetic.main.row_contact.view.*

class MainAdapter(
    private val contactClickListener: MainAdapter.OnContactClickListener
) :RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private var _userList = emptyList<UserEntity>()
    fun setData(data: List<UserEntity>){
        this._userList = data
        notifyDataSetChanged()
    }

    interface OnContactClickListener{
        fun onItemClicked(user: UserEntity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.MainViewHolder {
        return MainViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_contact,parent,false))
    }

    override fun onBindViewHolder(holder: MainAdapter.MainViewHolder, position: Int) {
        val user = _userList[position]
        holder.bindView(user)
    }

    override fun getItemCount(): Int = _userList.size

    inner class MainViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bindView(user: UserEntity){
            itemView.tv_user_name.text  = user.username
            itemView.tv_user_email.text = user.email
            itemView.setOnClickListener {
                contactClickListener.onItemClicked(user)
            }
        }
    }

}