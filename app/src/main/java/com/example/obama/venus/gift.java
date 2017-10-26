package com.example.obama.venus;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;

public class gift extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gift);

        ImageButton imageButton1 = (ImageButton) findViewById(R.id.imageButton1);
        imageButton1.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(gift.this,vcoin.class);
                startActivity(intent);
                gift.this.finish();
            }
        });

        findViews();

    }

    private void findViews() {
        LinearLayout gift_list = (LinearLayout)findViewById(R.id.gift_list);
        try {
            final String result = DBConnector.executeQuery("SELECT * FROM shop INNER JOIN gift ON shop.sh_id = gift.sh_id ");

            JSONArray jsonArray = new JSONArray(result);
            for(int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonData = jsonArray.getJSONObject(i);

                final String gift_id = jsonData.getString("gift_id");
                LinearLayout gift_aaa = new LinearLayout(gift.this);
                gift_aaa.setOrientation(LinearLayout.HORIZONTAL);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(0,20,0,20);
                gift_aaa.setPadding(0,0,0,0);
                gift_aaa.setOnClickListener(new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.putExtra("gift_id",gift_id);
                        intent.setClass(gift.this, gift_item.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    }
                });

                LinearLayout img = new LinearLayout(gift.this);
                img.setOrientation(LinearLayout.VERTICAL);
                LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                layoutParams5.setMargins(15,0,0,0);
                img.setPadding(0,0,0,0);

                ImageView gift_img = new ImageView(gift.this);
                new DownloadImageTask(gift_img)
                        .execute(jsonData.getString("gift_pic"));
                gift_img.setScaleType(ImageView.ScaleType.FIT_XY);
                LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(250, 250);
                layoutParams1.setMargins(15,0,0,0);

                LinearLayout text = new LinearLayout(gift.this);
                text.setOrientation(LinearLayout.VERTICAL);
                LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT);
                layoutParams2.setMargins(30,0,0,0);
                text.setPadding(0,0,0,0);
                text.setBackground(this.getResources().getDrawable(R.drawable.bottom_line_black));
                layoutParams2.gravity = Gravity.CENTER_VERTICAL;

                TextView gift_name = new TextView(gift.this);
                gift_name.setText(jsonData.getString("gift_name"));
                gift_name.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
                gift_name.setPadding(0,0,0,10);
                gift_name.setTextSize(18);
                gift_name.setTextColor(Color.BLACK);

                TextView gift_store = new TextView(gift.this);
                gift_store.setText("由"+jsonData.getString("sh_name")+"所提供");
                gift_store.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
                gift_store.setPadding(0,0,0,10);
                gift_store.setTextSize(15);
                gift_store.setTextColor(Color.BLACK);

                LinearLayout gift_bbb = new LinearLayout(gift.this);
                gift_bbb.setOrientation(LinearLayout.HORIZONTAL);
                LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                gift_bbb.setPadding(0,0,0,0);
                gift_bbb.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_VERTICAL);
                layoutParams3.gravity = Gravity.CENTER_VERTICAL;

                ImageView coin_img = new ImageView(gift.this);
                coin_img.setBackground(this.getResources().getDrawable(R.drawable.vcoins));
                gift_img.setScaleType(ImageView.ScaleType.FIT_XY);
                LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(45, 45);
                layoutParams1.setMargins(0,0,10,0);

                TextView gift_coin = new TextView(gift.this);
                gift_coin.setText(jsonData.getString("gift_coin"));
                gift_coin.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT));
                gift_coin.setTextSize(16);
                gift_coin.setTextColor(Color.BLACK);

                img.addView(gift_img,layoutParams1);
                gift_aaa.addView(img,layoutParams5);
                text.addView(gift_name);
                text.addView(gift_store);
                gift_bbb.addView(coin_img,layoutParams4);
                gift_bbb.addView(gift_coin,layoutParams3);
                text.addView(gift_bbb,layoutParams3);
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
