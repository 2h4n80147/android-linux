package com.zhaku.detailing.Others

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ListView
import com.zhaku.detailing.R
import com.zhaku.detailing.data.User
import java.util.*
import kotlin.collections.ArrayList


class SearchActivity : AppCompatActivity() {

    private val mContext = this@SearchActivity

    //widgets
    private var mSearchParam: EditText? = null
    private var mListView: ListView? = null

    //vars
    private var mUserList: MutableList<User>? = null
    private var mAdapter: UserListAdapter? = null

    override fun onCreate( savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        mSearchParam = findViewById(R.id.search) as EditText
        mListView = findViewById(R.id.listView) as ListView
        Log.d(TAG, "onCreate: started.")

        //hideSoftKeyboard()
        //setupBottomNavigationView()
        initTextListener()
    }

    private fun initTextListener() {
        Log.d(TAG, "initTextListener: initializing")

        mUserList = ArrayList()

        mSearchParam!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {

                val text = mSearchParam!!.text.toString().toLowerCase(Locale.getDefault())
                searchForMatch(text)
            }
        })
    }

    private fun searchForMatch(keyword: String) {
        Log.d(TAG, "searchForMatch: searching for a match: $keyword")
        mUserList!!.clear()
        //update the users list view
        if (keyword.length == 0) {

        } else {

//            val reference = FirebaseDatabase.getInstance().getReference()
//            val query = reference.child(getString(R.string.dbname_users))
//                .orderByChild(getString(R.string.field_username)).equalTo(keyword)
//            query.addListenerForSingleValueEvent(object : ValueEventListener() {
//                fun onDataChange(dataSnapshot: DataSnapshot) {
//                    for (singleSnapshot in dataSnapshot.getChildren()) {
//                        Log.d(
//                            TAG,
//                            "onDataChange: found user:" + singleSnapshot.getValue(User::class.java).toString()
//                        )
//
//                        mUserList!!.add(singleSnapshot.getValue(User::class.java))
//                        //update the users list view
//                        updateUsersList()
//                    }
//                }
//
//                fun onCancelled(databaseError: DatabaseError) {
//
//                }
//            })
        }
    }
//
//    private fun updateUsersList() {
//        Log.d(TAG, "updateUsersList: updating users list")
//
//        mAdapter = UserListAdapter(this@SearchActivity, R.layout.layout_user_listitem, mUserList)
//
//        mListView!!.setAdapter(mAdapter)
//
//        mListView!!.setOnItemClickListener(object : AdapterView.OnItemClickListener {
//            fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
//                Log.d(TAG, "onItemClick: selected user: " + mUserList!![position].toString())
//
//                //navigate to profile activity
//                val intent = Intent(this@SearchActivity, ProfileActivity::class.java)
//                intent.putExtra(
//                    getString(R.string.calling_activity),
//                    getString(R.string.search_activity)
//                )
//                intent.putExtra(getString(R.string.intent_user), mUserList!![position])
//                startActivity(intent)
//            }
//        })
//    }
//
//
//    private fun hideSoftKeyboard() {
//        if (currentFocus != null) {
//            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//            imm!!.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
//        }
//    }
//
//
//    /**
//     * BottomNavigationView setup
//     */
//    private fun setupBottomNavigationView() {
//        Log.d(TAG, "setupBottomNavigationView: setting up BottomNavigationView")
//        val bottomNavigationViewEx = findViewById(R.id.bottomNavViewBar) as BottomNavigationViewEx
//        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx)
//        BottomNavigationViewHelper.enableNavigation(mContext, this, bottomNavigationViewEx)
//        val menu = bottomNavigationViewEx.menu
//        val menuItem = menu.getItem(ACTIVITY_NUM)
//        menuItem.isChecked = true
//    }
//
    companion object {
        private val TAG = "SearchActivity"
        private val ACTIVITY_NUM = 1
    }
}