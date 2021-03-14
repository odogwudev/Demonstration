package com.odogwudev.demonstration.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.odogwudev.demonstration.model.FunctionType
import com.odogwudev.demonstration.model.ItemMenuEntity
import com.odogwudev.demonstration.ui.image.ImageFragment
import com.odogwudev.demonstration.ui.text.TextFragment
import com.odogwudev.demonstration.ui.url.UrlFragment


class FragmentBuilder constructor() {

    companion object {
        fun create(menuItem: ItemMenuEntity): Fragment {
            val fragment: Fragment = when(menuItem.function) {
                is FunctionType.Url -> {
                    UrlFragment()
                }
                is FunctionType.Image -> {
                    ImageFragment()
                }
                is FunctionType.Text -> {
                    TextFragment()
                }
            }
            val bundle = Bundle()
            bundle.putString("param", menuItem.function.param)
            fragment.arguments = bundle
            return fragment
        }
    }
}