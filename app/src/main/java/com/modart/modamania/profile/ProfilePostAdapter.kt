package com.modart.modamania.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.modart.modamania.R
import com.modart.modamania.feed.FeedAdapter
import com.modart.modamania.feed.FeedViewModel
import com.modart.modamania.model.FeedModel
import com.modart.modamania.model.FeedPostModel
import com.modart.modamania.util.getPostDate
import com.modart.modamania.util.loadImage

class ProfilePostAdapter constructor(
    viewModel: ProfileViewModel,
    lifecycleOwner: LifecycleOwner
) : RecyclerView.Adapter<ProfilePostAdapter.ProfilePostAdapterVH>() {


    private val dataList = arrayListOf<FeedPostModel>()

    init {
        viewModel.getPostData().observe(lifecycleOwner, Observer {
            it?.let { list ->

                dataList.clear()
                dataList.addAll(list)
                notifyDataSetChanged()

            }
        })
    }

    override fun onBindViewHolder(holder: ProfilePostAdapterVH, position: Int) {
        holder.bind(dataList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfilePostAdapterVH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_post,parent,false)
        return ProfilePostAdapterVH(view)
    }


    override fun getItemCount() = dataList.count()


    class ProfilePostAdapterVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private lateinit var model: FeedPostModel
        private val userImage = itemView.findViewById<ImageView>(R.id.imgUser)
        private val postImage = itemView.findViewById<ImageView>(R.id.imgPost)
        private val username = itemView.findViewById<TextView>(R.id.txtUsername)
        private val cDate = itemView.findViewById<TextView>(R.id.txtDate)
        private val desc = itemView.findViewById<TextView>(R.id.txtDesc)


        fun bind(model: FeedPostModel) {
            this.model = model
            if (model.owner.user_folder != "default.jpeg"){
                userImage.loadImage(model.owner.user_folder)
            }
            postImage.loadImage(model.post_image)
            username.text = model.owner.username
            desc.text = model.description
            cDate.text = getPostDate(model.created_date.dropLast(5))
        }

    }


}