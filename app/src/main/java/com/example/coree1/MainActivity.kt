package com.example.coree1


import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView



import androidx.appcompat.app.AppCompatActivity



class MainActivity : AppCompatActivity() {



    override fun onStart() {
        super.onStart()
        Log.i("LIFECYCLE", "onStart")
    }


    var increment_number = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        Log.i("LIFECYCLE", "onCreate")

        //connecting xml view with java view variables
        val score = findViewById<Button>(R.id.score);
        val steal = findViewById<Button>(R.id.steal);
        val reset = findViewById<Button>(R.id.reset);
        val result = findViewById<TextView>(R.id.result);

        Log.i("LIFECYCLE", "" + increment_number);

        // Setting the textview to last stored value so that on rotation we can see that stored value
        result.text = increment_number.toString()

        check(increment_number)

        score.setOnClickListener {
            if (increment_number in 0..14) {
                increment_number++
                result.text = increment_number.toString()
                check(increment_number)
            }

        }


        steal.setOnClickListener {
            if (increment_number > 0) {
                increment_number--
                result.text = increment_number.toString()
                check(increment_number)


            }

        }

        reset.setOnClickListener {
            increment_number = 0
            result.text = increment_number.toString()
            check(increment_number)
        }
    }

    private fun check(increment_number: Int) {

         if (increment_number == 15) {
            val mediaPlayer = MediaPlayer.create(this, R.raw.bugle_tune) //audio
            mediaPlayer.start()
        }
        else if (increment_number >= 10) {
            findViewById<TextView>(R.id.result).setTextColor(Color.GREEN) }
        else if (increment_number >= 5) {
            findViewById<TextView>(R.id.result).setTextColor(Color.BLUE)
        } else if (increment_number >= 5) {
            findViewById<TextView>(R.id.result).setTextColor(Color.BLUE)

        } else {
            findViewById<TextView>(R.id.result).setTextColor(Color.BLACK)
        }
    }

    //Use onSaveInstanceState(Bundle) and onRestoreInstanceState
    override fun onSaveInstanceState(savedInstanceState: Bundle) {

        // Save UI state changes to the savedInstanceState.
        // This bundle will be passed to onCreate if the process is
        // killed and restarted.
        super.onSaveInstanceState(savedInstanceState)
        savedInstanceState.putInt("MyInt", increment_number)
    }

    //onRestoreInstanceState
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        // Restore UI state from the savedInstanceState.
        increment_number = savedInstanceState.getInt("MyInt")
        findViewById<TextView>(R.id.result).text = increment_number.toString()
        check(increment_number)
    }


}
