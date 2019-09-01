package com.zhaku.detailing.Details

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.zhaku.detailing.EdField
import com.zhaku.detailing.EducationCenter
import com.zhaku.detailing.Locations
import com.zhaku.detailing.R
import com.zhaku.detailing.StudentContent.EducationCenterContent
import kotlinx.android.synthetic.main.activity_item_detail.*

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
    val TAG = "EducationCenterDetailFragment"
    lateinit var item : EducationCenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("oncreate", TAG)

        arguments?.let {

                // Load the dummy content specified by the fragment
                // arguments. In a real-world scenario, use a Loader
                // to load content from a content provider.

                item = EducationCenterContent.ITEM_MAP[it.getString(ARG_ITEM_ID)]!!

                activity?.detail_toolbar?.title = item.user.first_name


                Log.d("oncreate", item.user.first_name)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.item_detail, container, false)

        val infoText = rootView.findViewById<TextView>(R.id.edu_detail_info_text)
        val avatarView = rootView.findViewById<ImageView>(R.id.edu_detaiL_profile_image)
        val edu_name : TextView = rootView.findViewById(R.id.edu_name)
        val edu_city : TextView = rootView.findViewById(R.id.edu_city)
        val edu_fields : TextView = rootView.findViewById(R.id.detail_edu_fields)
        val cost_range : TextView = rootView.findViewById(R.id.edu_cost_range)
        val contact_number: TextView = rootView.findViewById(R.id.edu_contact_number)


        // Show the dummy content as text in a TextView.

        item.let {
            infoText.text = formatToParagraph(it.info)
            if (item.profile_photos.isNotEmpty()) {
                Glide.with(rootView)
                    .load(item.profile_photos[0].picture.toString())
                    //.centerCrop()
                    .into(avatarView)
            }


            edu_name.text =  it.user.first_name + it.user.last_name
            edu_city.text = Locations.cities[it.location.city]
            edu_fields.text = edufield_torow(it.ed_field)
            cost_range.text = "с " + it.min_amount + " до " + it.max_amount
            contact_number.text = it.phone_number
        }


        return rootView
    }
    public fun edufield_torow(fields : List<EdField>) : String {
        var s = StringBuilder()
        for (i : Int in 0..fields.size-2)
            s.append(fields[i].name+", ")
        s.append(fields.last().name)
        return s.toString()
    }
    fun formatToParagraph (s : String) : String {
        var x = "\n".count{ s.contains(it)}
        
        Log.d("Spacing", "replaced this much $x")

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
