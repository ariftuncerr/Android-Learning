package com.example.sqlitecrud

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView

class UserAdapter(private val context: Context, private val userList: List<UserModel>) : RecyclerView.Adapter<UserAdapter.UserCardHolder>() {

    class UserCardHolder(itemView : View ) : RecyclerView.ViewHolder(itemView){
        val id = itemView.findViewById<TextView>(R.id.cardidTxt)
        val name = itemView.findViewById<TextView>(R.id.cardNameTxt)
        val email = itemView.findViewById<TextView>(R.id.cardEmailTxt)

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserCardHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_card,parent,false)
        return UserCardHolder(view)
    }

    override fun onBindViewHolder(
        holder: UserCardHolder,
        position: Int
    ) {
        holder.id.text = userList[position].id.toString()
        holder.name.text = userList[position].name.toString()
        holder.email.text = userList[position].email.toString()

    }

    override fun getItemCount(): Int {
        return userList.size
    }


}