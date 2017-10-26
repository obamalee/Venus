package com.example.obama.venus;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
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

public class information extends AppCompatActivity {

    //session
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String mb_id = "mb_idlKey";
    SharedPreferences sharedpreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.information);

        //抓取 mb_id
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        final String my_id = sharedpreferences.getString(mb_id, "F");

        ImageButton imageButton15 = (ImageButton) findViewById(R.id.imageButton15);
        imageButton15.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                information.this.finish();
            }
        });

        Button button18 = (Button)findViewById(R.id.button18);
        button18.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(information.this, privacy.class);
                startActivity(intent);
                information.this.finish();
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        Button button19 = (Button)findViewById(R.id.button19);
        button19.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(information.this, vcoin.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        ImageView imageView4 = (ImageView) findViewById(R.id.imageView4);
        new DownloadImageTask((ImageView) findViewById(R.id.imageView4))
                .execute("http://140.135.168.101/ui/icon/people-icon--icon-search-engine-18.png");


        ImageView imageView5 = (ImageView) findViewById(R.id.imageView5);
        new DownloadImageTask((ImageView) findViewById(R.id.imageView5))
                .execute("http://140.135.168.101/ui/icon/stacks_image_6749.png");

        ImageView imageView6 = (ImageView) findViewById(R.id.imageView6);
        new DownloadImageTask((ImageView) findViewById(R.id.imageView6))
                .execute("http://140.135.168.101/ui/icon/2288-200.png");

        ImageView imageView7 = (ImageView) findViewById(R.id.imageView7);
        new DownloadImageTask((ImageView) findViewById(R.id.imageView7))
                .execute("http://140.135.168.101/ui/icon/coins-icon-4.png");

        ImageView imageView45 = (ImageView) findViewById(R.id.imageView45);
        new DownloadImageTask((ImageView) findViewById(R.id.imageView45))
                .execute("http://140.135.168.101/ui/icon/push_notifications1600.png");


        TextView name = (TextView)findViewById(R.id.textView18);
        TextView mail = (TextView)findViewById(R.id.textView19);
        try {
            String result = DBConnector.executeQuery("SELECT * FROM member WHERE mb_id = '"+my_id+"'");

            JSONArray jsonArray = new JSONArray(result);

            for(int i = 0; i < jsonArray.length(); i++)
            {
                JSONObject jsonData = jsonArray.getJSONObject(i);
                name.setText(jsonData.getString("mb_name"));
                mail.setText(jsonData.getString("mb_mail"));

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

