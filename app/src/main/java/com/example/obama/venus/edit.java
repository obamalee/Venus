package com.example.obama.venus;

import android.app.DatePickerDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.Calendar;

public class edit extends AppCompatActivity {
    private int mYear, mMonth, mDay;
    String name_input;
    String date;
    String member_sex;
    String phone_input;

    RadioButton man;
    RadioButton woman;
    RadioGroup group;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit);
        try {
            String result = DBConnector.executeQuery("SELECT * FROM member WHERE mb_id = '1'");

            JSONArray jsonArray = new JSONArray(result);

            for(int i = 0; i < jsonArray.length(); i++)
            {
                JSONObject jsonData = jsonArray.getJSONObject(i);


                //姓名
                EditText name = (EditText)findViewById(R.id.editText4);
                name.setText(jsonData.getString("mb_name"));
                name_input = name.getText().toString();

                EditText phone = (EditText)findViewById(R.id.editText7);
                phone.setText(jsonData.getString("mb_phone"));
                phone_input = phone.getText().toString();


                //性別
                man = (RadioButton)findViewById(R.id.radioButton);
                woman = (RadioButton)findViewById(R.id.radioButton2);

                group = (RadioGroup) findViewById(R.id.group);
                group.setOnCheckedChangeListener(radGrpRegionOnCheckedChange);
                if(jsonData.getString("mb_gender").equals("男"))
                {
                    group.check(R.id.radioButton);
                }else
                {
                    group.check(R.id.radioButton2);
                }

                //選日期
                final Button dateButton = (Button)findViewById(R.id.button24);
                dateButton.setText(jsonData.getString("mb_birth"));
                date = jsonData.getString("mb_birth");
                dateButton.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        final Calendar c = Calendar.getInstance();
                        mYear = c.get(Calendar.YEAR);
                        mMonth = c.get(Calendar.MONTH);
                        mDay = c.get(Calendar.DAY_OF_MONTH);
                        new DatePickerDialog(edit.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int day) {
                                String format = setDateFormat(year,month,day);
                                dateButton.setText(format);
                                date = format;
                            }
                        }, mYear,mMonth, mDay).show();

                    }

                });


                CircleImageView photo= (CircleImageView) findViewById(R.id.photo);
                new DownloadImageTask((CircleImageView) findViewById(R.id.photo))
                        .execute(jsonData.getString("mb_pic"));

            }
        } catch(Exception e) {
            // Log.e("log_tag", e.toString());
        }

        ImageButton imageButton1 = (ImageButton) findViewById(R.id.imageButton1);
        imageButton1.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText name = (EditText)findViewById(R.id.editText4);
                name_input = name.getText().toString();
                EditText phone = (EditText)findViewById(R.id.editText7);
                phone_input = phone.getText().toString();
                update.executeQuery("member SET mb_name = '"+name_input+"',mb_birth = '"+date+"',mb_phone = '"+phone_input+"' WHERE mb_id = '1'");
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                edit.this.finish();
            }
        });

    }

    private RadioGroup.OnCheckedChangeListener radGrpRegionOnCheckedChange =
            new RadioGroup.OnCheckedChangeListener()
            {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId)
                {

                    switch (checkedId)
                    {
                        case R.id.radioButton: //case mRadioButton0.getId():
                            update.executeQuery("member SET mb_gender = '男' WHERE mb_id = '1'");
                            break;

                        case R.id.radioButton2: //case mRadioButton1.getId():
                            update.executeQuery("member SET mb_gender = '女' WHERE mb_id = '1'");
                            break;

                    }
                }
            };


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
    //選日期
    private String setDateFormat(int year,int monthOfYear,int dayOfMonth){
        return String.valueOf(year) + "-"
                + String.valueOf(monthOfYear + 1) + "-"
                + String.valueOf(dayOfMonth);
    }
}
