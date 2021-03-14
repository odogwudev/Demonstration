package com.odogwudev.demonstration.ui.text

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.odogwudev.demonstration.R
import kotlinx.android.synthetic.main.fragment_text.*

class TextFragment : Fragment() {

    private val viewModel: TextFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_text, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString("param")?.let { bindParam(it) }
    }

    private fun bindParam(param: String) {
        text_home.text = param
    }
}