package com.example.manan.javaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.barteksc.pdfviewer.PDFView;

public class Content extends AppCompatActivity {

    private String lecture;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        PDFView pdfView = findViewById(R.id.pdfView);
        Button backBtn = findViewById(R.id.backBtn);

        // Get lecture from intent
        lecture = getIntent().getStringExtra("lecture");

        // Load lecture pdf file
        pdfView.fromAsset(lecture).load();

        // Set back button onclicklistener
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Home.class);
                startActivity(intent);
            }
        });
    }
}
