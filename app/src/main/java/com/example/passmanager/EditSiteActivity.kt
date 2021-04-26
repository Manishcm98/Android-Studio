package com.example.passmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.widget.Toolbar
import kotlinx.android.synthetic.main.activity_add_site.*
import kotlinx.android.synthetic.main.activity_edit_site.*

class EditSiteActivity : AppCompatActivity() {
    lateinit var folderName : String
    lateinit var toolbar : Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_site)

        toolbar = findViewById(R.id.edittToolBar)
        //toolbar.setTitle("Edit")
        setSupportActionBar(toolbar)
        //supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.title = "Edit"

        val SiteName = intent.extras?.getString("posa")
        val Url = intent.extras?.getString("posb")
        val Folder = intent.extras?.getString("posc")
        val UserName = intent.extras?.getString("posd")
        val SitePwd = intent.extras?.getString("pose")
        val Notes = intent.extras?.getString("posf")
        val mobdet = intent.extras?.getString("posg")

        val spin = findViewById<Spinner>(R.id.edtspinner)
        val adapter : ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(this, R.array.folders, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        //val adapter  = context?.let { ArrayAdapter(it,R.layout.support_simple_spinner_dropdown_item,resources.getStringArray(R.array.folders)) } as SpinnerAdapter
        spin.adapter = adapter
        spin.onItemSelectedListener
        spin.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                folderName = p0?.getItemAtPosition(p2).toString()
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                //TODO("Not yet implemented")
            }
        }
        edt_sname.setText(SiteName)
        edt_url.setText(Url)
        edt_uname.setText(UserName)
        edt_pw.setText(SitePwd)
        edt_notes.setText(Notes)

        update.setOnClickListener {
            val url = edt_url.text.toString()
            val sitename = edt_sname.text.toString()
            val folder = spinner
            val username = edt_uname.text.toString()
            val passwd = edt_pw.text.toString()
            val notes = edt_notes.text.toString()
            SignInActivity.userSignupdata!!.mysiteDao().update(sitename,url,folderName,username,passwd,notes,SiteName.toString())
            val intnt = Intent(this,HomeActivity::class.java)
            intnt.putExtra("posmob",mobdet)
            startActivity(intnt)
        }

    }
}