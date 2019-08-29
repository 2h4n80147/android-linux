package com.zhaku.detailing.ItemLists

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.zhaku.detailing.R
import androidx.annotation.NonNull

import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.zhaku.detailing.Search.SearchFragment
import kotlinx.android.synthetic.main.activity_item_list.*
import android.view.ViewGroup
import javax.swing.UIManager.put
import android.util.SparseArray
import androidx.fragment.app.FragmentPagerAdapter
import android.R
import androidx.fragment.app.FragmentManager
import java.lang.ref.WeakReference


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
    val navController by lazy { findNavController(R.id.my_nav_host_fragment) }
    val bottomBar by lazy { findViewById<BottomNavigationView>(R.id.bottom_bar) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_list)

//
//        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }
//        val fragment = ItemListFragment()
//        supportFragmentManager.beginTransaction()
//            .add(R.id.fragment_container, fragment)
//            .commit()

        bottomBar.setupWithNavController(navController)

        //findNavController().navigate(R.id.item_list)

//
//        bottomBar.setOnNavigationItemSelectedListener{
//            //val x = Navigation.createNavigateOnClickListener(R.id.ic_search, null)
//            //it.onNavDestinationSelected(navController)
//
//
////                var fragment: Fragment? = null
////                when (it.itemId) {
////                    R.id.ic_search -> {
////                      fragment = SearchFragment()
////                    }
////                    R.id.ic_home -> {
////                        fragment = ItemListFragment()
////                    }
////                }
////            if (fragment!=null)
////                supportFragmentManager.beginTransaction()
////                .add(R.id.fragment_container, fragment)
////                .commit()
//
//            return@setOnNavigationItemSelectedListener true
//        }
    }
}

    private class ViewPagerAdapter internal constructor(manager: FragmentManager) :
        FragmentPagerAdapter(manager) {
        private val instantiatedFragments = SparseArray<WeakReference<Fragment>>()
        private val mFragmentList = ArrayList()
        private val mFragmentTitleList = ArrayList()

        override fun getItem(position: Int): Fragment {
            return mFragmentList.get(position)
        }

        override fun getCount(): Int {
            return mFragmentList.size
        }

        internal fun addFragment(fragment: Fragment, title: String) {
            mFragmentList.add(fragment)
            mFragmentTitleList.add(title)
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val fragment = super.instantiateItem(container, position) as Fragment
            instantiatedFragments.put(position, WeakReference(fragment))
            return fragment
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            instantiatedFragments.remove(position)
            super.destroyItem(container, position, `object`)
        }

        internal fun getFragment(position: Int): Fragment? {
            val wr = instantiatedFragments.get(position)
            return if (wr != null) {
                wr!!.get()
            } else {
                null
            }
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return mFragmentTitleList.get(position)
        }

}
