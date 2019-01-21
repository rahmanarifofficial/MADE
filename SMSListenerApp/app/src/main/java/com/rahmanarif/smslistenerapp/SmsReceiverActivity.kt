package com.rahmanarif.smslistenerapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_sms_receiver.*

class SmsReceiverActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_close -> finish()
        }
    }

    companion object {
        public val EXTRA_SMS_NO: String = "extra_sms_no"
        public val EXTRA_SMS_MESSAGE: String = "extra_sms_message"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sms_receiver)

        setTitle("Incoming Message")

        btn_close.setOnClickListener(this)

        var senderNo: String = intent.getStringExtra(EXTRA_SMS_NO)
        var senderMessage: String = intent.getStringExtra(EXTRA_SMS_MESSAGE)

        tv_no.setText(String.format("from : %s", senderNo))
        tv_message.setText(senderMessage)
    }
}
