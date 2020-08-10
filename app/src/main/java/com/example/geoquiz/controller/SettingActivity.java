package com.example.geoquiz.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.example.geoquiz.R;

public class SettingActivity extends AppCompatActivity {
    public static final String EXTRA_COLOR = "COLOR";
    public static  final String EXTRA_SIZE ="SIZE" ;
    public static final String BUNDLE_SIZE = "BUNDLE_SIZE";
    public static final String BUNDLE_COLOR = "BUNDLE_COLOR";
    private RadioButton mButton_Medium;
    private RadioButton mButton_Large;
    private RadioButton mButton_Small;
    private Button mButton_save;
    private Button mButton_discard;
    private Button mButton_Red;
    private Button mButton_green;
    private Button mButton_Blue;
    private Button mButton_White;
    private int size;

    private String color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        if (savedInstanceState != null) {
           size=savedInstanceState.getInt(BUNDLE_SIZE,size);
           color=savedInstanceState.getString(BUNDLE_COLOR,color);
        }
        find();
        setListeners();

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putInt(BUNDLE_SIZE,size);
        outState.putString(BUNDLE_COLOR,color);

    }

    private void setListeners() {
        mButton_Small.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                size=14;

            }
        });
        mButton_Large.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               size=26;
            }
        });
        mButton_Medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                size=18;
            }
        });
        mButton_Red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                color="red";
            }
        });
        mButton_Blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                color="blue";
            }
        });
        mButton_green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                color="green";
            }
        });
        mButton_White.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                color="white";
            }
        });
        mButton_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent();
                intent.putExtra(EXTRA_SIZE,size);
                setResult(RESULT_OK,intent);
                Intent intent1=new Intent();
                intent1.putExtra(EXTRA_COLOR,color);
                setResult(RESULT_OK,intent1);
            }
        });
        mButton_discard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finishActivity(1);
                mButton_save.setEnabled(false);

            }
        });
    }

    private void find() {
        mButton_Large=findViewById(R.id.large_Button);
        mButton_Medium=findViewById(R.id.medium_Button);
        mButton_Small=findViewById(R.id.small_Button);
        mButton_save=findViewById(R.id.save);
        mButton_discard=findViewById(R.id.discard);
        mButton_Blue=findViewById(R.id.bluebutton);
        mButton_green=findViewById(R.id.greenbutton);
        mButton_Red=findViewById(R.id.redbutton);
        mButton_White=findViewById(R.id.whitebutton);
    }

}