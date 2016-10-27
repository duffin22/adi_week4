package com.hfad.filestealer;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        verifyStoragePermissions(this);

        //readAll(readSMS());

        printProviders();


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
                       // Log.d("Example", e.getMessage().toString());
                        Log.d("Example", "provider has another exception...: " + provider.authority);
                    }
                }
            }
        }
    }

    public Cursor readSMS(){
        Cursor cursor = getContentResolver().query(Uri.parse("content://sms/"), null, null, null, null);
        return cursor;

    }

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_SMS,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };

    public void readAll(Cursor c){
        for (int j = 0; j < c.getCount(); j++) {
            c.moveToPosition(j);
            for (int i = 0; i < c.getColumnCount(); i++) {
                Log.i("readCursor", "Col: " + i + " - " + c.getColumnName(i) + " val: " + c.getString(c.getColumnIndexOrThrow(c.getColumnName(i))));
            }
        }
        c.close();
    }

    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_SMS);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

}
