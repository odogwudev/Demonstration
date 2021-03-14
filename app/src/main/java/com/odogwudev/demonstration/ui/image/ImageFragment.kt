package com.odogwudev.demonstration.ui.image

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.odogwudev.demonstration.R
import kotlinx.android.synthetic.main.fragment_image.*

class ImageFragment : Fragment() {

    private val viewModel: ImageFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_image, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString("param")?.let { bindParam(it) }
    }

    private fun bindParam(param: String) {
        Glide.with(this)
            .load(param)
            .placeholder(R.drawable.ic_launcher_background)
            .into(image)
    }
}