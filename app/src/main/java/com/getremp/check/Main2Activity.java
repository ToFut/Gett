package com.getremp.check;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    String source=null;
    String dest=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        if (getIntent().hasExtra("Source") &&getIntent().hasExtra("Dest")) {
            source = getIntent().getStringExtra("Source");
            dest=getIntent().getStringExtra("Dest");

        } else {
            throw new IllegalArgumentException("Activity cannot find  extras " );
        }
        TextView SourceTextView = (TextView) findViewById(R.id.SourceFromFirstAcitvity);
        SourceTextView.setText(source);
        TextView DestTextView = (TextView) findViewById(R.id.DestFromFirstAcitvity);
        DestTextView.setText(dest);



    }
}
