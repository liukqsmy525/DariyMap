package com.example.liuk.Activity;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.liuk.myapplication.R;

import java.io.File;

public class PhotoViewerActivity extends AppCompatActivity {

    private ImageView iv;
    public static final String EXTRA_PATH = "path";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_viewer);

        String path = getIntent().getStringExtra(EXTRA_PATH);
        if(path != null)
        {
            iv.setImageURI(Uri.fromFile(new File(path)));

        }else
        {
            finish();
        }
    }


}
