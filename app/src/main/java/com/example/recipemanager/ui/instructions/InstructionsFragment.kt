package com.example.recipemanager.ui.instructions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.example.recipemanager.R
import com.example.recipemanager.models.Result
import com.example.recipemanager.util.Constants
import kotlinx.android.synthetic.main.fragment_instructions.view.*


class InstructionsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_instructions, container, false)

        val args = arguments
        val myBundle: Result? = args?.getParcelable(Constants.RECIPE_RESULT_KEY)


        view.instructions_webView?.webViewClient = object : WebViewClient() {}
        val websiteUrl = myBundle!!.sourceUrl
        view.instructions_webView?.loadUrl(websiteUrl)

        return view
    }


}