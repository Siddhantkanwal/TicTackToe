package com.example.tictacktoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener{
    var PLAYER=true;
    var Turn_Count=0;
    var boardstatus=Array(3){IntArray(3)}
    lateinit var board : Array<Array<Button>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        board= arrayOf(
            arrayOf(Button1,Button2,Button3),
            arrayOf(Button4,Button5,Button6),
            arrayOf(Button7,Button8,Button9)
        )
        for(i in board){
            for (button in i){
                button.setOnClickListener(this)
            }
        }
        intializeBoardStatus()
        resetBtn.setOnClickListener(){
            PLAYER=true
            Turn_Count=0
            intializeBoardStatus()
        }
    }
    override fun onClick(view: View) {
        when(view.id){
            R.id.Button1->{
                updatevalue(row=0,column=0,player=PLAYER)
            }
            R.id.Button2->{
                updatevalue(row=0,column=1,player=PLAYER)
            }
            R.id.Button3-> {
                updatevalue(row=0,column=2,player=PLAYER)
            }
            R.id.Button4->{
                updatevalue(row=1,column=0,player=PLAYER)
            }
            R.id.Button5->{
                updatevalue(row=1,column=1,player=PLAYER)
            }
            R.id.Button6->{
                updatevalue(row=1,column=2,player=PLAYER)
            }
            R.id.Button7->{
                updatevalue(row=2,column=0,player=PLAYER)
            }
            R.id.Button8->{
                updatevalue(row=2,column=1,player=PLAYER)
            }
            R.id.Button9->{
                updatevalue(row=2,column=2,player=PLAYER)
            }
        }
        Turn_Count++
        PLAYER=!PLAYER
        if(PLAYER){
            updateDisplay("Player X turn")
        }
        else{
            updateDisplay("Player 0 turn")
        }
        if(Turn_Count==9){
            updateDisplay("Game Draw")
        }
        checkWinner()
    }

    private fun checkWinner() {
        for(i in 0..2){
            if(boardstatus[i][0]==boardstatus[i][1]&&boardstatus[i][0]==boardstatus[i][2]){
                if(boardstatus[i][0]==1){
                    updateDisplay("Player X is Winner")
                    break
                }
                else if(boardstatus[i][0]==0){
                    updateDisplay("Player 0 is Winner")
                    break
                }
            }
        }
        for(i in 0..2){
            if(boardstatus[0][i]==boardstatus[1][i]&&boardstatus[0][i]==boardstatus[2][i]){
                if(boardstatus[0][i]==1){
                    updateDisplay("Player X is Winner")
                    break
                }
                else if(boardstatus[0][i]==0){
                    updateDisplay("Player 0 is Winner")
                    break
                }
            }
        }
        if(boardstatus[0][0]==boardstatus[1][1]&&boardstatus[0][0]==boardstatus[2][2]){
            if(boardstatus[0][0]==1){
                updateDisplay("Player X is Winner")
            }
            else if(boardstatus[0][0]==0){
                updateDisplay("Player 0 is Winner")
            }
        }
        if(boardstatus[0][2]==boardstatus[1][1]&&boardstatus[0][2]==boardstatus[2][0]){
            if(boardstatus[0][2]==1){
                updateDisplay("Player X is Winner")
            }
            else if(boardstatus[0][2]==0){
                updateDisplay("Player 0 is Winner")
            }
        }

    }

    private fun updateDisplay(text: String) {
        displayTv.text=text
        if(text.contains("Winner")){
            diablebutton()
        }

    }

    private fun diablebutton() {
        for (i in board){
            for(button in i){
                button.isEnabled=false
            }
        }
    }

    private fun updatevalue(row: Int, column: Int, player: Boolean) {
        val text:String=if(player)"X" else "0"
        val value:Int= if(player) 1 else 0
        board[row][column].apply {
            isEnabled=false
            setText(text)
        }
        boardstatus[row][column]=value
    }

    private fun intializeBoardStatus() {
        for(i in 0..2){
            for(j in 0..2){
                boardstatus[i][j]=-1
                board[i][j].isEnabled=true
                board[i][j].text=""
            }
        }
    }

}