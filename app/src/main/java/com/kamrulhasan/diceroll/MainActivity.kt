package com.kamrulhasan.diceroll

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    val TAG = "mainactivity"

    private lateinit var btn_dice_roll: Button
    private lateinit var btn_reset: Button
    private lateinit var btnRules: Button
    private lateinit var tv_massage: TextView
    private lateinit var tv_score_board: TextView
    private lateinit var iv_image: ImageView
    private lateinit var parentLayout: ConstraintLayout
    private lateinit var builder: AlertDialog.Builder
    private lateinit var btnExit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        parentLayout = findViewById(R.id.lo_parent)
        tv_massage = findViewById(R.id.tv_welcome_massage)
        btnRules = findViewById(R.id.btn_rules)
        iv_image = findViewById(R.id.iv_dice_image)
        btn_dice_roll = findViewById(R.id.btn_roll)
        tv_score_board = findViewById(R.id.tv_score)
        btn_reset = findViewById(R.id.btn_reset)
        btnExit = findViewById(R.id.btn_exit)

        //redirect rules page
        btnRules.setOnClickListener(View.OnClickListener {
            //startActivity(Intent(this, GameRules::class.java))
            val mgs = getString(R.string.tv_rules)
            Log.d(TAG, "onCreate: mgs: $mgs" )
            openDialog("Game Rules", mgs)
        })

        var score: Int = 0
        var rolledTime: Int = 0

        val dice = Dice()
        // roll button
        btn_dice_roll.setOnClickListener(View.OnClickListener {
            if (btn_dice_roll.text.toString() == "roll") {
                val result = dice.roll().toString()
                rolledTime += 1
                if (result == "1") {
                    iv_image.setImageResource(R.drawable.dice_1)
                    score += 1
                } else if (result == "2") {
                    iv_image.setImageResource(R.drawable.dice_2)
                    score += 2
                } else if (result == "3") {
                    iv_image.setImageResource(R.drawable.dice_3)
                    score += 3
                } else if (result == "4") {
                    iv_image.setImageResource(R.drawable.dice_4)
                    score += 4
                } else if (result == "5") {
                    iv_image.setImageResource(R.drawable.dice_5)
                    score += 5
                } else if (result == "6") {
                    iv_image.setImageResource(R.drawable.dice_6)
                    score += 6
                }
                tv_score_board.text = "Rolled ${rolledTime} Times\nScore: ${score}"


                // check if wine or loss
                if (rolledTime == 10) {

                    if (score >= 35) {

                        openDialog(
                            "'''''..HURRAY..'''''",
                            "Game is over.\nYou Are Winner..!\nYour score is ${score}"
                        )
                    } else {
                        openDialog(
                            "'''''..SORRY..'''''",
                            "Game is over.\nYou Are Loser..!\nYour score is ${score}"
                        )
                    }
                    score = 0
                    rolledTime = 0
                    btn_dice_roll.text = "start"
                    tv_score_board.text = getString(R.string.tv_rolling_time_title)
                }
            }
            else{

                tv_massage.text = "Game is running..."
                btn_dice_roll.text = "roll"
                showSnackbar("You can play Now")

            }

        })

        btn_reset.setOnClickListener(View.OnClickListener {
            tv_massage.text = getString(R.string.tv_welcome_massage)
            iv_image.setImageResource(R.drawable.img)
            tv_score_board.text = getString(R.string.tv_score)
            score = 0
            rolledTime = 0
            btn_dice_roll.text = getString(R.string.btn_title)

        })
        btnExit.setOnClickListener(View.OnClickListener {
            finish()
            //system.destry()
        })
    }

    private fun openDialog(title: String, massage: String) {
        //tv_massage.text = "it works"
        //showSnackbar("You can play again.")
        builder = AlertDialog.Builder(this)
        builder.setTitle(title)
            .setMessage(massage)
            .setCancelable(true)
            .setNeutralButton("ok"){dialogInterface, it->
                showSnackbar("You can play again")

//                if(title != "Game Rules")
//                    tv_score_board.text = getString(R.string.tv_rolling_time_title)
//
                dialogInterface.cancel()
            }
            .show()


//        MaterialAlertDialogBuilder(this)
//            .setTitle(title)
//            .setMessage(massage)
//            .setNeutralButton(
//                "ok"
//            ) { dialog, which ->
//                showSnackbar("You can play again.")
//            }
    }

    private fun showSnackbar(massage: String) {
        Snackbar.make(
            parentLayout,
            massage,
            Snackbar.LENGTH_SHORT
        )
            .setAction("Action", null)
            .show()
    }


}

class Dice {
    fun roll(): Int {
        return (1..6).random()
    }
}