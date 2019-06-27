package com.modart.modamania.feed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.modart.modamania.R
import com.modart.modamania.model.FeedModel
import com.modart.modamania.model.FeedPostModel
import com.modart.modamania.util.getPostDate
import com.modart.modamania.util.loadImage
import org.w3c.dom.Text

class FeedAdapter
constructor(
    viewModel: FeedViewModel,
    lifecycleOwner: LifecycleOwner
) : RecyclerView.Adapter<FeedAdapter.FeedVH>() {


    private val dataList = arrayListOf<FeedModel>()

    init {
        viewModel.getFeedData().observe(lifecycleOwner, Observer {
            it?.let { listData ->
                dataList.clear()
                dataList.addAll(listData)
            }
            notifyDataSetChanged()
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedVH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_post, parent, false)
        return FeedVH(view)
    }

    override fun getItemCount() = dataList.count()

    override fun onBindViewHolder(holder: FeedVH, position: Int) {
        holder.bind(dataList[position])
    }


    class FeedVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private lateinit var model: FeedModel
        private val userImage = itemView.findViewById<ImageView>(R.id.imgUser)
        private val postImage = itemView.findViewById<ImageView>(R.id.imgPost)
        private val username = itemView.findViewById<TextView>(R.id.txtUsername)
        private val cDate = itemView.findViewById<TextView>(R.id.txtDate)
        private val desc = itemView.findViewById<TextView>(R.id.txtDesc)


        fun bind(model: FeedModel) {
            this.model = model
            if (model.posts.owner.user_folder != "default.jpeg"){
                userImage.loadImage(model.posts.owner.user_folder)
            }
            postImage.loadImage(model.posts.post_image)
            username.text = model.posts.owner.username
            desc.text = model.posts.description
            cDate.text = getPostDate(model.posts.created_date.dropLast(5))
        }

    }


}