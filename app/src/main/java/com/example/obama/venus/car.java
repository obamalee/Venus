package com.example.obama.venus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class car extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.car);

        ImageButton Button20 = (ImageButton) findViewById(R.id.imageButton20);
        Button20.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                car.this.finish();
            }
        });

        LinearLayout park1 = (LinearLayout)findViewById(R.id.park1);
        park1.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("location","桃園市中壢區九和一街35號");
                intent.putExtra("name","銀河廣場停車場");
                intent.setClass(car.this, maps.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        LinearLayout park2 = (LinearLayout)findViewById(R.id.park2);
        park2.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("location","桃園市中壢區慈惠一街150號");
                intent.putExtra("name","世紀停車場");
                intent.setClass(car.this, maps.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        LinearLayout park3 = (LinearLayout)findViewById(R.id.park3);
        park3.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("location","桃園市中壢區新生路165號");
                intent.putExtra("name","新生停車場");
                intent.setClass(car.this, maps.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        LinearLayout park4 = (LinearLayout)findViewById(R.id.park4);
        park4.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("location","桃園市中壢區元化路二段67號");
                intent.putExtra("name","吉祥計次停車場");
                intent.setClass(car.this, maps.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });


    }
}
