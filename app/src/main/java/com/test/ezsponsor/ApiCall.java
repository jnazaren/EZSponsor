package com.test.ezsponsor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


public class ApiCall extends AppCompatActivity {

    TextView messageTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api_call);
        String message = getIntent().getStringExtra("sender");
        messageTextView = (TextView)findViewById(R.id.textView);
        messageTextView.setText(message);
    }
}
