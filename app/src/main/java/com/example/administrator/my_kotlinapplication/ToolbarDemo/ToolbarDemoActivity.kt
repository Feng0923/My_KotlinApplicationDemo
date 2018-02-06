package com.example.administrator.my_kotlinapplication.ToolbarDemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.ActionBarDrawerToggle
import android.view.Gravity
import android.view.Menu
import com.example.administrator.my_kotlinapplication.R
import kotlinx.android.synthetic.main.activity_toolbar_demo.*
import kotlinx.android.synthetic.main.drawerlayout.*
import kotlinx.android.synthetic.main.mtoolbar.*
import org.jetbrains.anko.doAsync

class ToolbarDemoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_toolbar_demo)
        initView()
    }

    private fun initView() {
        setSupportActionBar(mtoolbar)
        mtoolbar.title = "mToolbar"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val mDrawerToggle = ActionBarDrawerToggle(this,dl_cehua,mtoolbar,R.string.drawer_open,R.string.drawer_close)
        dl_cehua.addDrawerListener(mDrawerToggle)
        tv_recover.setOnClickListener { dl_cehua.closeDrawer(Gravity.LEFT) }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        return super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.main,menu)
        return true
    }
}
