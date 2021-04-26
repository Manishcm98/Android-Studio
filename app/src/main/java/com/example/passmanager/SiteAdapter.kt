package com.example.passmanager

import android.R.attr.label
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.passwordmanager.roomaddsite.AddSiteInfo


class SiteAdapter(private var arrayList: List<AddSiteInfo>, private val clickListener: OnSiteItemClickListener) : RecyclerView.Adapter<SiteAdapter.ViewHolder>() {

    val num : Int = itemCount
    lateinit var pwd : String

    inner class ViewHolder(view: View, private val onClickListener: OnSiteItemClickListener) : RecyclerView.ViewHolder(view), View.OnClickListener{
        val textView1: TextView = view.findViewById(R.id.sitename)
        val textView2: TextView = view.findViewById(R.id.siteurl)
        val imageView : ImageView = view.findViewById(R.id.imglogo)
        val textView3: TextView = view.findViewById(R.id.cppw)

        init {
            itemView.setOnClickListener(this)
            textView3.setOnClickListener {
                val clipboardManager = it.context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clipData = ClipData.newPlainText("text",arrayList[adapterPosition].SitePwd)
                clipboardManager.setPrimaryClip(clipData)
                Toast.makeText(it.context,"Password Copied to clipboard",Toast.LENGTH_LONG).show()
            }
            //val urll = arrayList[adapterPosition].Url
            textView2.setOnClickListener {
                val tnt = Intent(Intent.ACTION_VIEW, Uri.parse("https://" + arrayList[adapterPosition].Url))
                it.context.startActivity(tnt)
            }
        }

        override fun onClick(p0: View?) {
            onClickListener.onSiteClick(adapterPosition)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.siteitem, parent, false)
        return ViewHolder(itemView, clickListener)
    }

    //@SuppressLint("CheckResult")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val arrayList = arrayList[position]
        pwd = arrayList.SitePwd
        holder.textView1.text = arrayList.SiteName
        holder.textView2.text = arrayList.Url
        val url = "https://www.google.com/s2/favicons?sz=64&domain_url=${arrayList.Url}"
        Glide.with(holder.imageView).load(url).diskCacheStrategy(DiskCacheStrategy.ALL).dontAnimate().into(holder.imageView)
        //holder.imageView.setImageIcon()
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    interface OnSiteItemClickListener {
        fun onSiteClick(position: Int)
    }

//    interface OnSiteItemClickListener {
//        fun onSiteClick(addSiteInfo: AddSiteInfo)
//    }

}