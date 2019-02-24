package com.dawn.permissionapplication.permission2;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class PermissionUtils {

    /**
     * 判断当前的系统版本和Android.6
     * @return
     */
    public static boolean isOverMarshmallow() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    /**
     * 通过传进来的上下文获取activity
     * @param mObject
     * @return
     */
    public Activity getActivity(Object mObject){
        if(null == mObject){
            return null;
        }
        if(mObject instanceof Activity){
            return (Activity)mObject;
        }else if(mObject instanceof Fragment){
            return ((Fragment)mObject).getActivity();
        }else{
            return null;
        }
    }

    /**
     * 获取还没授权的permission
     * @param activity
     * @param permission
     * @return
     */
    @TargetApi(value = Build.VERSION_CODES.M)
    public static List<String> getRejectPermission(Activity activity, String... permission){
        List<String> denyPermissions = new ArrayList<>();
        for(String strPermission: permission){
            if(activity.checkSelfPermission(strPermission) != PackageManager.PERMISSION_GRANTED){
                denyPermissions.add(strPermission);
            }
        }
        return denyPermissions;
    }



}
