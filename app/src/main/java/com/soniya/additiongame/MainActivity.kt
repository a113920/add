package com.soniya.additiongame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val score = findViewById(R.id.score) as TextView
        score.text = Integer.toString(0)

        replay(score)
    }

    //TextView ques = (TextView)findViewById(R.id.ques);
    var rand = Random()
    var v1 = 0
    var v2:Int = 0
    var rightAnswer:Int = 0
    var userAnswer:Int = 0
    var tQuestions:Int = 0
    var star = true


    fun nextQuestion() {
        val ques = findViewById(R.id.ques) as TextView
        val a = rand.nextInt(50)
        val b = rand.nextInt(50)
        v1 = a
        v2 = b
        rightAnswer = v1 + v2
        ques.text = Integer.toString(v1) + " + " + Integer.toString(v2) + " = ?"
        userAnswer = 0
    }

    fun buttonPressed(view: View) {
        //Button but =(Button) view;
        //int t = (int) but.getTag();
        val ques = findViewById(R.id.ques) as TextView
        var t = 0
        val id = view.id
        if (id == R.id.b1) t = 1
        if (id == R.id.b2) t = 2
        if (id == R.id.b3) t = 3
        if (id == R.id.b4) t = 4
        if (id == R.id.b5) t = 5
        if (id == R.id.b6) t = 6
        if (id == R.id.b7) t = 7
        if (id == R.id.b8) t = 8
        if (id == R.id.b9) t = 9
        if (id == R.id.b0) t = 0
        userAnswer = userAnswer * 10 + t
        ques.text = Integer.toString(v1) + " + " + Integer.toString(v2) + " = " + Integer.toString(userAnswer)
        if (userAnswer == rightAnswer) {
            nextQuestion()
            tQuestions++
            val score = findViewById(R.id.score) as TextView
            score.text = Integer.toString(tQuestions)
        }
    }

    fun backspace(view: View?) {
        val ques = findViewById(R.id.ques) as TextView
        userAnswer /= 10
        if (userAnswer == 0) ques.text = Integer.toString(v1) + " + " + Integer.toString(v2) + " = ?"
    }


    fun replay(view: View?) {
        userAnswer = 0
        tQuestions = 0
        val timer = findViewById(R.id.timer) as TextView
        val replayScreen = findViewById(R.id.replayScreen) as LinearLayout
        replayScreen.visibility = View.INVISIBLE
        object : CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timer.text = Integer.toString(millisUntilFinished.toInt() / 1000)
                if (star == true) {
                    nextQuestion()
                    star = false
                }
            }

            override fun onFinish() {
                val fScore = findViewById(R.id.fScore) as TextView
                fScore.text = Integer.toString(tQuestions)
                val replayScreen = findViewById(R.id.replayScreen) as LinearLayout
                replayScreen.visibility = View.VISIBLE
                val score = findViewById(R.id.score) as TextView
                score.text = Integer.toString(0)
            }
        }.start()
    }
}