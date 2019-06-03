package com.geekorum.includedatabindingbug

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.geekorum.includedatabindingbug.databinding.ActivityCardViewBinding

class CustomViewGroupActivity : AppCompatActivity() {

    lateinit var binding: ActivityCardViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_custom_view)
    }
}
