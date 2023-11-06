package com.comu.android.secretmemo

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.widget. TextView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent(this, MemoActivity::class.java)

        findViewById<Button>(R.id.main_startButton).setOnClickListener{
            startActivity(intent)
            this.finish()
        }
    }
}