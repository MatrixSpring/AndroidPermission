package com.dawn.permissionapplication;

import android.Manifest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.dawn.permissionapplication.permission.PermissionListener;
import com.dawn.permissionapplication.permission.PermissionUtil;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    PermissionUtil permissionUtil;
    TextView tv_permssion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        permissionUtil = new PermissionUtil(MainActivity.this);
        tv_permssion = findViewById(R.id.permission);
        tv_permssion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestPermissionFunction();
            }
        });
    }

    private void requestPermissionFunction() {
        permissionUtil.requestPermissions(new String[]{Manifest.permission.CALL_PHONE},
                new PermissionListener() {
                    @Override
                    public void onGranted() {
                        Toast.makeText(MainActivity.this, "申请成功", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onDenied(List<String> deniedPermission) {
                        //Toast第一个被拒绝的权限
                    }

                    @Override
                    public void onShouldShowRationale(List<String> deniedPermission) {
                    }
                });
    }



}
