package com.example.puttyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId

class MainActivity : AppCompatActivity() {

    var TAG = "TOKEN"
    lateinit var btRefresh: Button
    lateinit var image: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUI()
        getFirebaseToken()

    }

    private fun initUI() {
        image = findViewById(R.id.image)
        btRefresh = findViewById(R.id.btRefresh)
        btRefresh.setOnClickListener {
            getFirebaseToken()
        }
        setImage()
    }

    private fun setImage(){
        if(intent != null){
        Toast.makeText(this, "first", Toast.LENGTH_SHORT).show()
            intent.extras?.let {
                Toast.makeText(this, "sec", Toast.LENGTH_SHORT).show()
                for (key in it.keySet()) {
                    val value = intent.extras?.get(key)
                    Toast.makeText(this, "key val:"+value, Toast.LENGTH_SHORT).show()
                    Log.d("INTENT PARAM", "Key: $key Value: $value")
                }
            }
        }

    }

    private fun getFirebaseToken() {
        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w(TAG, "getInstanceId failed", task.exception)
                    return@OnCompleteListener
                }

                // Get new Instance ID token
                val token = task.result?.token
                Log.d(TAG, token)
                // Log and toast
                /*val msg = getString(R.string.msg_token_fmt, token)
                Log.d(TAG, msg)
                Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()*/
            })
    }
}
