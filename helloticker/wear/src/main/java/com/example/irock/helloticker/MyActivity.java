package com.example.irock.helloticker;

import android.app.Activity;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.util.Log;

import java.io.File;

import android.widget.TextView;

public class MyActivity extends Activity {

    private TextView mTextView;
    private String TAG = "Vj_G";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "Hey Yo");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        Log.d(TAG, "*** FilesDirPath: " + getFilesDir().getPath());
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                mTextView = (TextView) stub.findViewById(R.id.text);
                Log.d(TAG, "TextView: " + mTextView.getText() + " view=" + mTextView);
            }
        });
        listFiles(new File("/vendor"));
        Log.d(TAG, "$$$_Finished introspecting File System");
    }

    private void printFiles(File[] files) {
        for (File f : files) {
            printFile(f);
        }
    }

    private void printFile(File file) {
      //  if (file.getName().contains("jpg") || file.getName().contains("png") || file.getName().contains("jpeg") || file.getName().contains("bmp")) {
            Log.d(TAG, file.getAbsolutePath() + ": " + file);
        //}
    }

    private void listFiles(File directory) {
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null && files.length > 0) {
                printFiles(files);
                for (File f : files) {
                    if (f.isDirectory()) {
                        printFile(f);
                        //listFiles(f);
                    } else {
                        printFile(f);
                    }
                }
            } else{
                Log.d(TAG, "Ouch...Ain't no found nothing under: " + directory.getName());
            }
        }
    }
}