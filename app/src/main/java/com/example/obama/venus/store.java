package com.example.obama.venus;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class store extends AppCompatActivity {
    private TabLayout mTablayout;
    private ViewPager mViewPager;
    private ViewPager mViewPager2;
    private List<PageView> pageList;
    private List<PageView> pageList2;

    String shop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store);

        Intent intent = this.getIntent();
        shop = intent.getStringExtra("shop_num");


        ImageButton imageButton18 = (ImageButton) findViewById(R.id.imageButton18);
        imageButton18.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                store.this.finish();
            }
        });

        initData();
        initView();
    }
    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(new SamplePagerAdapter());

        mViewPager2 = (ViewPager) findViewById(R.id.pager2);
        mViewPager2.setAdapter(new SamplePagerAdapter2());


        mTablayout = (TabLayout) findViewById(R.id.tabs);
        mTablayout.addTab(mTablayout.newTab().setText("商店介紹"));
        mTablayout.addTab(mTablayout.newTab().setText("優惠資訊"));
        initListener();
    }

    private void initData() {
        pageList = new ArrayList<>();
        pageList.add(new PageOneView(store.this));
        pageList.add(new PageTwoView(store.this));
        pageList.add(new PageThreeView(store.this));

        pageList2 = new ArrayList<>();
        pageList2.add(new PageFourView(store.this));
        pageList2.add(new PageSixView(store.this));
    }
    private void initListener() {
        mTablayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager2));
        mViewPager2.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTablayout));
    }

    private class SamplePagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return pageList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return o == view;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(pageList.get(position));
            return pageList.get(position);
        }
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

    }

    private class SamplePagerAdapter2 extends PagerAdapter {

        @Override
        public int getCount() {
            return pageList2.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return o == view;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(pageList2.get(position));
            return pageList2.get(position);
        }
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

    }
    //圖片
    public class PageOneView extends PageView{
        public PageOneView(Context context) {
            super(context);
            View view = LayoutInflater.from(context).inflate(R.layout.page_content, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.imageView2);
            try {
                String result = DBConnector.executeQuery("SELECT sh_pic1 FROM shop WHERE sh_id = '"+shop+"'");

                JSONArray jsonArray = new JSONArray(result);

                for(int i = 0; i < jsonArray.length(); i++)
                {
                    JSONObject jsonData = jsonArray.getJSONObject(i);
                    new DownloadImageTask(imageView)
                            .execute("http://140.135.168.101:3000/uploads/"+jsonData.getString("sh_pic1"));
                }
            } catch(Exception e) {
                // Log.e("log_tag", e.toString());
            }
            addView(view);
        }

        @Override
        public void refreshView() {

        }
    }

    public class PageTwoView extends PageView{
        public PageTwoView(Context context) {
            super(context);
            View view = LayoutInflater.from(context).inflate(R.layout.page_content, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.imageView2);
            try {
                String result = DBConnector.executeQuery("SELECT sh_pic2 FROM shop WHERE sh_id = '"+shop+"'");

                JSONArray jsonArray = new JSONArray(result);

                for(int i = 0; i < jsonArray.length(); i++)
                {
                    JSONObject jsonData = jsonArray.getJSONObject(i);
                    new DownloadImageTask(imageView)
                            .execute("http://140.135.168.101:3000/uploads/"+jsonData.getString("sh_pic2"));
                }
            } catch(Exception e) {
                // Log.e("log_tag", e.toString());
            }
            addView(view);
        }

        @Override
        public void refreshView() {

        }
    }

    public class PageThreeView extends PageView{
        public PageThreeView(Context context) {
            super(context);
            View view = LayoutInflater.from(context).inflate(R.layout.page_content, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.imageView2);
            try {
                String result = DBConnector.executeQuery("SELECT sh_pic3 FROM shop WHERE sh_id = '"+shop+"'");

                JSONArray jsonArray = new JSONArray(result);

                for(int i = 0; i < jsonArray.length(); i++)
                {
                    JSONObject jsonData = jsonArray.getJSONObject(i);
                    new DownloadImageTask(imageView)
                            .execute("http://140.135.168.101:3000/uploads/"+jsonData.getString("sh_pic3"));
                }
            } catch(Exception e) {
                // Log.e("log_tag", e.toString());
            }
            addView(view);
        }

        @Override
        public void refreshView() {

        }
    }

    public class PageFourView extends PageView {
        public PageFourView(Context context) {
            super(context);
            View view = LayoutInflater.from(context).inflate(R.layout.store_intro, null);
            TextView store_name = (TextView) view.findViewById(R.id.textView);
            TextView store_local = (TextView) view.findViewById(R.id.textView2);
            TextView store_phone = (TextView) view.findViewById(R.id.textView3);
            TextView store_info = (TextView) view.findViewById(R.id.textView33);
            try {
                String result = DBConnector.executeQuery("SELECT * FROM shop WHERE sh_id = '"+shop+"'");

                JSONArray jsonArray = new JSONArray(result);

                for(int i = 0; i < jsonArray.length(); i++)
                {
                    JSONObject jsonData = jsonArray.getJSONObject(i);
                    store_name.setText(jsonData.getString("sh_name"));
                    store_local.setText("地址:"+jsonData.getString("sh_address"));
                    store_phone.setText("電話"+jsonData.getString("sh_phone"));
                    store_info.setText(jsonData.getString("sh_info"));
                }
            } catch(Exception e) {
                // Log.e("log_tag", e.toString());
            }



            addView(view);
        }

        @Override
        public void refreshView() {

        }
    }

    public class PageSixView extends PageView{
        public PageSixView(Context context) {
            super(context);
            View view = LayoutInflater.from(context).inflate(R.layout.store_sales, null);
            LinearLayout offer = (LinearLayout)view.findViewById(R.id.offer);
            LinearLayout img = (LinearLayout) view.findViewById(R.id.img);
            TextView store_name = (TextView)view.findViewById(R.id.textView);
            TextView store_offer = (TextView)view.findViewById(R.id.textView31);
            TextView start_day = (TextView)view.findViewById(R.id.textView11);
            TextView end_day = (TextView)view.findViewById(R.id.textView12);
            ImageView offer_img = new ImageView(store.this);
            try {
                String result = DBConnector.executeQuery("SELECT * FROM shop , offer where shop.sh_id = offer.sh_id AND shop.sh_id =  '"+shop+"'");

                JSONArray jsonArray = new JSONArray(result);

                for(int i = 0; i < jsonArray.length(); i++)
                {
                    JSONObject jsonData = jsonArray.getJSONObject(i);
                    store_name.setText(jsonData.getString("offer_title"));
                    start_day.setText("開始日期:"+jsonData.getString("offer_startdate"));
                    end_day.setText("結束日期:"+jsonData.getString("offer_enddate"));
                    store_offer.setText(jsonData.getString("offer_text"));


                    offer_img.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    new DownloadImageTask(offer_img)
                            .execute("http://140.135.168.101:3000/uploads/"+jsonData.getString("offer_pic"));
                    offer_img.setScaleType(ImageView.ScaleType.FIT_XY);
                    img.addView(offer_img);

                }
            } catch(Exception e) {
                // Log.e("log_tag", e.toString());
            }

            addView(view);
        }

        @Override
        public void refreshView() {

        }
    }
    //抓圖片
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
