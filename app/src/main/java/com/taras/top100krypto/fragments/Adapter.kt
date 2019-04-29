package com.taras.top100krypto.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.NonNull
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.taras.top100krypto.BR
import com.taras.top100krypto.R
import com.taras.top100krypto.data.model.Currency

class Adapter(listener: OnClickListener) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    private var mList = ArrayList<Currency>()
    private val mListener: OnClickListener = listener

    interface OnClickListener {
        fun onItemClick(model: Currency)
    }

    fun setList(list: List<Currency>) {
        mList.clear()
        mList.addAll(list)
        notifyDataSetChanged()
    }

    fun getItem(position: Int): Currency {
        return mList[position]
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val holder: ViewDataBinding = DataBindingUtil.inflate(inflater, R.layout.item_recycler_view, parent, false)
        return ViewHolder(holder)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), mListener)
    }

    class ViewHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Any, listener: OnClickListener) {
            binding.setVariable(BR.model, data)
            binding.setVariable(BR.clickListener, listener)
        }
    }

    companion object {
        @JvmStatic
        @BindingAdapter("bind_image")
        fun bindImage(@NonNull imageView: ImageView, @NonNull model: Currency) {
//            if (model == null) {
//                return
//            }
            Glide.with(imageView.context).load(model.image).into(imageView)
        }
    }
}