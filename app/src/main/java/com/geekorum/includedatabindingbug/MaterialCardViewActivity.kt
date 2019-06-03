package com.geekorum.includedatabindingbug

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.geekorum.includedatabindingbug.databinding.ActivityMaterialCardViewBinding

class MaterialCardViewActivity : AppCompatActivity() {

    lateinit var binding: ActivityMaterialCardViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_material_card_view)
    }
}
