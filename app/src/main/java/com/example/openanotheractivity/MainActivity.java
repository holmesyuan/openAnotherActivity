package com.example.openanotheractivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Camera;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.server.converter.StringToIntConverter;


import java.io.File;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "com.example.openanotheractiviy.MESSAGE";

    int app_use_times = 0;
    String APP_USE_TIMES_SPREFS = "APP_USE_TIMES_SPREF";
    String APP_USE_TIMES = "APP_USE_TIMES";

    String filename = "myfile";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the user interface layout for this Activity
        setContentView(R.layout.activity_main);//绑定layout里面的activity_main.xml

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            ActionBar actionBar = getSupportActionBar();
            actionBar.setHomeButtonEnabled(false);
        }

    }



    @Override
    public void onDestroy(){
        super.onDestroy();

        android.os.Debug.stopMethodTracing();
    }

    @Override
    public void onPause(){
        super.onPause();

        //看看onPause的函数调用
        TextView textView = (TextView)findViewById(R.id.textView_pauseorstop);
        textView.setText("onPause");
    }

    @Override
    public void onResume(){
        super.onResume();

        SharedPreferences sharedPreferences = getSharedPreferences(APP_USE_TIMES_SPREFS,MODE_PRIVATE);
        app_use_times = sharedPreferences.getInt(APP_USE_TIMES,0);

        TextView textView1 = (TextView)findViewById(R.id.textView_savest);
        textView1.setText(String.valueOf(app_use_times));

        //看看onPause的函数调用
        TextView textView2 = (TextView)findViewById(R.id.textView_pauseorstop);
        textView2.setText("onRsume");

        TextView textView3 = (TextView)findViewById(R.id.textView_file_name);
        textView3.setText(getFilesDir().toString());

        File file = new File(getFilesDir(),filename);
        if(!file.exists()){
            file.mkdir();
        }

    }



    @Override
    protected void onStop() {
        super.onStop(); // Always call the superclass method first

        SharedPreferences sharedPreferences = getSharedPreferences(APP_USE_TIMES_SPREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        app_use_times = app_use_times + 1;
        editor.putInt(APP_USE_TIMES,app_use_times);
        editor.commit();
    }

    public void saveFileClick() {
        String fileContext = "Hello world!!!!!!";
        FileOutputStream outputStream;

        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(fileContext.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void sendMessage(View view){
        String message = ((EditText)findViewById(R.id.edit_message)).getText().toString();

        Intent intent_open = new Intent(this, display.class);
        intent_open.putExtra(EXTRA_MESSAGE,message);
        if(intent_open.resolveActivity(getPackageManager()) != null) {
            startActivity(intent_open);
        }
    }

    public void browsePageClick(View view){
        Intent intent_browsePage = new Intent(Intent.ACTION_VIEW);
        intent_browsePage.setData(Uri.parse("http://www.baidu.com/"));
        if(intent_browsePage.resolveActivity(getPackageManager()) != null) {
            startActivity(intent_browsePage);
        }
    }

    public void openDiaPageClick(View view){
        Intent intent_openDiaPage = new Intent(Intent.ACTION_DIAL);
        intent_openDiaPage.setData(Uri.parse("tel:10086"));
        if (intent_openDiaPage.resolveActivity(getPackageManager()) != null) {
            startActivity(intent_openDiaPage);
        }
    }

    public void dialPhoneClick(View view){
        Intent intent_dialPhone = new Intent(Intent.ACTION_CALL);
        intent_dialPhone.setData(Uri.parse("tel:10086"));
        if(intent_dialPhone.resolveActivity(getPackageManager()) != null) {
            startActivity(intent_dialPhone);
        }
    }

    public void openMsgPageClick(View view){
        Intent intent_openMsgPage = new Intent(Intent.ACTION_VIEW);
        intent_openMsgPage.setType("vnd.android-dir/mms-sms");
        intent_openMsgPage.putExtra("sms_body","Hello Baby!!!");
        if(intent_openMsgPage.resolveActivity(getPackageManager()) != null) {
            startActivity(intent_openMsgPage);
        }
    }

    public void sendMsgClick(View view){
        Intent intent_sendMsg = new Intent(Intent.ACTION_VIEW);
        intent_sendMsg.setData(Uri.parse("smsto:15220772812"));
        intent_sendMsg.putExtra("sms_body","Hello baby!!!");
        if(intent_sendMsg.resolveActivity(getPackageManager()) != null) {
            startActivity(intent_sendMsg);
        }
    }

    public void playMusicClick(View view){
        Intent intent_playMusic = new Intent(Intent.ACTION_VIEW);
        //Uri uri = Uri.parse("file:///storage/sdcard0/平凡之路.mp3");//SD卡根目录
        Uri uri = Uri.parse("file:///storage/sdcard0/netease/cloudmusic/Music/平凡之路.mp3");
        intent_playMusic.setDataAndType(uri,"audio/mp3");
        if(intent_playMusic.resolveActivity(getPackageManager()) != null) {
            startActivity(intent_playMusic);
        }
    }

}
