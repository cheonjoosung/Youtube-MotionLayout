package com.example.youtube_motionlayout.ui.youtube

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.youtube_motionlayout.R
import com.example.youtube_motionlayout.databinding.FragmentYoutubeViewBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener

class YoutubeViewFragment(
    val videoId: String
) : Fragment() {

    private var _binding: FragmentYoutubeViewBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentYoutubeViewBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            viewContainer.transitionToEnd()

            playerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    youTubePlayer.loadVideo(videoId, 0F)
                    youTubePlayer.play()
                }
            })

            ivClose.setOnClickListener {
                requireActivity().supportFragmentManager
                    .beginTransaction()
                    //.setCustomAnimations(0, R.anim.slide_in_down)
                    .remove(this@YoutubeViewFragment)
                    .commit()
            }
        }
    }


    fun closeFragment() {
        if (binding.viewContainer.currentState == R.id.start) {
            requireActivity().supportFragmentManager
                .beginTransaction()
                //.setCustomAnimations(0, R.anim.slide_in_down)
                .remove(this@YoutubeViewFragment)
                .commit()
        } else {
            binding.viewContainer.transitionToStart()
        }
    }
}