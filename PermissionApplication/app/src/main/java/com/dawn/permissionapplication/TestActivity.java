package com.dawn.permissionapplication;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.dawn.permissionapplication.permission.PermissionUtil;
import com.dawn.permissionapplication.permission2.PermissionHelper;
import com.dawn.permissionapplication.permission2.inter.PermissionSuccess;


public class TestActivity extends AppCompatActivity {

    TextView tv_permssion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_permssion = findViewById(R.id.permission);
        tv_permssion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                requestPermissionFunction();
                test();
            }
        });
    }


    private void test(){
        PermissionHelper.with(this).requestCode(100).requestPermission(Manifest.permission.READ_CONTACTS).request();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionHelper.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    @PermissionSuccess(requestCode = 100)
    private void permissionRequestSuccess(){
        Log.d("permissionRequest","permissionRequest Success");
    }

}
