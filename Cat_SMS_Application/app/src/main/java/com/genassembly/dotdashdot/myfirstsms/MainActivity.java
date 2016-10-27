package com.genassembly.dotdashdot.myfirstsms;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.media.Image;
import android.media.MediaPlayer;
import android.media.session.MediaController;
import android.net.Uri;
import android.provider.CalendarContract;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    VideoView myVideoView;
    ImageView myImageView;
    boolean toggle = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myVideoView = (VideoView) findViewById(R.id.videoView);
        myImageView = (ImageView) findViewById(R.id.imageView);

        try {
            //set the uri of the video to be played
            myVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.cat_thing));
        } catch (Exception e) {
            Log.i("Error", e.getMessage());
        }
        myVideoView.requestFocus();

//        we also set an setOnPreparedListener in order to know when the video file is ready for playback
        myVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            public void onPrepared(MediaPlayer mediaPlayer) {
                myVideoView.start();
            }
        });

    }


    @Override
    public void onStart(){
        super.onStart();
        readSMS();
        ArrayList<String[]> allContacts = readContacts();

        Log.i("LOG", "Just added line for debug point");

        //sendEmail();
        //printProviders();
        //readBrowser();
    }

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_SMS,
            Manifest.permission.READ_CONTACTS
    };

    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_SMS);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity, PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

    public void printProviders(){
        for (PackageInfo pack : getPackageManager().getInstalledPackages(PackageManager.GET_PROVIDERS)) {
            ProviderInfo[] providers = pack.providers;
            if (providers != null) {
                for (ProviderInfo provider : providers) {
                    try {
                        getContentResolver().query(Uri.parse("content://" + provider.authority + "/"), null, null, null, null);
                        Log.d("Example", "provider checks out: " + provider.authority);
                    } catch (IllegalArgumentException e) {
                        //This provider doesn't let you see the database
                    } catch (SecurityException e) {
                        Log.d("Example", "provider needs permissions: " + provider.authority);
                    } catch (Exception e) {
                        //Log.d("Example", e.getMessage());
                        Log.d("Example", "provider has another exception...: " + provider.authority);
                    }
                }
            }
        }
    }

    public void sendEmail(){
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, "m@mi6.com");
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message goes here");

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            Log.i("","Finished sending email...");
        }
        catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }

    public void readBrowser(){
        Cursor cursor = getContentResolver().query(Uri.parse("content://settings/"), null, null, null, null);
        readAll(cursor);
    }

    public void readSMS(){
        Cursor cursor = getContentResolver().query(Uri.parse("content://sms/"), null, null, null, null);
        ListView listy = (ListView) findViewById(R.id.listView);
        SimpleAdapter adapty = new SimpleAdapter(this, cursor);
        if (listy != null){
            listy.setAdapter(adapty);
        }
    }

    public ArrayList<String[]> readContacts(){

        String[] tableColumns = new String[] {
                "_id",
                "display_name",
                "data1"
        };

        ArrayList<String[]> contactLists = new ArrayList<>();

        Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, tableColumns, null, null, null);
        for (int k = 0; k < phones.getCount(); k++) {
            phones.moveToPosition(k);
            String[] info = new String[3];
            for (int i = 0; i < phones.getColumnCount(); i++) {
                Log.i("readPhone", "Col: " + i + " - " + phones.getColumnName(i) + " val: " + phones.getString(phones.getColumnIndexOrThrow(phones.getColumnName(i))));
                info[i]=phones.getString(phones.getColumnIndexOrThrow(phones.getColumnName(i)));
            }
            contactLists.add(info);
        }


        ListView listy = (ListView) findViewById(R.id.listView2);
        ContactsAdapter adapty = new ContactsAdapter(this, phones);
        if (listy != null){
            listy.setAdapter(adapty);
        }

        return contactLists;

    }

    public void readAll(Cursor c){
        for (int j = 0; j < c.getCount(); j++) {
            c.moveToPosition(j);
            for (int i = 0; i < c.getColumnCount(); i++) {
                Log.i("readCursor", "Col: " + i + " - " + c.getColumnName(i) + " val: " + c.getString(c.getColumnIndexOrThrow(c.getColumnName(i))));
            }
        }
        c.close();
    }
}
