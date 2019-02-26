package com.imdevil.remoteviews;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Switch;
import android.widget.TextView;

public class RecevierActivity extends AppCompatActivity {

    private Intent intent;
    private TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recevier);
        txt = findViewById(R.id.txt);
        intent = getIntent();
        switch (intent.getAction()) {
            case "Action_SIMPLE_OPEN":
                txt.setText("Action_SIMPLE_OPEN");
                break;
            case "Action_SIMPLE_DELETE":
                txt.setText("Action_SIMPLE_DELETE");
                break;
            default:
                break;
        }
    }
}
