package com.test.ezsponsor;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

public class CustomListView extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] maintitle;
    private final String[] subtitle;
    private final Integer[] imgid;
    private final Integer[] progress;
    private Boolean completed;

    CustomListView(Activity context, String[] maintitle, String[] subtitle, Integer[] imgid, Integer[] progress, Boolean completed) {
        super(context, R.layout.mylist, maintitle);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.maintitle=maintitle;
        this.subtitle=subtitle;
        this.imgid=imgid;
        this.progress = progress;
        this.completed = completed;

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.mylist, null,true);

        TextView titleText = (TextView) rowView.findViewById(R.id.title);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        TextView subtitleText = (TextView) rowView.findViewById(R.id.subtitle);
        ProgressBar progressBar = (ProgressBar) rowView.findViewById(R.id.progressBar);

        titleText.setText(maintitle[position]);
        imageView.setImageResource(imgid[position]);
        subtitleText.setText(subtitle[position]);
        progressBar.setMin(0);
        progressBar.setMax(100);
        progressBar.setProgress(progress[position]);

        final Button donateButton = (Button) rowView.findViewById(R.id.button);
        donateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, InteracActivity.class);
                context.startActivity(intent);
            }
        });

        if (this.completed) {
            final Button dButton = (Button) rowView.findViewById(R.id.button);
            dButton.setBackgroundColor(Color.parseColor("#3d961d"));
            dButton.setText("COMPLETED");
            dButton.setClickable(false);
        }

        return rowView;

    };
}