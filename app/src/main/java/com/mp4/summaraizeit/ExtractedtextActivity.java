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


public class ExtractedtextActivity extends AppCompatActivity {
Button button_copy, button_summarize;
EditText textview_data;
String scanned_text;
String extracted;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extractedtext);
        button_copy = findViewById(R.id.button_copy);
        button_summarize =  findViewById(R.id.button_summarize);
        textview_data= findViewById(R.id.text_data);
        Intent intent = getIntent();
        extracted = intent.getStringExtra("extracted");
        extracted = extracted.replace("\n", " ").replace("\r", " ");
        extracted = extracted.replaceAll("^\"|\"$", "");
        extracted = extracted.replaceAll("^\"|\"$", "");
        textview_data.setText(extracted);


        String url = "https://extractiveSummary.shivanisorte.repl.co";



        button_copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanned_text = textview_data.getText().toString();
                copyToClipboard(scanned_text);
            }
        });

        button_summarize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HashMap<String, Object> params = new HashMap<>();
                params.put("1", extracted);

                StringRequest request = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            public void onResponse(String response) {
                                Intent intent = new Intent(ExtractedtextActivity.this, SummaryActivity.class);
                                intent.putExtra("summary", response.toString());
                                startActivity(intent);
//                                textview_data.setText(response);
                                Log.e("TAG", "onResponse:dhiosd;hahasdhasdhojasdj;asdj;asdj;o"+response);
//                        Toast.makeText(ExtractedtextActivity.this, response.d, Toast.LENGTH_SHORT).show();

                            }
                        },
                        new Response.ErrorListener() {
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(ExtractedtextActivity.this, "Error", Toast.LENGTH_SHORT).show();
                            }
                        }
                ) {
                    public byte[] getBody() {
                        return new JSONObject(params).toString().getBytes();
                    }
                    public String getBodyContentType() {
                        return "application/json";
                    }
                };

                Volley.newRequestQueue(ExtractedtextActivity.this).add(request);

            }
        });


    }

    private void copyToClipboard(String text){
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Copied data", text);
        clipboard.setPrimaryClip((clip));
        Toast.makeText(ExtractedtextActivity.this, "Copied to clipboard", Toast.LENGTH_SHORT).show();
    }







}