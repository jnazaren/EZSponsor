package com.test.ezsponsor;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

public class DonationInfo extends AppCompatActivity {

    private ListView list;

    String [] maintitle = {
        "Education",
        "1 month of food",
        "Medical"
    };

    String [] subtitle = {
        "$400 raised of $1,500 goal\n150 donors",
        "$23 raised of $50 goal\n60 donors",
        "$75 raised of $100 goal\n51 donors"
    };

    Integer [] progress = {25, 48, 75};

    Integer [] imgid = {
        R.drawable.ic_notifications_black_24dp,
        R.drawable.ic_notifications_black_24dp,
        R.drawable.ic_notifications_black_24dp
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        CustomListView adapter=new CustomListView(this, maintitle, subtitle, imgid, progress);
        list=(ListView)findViewById(R.id.listView);
        list.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
