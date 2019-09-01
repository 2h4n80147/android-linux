package com.zhaku.detailing.Search

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import android.widget.TextView
import android.os.Bundle
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.NonNull
import com.zhaku.detailing.R




class SampleFragmentPagerAdapter(fm: FragmentManager) :
    FragmentPagerAdapter(fm) {
    internal val PAGE_COUNT = 3
    private val tabTitles = arrayOf("Оброзовательный центр", "Репетитор", "Ученик")
    var fragments = arrayListOf<Fragment>(ListFragment(0), ListFragment(1), ListFragment(2))


    override fun getCount(): Int {
        return PAGE_COUNT
    }


    override fun getItem(position: Int): Fragment {
        return PageFragment.newInstance(position + 1)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        // генерируем заголовок в зависимости от позиции
        return tabTitles[position]
    }

}

class PageFragment : Fragment() {

    private var mPage: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mPage = arguments!!.getInt(ARG_PAGE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_page, container, false)
        val textView = view as TextView
        textView.text = "Fragment #$mPage"
        return view
    }

    companion object {
        val ARG_PAGE = "ARG_PAGE"

        fun newInstance(page: Int): PageFragment {
            val args = Bundle()
            args.putInt(ARG_PAGE, page)
            val fragment = PageFragment()
            fragment.arguments = args
            return fragment
        }
    }
}