package com.example.recycleview

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(resources: AppResources) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    // Instance variables
    private val titles = resources.getTitles()
    private val details = resources.getDetails()
    private val images = resources.getImages()

    // inflate card layout & create a new ViewHolder object
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.card_layout, viewGroup, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return titles.size
    }

    // binding data to ViewHolder views
    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.itemTitle.text = titles[i]
        viewHolder.itemDetail.text = details[i]
        viewHolder.itemImage.setImageResource(images[i])
    }

    // defines the ViewHolder for the adapter
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        // from card layout
        var itemImage: ImageView
        var itemTitle: TextView
        var itemDetail: TextView

        //sets up the views and attaches the OnClickListener to the item view
        init {
            itemImage = itemView.findViewById(R.id.itemImage)
            itemTitle = itemView.findViewById(R.id.itemTitle)
            itemDetail = itemView.findViewById(R.id.itemDetail)

            itemView.setOnClickListener(this)

            /*itemView.setOnClickListener { v: View ->
               val position: Int = adapterPosition + 1

               Snackbar.make(
                   v, "Click detected on item $position",
                   Snackbar.LENGTH_LONG
               ).setAction("Action", null).show()
           }*/
        }

        // override function to handle click events
        override fun onClick(view: View?) {
            // Create an Intent to launch MainActivity2 with the title, details, and image of the clicked item as extras.
            val intent = Intent(view?.context, MainActivity2::class.java)
            intent.putExtra("title", itemTitle.text)
            intent.putExtra("details", itemDetail.text)
            intent.putExtra("image", images[adapterPosition])
            // Start the activity using the Intent
            view?.context?.startActivity(intent)
        }
    }
}