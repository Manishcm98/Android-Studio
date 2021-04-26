package com.example.passmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.widget.Toolbar
import com.example.passwordmanager.roomaddsite.AddSiteInfo
import kotlinx.android.synthetic.main.activity_add_site.*

class AddSiteActivity : AppCompatActivity() {
    lateinit var folderName : String
    lateinit var toolbar : Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_site)

        toolbar = findViewById(R.id.addToolBar)
        //toolbar.setTitle("Add Site")
        setSupportActionBar(toolbar)
        //supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.title = "Add Site"

        val Url = et_url
        val SiteName = et_sname
        val Folder = spinner
        val UserName = et_uname
        val Passwd = et_pw
        val Notes = et_notes

        val mobo = intent.extras?.getString("posmobo").toString()

        val spin = findViewById<Spinner>(R.id.spinner)
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

        save.setOnClickListener {
            val newSite = AddSiteInfo(SiteName.text.toString(),Url.text.toString(),folderName,UserName.text.toString(),Passwd.text.toString(),Notes.text.toString(),mobo)
            SignInActivity.userSignupdata!!.mysiteDao().addSite(newSite)
            val intnt = Intent(this,HomeActivity::class.java)
            intnt.putExtra("posmob", mobo)
            startActivity(intnt)
        }

        reset.setOnClickListener {
            //Toast.makeText(this,folderName, Toast.LENGTH_LONG).show()
            Url.setText("")
            SiteName.setText("")
            UserName.setText("")
            Passwd.setText("")
            Notes.setText("")
        }

    }

}