package com.example.obama.venus;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.Integer.parseInt;

public class gift_item extends AppCompatActivity {
    String gift_id;
    String name;
    int coin;
    int point;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gift_item);

        ImageButton imageButton1 = (ImageButton) findViewById(R.id.imageButton1);
        imageButton1.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                gift_item.this.finish();
            }
        });

        Intent intent_type = this.getIntent();
        gift_id = intent_type.getStringExtra("gift_id");

        try {
            final String result = DBConnector.executeQuery("SELECT * FROM gift WHERE gift_id = '"+gift_id+"' ");

            JSONArray jsonArray = new JSONArray(result);
            for(int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonData = jsonArray.getJSONObject(i);

                ImageView gift_pic = (ImageView)findViewById(R.id.imageView10);
                new DownloadImageTask(gift_pic)
                        .execute(jsonData.getString("gift_pic"));

                TextView gift_name = (TextView)findViewById(R.id.textView13);
                gift_name.setText(jsonData.getString("gift_name"));
                name = jsonData.getString("gift_name");

                Button gift_coin = (Button)findViewById(R.id.button4);
                gift_coin.setText(jsonData.getString("gift_coin"));
                coin = parseInt(jsonData.getString("gift_coin"));

                gift_coin.setOnClickListener(new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        try {
                            final String result = DBConnector.executeQuery("SELECT * FROM point WHERE mb_id = '1'");
                            JSONArray jsonArray = new JSONArray(result);
                            for(int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonData = jsonArray.getJSONObject(i);
                                point = parseInt(jsonData.getString("point"));
                            }
                        } catch(Exception e) {
                            // Log.e("log_tag", e.toString());
                        }

                        if (point>coin)
                        {
                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
                            Date curDate = new Date(System.currentTimeMillis()) ; // 獲取當前時間
                            String str = formatter.format(curDate);

                            delete.executeQuery("INSERT INTO gift_box (gift_id,mb_id) VALUES ("+gift_id+",1)");
                            update.executeQuery("point SET point = '"+(point-coin)+"' WHERE mb_id = '1' ");
                            delete.executeQuery("INSERT INTO gift_record (gift_id,mb_id,less_point,updated_at) VALUES ('"+gift_id+"','1','"+coin+"','"+str+"')");
                            new AlertDialog.Builder(gift_item.this)
                                    .setTitle("已兌換成功")//設定視窗標題
                                    //.setIcon(R.mipmap.ic_launcher)//設定對話視窗圖示
                                    .setMessage("已成功兌換"+name)//設定顯示的文字
                                    .setPositiveButton("關閉視窗",new DialogInterface.OnClickListener(){
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            finish();
                                        }
                                    })//設定結束的子視窗
                                    .show();//呈現對話視窗
                        }else
                        {
                            new AlertDialog.Builder(gift_item.this)
                                    .setTitle("兌換失敗")//設定視窗標題
                                    //.setIcon(R.mipmap.ic_launcher)//設定對話視窗圖示
                                    .setMessage("積點不足，無法兌換")//設定顯示的文字
                                    .setPositiveButton("關閉視窗",new DialogInterface.OnClickListener(){
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            finish();
                                        }
                                    })//設定結束的子視窗
                                    .show();//呈現對話視窗
                        }

                    }
                });

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
