package com.example.minesweeperproject

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.SystemClock
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_gameplay__main.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlin.random.Random

class Gameplay_Main : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gameplay__main)

        //Passed value from MainActivity.kt and CustomBoard.kt are passed here to intent
        var intent = getIntent()
        var height = intent.getIntExtra("height", 8)
        var width = intent.getIntExtra("width", 8)
        var mines = intent.getIntExtra("mines", 9)
        //   var player_name=intent.getStringExtra("player_name")

            //edit text option for user to write his/her name
        val nameplayer:EditText=findViewById(R.id.nameplayer)
        var player_name = nameplayer.text.toString()

        //setMine function is called and then it will return mutableSetOf array which carries all the id of mines
        var toalmines1 = setMine(mines, height)

        //setupBoard function is called to make the board
        setupBoard(height, width, mines, player_name, toalmines1)

        //this reflect on the screeen, how many mines are left
        mines_left.text=mines.toString()

    }

        fun setMine(mines: Int, height: Int): MutableSet<Int> {
            //this is setMine function, in this random values are being choosen for mines.id and then it is being saved in mutableSetOf<int>
            var totalmines = mutableSetOf<Int>()
            var mines1 = mines
            while (mines1 != 0) {
                val myrandomno = Random.nextInt(height * height)
                if (totalmines.contains(myrandomno)) {
                    continue;
                } else {
                    totalmines.add(myrandomno)
                    mines1--
                }

            }
            return totalmines
        }

        fun setupBoard(height: Int, width: Int, mines: Int,player_name: String,totalmines2:MutableSet<Int>) {
            //counter varible is used for giving id to each button uquinely
            var counter = 1
            var minestoset = mines

            //below we are making linear layout for buttons
            val params1 = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                0
            )
            val params2 = LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.MATCH_PARENT
            )

            for (i in 1..height) {
                val linearLayout = LinearLayout(this)
                linearLayout.orientation = LinearLayout.HORIZONTAL
                linearLayout.layoutParams = params1
                params1.weight = 1.0F

                for (j in 1..width) {
                    val button = Button(this)

                    button.id = counter
                    button.textSize = 18.0F
                    button.setTextColor(ContextCompat.getColor(this, R.color.colorAccent))

                    button.layoutParams = params2
                    params2.weight = 1.0F

                    button.setOnClickListener {
                        //if user click the button, then that button changed it drawable
                        button.setBackgroundResource(R.drawable.safe)
                        //and then record move function is called for futher actions
                         recordMove(it,height,player_name,totalmines2,minestoset)
                    }
                    button.setOnLongClickListener {
                        //is the user long click
                        //this snipet is for flag option in the game

                        if(totalmines2.contains(button.id))
                        {//is the button which is being clicked contains mine
                            minestoset--
                            mines_left.text=minestoset.toString()
                            button.setBackgroundResource(R.drawable.flag)
                            button.isEnabled=false
                            if(minestoset==0)
                            {
                                val elapsedTimer:Long=SystemClock.elapsedRealtime()-SystemClock.elapsedRealtime()
                                val sharedPref:SharedPreferences=getPreferences(Context.MODE_PRIVATE)
                                val lastTime:Int=elapsedTimer.toInt()

                                //then ending the game and calling and passing the values of Result_Main.kt class
                                val intent= Intent(this, Result_Main::class.java).apply{
                                    putExtra("player_name",player_name)
                                    putExtra("result","Congralutation \n Try Again")
                                   // putExtra("lasttime",lastTime)
                             }
                                startActivity(intent)
                            }
                        }
                        Toast.makeText(this, " ", Toast.LENGTH_SHORT).show()
                        return@setOnLongClickListener true
                    }
                    linearLayout.addView(button)
                    counter++
                    //counter++ for assigning differernt id for every button in game
                }
                board.addView(linearLayout)
            }

        }


//
//

        //
        private fun recordMove(view: View,height: Int,player_name:String,totalmines2: MutableSet<Int>,mines: Int) {
            //this is recordMove function

            val button = view as Button
            val id = button.id

            //checking if the button contains mines or not
            if (totalmines2.contains(id)) {
                //if it contains mines
                for(items in totalmines2) {
                    button.setBackgroundResource(R.drawable.bomb)
                }
                //game over and passing the values to Result_Main.kt class
                val intent= Intent(this, Result_Main::class.java).apply{
                    putExtra("player_name",player_name)
                    putExtra("result","You Lost \n Try Again")
                }
                startActivity(intent)

            } else {
                // if button doesn't contain the mine
                //disabling the button after being clicked
                button.isEnabled = false
                //changing the background of the sbutton
                button.setBackgroundResource(R.drawable.safe)

                //calling updateNeighbours button to check how many mines are there in the neighbour of that click button
                   var count= updateNeighbours(id,height,totalmines2)
                if(count==1)
                {
                    button.setBackgroundResource(R.drawable.one)
                }
                else if (count==2)
                {
                    button.setBackgroundResource(R.drawable.two)
                }
                else if(count==3)
                {
                    button.setBackgroundResource(R.drawable.three)
                }
                else if(count==4)
                {
                    button.setBackgroundResource(R.drawable.four)
                }
                else if(count==5)
                {
                    button.setBackgroundResource(R.drawable.five)
                }
                else if(count==6)
                {
                    button.setBackgroundResource(R.drawable.six)
                }
                else if(count==7)
                {
                    button.setBackgroundResource(R.drawable.seven)
                }
                else{
                    button.setBackgroundResource(R.drawable.three)

                }

                }
            }
        }


        private fun updateNeighbours(id: Int,height: Int,totalmines2: MutableSet<Int>):Int {
            //this funtion is basically just checking the neighbours if they contain the mines or not, if contain then count is incremented
            var count=0
            var heighttocheck=height

            if(totalmines2.contains(heighttocheck-1))
                {
                count++
                }
            if(totalmines2.contains(heighttocheck+1))
                {
                count++
                }
           if(totalmines2.contains((heighttocheck-(heighttocheck-1))))
                {
                    count++
                }
            if(totalmines2.contains((heighttocheck-(heighttocheck-2))))
                {
                    count++
                }
            if(totalmines2.contains((heighttocheck-(heighttocheck-3))))
                {
                    count++
                }
            if(totalmines2.contains((heighttocheck+(heighttocheck-1))))
                 {
                    count++
                 }
            if(totalmines2.contains((heighttocheck+(heighttocheck-2))))
                {
                  count++
                }
            if(totalmines2.contains((heighttocheck+(heighttocheck-3))))
                {
                 count++
                }

                return count
        }






