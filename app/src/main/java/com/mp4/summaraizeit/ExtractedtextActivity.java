package com.mp4.summaraizeit;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.IOException;

public class ExtractedtextActivity extends AppCompatActivity {
Button button_copy;
TextView textview_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extractedtext);
        button_copy = findViewById(R.id.button_copy);
        textview_data= findViewById(R.id.text_data);
        Intent intent = getIntent();
        String extracted = intent.getStringExtra("extracted");
        textview_data.setText(extracted);


        button_copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String scanned_text = textview_data.getText().toString();
                copyToClipboard(scanned_text);
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