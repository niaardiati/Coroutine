package com.example.coroutinedrummachine

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {

    private lateinit var labbaikk: MediaPlayer
    private lateinit var syukronn: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        labbaikk = MediaPlayer.create(this, R.raw.labbaik)
        syukronn = MediaPlayer.create(this, R.raw.syukron)

        btnStart.setOnClickListener {
            runBlocking {
                launch { playBeats("x-x-x-x-x-x-x-x-x-x-x-x-", R.raw.syukron)
                }
                playBeats("x-----x-----x-----x-----", R.raw.labbaik)
          }
        }


    }
    suspend fun playBeats(beats: String, fileId: Int){
        val parts = beats.split("x")
        var count = 0
        for(part in parts){
            count += part.length + 1
            if(part == "") {
                if (fileId == R.raw.labbaik)
                    labbaikk.start()
             else
                syukronn.start()
        }else {
            delay(1000 * (part.length + 1L))
            if (count < beats.length) {
                if (fileId == R.raw.labbaik)
                    labbaikk.start()
                else
                    syukronn.start()
            }
        }
    }
}

    override fun onStop() {
        super.onStop()
        labbaikk.stop()
        syukronn.stop()
    }
}


