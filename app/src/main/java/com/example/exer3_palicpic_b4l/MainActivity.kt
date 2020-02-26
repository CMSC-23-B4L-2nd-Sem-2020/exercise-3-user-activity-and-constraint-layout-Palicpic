package com.example.exer3_palicpic_b4l

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import kotlin.math.abs

class MainActivity : AppCompatActivity() {

    private var clickCounter = 0        //declare the clickCounter and initialize to zero

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //attach the click listener to the DONE Button to add the name
        findViewById<Button>(R.id.done_button).setOnClickListener{
            addName(it)
        }

        //attach a click listener to the name_text to update the name
        findViewById<TextView>(R.id.name_text).setOnClickListener {
            updateName(it)
        }

        //listener on the text view if tapped
        setListenersTextView()

        //attach the click listener to the retry button to reset the game
        findViewById<Button>(R.id.retry_button).setOnClickListener{
            retry()
        }
    }

    //function to add name
    private fun addName (view: View){
        //declarations to get the reference to the name_edit edit text and name_text text view
        val editText = findViewById<EditText>(R.id.name_edit)
        val nameTextView = findViewById<TextView>(R.id.name_text)

        //set the nameTextView to the input of the user from editText
        nameTextView.text = editText.text

        //set the button and edit text visibility to gone
        editText.visibility = View.GONE
        view.visibility = View.GONE

        //set the nameTextView visibility to visible
        nameTextView.visibility = View.VISIBLE

        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    //function to update the name
    private fun updateName(view: View){
        //declarations to get the reference to the name_edit and done button
        val editText = findViewById<EditText>(R.id.name_edit)
        val doneButton = findViewById<Button>(R.id.done_button)

        //update the visibility of name_edit, button to visible and name_text to gone
        editText.visibility = View.VISIBLE
        doneButton.visibility = View.VISIBLE
        view.visibility = View.GONE

        // Set the focus to the edit text.
        editText.requestFocus()

        // Show the keyboard.
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(editText, 0)
    }

    //function to count the click
    private fun clickCount(){
        val counter = findViewById<TextView>(R.id.num_of_click)
        clickCounter++
        counter.text = clickCounter.toString()
    }

    //function to get the id
    private fun getIdTextView(x:Int, y:Int): Int {
        //make a list of list of text view
        val list: List<List<Int>> = listOf(
            listOf(
                R.id.box_one,
                R.id.box_two,
                R.id.box_three,
                R.id.box_four,
                R.id.box_five
            ),
            listOf(
                R.id.box_six,
                R.id.box_seven,
                R.id.box_eight,
                R.id.box_nine,
                R.id.box_ten
            ),
            listOf(
                R.id.box_eleven,
                R.id.box_twelve,
                R.id.box_thirteen,
                R.id.box_fourteen,
                R.id.box_fifteen
            ),
            listOf(
                R.id.box_sixteen,
                R.id.box_seventeen,
                R.id.box_eighteen,
                R.id.box_nineteen,
                R.id.box_twenty
            ),
            listOf(
                R.id.box_twenty_one,
                R.id.box_twenty_two,
                R.id.box_twenty_three,
                R.id.box_twenty_four,
                R.id.box_twenty_five
            )
        )
        return list[x][y]
    }

    //function to set listener in the text view if tapped
    private fun setListenersTextView() {
        //declaration of mutable list of clickable text views
        val clickableViews: MutableList<View> = mutableListOf()

        //add the text views to the mutable list
        for (i in 0..4){
            for (j in 0..4){
                clickableViews.add(findViewById(getIdTextView(i,j)))
            }
        }

        //assign a click listener to each view
        for (item in clickableViews) {
            item.setOnClickListener { makeSwitch(it) }
        }
    }

    //function to switch the background of text views
    private fun makeSwitch(view: View){
        //get the coordinates
        val status = view.tag.toString().split("-")
        val x = status[1].toInt()
        val y = status[2].toInt()

        if(x in 1..3 && y in 1..3){ //first case middle of 5x5 box
            switch(view)
            switch(findViewById<TextView>(getIdTextView(x+1, y)))
            switch(findViewById<TextView>(getIdTextView(x, y-1)))
            switch(findViewById<TextView>(getIdTextView(x-1, y)))
            switch(findViewById<TextView>(getIdTextView(x, y+1)))
        }else if(abs(x-y) in 1..3){     //second case side of 5x5 boxes
            if(y == 0){     //left side
                switch(view)
                switch(findViewById<TextView>(getIdTextView(x+1, y)))
                switch(findViewById<TextView>(getIdTextView(x-1, y)))
                switch(findViewById<TextView>(getIdTextView(x, y+1)))
            }else if(x == 0){   //top
                switch(view)
                switch(findViewById<TextView>(getIdTextView(x+1, y)))
                switch(findViewById<TextView>(getIdTextView(x, y-1)))
                switch(findViewById<TextView>(getIdTextView(x, y+1)))
            }else if(x == 4){   //bottom
                switch(view)
                switch(findViewById<TextView>(getIdTextView(x, y-1)))
                switch(findViewById<TextView>(getIdTextView(x-1, y)))
                switch(findViewById<TextView>(getIdTextView(x, y+1)))
            }else if(y == 4){   //right
                switch(view)
                switch(findViewById<TextView>(getIdTextView(x+1, y)))
                switch(findViewById<TextView>(getIdTextView(x, y-1)))
                switch(findViewById<TextView>(getIdTextView(x-1, y)))
            }
        }else{  //edges
            if(x == 0 && y == 0){       //top left
                switch(view)
                switch(findViewById<TextView>(getIdTextView(x+1, y)))
                switch(findViewById<TextView>(getIdTextView(x, y+1)))
            }else if(x == 0 && y == 4){     //top right
                switch(view)
                switch(findViewById<TextView>(getIdTextView(x+1, y)))
                switch(findViewById<TextView>(getIdTextView(x, y-1)))
            }else if(x == 4 && y == 0){     //bottom left
                switch(view)
                switch(findViewById<TextView>(getIdTextView(x-1, y)))
                switch(findViewById<TextView>(getIdTextView(x, y+1)))
            }else if(x == 4 && y == 4){     //bottom right
                switch(view)
                switch(findViewById<TextView>(getIdTextView(x, y-1)))
                switch(findViewById<TextView>(getIdTextView(x-1, y)))
            }
        }
        clickCount()    //update the click counter

        //check if the user solve the game
        if(completeChecker()){
            for(i in 0..4){
                for(j in 0..4){
                    findViewById<TextView>(getIdTextView(i,j)).visibility = View.GONE   //hide the boxes
                }
            }
            findViewById<LinearLayout>(R.id.clicks).visibility = View.GONE      //hide the click counter
            findViewById<TextView>(R.id.congratulations).visibility = View.VISIBLE  //put congratulations message
        }
    }

    //function to change the background the text view
    private fun switch(view: View){
        val status = view.tag.toString().split("-")
        val flag = status[0]
        val x = status[1]
        val y = status[2]

        if (flag == "star") {
            view.setBackgroundResource(R.drawable.heart)
            view.tag = "heart-${x}-${y}"    //update the tag
        }else{
            view.setBackgroundResource(R.drawable.stars)
            view.tag = "star-${x}-${y}"     //update the tag
        }
    }

    //function to check if the user solve the game
    private fun completeChecker(): Boolean {
        var complete = true

        for(i in 0..4){
            for(j in 0..4){
                if(findViewById<TextView>(getIdTextView(i,j)).tag.toString() == "star-${i}-${j}"){
                    complete = false
                    break
                }
            }
        }
        return complete
    }

    //function to retry button
    private fun retry(){
        clickCounter = 0    //set the click counter to 0
        val counter = findViewById<TextView>(R.id.num_of_click)
        counter.text = clickCounter.toString()
        findViewById<LinearLayout>(R.id.clicks).visibility = View.VISIBLE
        findViewById<TextView>(R.id.congratulations).visibility = View.GONE
        for(i in 0..4){
            for(j in 0..4){
                findViewById<TextView>(getIdTextView(i,j)).tag = "star-${i}-${j}"
                findViewById<TextView>(getIdTextView(i,j)).setBackgroundResource(R.drawable.stars)
                findViewById<TextView>(getIdTextView(i,j)).visibility = View.VISIBLE
            }
        }
    }


    //picture references
    //marble
        //https://www.google.com/search?q=high+resolution+1080p+white+marble&tbm=isch&hl=en&hl=en&ved=2ahUKEwj-5_n_i-znAhWOk0sFHdl4CZ8QrNwCKAB6BAgBEEg&biw=1519&bih=702#imgrc=0lAKKYzBH_5KRM
    //heart
        //https://www.google.com/search?q=heart&tbm=isch&hl=en&chips=q:heart,g_1:love:j7CmL3_BVNM%3D&hl=en&ved=2ahUKEwiNp8y4wOznAhVZOCsKHXAjDBUQ4lYoAXoECAEQFw&biw=1519&bih=767#imgrc=3bD2fI_4fDjZDM
    //star
        //https://www.google.com/search?q=twinkle+gifpink&tbm=isch&ved=2ahUKEwiWouyMiuznAhXXEnIKHQFkDTYQ2-cCegQIABAA&oq=twinkle+gifpink&gs_l=img.3...14657.15417..15736...0.0..0.170.428.3j1......0....1..gws-wiz-img.Tx-1AA6de7g&ei=JL9UXpaAINelyAOByLWwAw&bih=702&biw=1536#imgrc=4hIvRCoPiHFuKM
}
