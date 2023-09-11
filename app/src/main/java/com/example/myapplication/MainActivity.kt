package com.example.myapplication

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import java.util.*
import androidx.appcompat.app.AppCompatActivity
import android.app.AlertDialog
import android.widget.TextView


class MainActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Find a specific view so as to alter some of its attributes later
        val whichPlayer = findViewById(R.id.playerturn) as TextView
        // Display the first player's turn
        whichPlayer.text="It's Player X's turn"
    }
    public fun btnTicTac(view: View){
        val btnSelected = view as Button
        var cellID= 0
        /*Determine which box has been clicked and assign it a number based on its position in the
         grid*/
        when(btnSelected.id){
            R.id.btnTicTac1 -> cellID=1
            R.id.btnTicTac2 -> cellID=2
            R.id.btnTicTac3 -> cellID=3
            R.id.btnTicTac4 -> cellID=4
            R.id.btnTicTac5 -> cellID=5
            R.id.btnTicTac6 -> cellID=6
            R.id.btnTicTac7 -> cellID=7
            R.id.btnTicTac8 -> cellID=8
            R.id.btnTicTac9 -> cellID=9
        }
        //   Toast.makeText(this, "ID: "+cellID, Toast.LENGTH_SHORT).show()
        GameLogic(cellID, btnSelected)
    }
    public fun btnRestart(view: View){
        startActivity(Intent(this, MainActivity::class.java))
    }
    var Player1= ArrayList<Int>()
    var Player2= ArrayList<Int>()
    var ActivePlayer=1

    fun GameLogic(cellId:Int, btnSelected: Button){
        val whichPlayer = findViewById(R.id.playerturn) as TextView
        /* Determine what value to fill in the clicked box, add the box's unique assigned number
        to the corresponding player's resizable list (ArrayList), display who's turn it is to play
         next and finally change the current player */
        if (ActivePlayer == 1){
            btnSelected.text="X"
            btnSelected.setBackgroundColor(Color.RED)
            Player1.add(cellId)
            whichPlayer.text="It's Player O's turn"
            ActivePlayer=2
        }else {
            btnSelected.text="0"
            btnSelected.setBackgroundColor(Color.BLUE)
            Player2.add(cellId)
            whichPlayer.text="It's Player X's turn"
            ActivePlayer=1
        }
        btnSelected.isEnabled= false
        FindWinner()
    }
    // Determine if there is a winner and if so who the winner is
    fun FindWinner(){
        var winner= -1
        // row 1
        if (Player1.contains(1)&& Player1.contains(2)&& Player1.contains(3)){
            winner=1
        }
        if (Player2.contains(1)&& Player2.contains(2)&& Player2.contains(3)){
            winner=2
        }
        // row 2
        if (Player1.contains(4)&& Player1.contains(5)&& Player1.contains(6)){
            winner=1
        }
        if (Player2.contains(4)&& Player2.contains(5)&& Player2.contains(6)){
            winner=2
        }
        // row 3
        if (Player1.contains(7)&& Player1.contains(8)&& Player1.contains(9)){
            winner=1
        }
        if (Player2.contains(7)&& Player2.contains(8)&& Player2.contains(9)){
            winner=2
        }

        // col 1
        if (Player1.contains(1)&& Player1.contains(4)&& Player1.contains(7)){
            winner=1
        }
        if (Player2.contains(1)&& Player2.contains(4)&& Player2.contains(7)){
            winner=2
        }
        // col 2
        if (Player1.contains(2)&& Player1.contains(5)&& Player1.contains(8)){
            winner=1
        }
        if (Player2.contains(2)&& Player2.contains(5)&& Player2.contains(8)){
            winner=2
        }
        // col 3
        if (Player1.contains(3)&& Player1.contains(6)&& Player1.contains(9)){
            winner=1
        }
        if (Player2.contains(3)&& Player2.contains(6)&& Player2.contains(9)){
            winner=2
        }
        // Diagonal 1
        if (Player1.contains(1)&& Player1.contains(5)&& Player1.contains(9)){
            winner=1
        }
        if (Player2.contains(1)&& Player2.contains(5)&& Player2.contains(9)){
            winner=2
        }
        // Diagonal 2
        if (Player1.contains(3)&& Player1.contains(5)&& Player1.contains(7)){
            winner=1
        }
        if (Player2.contains(3)&& Player2.contains(5)&& Player2.contains(7)){
            winner=2
        }

        var gameDraw = -1
        // Check if all boxes have been clicked which would indicate that the game is a draw
        if ((Player1.contains(1)|| Player2.contains(1))&& (Player1.contains(2)|| Player2.contains(2))&& (Player1.contains(3)|| Player2.contains(3))&& (Player1.contains(4)|| Player2.contains(4))&& (Player1.contains(5)|| Player2.contains(5))&& (Player1.contains(6)|| Player2.contains(6))&& (Player1.contains(7)|| Player2.contains(7))&& (Player1.contains(8)|| Player2.contains(8))&& (Player1.contains(9)|| Player2.contains(9))){
            gameDraw=1
        }
        /* Create and show a dialog box the moment there is a winner or the game has been drawn,
        stop the gameplay and give an option to restart the game and make the dialog undismissable
         by touching outside it */
        if (winner != -1){
            val whichPlayer = findViewById(R.id.playerturn) as TextView
            whichPlayer.text=""
            if (winner==1){
                AlertDialog.Builder(this@MainActivity).setTitle("Game Over").setMessage("Player X has won!").setPositiveButton("Restart Game"){
                        dialog, which ->
                    startActivity(Intent(this, MainActivity::class.java))//start the root activity similar to starting the app afresh
                }.setCancelable(false).create().show()
            }
            else if(gameDraw != -1){
                AlertDialog.Builder(this@MainActivity).setTitle("Game Over").setMessage("The game has ended in a draw").setPositiveButton("Restart Game"){
                        dialog, which ->
                    startActivity(Intent(this, MainActivity::class.java))
                }.setCancelable(false).create().show()
            }
            else {
                AlertDialog.Builder(this@MainActivity).setTitle("Game Over").setMessage("Player O has won!").setPositiveButton("Restart Game"){
                        dialog, which ->
                    startActivity(Intent(this, MainActivity::class.java))
                }.setCancelable(false).create().show()
            }
        }
        if(gameDraw != -1&& winner == -1){
            val whichPlayer = findViewById(R.id.playerturn) as TextView
            whichPlayer.text=""
            AlertDialog.Builder(this@MainActivity).setTitle("Game Over").setMessage("The game has ended in a draw").setPositiveButton("Restart Game"){
                    dialog, which ->
                startActivity(Intent(this, MainActivity::class.java))
            }.setCancelable(false).create().show()
        }
    }

}
