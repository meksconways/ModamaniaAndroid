package com.modart.modamania.main

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.onNavDestinationSelected
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.modart.modamania.R
import com.modart.modamania.util.ToolbarFont
import com.modart.modamania.util.gone
import com.modart.modamania.util.visible
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    lateinit var viewModel: MainActivityVM
    private val navController by lazy {
        Navigation.findNavController(this, R.id.container_navhost)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this)[MainActivityVM::class.java]
        observeVM()

    }

    private fun abortShow() {
        val w: Window = window
        w.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimaryDark)
        w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
    }

    private fun abortHide() {
        val w: Window = window
        w.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        w.statusBarColor = ContextCompat.getColor(this, android.R.color.transparent)

    }

    private fun setupActionBar() {
        setSupportActionBar(toolbar)
        val appBarConfiguration = AppBarConfiguration
            .Builder(
                R.id.tab1,
                R.id.tab2,
                R.id.tab3,
                R.id.tab4
            )
            .build()
        setupActionBarWithNavController(this, navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }

    private fun observeVM() {
        viewModel.getBottomState().observe(this, Observer {

            if (it) {
                toolbar.visible()
                viewtoolbarbtm.visible()
            } else {
                toolbar.gone()
                viewtoolbarbtm.gone()
            }

        })
        viewModel.getToolbarState().observe(this, Observer {

            if (it) {
                bottom_nav.visible()
                bottomView.visible()
            } else {
                bottom_nav.gone()
                bottomView.gone()
            }

        })
        viewModel.getIsFullScreen().observe(this, Observer {
            if (it) abortHide() else abortShow()
        })

        viewModel.getMainScreenState().observe(this, Observer {
            if (it) {
                navController.setGraph(R.navigation.nav_main)
                viewModel.setBottomState(true)
                viewModel.setIsFullScreen(false)
                viewModel.setToolbarState(true)
                setupActionBar()
                bottom_nav.setupWithNavController(Navigation.findNavController(this, R.id.container_navhost))
                bottom_nav.setOnNavigationItemSelectedListener { item ->
                    onNavDestinationSelected(item, navController)
                }
            }

        })
        viewModel.getToolbarTitle().observe(this, Observer {
            toolbar.title = it
        })
        viewModel.getToolbarFontState().observe(this, Observer {
            if (it == ToolbarFont.N){
                toolbar.setTitleTextAppearance(this,R.style.NoteWorthyAppearance)
            }else{
                toolbar.setTitleTextAppearance(this,R.style.OpenSansAppearance)
            }
        })
    }


}
