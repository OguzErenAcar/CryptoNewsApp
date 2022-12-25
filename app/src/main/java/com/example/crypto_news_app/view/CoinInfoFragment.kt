package com.example.crypto_news_app.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.crypto_news_app.R
import com.example.crypto_news_app.VM.Coin_infoVM
import com.example.crypto_news_app.util.gorselIndir
import com.example.crypto_news_app.util.placeholderYap
import kotlinx.android.synthetic.main.fragment_coin_info.*

class CoinInfoFragment : Fragment() {

    private var id_=0
    private lateinit var viewModel:Coin_infoVM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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

        }
        viewModel=ViewModelProviders.of(this).get(Coin_infoVM::class.java)
        viewModel.roomVerisiniAl(id_)


        observeLiveData()

    }

    fun observeLiveData(){

        viewModel.CoinLiveData.observe(viewLifecycleOwner, Observer { Coin->
            Coin?.let{
                CoinName.text=Coin.coinName
                Price.text=Coin.price
                context?.let {
                    //data url dolayısıyla resim her zaman indiriliyor
                    coin_IV.gorselIndir(Coin.iconUrl, placeholderYap(it))
                }
            }
        })


    }

}