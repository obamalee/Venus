package com.example.obama.venus;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;

import static android.view.View.TEXT_ALIGNMENT_CENTER;
import static java.lang.Integer.parseInt;

public class food_list extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //session
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String mb_id = "mb_idlKey";
    SharedPreferences sharedpreferences;

    String type;
    String a;
    String sql;
    String my_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //抓取 mb_id
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        my_id = sharedpreferences.getString(mb_id, "F");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        Intent intent_type = this.getIntent();
        type = intent_type.getStringExtra("shop_type");

        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        findViews();

        Button Button7 = (Button) findViewById(R.id.button7);
        Button7.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(food_list.this, cinema.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        Button Button11 = (Button) findViewById(R.id.button11);
        Button11.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(food_list.this,transfer.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        Button Button15 = (Button) findViewById(R.id.button15);
        Button15.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(food_list.this,information.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        Button food = (Button) findViewById(R.id.button8);
        food.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(food_list.this,food_list.class);
                intent.putExtra("shop_type","1");
                startActivity(intent);
                food_list.this.finish();
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        Button clothing = (Button) findViewById(R.id.button9);
        clothing.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(food_list.this,food_list.class);
                intent.putExtra("shop_type","2");
                startActivity(intent);
                food_list.this.finish();
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        Button housing = (Button) findViewById(R.id.button10);
        housing.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(food_list.this,food_list.class);
                intent.putExtra("shop_type","3");
                startActivity(intent);
                food_list.this.finish();
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        Button recreation = (Button) findViewById(R.id.button13);
        recreation.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(food_list.this,food_list.class);
                intent.putExtra("shop_type","4");
                startActivity(intent);
                food_list.this.finish();
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        Button note = (Button) findViewById(R.id.button14);
        note.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(food_list.this,food_list.class);
                intent.putExtra("shop_type","5");
                startActivity(intent);
                food_list.this.finish();
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        Button sale = (Button) findViewById(R.id.button12);
        sale.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(food_list.this,food_list.class);
                intent.putExtra("shop_type","6");
                startActivity(intent);
                food_list.this.finish();
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        Button logout = (Button) findViewById(R.id.button26);
        logout.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString(mb_id, "F");
                editor.commit();
                //editor.remove("mb_id").commit();
                //Log.d(my_id);
                //SharedPreferences.Editor clear (String "mb_id);
                //editor.clear();
                //editor.commit();
                Intent intent = new Intent();
                intent.setClass(food_list.this,login.class);
                //intent.putExtra("shop_type","6");
                startActivity(intent);
                food_list.this.finish();
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });



    }

    private void findViews() {
        LinearLayout shop = (LinearLayout)findViewById(R.id.shop);
        try {
            if(type.equals("5"))
            {
                sql = "SELECT * FROM notes INNER JOIN shop ON notes.sh_id = shop.sh_id WHERE mb_id = '"+my_id+"'";
            }else if(type.equals("6"))
            {
                sql = "SELECT * FROM shop INNER JOIN offer ON shop.sh_id = offer.sh_id ORDER BY offer_level_id ASC";
            }
            else
            {
                sql = "SELECT * FROM shop WHERE sh_type= '"+type+"'";
            }
            final String result = DBConnector.executeQuery(sql);

            JSONArray jsonArray = new JSONArray(result);
            for(int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonData = jsonArray.getJSONObject(i);
                LinearLayout shop_aaa = new LinearLayout(food_list.this);
                shop_aaa.setOrientation(LinearLayout.HORIZONTAL);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(0,0,0,0);
                layoutParams.gravity = Gravity.CENTER_HORIZONTAL;

                final String shop_num = jsonData.getString("sh_id");

                ImageButton shop_img = new ImageButton(food_list.this);
                shop_img.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 500));
                new DownloadImageTask(shop_img)
                        .execute("http://140.135.168.101:3000/uploads/"+jsonData.getString("sh_pic1"));
                shop_img.setBackground(this.getResources().getDrawable(R.drawable.liner));
                shop_img.setPadding(2,2,2,2);
                shop_img.setScaleType(ImageView.ScaleType.FIT_XY);
                shop_img.setOnClickListener(new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.setClass(food_list.this, store.class);
                        intent.putExtra("shop_num",shop_num);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    }
                });


                shop_aaa.addView(shop_img);
                shop.addView(shop_aaa,layoutParams);

                LinearLayout shop_bbb = new LinearLayout(food_list.this);
                shop_aaa.setOrientation(LinearLayout.HORIZONTAL);
                LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 120);
                layoutParams2.gravity = Gravity.CENTER;
                shop_bbb.setBackground(this.getResources().getDrawable(R.drawable.liner));

                TextView shop_name = new TextView(food_list.this);
                shop_name.setLayoutParams(new LinearLayout.LayoutParams(700,LinearLayout.LayoutParams.WRAP_CONTENT));
                shop_name.setPadding(10,5,0,5);
                shop_name.setText(jsonData.getString("sh_name"));
                shop_name.setTextSize(20);
                shop_name.setTextColor(Color.BLACK);

                final String location = jsonData.getString("sh_address");
                final String name = jsonData.getString("sh_name");
                ImageButton shop_local = new ImageButton(food_list.this);
                LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(80,80);
                layoutParams3.gravity = Gravity.CENTER;
                layoutParams3.setMargins(8,0,8,0);
                shop_local.setBackgroundColor(Color.TRANSPARENT);
                shop_local.setBackground(this.getResources().getDrawable(R.drawable.placeholder));
                shop_local.setScaleType(ImageView.ScaleType.FIT_CENTER);
                shop_local.setOnClickListener(new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.setClass(food_list.this, maps.class);
                        intent.putExtra("location",location);
                        intent.putExtra("name",name);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    }
                });

                final ImageButton shop_like = new ImageButton(food_list.this);
                LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(80,80);
                layoutParams4.gravity = Gravity.CENTER;
                layoutParams4.setMargins(20,0,20,0);
                shop_like.setBackgroundColor(Color.TRANSPARENT);
                try {
                    String result1 = DBConnector.executeQuery("SELECT * FROM member INNER JOIN notes ON member.mb_id = notes.mb_id WHERE sh_id= '" + shop_num + "' AND notes.mb_id = '"+my_id+"'");
                    JSONArray jsonArray1 = new JSONArray(result1);
                    shop_like.setBackground(this.getResources().getDrawable(R.drawable.heart2));
                } catch (Exception e) {
                    shop_like.setBackground(this.getResources().getDrawable(R.drawable.heart));
                }

                shop_like.setScaleType(ImageView.ScaleType.FIT_CENTER);

                final TextView shop_heart = new TextView(food_list.this);
                LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(80,LinearLayout.LayoutParams.WRAP_CONTENT);
                layoutParams5.gravity = Gravity.CENTER;
                if(jsonData.getString("sh_like") == "null")
                    a = "0";
                else
                    a = jsonData.getString("sh_like");
                shop_heart.setText(a);
                shop_heart.setTextSize(14);
                shop_heart.setTextAlignment(TEXT_ALIGNMENT_CENTER);
                shop_heart.setTextColor(Color.BLACK);

                shop_like.setOnClickListener(new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            String result1 = DBConnector.executeQuery("SELECT * FROM member INNER JOIN notes ON member.mb_id = notes.mb_id WHERE sh_id= '" + shop_num + "' AND notes.mb_id = '"+my_id+"'");
                            JSONArray jsonArray1 = new JSONArray(result1);
                            int like = parseInt(a);
                            like--;
                            shop_heart.setText(String.valueOf(like));
                            a = String.valueOf(like);
                            update.executeQuery("shop SET sh_like ='"+like+"' WHERE sh_id = '"+shop_num+"'");
                            delete.executeQuery("DELETE FROM notes WHERE sh_id = "+shop_num+" AND mb_id = "+my_id+"");
                            shop_like.setBackground(getResources().getDrawable(R.drawable.heart));

                        } catch (Exception e) {
                            int like = parseInt(a);
                            like++;
                            shop_heart.setText(String.valueOf(like));
                            a = String.valueOf(like);
                            update.executeQuery("shop SET sh_like ='" + like + "' WHERE sh_id = '" + shop_num + "'");
                            delete.executeQuery("INSERT INTO notes(mb_id,sh_id) VALUES ("+my_id+"," + shop_num + ")");
                            shop_like.setBackground(getResources().getDrawable(R.drawable.heart2));
                        }

                    }
                });

                shop_bbb.addView(shop_name);
                shop_bbb.addView(shop_local,layoutParams3);
                shop_bbb.addView(shop_like,layoutParams4);
                shop_bbb.addView(shop_heart,layoutParams5);
                shop.addView(shop_bbb,layoutParams2);


            }
        } catch(Exception e) {
            // Log.e("log_tag", e.toString());
        }


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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
