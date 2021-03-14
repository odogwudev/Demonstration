package com.odogwudev.demonstration.ui.mainActivity

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.android.material.navigation.NavigationView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.fragment.app.replace
import androidx.lifecycle.Observer
import com.odogwudev.demonstration.R
import com.odogwudev.demonstration.model.ItemMenuEntity
import com.odogwudev.demonstration.ui.FragmentBuilder
import com.odogwudev.demonstration.utils.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toggle = ActionBarDrawerToggle(
            this,
            drawer_layout,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        setSupportActionBar(toolbar)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        subscribeObservers()
        viewModel.uploadItemsList()
    }

    private fun subscribeObservers() {
        viewModel.itemsList.observe(this, Observer {
            when (it) {
                is DataState.Success<List<ItemMenuEntity>> -> {
                    createMenu(it.data)
                    viewModel.setItemsList(it.data)
                    progress_bar.visibility = View.GONE
                }
                is DataState.Error -> {
                    progress_bar.visibility = View.GONE
                    Toast.makeText(this, it.exception.message, Toast.LENGTH_LONG).show()
                }
                is DataState.Loading -> {
                    progress_bar.visibility = View.VISIBLE
                }
            }
        })
        viewModel.selectedItem.observe(this, Observer {
            Toast.makeText(this, it.name, Toast.LENGTH_SHORT).show()
            showFragment(it)
        })
    }

    private fun createMenu(items: List<ItemMenuEntity>) {
        nav_view.menu.clear()
        for (item in items) {
            nav_view.menu.add(item.name)
        }
        nav_view.setNavigationItemSelectedListener(this)
    }

    private fun showFragment(menuItem: ItemMenuEntity) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, FragmentBuilder.create(menuItem)).commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        viewModel.itemSelected(item.title)
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home)
            drawer_layout.openDrawer(GravityCompat.START)
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val TAG = "MainActivity"
    }
}