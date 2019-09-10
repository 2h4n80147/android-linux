package com.zhaku.detailing.Search

import android.media.Image
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.zhaku.detailing.ItemLists.*
import com.zhaku.detailing.R
import com.zhaku.detailing.MainActivity
import kotlinx.android.synthetic.main.fragment_search.view.*
import kotlinx.android.synthetic.main.layout_appbar_with_toolbar.view.*
import com.google.android.material.bottomsheet.BottomSheetDialog





// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [SearchFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [SearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchFragment : Fragment() {
    // TODO: Rename and change types of parameters
//    private var param1: String? = null
//    private var param2: String? = null
//    private var listener: OnFragmentInteractionListener? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
//    }

    private val tabTitles = arrayOf("Оброзовательный\nцентр", "Репетитор", "Ученик")
    private lateinit var fabFilter : FloatingActionButton
    private val tabIcons = arrayOf(R.drawable.ic_school_foreground, R.drawable.tutor, R.drawable.student)
    val TAG = "SearchFragment"
    lateinit var viewPager : ViewPager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("oncreate", TAG)
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_search, container, false)
         viewPager = root.findViewById<ViewPager>(R.id.viewPager)

        val tabLayout = root.findViewById<TabLayout>(R.id.tabLayout)
        setupViewPager()
        tabLayout.setupWithViewPager(viewPager)
        with(root) {
            fabFilter = findViewById(R.id.fab_filter)
        }
        fabFilter.setOnClickListener{
            val mBottomSheetDialog = BottomSheetDialog(activity!!)
            val sheetView = activity!!.layoutInflater.inflate(
                R.layout.bottom_sheet_filter,
                null
            )
            mBottomSheetDialog.setContentView(sheetView)
            mBottomSheetDialog.show()
        }


        return root
    }
    fun setupViewPager() {
        viewPager.setAdapter(
            SampleFragmentPagerAdapter(childFragmentManager)
        )
    }

    class SampleFragmentPagerAdapter(fm: FragmentManager) :
        FragmentPagerAdapter(fm) {
        internal val PAGE_COUNT = 3
        private val tabTitles = arrayOf("Оброзовательный центр", "Репетитор", "Ученик")
        val fragments = arrayOf(EducationItemListFragment(), TutorItemListFragment(), StudentItemListFragment())

        override fun getCount(): Int {
            return PAGE_COUNT
        }


        override fun getItem(position: Int): Fragment {
            return fragments[position]
        }

        override fun getPageTitle(position: Int): CharSequence? {
            // генерируем заголовок в зависимости от позиции
            return tabTitles[position]
        }

    }
    // TODO: Rename method, update argument and hook method into UI event
//    fun onButtonPressed(uri: Uri) {
//        listener?.onFragmentInteraction(uri)
//    }
//
//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        if (context is OnFragmentInteractionListener) {
//            listener = context
//        } else {
//            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
//        }
//    }
//
//    override fun onDetach() {
//        super.onDetach()
//        listener = null
//    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
//    interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        fun onFragmentInteraction(uri: Uri)
//    }
//
//    companion object {
//        /**
//         * Use this factory method to create a new instance of
//         * this fragment using the provided parameters.
//         *
//         * @param param1 Parameter 1.
//         * @param param2 Parameter 2.
//         * @return A new instance of fragment SearchFragment.
//         */
//        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            SearchFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }
}
