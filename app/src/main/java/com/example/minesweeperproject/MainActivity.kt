package com.example.minesweeperproject

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import android.view.View
import android.widget.*

const val FAVORITE_KEY="FAVORITE_KEY"

class MainActivity : AppCompatActivity() {
   // val highscore=fillhighscoreData()
    override fun onCreate(savedInstanceState: Bundle?) {
       super.onCreate(savedInstanceState)
       setContentView(R.layout.activity_main)
       //  loadFavorites()
       val start: Button = findViewById<Button>(R.id.start)
       //start button to start the game
       val radio_group: RadioGroup = findViewById(R.id.radioGroup)
    //radio group , in this 4 radio button are there to choose from

        radio_group.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener { group, checkedId ->
                val radio: RadioButton = findViewById(checkedId)
            }
        )
        start.setOnClickListener {
            var id = radioGroup.checkedRadioButtonId
            if (id != -1) {
                //if the choice is not empty
                val radio: RadioButton = findViewById(id)
                Toast.makeText(this, "clicked ${radio.text}", Toast.LENGTH_SHORT).show()

              if(radio.text.equals("EASY"))
                {
                    //this is easy layout
                    val intent=Intent(this@MainActivity, Gameplay_Main::class.java).apply{
                        putExtra("height",5)  //put the value
                        putExtra("width",5)
                        putExtra("mines",5)
                       // putExtra("player_name",player_name)
                    }
                    startActivity(intent)

                }
                else if(radio.text.equals("MEDIUM"))
                {
                    //this is medium radio button program
                    val intent=Intent(this@MainActivity, Gameplay_Main::class.java).apply{
                        putExtra("height",7)  //put the value
                        putExtra("width",7)
                        putExtra("mines",20)
                      //  putExtra("player_name",player_name)
                    }
                    startActivity(intent)
                }
                else if(radio.text.equals("HARD"))
                {
                    //this is hard radio button program
                    val intent=Intent(this@MainActivity, Gameplay_Main::class.java).apply{
                        putExtra("height",9)  //put the value
                        putExtra("width",9)
                        putExtra("mines",40)
                       // putExtra("player_name",player_name)
                    }
                    startActivity(intent)
                }
                else if(radio.text.equals("CUSTOM"))
              {
                  //this is custom board radio button program
                  val intent=Intent(this@MainActivity, CustomBoard::class.java).apply{
                      putExtra("height",9)  //put the value
                      putExtra("width",9)
                      putExtra("mines",40)
                      // putExtra("player_name",player_name)
                  }
                  startActivity(intent)
              }
            }
            else
            {   //if the user clicks start without chooseing any radio option
                Toast.makeText(this, "Choose Again", Toast.LENGTH_SHORT).show()
            }
        }

    }

}
