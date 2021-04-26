package com.example.passmanager

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.passwordmanager.roomaddsite.AddSiteInfo
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(), SiteAdapter.OnSiteItemClickListener  {
    lateinit var toolbar : Toolbar
    lateinit var sites : ArrayList<AddSiteInfo>
    lateinit var folderName : String
    lateinit var adapter: SiteAdapter
    lateinit var recyclerView: RecyclerView
    lateinit var mob : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        mob = intent.extras?.getString("posmob").toString()

        //sites = SignInActivity.userSignupdata!!.mysiteDao().selectfolder("Social Media") as ArrayList<AddSiteInfo>
        //sites = SignInActivity.userSignupdata!!.mysiteDao().getSites() as ArrayList<AddSiteInfo>
        //val nm : Int = SiteAdapter(sites,this).num
        //val total_count : TextView = findViewById(R.id.count)
        //total_count.text = nm.toString()
        //adapter = SiteAdapter(sites,this)
        //recyclerView = findViewById(R.id.recycler_view_rs)
        //recyclerView.adapter = adapter
        //recyclerView.layoutManager = LinearLayoutManager(this)

        toolbar = findViewById(R.id.hometoolbar)
        setSupportActionBar(toolbar)
        //supportActionBar?.hide()
        supportActionBar?.setLogo(R.drawable.pmm)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val textspin = findViewById<TextView>(R.id.cat)
        val spinr = findViewById<Spinner>(R.id.spinhm)
        val spinadapter : ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(this, R.array.folders, android.R.layout.simple_spinner_item)
        spinadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinr.adapter = spinadapter
        spinr.onItemSelectedListener
        spinr.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                folderName = p0?.getItemAtPosition(p2).toString()
                textspin.setText(folderName)
                sites = SignInActivity.userSignupdata!!.mysiteDao().selectfolder(folderName,mob) as ArrayList<AddSiteInfo>
                val nmm : Int = SiteAdapter(sites, this@HomeActivity).num
                val total_countt : TextView = findViewById(R.id.count)
                total_countt.text = "0"+nmm.toString()
                adapter = SiteAdapter(sites, this@HomeActivity)
                //val recyclerView : RecyclerView = recycler_view_rs
                recyclerView = findViewById(R.id.recycler_view_rs)
                recyclerView.adapter = adapter
                recyclerView.layoutManager = LinearLayoutManager(this@HomeActivity)
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                //TODO("Not yet implemented")
            }
        }

        float_add.setOnClickListener {
            val intent = Intent(this, AddSiteActivity::class.java)
            intent.putExtra("posmobo", mob)
            startActivity(intent)
        }

    }

    override fun onSiteClick(position: Int) {
        val intent= Intent(this, SiteDetailsActivity::class.java)
        intent.putExtra("position", position)
        intent.putExtra("posfolder", folderName)
        intent.putExtra("posmobdet", mob)
        startActivity(intent)

    }

    private fun searchSite(query: String) {
        val squery = "%$query%"
        sites = SignInActivity.userSignupdata!!.mysiteDao().searchSite(squery,folderName,mob) as ArrayList<AddSiteInfo>
        adapter = SiteAdapter(sites, this@HomeActivity)
        val recyclersView : RecyclerView = findViewById(R.id.recycler_view_rs)
        //recyclersView = findViewById(R.id.recycler_view_rs)
        recyclersView.adapter = adapter
        recyclersView.layoutManager = LinearLayoutManager(this@HomeActivity)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home,menu)
        val menuItem = menu?.findItem(R.id.searchvw)
        //if (menuItem != null){
            val searchView: SearchView = menuItem?.actionView as SearchView
            searchView.maxWidth = Int.MAX_VALUE

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if (query != null){
                        //searchSite(query)
                        //Toast.makeText(this@HomeActivity,query,Toast.LENGTH_LONG).show()
                        sites = SignInActivity.userSignupdata!!.mysiteDao().searchSite(query,folderName,mob) as ArrayList<AddSiteInfo>
                        adapter = SiteAdapter(sites, this@HomeActivity)
                        val recyclerVieww : RecyclerView = findViewById(R.id.recycler_view_rs)
                        //recyclerView = findViewById(R.id.recycler_view_rs)
                        recyclerVieww.adapter = adapter
                        recyclerVieww.layoutManager = LinearLayoutManager(this@HomeActivity)
                    }
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if (newText != null){
//                        //Toast.makeText(this@HomeActivity,newText,Toast.LENGTH_LONG).show()
//                        sites = SignInActivity.userSignupdata!!.mysiteDao().searchSite(newText) as ArrayList<AddSiteInfo>
//                        adapter = SiteAdapter(sites, this@HomeActivity)
//                        val recyclerrView : RecyclerView = findViewById(R.id.recycler_view_rs)
//                        //recyclerView = findViewById(R.id.recycler_view_rs)
//                        recyclerrView.adapter = adapter
//                        recyclerrView.layoutManager = LinearLayoutManager(this@HomeActivity)
                        searchSite(newText)
                    }
                    return true
                }

            })
        //}

        return super.onCreateOptionsMenu(menu)
    }

}



