package com.example.minesweeperproject

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_custom_board.*
import java.lang.Integer.parseInt

class CustomBoard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_board)


        var intent = getIntent()
        var height1 = intent.getIntExtra("height", 8)
        var width2 = intent.getIntExtra("width", 8)
        var mines45 = intent.getIntExtra("mines", 9)



    //all these are edit text
        val height9:EditText=findViewById(R.id.hi)
        val width9:EditText=findViewById(R.id.wi)
        val mines9:EditText=findViewById(R.id.mi)




        submit.setOnClickListener {
            //after clicking submit button, this will tranfser or pass the value which the user has entered
            var height5=parseInt(height9.text.toString())
            var width5= parseInt(width9.text.toString())
            var mines5= parseInt(mines9.text.toString())

            val intent= Intent(this, Gameplay_Main::class.java).apply{
                putExtra("height",height5)  //put the value
                putExtra("width",width5)
                putExtra("mines",mines5)
                // putExtra("player_name",player_name)
            }
            startActivity(intent)
        }
    }

}