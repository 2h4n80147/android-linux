//package com.zhaku.detailing.Register
//
//import android.content.Context
//import android.view.ViewGroup
//import android.widget.TextView
//import java.util.Arrays.asList
//import android.content.Context.LAYOUT_INFLATER_SERVICE
//import android.util.Log
//import androidx.core.content.ContextCompat.getSystemService
//import android.view.LayoutInflater
//import android.view.View
//import android.widget.BaseAdapter
//import android.widget.Filter
//import android.widget.Filterable
//import java.util.*
//import kotlin.collections.ArrayList
//
//
//class CustomAutoCompleteAdapter(
//    context: Context, resource: Int, objects: Array<String>, // the last item, i.e the footer
//    private val mFooterText: String
//) : BaseAdapter(), Filterable {
//    override fun getFilter(): Filter {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//
//    private var mObjects: List<String>? = null
//    private val mLock = Any()
//
//    private val mResource: Int
//    private var mDropDownResource: Int = 0
//
//    private var mOriginalValues: ArrayList<String>? = null
//    private var mFilter: ArrayFilter? = null
//
//    private val mInflater: LayoutInflater
//
//    // our listener
//    private var mListener: OnFooterClickListener? = null
//
//    val filter: Filter
//        get() {
//            if (mFilter == null) {
//                mFilter = ArrayFilter()
//            }
//            return mFilter
//        }
//
//    interface OnFooterClickListener {
//        fun onFooterClicked()
//    }
//
//    init {
//        mInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)
//        mDropDownResource = resource
//        mResource = mDropDownResource
//        mObjects = Arrays.asList(objects)
//    }
//
//
//    /**
//     * Set listener for clicks on the footer item
//     */
//    fun setOnFooterClickListener(listener: OnFooterClickListener) {
//        mListener = listener
//    }
//
//    override fun getCount(): Int {
//        return mObjects!!.size + 1
//    }
//
//    override fun getItem(position: Int): String {
//        return if (position == count - 1) {
//            // last item is always the footer text
//            mFooterText
//        } else mObjects!![position]
//
//        // return real text
//    }
//
//    override fun getItemId(position: Int): Long {
//        return position.toLong()
//    }
//
//    override fun getView(position: Int, convertView: View, parent: ViewGroup): View {
//        return createViewFromResource(position, convertView, parent, mResource)
//    }
//
//    private fun createViewFromResource(
//        position: Int, convertView: View?, parent: ViewGroup,
//        resource: Int
//    ): View {
//        val view: View
//        val text: TextView
//
//        if (convertView == null) {
//            view = mInflater.inflate(resource, parent, false)
//        } else {
//            view = convertView
//        }
//
//        try {
//            //  If no custom field is assigned, assume the whole resource is a TextView
//            text = view
//        } catch (e: ClassCastException) {
//            Log.e("CustomAutoCompleteAdapter", "Layout XML file is not a text field")
//            throw IllegalStateException("Layout XML file is not a text field", e)
//        }
//
//        text.text = getItem(position)
//
//        if (position == count - 1) {
//            // it's the last item, bind click listener
//            view.setOnClickListener(object : View.OnClickListener() {
//                fun onClick(v: View) {
//                    if (mListener != null) {
//                        mListener!!.onFooterClicked()
//                    }
//                }
//            })
//        } else {
//            // it's a real item, set click listener to null and reset to original state
//            view.setOnClickListener(null)
//            view.setClickable(false)
//        }
//
//        return view
//    }
//
//    override fun getDropDownView(position: Int, convertView: View, parent: ViewGroup): View {
//        return createViewFromResource(position, convertView, parent, mDropDownResource)
//    }
//
//    /**
//     *
//     * An array filter constrains the content of the array adapter with
//     * a prefix. Each item that does not start with the supplied prefix
//     * is removed from the list.
//     */
//    private inner class ArrayFilter : Filter() {
//        protected override fun performFiltering(prefix: CharSequence?): FilterResults {
//            val results = FilterResults()
//
//            if (mOriginalValues == null) {
//                synchronized(mLock) {
//                    mOriginalValues = ArrayList(mObjects)
//                }
//            }
//
//            if (prefix == null || prefix.length == 0) {
//                val list: ArrayList<String>
//                synchronized(mLock) {
//                    list = ArrayList(mOriginalValues)
//                }
//                results.values = list
//
//                // add +1 since we have a footer item which is always visible
//                results.count = list.size + 1
//            } else {
//                val prefixString = prefix.toString().toLowerCase()
//
//                val values: ArrayList<String>
//                synchronized(mLock) {
//                    values = ArrayList(mOriginalValues)
//                }
//
//                val count = values.size
//                val newValues = ArrayList<String>()
//
//                for (i in 0 until count) {
//                    val value = values[i]
//                    val valueText = value.toLowerCase()
//
//                    // First match against the whole, non-splitted value
//                    if (valueText.startsWith(prefixString)) {
//                        newValues.add(value)
//                    } else {
//                        val words = valueText.split(" ".toRegex()).dropLastWhile { it.isEmpty() }
//                            .toTypedArray()
//                        val wordCount = words.size
//
//                        // Start at index 0, in case valueText starts with space(s)
//                        for (k in 0 until wordCount) {
//                            if (words[k].startsWith(prefixString)) {
//                                newValues.add(value)
//                                break
//                            }
//                        }
//                    }
//                }
//
//                results.values = newValues
//                // add one since we always show the footer
//                results.count = newValues.size + 1
//            }
//
//            return results
//        }
//
//        protected override fun publishResults(constraint: CharSequence, results: FilterResults) {
//
//            mObjects = results.values as List<String>
//            notifyDataSetChanged()
//        }
//    }
//}