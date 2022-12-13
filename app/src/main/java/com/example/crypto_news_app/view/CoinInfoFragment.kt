package com.example.crypto_news_app.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.crypto_news_app.R

class CoinInfoFragment : Fragment() {

    private var id_=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment




        return inflater.inflate(R.layout.fragment_coin_info, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let{
            id_ =CoinInfoFragmentArgs.fromBundle(it).coinIdArguman
            System.out.println(id_)

            Toast.makeText(context , id_.toString(), Toast.LENGTH_SHORT).show()




        }

    }


}