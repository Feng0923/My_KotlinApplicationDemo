package com.example.administrator.my_kotlinapplication.CardViewDemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import com.example.administrator.my_kotlinapplication.R
import kotlinx.android.synthetic.main.activity_card_view_demo.*

class CardViewDemoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_view_demo)
        initView()
    }

    private fun initView() {

        sb_jianju.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                mCardView.setContentPadding(progress,progress,progress,progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })
    }
}
