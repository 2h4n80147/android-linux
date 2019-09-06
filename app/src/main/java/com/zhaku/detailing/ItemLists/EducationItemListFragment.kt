package com.zhaku.detailing.ItemLists

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zhaku.detailing.*
import com.zhaku.detailing.Details.EducationCenterDetailFragment
import com.zhaku.detailing.Details.ItemDetailActivity

import com.zhaku.detailing.StudentContent.EducationCenterContent
import com.zhaku.detailing.data.EdField
import com.zhaku.detailing.data.EducationCenter
import com.zhaku.detailing.data.backendApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.item_list_content.view.*

class EducationItemListFragment: Fragment() {

    val TAG = "ItemListFragment"
    var counter = 0

    var content = EducationCenterContent.ITEMS

    private var twoPane: Boolean = false
    lateinit var recyclerView: RecyclerView



    fun getEducationContent() {
        if (EducationCenterContent.ITEMS.isNotEmpty()) {
            content = EducationCenterContent.ITEMS
            return
        }
        val apiService = backendApiService.createWithRx()
        var res = apiService.getEducationCenterList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .take(10)
            .subscribe(
                {
                    Log.d("eduContent", "education content is loaded with $it.size elements")
                    val list = it

                    for (i in 0.. list.size-1) {
                        EducationCenterContent.addItem(list[i])
                    }
                    content = EducationCenterContent.ITEMS

                        recyclerView.adapter?.notifyDataSetChanged()
                },
                {
                    Log.d("error","throwable: $it")
                }
            )
    }

    fun getContent() {
            getEducationContent()

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getContent()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("oncreate", TAG)

        val rootView = inflater.inflate(R.layout.item_list, container, false)
        recyclerView = rootView.findViewById(R.id.item_list_recyclerview)
        setupRecyclerView()

        return rootView
    }

    fun getEducationSample() {
        var id = 7
        val apiService = backendApiService.createWithRx()
        var res = apiService.getEducationCenterById(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    if (content.isNotEmpty())
                        content.add(it as EducationCenter)

                    setupRecyclerView()
                },
                {

                }
            )
    }
    fun setupRecyclerView() {
        recyclerView.adapter =
            SimpleItemRecyclerViewAdapter(
                activity as ItemListActivity,
                content,
                twoPane
            )
        Log.d("adapter", "item count: ${recyclerView!!.adapter!!.itemCount}")
    }
    class SimpleItemRecyclerViewAdapter(
        private val parentActivity: ItemListActivity,
        private val values: MutableList<EducationCenter>,
        private val twoPane: Boolean
    ) :
        RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>() {

        private val onClickListener: View.OnClickListener

        init {
            onClickListener = View.OnClickListener { v ->
                val item = v.tag as EducationCenter
                if (twoPane) {
                    val fragment = EducationCenterDetailFragment().apply {
                        arguments = Bundle().apply {
                            putString(EducationCenterDetailFragment.ARG_ITEM_ID, item.id.toString())
                        }
                    }
                    parentActivity.supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.item_detail_container, fragment)
                        .commit()
                } else {
                    val intent = Intent(v.context, ItemDetailActivity::class.java).apply {
                        putExtra(EducationCenterDetailFragment.ARG_ITEM_ID, item.id.toString())
                    }
                    v.context.startActivity(intent)
                }
            }
        }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_content, parent, false)
            return ViewHolder(view)
        }

        public fun edufield_torow(fields : List<EdField>) : String {
            var s = StringBuilder()
            for (i : Int in 0..fields.size-2)
                s.append(fields[i].name+", ")
            s.append(fields.last().name)
            return s.toString()
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {

            val item = values[position]
            holder.nameView.text = item.user.first_name

            holder.edu_fieldView.text = edufield_torow(item.ed_field)
            if ( item.profile_photos.isNotEmpty())  {
                Glide.with(holder.itemView)
                    .load(item.profile_photos[0].picture.toString())
                    .centerCrop()
                    .into(holder.profileimgView)
            }
            else
                holder.profileimgView.setImageResource(R.drawable.ic_school_foreground)

            with(holder.itemView) {
                tag = item
                setOnClickListener(onClickListener)
            }
        }

        override fun getItemCount() = values.size

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

            val profileimgView: de.hdodenhof.circleimageview.CircleImageView = view.profile_img
            val nameView: TextView = view.profile_name
            val edu_fieldView: TextView = view.edu_field
        }
    }

}