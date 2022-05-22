package com.example.youtube_motionlayout

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.youtube_motionlayout.databinding.ActivityMainBinding
import com.example.youtube_motionlayout.ui.VideoIdListener
import com.example.youtube_motionlayout.ui.youtube.YoutubeViewFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), VideoIdListener {

    private lateinit var binding: ActivityMainBinding

    private val tag = "YoutubeViewFragment"

    private var isShow = false
    private var isHide = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


        binding.mainContainer.addTransitionListener(object : MotionLayout.TransitionListener{
            override fun onTransitionStarted(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int
            ) {
            }

            override fun onTransitionChange(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int,
                progress: Float
            ) {
                Log.e(MainActivity::class.java.simpleName, "change $startId $progress $startId $endId")

                if (progress > 0.5f) {
                    hideActionBar()
                } else {
                    showActionBar()
                }
            }

            override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {
            }

            override fun onTransitionTrigger(
                motionLayout: MotionLayout?,
                triggerId: Int,
                positive: Boolean,
                progress: Float
            ) {
            }

        })
    }

    override fun pass(videoId: String) {

        val id = if (videoId.isEmpty()) "JKIunx-L4w4" else videoId

        val youtubePlayerFragment = YoutubeViewFragment(id)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, youtubePlayerFragment, tag)
            .commit()
    }


    @SuppressLint("RestrictedApi")
    private fun showActionBar() {
        if (isShow) return

        isShow = true
        isHide = false
        supportActionBar?.let {
            it.setShowHideAnimationEnabled(false)
            it.show()
        }

    }

    @SuppressLint("RestrictedApi")
    private fun hideActionBar() {
        if (isHide) return

        isShow = false
        isHide = true
        supportActionBar?.let {
            it.setShowHideAnimationEnabled(false)
            it.hide()
        }
    }

    override fun onBackPressed() {

        val fragment = supportFragmentManager.findFragmentByTag(tag) as YoutubeViewFragment?
        if (fragment != null && fragment.isVisible) {
            fragment.closeFragment()
        } else {
            super.onBackPressed()
        }
    }
}