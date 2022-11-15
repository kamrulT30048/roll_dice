package com.kamrulhasan.diceroll

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class GameRules : AppCompatActivity() {
    private  lateinit var btn_close: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_rules)
        btn_close = findViewById(R.id.btn_close)

        btn_close.setOnClickListener(View.OnClickListener {
            finish()
            startActivity(Intent(this,MainActivity::class.java))
        })
    }
}