package com.example.liuk.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

public class StartActivity extends AppCompatActivity {
    public StartActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        // 启动一个线程
       Thread thread = new Thread(new Runnable() {
           @Override
           public void run() {
               try {
                   // 一秒后跳转到登录界面
                   Thread.sleep(1000);
                   startActivity(new Intent(StartActivity.this, MainActivity.class));
                   finish();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
       });
        thread.start();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.start, menu);
        return true;
    }
}
