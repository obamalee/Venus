package com.example.obama.venus;

import android.content.Intent;
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

import java.io.InputStream;

public class main_menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        ImageButton imageButton1 = (ImageButton) findViewById(R.id.imageButton1);
        new DownloadImageTask((ImageView) findViewById(R.id.imageButton1))
                .execute("http://140.135.168.101/ui/icon/Food-Dome-512.png");
        imageButton1.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(main_menu.this, food_list.class);
                intent.putExtra("shop_type","1");
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        ImageButton imageButton2 = (ImageButton) findViewById(R.id.imageButton2);
        new DownloadImageTask((ImageView) findViewById(R.id.imageButton2))
                .execute("http://140.135.168.101/ui/icon/75-512.png");
        imageButton2.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(main_menu.this, food_list.class);
                intent.putExtra("shop_type","2");
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        ImageButton imageButton3 = (ImageButton) findViewById(R.id.imageButton3);
        new DownloadImageTask((ImageView) findViewById(R.id.imageButton3))
                .execute("http://140.135.168.101/ui/icon/20151125_5655088ba5cdf.png");
        imageButton3.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(main_menu.this, food_list.class);
                intent.putExtra("shop_type","3");
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        ImageButton imageButton4 = (ImageButton) findViewById(R.id.imageButton4);
        new DownloadImageTask((ImageView) findViewById(R.id.imageButton4))
                .execute("http://140.135.168.101/ui/icon/icon-mobil.png");
        imageButton4.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(main_menu.this, transfer.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        ImageButton imageButton5 = (ImageButton) findViewById(R.id.imageButton5);
        new DownloadImageTask((ImageView) findViewById(R.id.imageButton5))
                .execute("http://140.135.168.101/ui/icon/Video-Game-Controller-Icon-IDV-edit-dark.svg.png");
        imageButton5.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(main_menu.this, food_list.class);
                intent.putExtra("shop_type","4");
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        ImageButton imageButton6 = (ImageButton) findViewById(R.id.imageButton6);
        new DownloadImageTask((ImageView) findViewById(R.id.imageButton6))
                .execute("http://140.135.168.101/ui/icon/gift-flat.png");
        imageButton6.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(main_menu.this, food_list.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        ImageButton imageButton7 = (ImageButton) findViewById(R.id.imageButton7);
        new DownloadImageTask((ImageView) findViewById(R.id.imageButton7))
                .execute("http://140.135.168.101/ui/icon/plus-4-xxl.png");
        imageButton7.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(main_menu.this, cinema.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        ImageButton imageButton8 = (ImageButton) findViewById(R.id.imageButton8);
        new DownloadImageTask((ImageView) findViewById(R.id.imageButton8))
                .execute("http://140.135.168.101/ui/icon/pencil-ruler-eraser-512.png");
        imageButton8.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("shop_type","6");
                intent.setClass(main_menu.this, food_list.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });


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
