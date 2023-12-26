package com.example.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class LianxiDetails extends AppCompatActivity {
    TextView dName,textView1,textView2,textView3;
    ImageView dImage;
    Button button_r;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lianxi_details);
        //获取上一个Actvity传过来的intent
        Intent intent=getIntent();
        dName=findViewById(R.id.textDetail);
        dImage=findViewById((R.id.imageDetail));
        //根据intent获取得到的数据设置item控件的值
        dImage.setImageResource(intent.getIntExtra("image",R.drawable.wode));
        dName.setText(intent.getStringExtra("details"));
        textView1=findViewById(R.id.phone);
        textView2=findViewById(R.id.region2);
        textView3=findViewById(R.id.wxtag2);
        textView1.setText(intent.getStringExtra("phone"));
        textView2.setText(intent.getStringExtra("region"));
        textView3.setText(intent.getStringExtra("tag"));
        button_r=findViewById(R.id.returnButton);
        button_r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }
}