package com.employee.directory.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.employee.directory.databinding.DetailFragmentBinding

import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ListDetailFragment : Fragment() {
   lateinit var binding : DetailFragmentBinding
   private val args:ListDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         binding = DetailFragmentBinding.inflate(inflater, container, false)
         context ?: return binding.root

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data = args.data

        Glide.with(requireView())
            .load(data.profile_image)
            .centerCrop()
            .into(binding.imageView2)

        binding.textView5.text = data.name
        binding.textView6.text = data.username
        binding.textView7.text = data.email
        binding.textView8.text = data.street
    }
}