package com.example.photoalbum

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isInvisible
import androidx.core.view.isVisible

class Photo : AppCompatActivity() {

    private lateinit var photoIM: ImageView
    private lateinit var nextBTN: Button
    private lateinit var prevBTN: Button

    private var index = 0
    private val photos = listOf(
        R.drawable.potter,
        R.drawable.weasley,
        R.drawable.grainger,
        R.drawable.neville,
        R.drawable.snappe
    )

    @SuppressLint("MissingInflatedId", "UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_photo)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        photoIM = findViewById(R.id.photoIV)
        nextBTN = findViewById(R.id.nextBTN)
        prevBTN = findViewById(R.id.prevBTN)

        nextBTN.setOnClickListener {
            when {
                index < photos.size - 1 -> {
                    index++
                    photoIM.setImageResource(photos[index])
                    prevBTN.isVisible = (index > 0)
                    if (index == 4) { nextBTN.text = "Завершить" }
                }

                else -> {
                    val intent = Intent(this, Finish::class.java)
                    startActivity(intent)
                }
            }
        }

        prevBTN.setOnClickListener {
            when {
                index < photos.size -> {
                    index--
                    photoIM.setImageResource(photos[index])
                    prevBTN.isInvisible = (index == 0)
                }
            }
        }
    }
}