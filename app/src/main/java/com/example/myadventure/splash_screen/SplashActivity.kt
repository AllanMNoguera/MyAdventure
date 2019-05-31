package com.example.myadventure.splash_screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myadventure.R
import kotlinx.coroutines.*
import java.util.concurrent.TimeUnit
import com.example.myadventure.MainActivity
import android.content.Intent



class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()
    }

    override fun onResume() {
        super.onResume()
        CoroutineScope(Dispatchers.IO).launch {
            delay(TimeUnit.SECONDS.toMillis(1))
            withContext(Dispatchers.Main) {
                val intent = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}
