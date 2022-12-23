package com.example.crypto_news_app.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crypto_news_app.R
import com.example.crypto_news_app.VM.CoinListVM
import com.example.crypto_news_app.adapter.CoinGridAdapter
//import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_main.*


class MainFragment : Fragment() {
  //  view model ile adapter oluşturduk ve burda tanımladık
    private lateinit var viewmodel:CoinListVM
    private var Coin_adapter=CoinGridAdapter(arrayListOf())

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
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navbar.setOnClickListener {

//          val action = MainFragmentDirections.actionMainFragmentToCoinInfoFragment(1907)
//          Navigation.findNavController(it).navigate(action)
        }
        //  ViewModelProviders--->fragment ile vm yyi bağlar
        viewmodel=ViewModelProviders.of(this).get(CoinListVM::class.java)
        viewmodel.refreshData()



        recyclerview.layoutManager= object :GridLayoutManager(context,3){
            override fun canScrollVertically(): Boolean {
                return false
            } }
//        recyclerview.setNestedScrollingEnabled(false);
//        recyclerview.setHasFixedSize(true)
        recyclerview
        recyclerview.adapter= Coin_adapter
        observeLiveData()
    }
    //viewmodeldeki verileri çeker
    fun observeLiveData(){

        viewmodel.Coinler.observe(viewLifecycleOwner, Observer {
            it?.let{//nullable ile çalışılrsa kullanılması gerekir
                recyclerview.visibility=View.VISIBLE
                Coin_adapter.coinListesiniGuncelle(it)

            }
        })
    }

}