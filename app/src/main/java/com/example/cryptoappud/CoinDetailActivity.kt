package com.example.cryptoappud

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.cryptoappud.databinding.ActivityCoinDetailBinding
import com.squareup.picasso.Picasso


class CoinDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: CoinViewModel
    lateinit var bindingClass: ActivityCoinDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityCoinDetailBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)
        if (!intent.hasExtra(EXTRA_FROM_SYMBOL)) {
            finish()
            return
        }
        val fromSymbol = intent.getStringExtra(EXTRA_FROM_SYMBOL)
        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        if (fromSymbol != null) {
            viewModel.getDetailInfo(fromSymbol).observe(this, Observer {
                bindingClass.tvPrice.text = it.price.toString()
                bindingClass.tvMinPrice.text = it.lowday.toString()
                bindingClass.tvMaxPrice.text = it.highday.toString()
                bindingClass.tvLastMarket.text = it.lastmarket
                bindingClass.tvLastUpdate.text = it.getFormattedTime()
                bindingClass.tvFromSymbol.text = it.fromsymbol
                bindingClass.tvToSymbol.text = it.tosymbol
                Picasso.get().load(it.getFullImageUrl()).into(bindingClass.ivLogoCoin)
            })
        }
    }

    companion object {
        private const val EXTRA_FROM_SYMBOL = "fSym"

        fun newIntent(context: Context, fromSymbol: String): Intent {
            val intent = Intent(context, CoinDetailActivity::class.java)
            intent.putExtra(EXTRA_FROM_SYMBOL, fromSymbol)
            return intent
        }
    }
}