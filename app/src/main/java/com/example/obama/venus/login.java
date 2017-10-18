package com.example.obama.venus;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import org.json.JSONArray;
import org.json.JSONObject;

public class login extends AppCompatActivity {

    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String mb_id = "mb_idlKey";
    SharedPreferences sharedpreferences;
    EditText mail ,pwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

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

        mail=(EditText)findViewById(R.id.editText);
        pwd=(EditText)findViewById(R.id.editText2);
        ImageButton imageButton7 = (ImageButton) findViewById(R.id.imageButton5);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        String my_id = sharedpreferences.getString(mb_id, "");

        if(my_id.equals("F") || my_id == null){
            imageButton7.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View v) {


                    String a = mail.getText().toString();
                    String b = pwd.getText().toString();
                    try {
                        String result = DBConnector.executeQuery("SELECT mb_id FROM member WHERE mb_pwd='"+b+"' AND mb_mail='"+a+"'");

                        JSONArray jsonArray = new JSONArray(result);

                        for(int i = 0; i < jsonArray.length(); i++)
                        {

                            JSONObject jsonData = jsonArray.getJSONObject(i);
                            if(jsonData.getString("mb_id") != null){
                                //TextView point = (TextView)findViewById(R.id.textView35);
                                //point.setText("5656");
                                SharedPreferences.Editor editor = sharedpreferences.edit();
                                editor.putString(mb_id, jsonData.getString("mb_id"));
                                editor.commit();

                                //TextView point = (TextView)findViewById(R.id.textView35);
                                //point.setText(sharedpreferences.getString(mb_id, "Not Value"));
                                //point.setText(jsonData.getString("mb_id"));


                                Intent intent = new Intent();
                                intent.setClass(login.this, main_menu.class);
                                startActivity(intent);
                                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                                login.this.finish();
                            }else{
                                new AlertDialog.Builder(login.this)
                                        .setTitle("")//設定視窗標題
                                        //.setIcon(R.mipmap.ic_launcher)//設定對話視窗圖示
                                        .setMessage("帳號密碼錯誤,請重新輸入")//設定顯示的文字
                                        .setPositiveButton("關閉視窗",new DialogInterface.OnClickListener(){
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

                                            }
                                        })//設定結束的子視窗
                                        .show();//呈現對話視窗
                            }


                        }
                    } catch(Exception e) {
                        // Log.e("log_tag", e.toString());
                        new AlertDialog.Builder(login.this)
                                .setTitle("")//設定視窗標題
                                //.setIcon(R.mipmap.ic_launcher)//設定對話視窗圖示
                                .setMessage("帳號密碼錯誤,請重新輸入")//設定顯示的文字
                                .setPositiveButton("關閉視窗",new DialogInterface.OnClickListener(){
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                })//設定結束的子視窗
                                .show();//呈現對話視窗
                    }
                }
            });
        } else{
            Intent intent = new Intent();
            intent.setClass(login.this, main_menu.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            login.this.finish();
            Log.d("b",my_id);
        }


    }
}
