package com.example.obama.venus;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

public class gift_record extends AppCompatActivity {

    //session
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String mb_id = "mb_idlKey";
    SharedPreferences sharedpreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gift_record);

        //抓取 mb_id
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        final String my_id = sharedpreferences.getString(mb_id, "F");

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectNetwork()
                .penaltyLog()
                .build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects()
                .penaltyLog()
                .penaltyDeath()
                .build());

        ImageButton imageButton1 = (ImageButton) findViewById(R.id.imageButton1);
        imageButton1.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                gift_record.this.finish();
            }
        });

        LinearLayout record_list = (LinearLayout)findViewById(R.id.record_list);
        try {
            final String result = DBConnector.executeQuery("SELECT * FROM gift_record INNER JOIN gift ON gift_record.gift_id = gift.gift_id WHERE mb_id = '"+my_id+"' ORDER BY updated_at DESC");

            JSONArray jsonArray = new JSONArray(result);
            for(int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonData = jsonArray.getJSONObject(i);

                LinearLayout gift_aaa = new LinearLayout(gift_record.this);
                gift_aaa.setOrientation(LinearLayout.HORIZONTAL);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                layoutParams.setMargins(20,0,20,0);
                gift_aaa.setBackground(this.getResources().getDrawable(R.drawable.bottom_line_black));

                LinearLayout img = new LinearLayout(gift_record.this);
                img.setOrientation(LinearLayout.VERTICAL);
                LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT,0.5f);
                layoutParams2.setMargins(0,30,0,30);

                TextView gift_name = new TextView(gift_record.this);
                gift_name.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
                gift_name.setTextSize(18);
                gift_name.setTextColor(Color.BLACK);
                gift_name.setText(jsonData.getString("gift_name"));

                TextView gift_time = new TextView(gift_record.this);
                gift_time.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
                gift_time.setTextSize(12);
                gift_time.setTextColor(Color.GRAY);
                gift_time.setText(jsonData.getString("updated_at"));

                LinearLayout text = new LinearLayout(gift_record.this);
                text.setOrientation(LinearLayout.HORIZONTAL);
                LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT,1.0f);
                layoutParams3.gravity = Gravity.CENTER_VERTICAL;
                layoutParams3.setMargins(0,30,0,30);

                ImageView coin_img = new ImageView(gift_record.this);
                coin_img.setBackground(this.getResources().getDrawable(R.drawable.vcoins));
                coin_img.setScaleType(ImageView.ScaleType.FIT_XY);
                LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(40, 40);
                layoutParams4.gravity = Gravity.CENTER_VERTICAL;

                TextView gift_coin = new TextView(gift_record.this);
                gift_coin.setTextSize(16);
                gift_coin.setTextColor(Color.BLACK);
                LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT,1.0f);
                layoutParams5.gravity = Gravity.CENTER_VERTICAL;
                gift_coin.setText(jsonData.getString("less_point"));

                img.addView(gift_name);
                img.addView(gift_time);

                text.addView(coin_img,layoutParams4);
                text.addView(gift_coin,layoutParams5);

                gift_aaa.addView(img,layoutParams2);
                gift_aaa.addView(text,layoutParams3);
                record_list.addView(gift_aaa,layoutParams);

            }
        } catch(Exception e) {
            // Log.e("log_tag", e.toString());
        }
    }
}
