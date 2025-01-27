package com.example.youtubeapikotlin.ui.channel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.youtubeapikotlin.databinding.FragmentChannelBinding


class ChannelFragment : Fragment() {

    private var _binding: FragmentChannelBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var channelViewModel: ChannelViewModel? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        channelViewModel?.channel?.observe(viewLifecycleOwner, {
            if (it != null && it.items.isNotEmpty()){
                it.items.forEach {  channel ->
                    binding.tvChannelName.text = channel.snippet.title
                    binding.tvChannelDescription.text = channel.snippet.description
                    Glide.with(requireContext()).load(channel.branding.image.bannerUrl).into(binding.imageChannel)
                    Glide.with(requireContext()).load(channel.snippet.thumbnails.high.url).into(binding.imageView2)
                }
            }
        })

        channelViewModel?.isLoading?.observe(viewLifecycleOwner, {
            if (it){
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        })
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
         channelViewModel =
            ViewModelProvider(this).get(ChannelViewModel::class.java)

        _binding = FragmentChannelBinding.inflate(inflater, container, false)



        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}