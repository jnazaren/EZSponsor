package com.test.ezsponsor;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Donation2 extends AppCompatActivity {

    private String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation2);
        final Button button = (Button) findViewById(R.id.amount);
        final String message = getIntent().getStringExtra("sender");
        Log.i("Hi", "HELLO");
        Log.i("passed", message);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(message));
                startActivity(i);
            }
        });
    }

}
