package com.example.minesweeperproject

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_result__main.*

class Result_Main : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result__main)
//passed value are passed here from Gameplay_Main.kt
        var intent = getIntent()
        var showName = intent.getStringExtra("player_name")
        var showResult = intent.getStringExtra("result")
       // val time=intent.getIntExtra("lasttime",50)

        //showing the result of the game
        show_name.text=showName
        show_result.text=showResult
       // lastime1.text= time.toString()

        //home button to go to MainActivity.kt class again and play again
        home.setOnClickListener {
            Toast.makeText(this,"Thank You For Playing",Toast.LENGTH_SHORT).show()
            val intent= Intent(this, MainActivity::class.java).apply{

            }
            startActivity(intent)
        }


    }
}