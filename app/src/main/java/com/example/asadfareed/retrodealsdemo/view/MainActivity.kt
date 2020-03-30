package com.example.asadfareed.retrodealsdemo.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.asadfareed.retrodealsdemo.model.Deal
import com.example.asadfareed.retrodealsdemo.viewModel.DealsViewModel
import com.example.asadfareed.retrodealsdemo.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: DealsViewModel
    private var index:Int=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(DealsViewModel::class.java)
        btn_fetch.setOnClickListener {
            viewModel.getDeals()
                .observe(this, Observer(function = fun(dealsList: ArrayList<Deal>?) {
                    dealsList?.let {
                       // Toast.makeText(this,"size : "+dealsList.size,Toast.LENGTH_LONG).show()
                        fetchDeal(dealsList,index)
                        if (index==0){
                            index++
                        }else{
                            index=0
                        }
                    }
                }))
        }
    }

    private fun fetchDeal(dealsList: ArrayList<Deal>, index: Int) {
        val sb = StringBuilder()
        sb.append(dealsList.get(index).id)
        sb.append("\n" + dealsList.get(index).restaurant_name)
        sb.append("\n" + dealsList.get(index).restaurant_id)
        var deal = sb
        tv_details.text = deal

        sb.clear()
        sb.append(dealsList.get(index).address.display_address)
        sb.append("\n" + dealsList.get(index).address.area)
        sb.append("\n" + dealsList.get(index).address.longitude)
        sb.append("\n" + dealsList.get(index).address.latitude)
        sb.append("\n" + dealsList.get(index).address.business_contact)
        sb.append("\n" + dealsList.get(index).address.business_contact_1)
        sb.append("\n" + dealsList.get(index).address.business_email)
        deal = sb
        tv_details_address.text = deal

        sb.clear()
        for (i in 1..dealsList.get(index).cuisines.size) {
            sb.append(dealsList.get(index).cuisines[i - 1]+"\n")
        }
        deal = sb
        tv_details_cuisines.text = deal

        sb.clear()
        sb.append(dealsList.get(index).id)
        sb.append("\n" + dealsList.get(index).created_on)
        sb.append("\n" + dealsList.get(index).updated_on)
        deal = sb
        tv_details_extra.text = deal
        // Toast.makeText(this,"deal : "+deal,Toast.LENGTH_LONG).show()
    }
}
