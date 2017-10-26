package com.example.obama.venus;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;

public class gift_box extends AppCompatActivity {

    //session
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String mb_id = "mb_idlKey";
    SharedPreferences sharedpreferences;
    String my_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gift_box);

        //抓取 mb_id
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        my_id = sharedpreferences.getString(mb_id, "F");


        ImageButton imageButton1 = (ImageButton) findViewById(R.id.imageButton1);
        imageButton1.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                gift_box.this.finish();
            }
        });

        Button button27 = (Button)findViewById(R.id.button27);
        button27.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(gift_box.this, gift_box_used.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        findViews();
    }

    private void findViews(){
        LinearLayout gift_list = (LinearLayout)findViewById(R.id.gift_list);
        try {
            final String result = DBConnector.executeQuery("SELECT * FROM gift_box INNER JOIN gift ON gift.gift_id = gift_box.gift_id INNER JOIN shop ON gift.sh_id = shop.sh_id WHERE mb_id = '"+my_id+"' AND used = 'F' ");

            JSONArray jsonArray = new JSONArray(result);
            for(int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonData = jsonArray.getJSONObject(i);

                final String gift_id = jsonData.getString("gift_id");
                LinearLayout gift_aaa = new LinearLayout(gift_box.this);
                gift_aaa.setOrientation(LinearLayout.HORIZONTAL);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(0,30,0,0);
                gift_aaa.setOnClickListener(new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.setClass(gift_box.this, qrcode.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        gift_box.this.finish();
                    }
                });

                LinearLayout img = new LinearLayout(gift_box.this);
                img.setOrientation(LinearLayout.VERTICAL);

                ImageView gift_img = new ImageView(gift_box.this);
                new DownloadImageTask(gift_img)
                        .execute(jsonData.getString("gift_pic"));
                gift_img.setScaleType(ImageView.ScaleType.FIT_XY);
                LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(200, 200);
                layoutParams1.setMargins(15,0,0,0);

                LinearLayout text = new LinearLayout(gift_box.this);
                text.setOrientation(LinearLayout.VERTICAL);
                LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT);
                layoutParams2.setMargins(30,0,0,0);
                text.setBackground(this.getResources().getDrawable(R.drawable.bottom_line_black));

                TextView gift_name = new TextView(gift_box.this);
                gift_name.setText(jsonData.getString("gift_name"));
                gift_name.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
                gift_name.setPadding(0,0,0,10);
                gift_name.setTextSize(18);
                gift_name.setTextColor(Color.BLACK);

                TextView gift_store = new TextView(gift_box.this);
                gift_store.setText("請至"+jsonData.getString("sh_name")+"兌換");
                gift_store.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
                gift_store.setPadding(0,0,0,10);
                gift_store.setTextSize(15);
                gift_store.setTextColor(Color.BLACK);

                img.addView(gift_img,layoutParams1);
                gift_aaa.addView(img);
                text.addView(gift_name);
                text.addView(gift_store);
                gift_aaa.addView(text,layoutParams2);
                gift_list.addView(gift_aaa,layoutParams);

            }
        } catch(Exception e) {
            // Log.e("log_tag", e.toString());
        }
    }
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
