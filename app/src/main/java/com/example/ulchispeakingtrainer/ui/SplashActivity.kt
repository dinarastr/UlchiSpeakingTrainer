package com.example.ulchispeakingtrainer.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.ulchispeakingtrainer.MainActivity
import com.example.ulchispeakingtrainer.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val vb: ActivitySplashBinding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(vb.root)

       /* val splashAnimation = AnimationUtils.loadAnimation(this,
            R.anim.anim_splash
        )
        vb.appName.animation = splashAnimation

        splashAnimation.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationRepeat(animation: Animation?) {

            }

            override fun onAnimationEnd(animation: Animation?) {*/
                Handler(Looper.getMainLooper()).postDelayed(
                    {
                        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                        finish()
                    }, 1000
                )
            }

           /* override fun onAnimationStart(animation: Animation?) {
            }

        })*/
    //}
}