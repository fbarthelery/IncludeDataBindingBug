package com.geekorum.includedatabindingbug

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun startMaterialCardViewActivity(view: View) {
        val intent = Intent(this, MaterialCardViewActivity::class.java)
        startActivity(intent)
    }

    fun startCardViewActivity(view: View) {
        val intent = Intent(this, CardViewActivity::class.java)
        startActivity(intent)
    }

    fun startCustomViewGroupActivity(view: View) {
        val intent = Intent(this, CustomViewGroupActivity::class.java)
        startActivity(intent)
    }
}
