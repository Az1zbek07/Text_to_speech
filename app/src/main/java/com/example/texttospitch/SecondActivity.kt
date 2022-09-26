package com.example.texttospitch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.tts.TextToSpeech
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.button.MaterialButton
import java.util.*
import kotlin.collections.ArrayList

class SecondActivity : AppCompatActivity() {
    private lateinit var btnSpeak: MaterialButton
    private lateinit var imageVoice: ImageView
    private lateinit var textTalk: TextView
    private lateinit var editText: EditText
    private var textToSpeech: TextToSpeech? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        speechToText1()
    }

    private fun speechToText1(){
        imageVoice = findViewById(R.id.imageVoice)
        textTalk = findViewById(R.id.textTalk)
        imageVoice.setOnClickListener {
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.US)
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "This is dialog..")
            try {
                startActivityForResult(intent, 100)
            } catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100){
            if (requestCode == RESULT_OK || data != null){
                val result: ArrayList<String> = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)!!
                textTalk.text = result[0]
                textToSpeech(result[0])
            }
        }
    }
    private fun textToSpeech(text: String){
        textToSpeech = TextToSpeech(this){ status ->
            if (status == TextToSpeech.SUCCESS){
                textToSpeech?.language = Locale.JAPANESE
                textToSpeech?.speak(text, TextToSpeech.QUEUE_FLUSH, null)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        textToSpeech = null
        textToSpeech?.stop()
        textToSpeech?.shutdown()
    }
}