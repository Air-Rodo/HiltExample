package com.example.hilt.user

import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.database.entity.User
import com.example.hilt.R
import com.example.hilt.databinding.ItemUserBinding
import com.example.utils.ImageUtil

/**
 * Desc:
 * @author lijt
 * Created on 2022/10/9 15:36
 * Email: lijt@eetrust.com
 */
class UserListAdapter : RecyclerView.Adapter<UserListAdapter.UserHolder>() {

    private val data = mutableListOf<User>()

    fun submit(data: MutableList<User>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        val inflate = DataBindingUtil.inflate<ItemUserBinding>(LayoutInflater.from(parent.context), R.layout.item_user, parent, false)
        return UserHolder(inflate)
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        val user = data[position]
        ImageUtil.loadRoundPhoto(holder.binding.ivPhoto, user.photo)
        holder.binding.tvUsername.text = user.userName
//        holder.binding.clRoot.setOnClickListener {
//            listener?.onClickListener(user, position)
//        }
//        holder.binding.clRoot.setOnLongClickListener {
//            listener?.onLongClickListener(user, position)
//            true
//        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class UserHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root), View.OnTouchListener, View.OnClickListener, View.OnLongClickListener {

        init {
            itemView.setOnClickListener(this)
            itemView.setOnLongClickListener(this)
            itemView.setOnTouchListener(this)
        }

        var x = 0
        var y = 0
        override fun onTouch(v: View?, event: MotionEvent?): Boolean {
            x = event?.x?.toInt() ?: 0
            y = event?.y?.toInt() ?: 0
            return false
        }

        override fun onClick(v: View?) {
            listener?.onClickListener(data[adapterPosition], adapterPosition)
        }

        override fun onLongClick(v: View?): Boolean {
            listener?.onLongClickListener(data[adapterPosition], adapterPosition, binding.root, x, y)
            return true
        }

    }

    interface OnItemClickListener {
        fun onClickListener(bean: User, position: Int)

        fun onLongClickListener(bean: User, position: Int, view: View, x: Int, y: Int)
    }

    var listener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}