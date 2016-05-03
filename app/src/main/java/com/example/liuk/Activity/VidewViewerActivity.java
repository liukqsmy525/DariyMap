package com.example.liuk.Activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.VideoView;

import com.example.liuk.myapplication.R;

import java.io.File;

public class VidewViewerActivity extends AppCompatActivity {

    private VideoView vv;
    public static final String EXTRA_PATH = "path";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videw_viewer);

        String path = getIntent().getStringExtra(EXTRA_PATH);
        if(path != null)
        {
            vv.setVideoPath(path);

        }else
        {
            finish();
        }
    }
}
