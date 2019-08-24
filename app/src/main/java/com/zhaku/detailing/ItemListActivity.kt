package com.zhaku.detailing

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bumptech.glide.Glide
import com.zhaku.detailing.StudentContent.EducationCenterContent

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_item_list.*
import kotlinx.android.synthetic.main.item_list_content.view.*
import kotlinx.android.synthetic.main.item_list.*

/**
 * An activity representing a list of Pings. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [ItemDetailActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
class ItemListActivity : AppCompatActivity() {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    var eduContent = EducationCenterContent.ITEMS
    private var twoPane: Boolean = false
    lateinit var recyclerView: RecyclerView
    var counter = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_list)

        setSupportActionBar(toolbar)
        toolbar.title = title

        recyclerView = item_list
//
//        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }

        if (item_detail_container != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            twoPane = true
        }
        counter++

        if (counter == 1) {
            getEducationContent()
        }
        else
            setupRecyclerView()
    }
    fun getEducationContent() {
        var id = 7
        val apiService = backendApiService.createWithRx()
        var res = apiService.getEducationCenterById(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    eduContent.add(it)
                    setupRecyclerView()
                },
                {

                }
            )
    }
    fun get
    fun setupRecyclerView() {

        recyclerView.adapter = SimpleItemRecyclerViewAdapter(this, eduContent , twoPane)
        Log.d("adapter", "item count: ${recyclerView!!.adapter!!.itemCount}")
    }
    inner class SimpleItemRecyclerViewAdapter(
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
                    val fragment = ItemDetailFragment().apply {
                        arguments = Bundle().apply {
                            putString(ItemDetailFragment.ARG_ITEM_ID, item.id.toString())
                        }
                    }
                    parentActivity.supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.item_detail_container, fragment)
                        .commit()
                } else {
                    val intent = Intent(v.context, ItemDetailActivity::class.java).apply {
                        putExtra(ItemDetailFragment.ARG_ITEM_ID, item.id)
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

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {

            val item = values[position]
            holder.nameView.text = item.user.first_name
            var s = StringBuilder()
            var edu = item.ed_field
            for (i : Int in 0..edu.size-2)
                s.append(edu[i].name+", ")
            holder.edu_fieldView.text = s.toString()
            if ( item.profile_photos.isNotEmpty())  {
                Glide.with(holder.itemView)
                    .load(item.profile_photos[0].picture.toString())
                    .centerCrop()
                    .into(holder.profileimgView)
            }
            else
                holder.profileimgView.setImageResource(R.drawable.profile_photo)

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
