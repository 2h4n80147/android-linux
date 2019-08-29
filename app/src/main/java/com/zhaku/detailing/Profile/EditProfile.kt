package com.zhaku.detailing.Profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.*
import com.zhaku.detailing.R
import androidx.core.content.ContextCompat
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.zhaku.detailing.User
import com.zhaku.detailing.globalUser
import kotlinx.android.synthetic.main.activity_edit_profile.*
import java.security.AccessController.getContext


class EditProfile : AppCompatActivity() {

    //var EduField_chipg_group: ChipGroup = findViewById(R.id.edu_field_chip_group)
    lateinit var User : globalUser
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        val name = findViewById<EditText>(R.id.edit_name)

        val username = findViewById<EditText>(R.id.edit_username)
        val email = findViewById<EditText>(R.id.edit_email)
        val phone = findViewById<EditText>(R.id.edit_phone)
        val sex = findViewById<Spinner>(R.id.edit_sex_spinner)
        val infoBody = findViewById<EditText>(R.id.edit_detail_info)

        val closeButton = findViewById<ImageView>(R.id.edit_close)
        val saveButton = findViewById<ImageView>(R.id.edit_save)


        closeButton.setOnClickListener{
            finish()
        }
        saveButton.setOnClickListener{
            saveaAlldata()

        }
    }
    fun saveaAlldata() {

    }




    private fun setupChips() {
        //
//        etValue.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, _ ->
//            if (actionId == EditorInfo.IME_ACTION_DONE) {
//                val txtVal = v.text
//                if(!txtVal.isNullOrEmpty()) {
//                    addChipToGroup(txtVal.toString(), chipGroup2)
//                    etValue.setText("")
//                }
//
//                return@OnEditorActionListener true
//            }
//            false
//        })

//
    }

    private fun addChipToGroup(txt: String, chipGroup: ChipGroup) {
        val x = getContext()

        val chip = Chip(applicationContext)
        chip.text = txt
//        chip.chipIcon = ContextCompat.getDrawable(requireContext(), baseline_person_black_18)
        chip.isCloseIconEnabled = true
        chip.setChipIconTintResource(R.color.black)

        // necessary to get single selection working
        chip.isClickable = false
        chip.isCheckable = false
        chipGroup.addView(chip as View)
        chip.setOnCloseIconClickListener { chipGroup.removeView(chip as View) }
        printChipsValue(chipGroup)
    }

    private fun printChipsValue(chipGroup: ChipGroup) {
        for (i in 0 until chipGroup.childCount) {
            val chipObj = chipGroup.getChildAt(i) as Chip
            Log.d("Chips text :: " , chipObj.text.toString())

        }
    }

    fun SetupSexSpinner() {
        val spinner = findViewById(R.id.edit_sex_spinner) as Spinner
    }
//    fun addChip() {
//        val chip = Chip(applicationContext)
//
//        chip.text = "Name Surname"
//        chip.chipIcon = ContextCompat.getDrawable(applicationContext, R.drawable.ic_clear_foreground)
//        chip.setChipIconTintResource(R.color.black)
//
//        chip.isClickable = true
//        chip.isCheckable = false
//        EduField_chipg_group.addView(chip as View)
//        chip.setOnCloseIconClickListener { EduField_chipg_group.removeView(chip as View) }
//    }





}
