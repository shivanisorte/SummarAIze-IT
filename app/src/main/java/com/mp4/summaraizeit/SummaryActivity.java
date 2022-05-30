package com.mp4.summaraizeit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class SummaryActivity extends AppCompatActivity {
    Button button_copy, button_goback;
    EditText textview_data;
    String scanned_text;
    String summary;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        button_copy = findViewById(R.id.button_copy);
        button_goback =  findViewById(R.id.button_goback);
        textview_data= findViewById(R.id.text_data);
        Intent intent = getIntent();
        summary = intent.getStringExtra("summary");
        textview_data.setText(summary);



        button_copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanned_text = textview_data.getText().toString();
                copyToClipboard(scanned_text);
            }
        });

        button_goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchActivity= new Intent(SummaryActivity.this,MainActivity.class);
                startActivity(launchActivity);

            }
        });


    }

    private void copyToClipboard(String text){
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Copied data", text);
        clipboard.setPrimaryClip((clip));
        Toast.makeText(SummaryActivity.this, "Copied to clipboard", Toast.LENGTH_SHORT).show();
    }





}