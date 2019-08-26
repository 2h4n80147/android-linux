package com.zhaku.detailing

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.TransformationUtils.centerCrop
import com.zhaku.detailing.StudentContent.EducationCenterContent
import com.zhaku.detailing.dummy.DummyContent
import kotlinx.android.synthetic.main.activity_item_detail.*
import kotlinx.android.synthetic.main.item_detail.*
import kotlinx.android.synthetic.main.item_detail.view.*

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a [ItemListActivity]
 * in two-pane mode (on tablets) or a [ItemDetailActivity]
 * on handsets.
 */
class EducationCenterDetailFragment : Fragment() {

    /**
     * The dummy content this fragment is presenting.
     */
    lateinit var item : EducationCenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {

                // Load the dummy content specified by the fragment
                // arguments. In a real-world scenario, use a Loader
                // to load content from a content provider.

                item = EducationCenterContent.ITEM_MAP[it.getString(ARG_ITEM_ID)]!!
                activity?.detail_toolbar?.title = item.user.first_name.toString()
                Log.d("oncreate", item.user.first_name)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.item_detail, container, false)

        val infoText = rootView.findViewById<TextView>(R.id.detail_info_text)
        val avatarView = rootView.findViewById<ImageView>(R.id.detaiL_profile_image)

        // Show the dummy content as text in a TextView.

        item?.let {
            infoText.text = formatToParagraph(it.info)
            if (item.profile_photos.isNotEmpty()) {
                Glide.with(rootView)
                    .load(item.profile_photos[0].picture.toString())
                    .centerCrop()
                    .into(avatarView)
            }
        }

        return rootView
    }
    fun formatToParagraph (s : String) : String {
        var x = "\n".count{ s.contains(it)}
        
        Log.d("Spacing", "replaced this much $s.count('\n')")
        s.replace("\n", "\n   ")

        return s
    }

    companion object {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        const val ARG_ITEM_ID = "item_id"
    }
}
