package com.example.passmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.activity_site_details.*

class SiteDetailsActivity : AppCompatActivity() {
    lateinit var toolbar : Toolbar
    lateinit var SiteName : String
    lateinit var Url : String
    lateinit var Folder : String
    lateinit var UserName : String
    lateinit var SitePwd : String
    lateinit var Notes : String
    lateinit var mobdet : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_site_details)

        mobdet = intent.extras?.getString("posmobdet").toString()
        toolbar = findViewById(R.id.detToolBar)
        //toolbar.setTitle("Site Details")
        setSupportActionBar(toolbar)
        //supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.title = "Site Details"

        val position = intent.extras?.getInt("position")
        val fname = intent.extras?.getString("posfolder")
        val items = SignInActivity.userSignupdata!!.mysiteDao().selectfolder(fname.toString(),mobdet.toString())
        SiteName = items[position!!].SiteName
        Url = items[position].Url
        Folder = items[position].Folder
        UserName = items[position].UserName
        SitePwd = items[position].SitePwd
        Notes = items[position].Notes

        val sname = dt_sname
        val url = dt_url
        val folder= dtspinner
        val uname= dt_uname
        val spwd = dt_pw
        val note= dt_notes
        sname.setText(SiteName)
        url.setText(Url)
        folder.setText(Folder)
        uname.setText(UserName)
        spwd.setText(SitePwd)
        note.setText(Notes)
        //Toast.makeText(context,SitePwd,Toast.LENGTH_LONG).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.edit,menu)
        val editmenu = menu?.findItem(R.id.editt)
        editmenu?.setOnMenuItemClickListener {

            val intent = Intent(this,EditSiteActivity::class.java)
            intent.putExtra("posa",SiteName)
            intent.putExtra("posb",Url)
            intent.putExtra("posc",Folder)
            intent.putExtra("posd",UserName)
            intent.putExtra("pose",SitePwd)
            intent.putExtra("posf",Notes)
            intent.putExtra("posg",mobdet)
            startActivity(intent)
            return@setOnMenuItemClickListener true

        }
        return super.onCreateOptionsMenu(menu)
    }
}