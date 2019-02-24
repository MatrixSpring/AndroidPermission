package com.dawn.permissionapplication.permission2;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.dawn.permissionapplication.permission.PermissionUtil;
import com.dawn.permissionapplication.permission2.inter.PermissionSuccess;

import java.lang.reflect.Method;
import java.security.Permission;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 参数：上下文 申请的code 权限的列表
 * 参数传递方式：链式传进来 普通传进来
 * 工具类的内存存在形式 单例也可以是消耗品 先弄成消耗品
 * 结果返回：接口返回，通过上下文反射获取有标记的方法
 * 请求方式封装：通过传进来的activity 或者fragment 获取activity
 * 通过activity的自带的权限请求方法请求权限，activity自带权限返回接口
 */
public class PermissionHelper {
    private Object mObject;
    private int mRequestCode;
    private String[] mRequestPemission;

    private PermissionHelper(Object object) {
        this.mObject = object;
    }


    public static PermissionHelper with(Activity activity){
        return new PermissionHelper(activity);
    }

    public static PermissionHelper with(Fragment fragment){
        return new PermissionHelper(fragment);
    }

    public PermissionHelper requestCode(int mRequestCode){
        this.mRequestCode = mRequestCode;
        return this;
    }

    public PermissionHelper requestPermission(String... permissions){
        this.mRequestPemission = permissions;
        return this;
    }

    /**
     * 发起权限请求 需要对平台版本做判断
     */
    public void request(){
        if(isOverMarshmallow()){
            requestPermission();
        }else{
            executeSuccessMethod(mObject, mRequestCode);
        }

    }

    /**
     * 判断系统版本
     * @return
     */
    private boolean isOverMarshmallow(){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 请求权限
     */
    private void requestPermission(){
        //判断是否授予了权限
//        getActivity().checkSelfPermission();
        List<String> tempPermission = new ArrayList<>();
        for(int i=0; i<mRequestPemission.length; i++){
            if(ActivityCompat.checkSelfPermission(getActivity(mObject),mRequestPemission[i]) == PackageManager.PERMISSION_DENIED){
                tempPermission.add(mRequestPemission[i]);
            }

        }

        //如果授予了直接执行成功方法
        if(tempPermission.size() == 0){
            executeSuccessMethod(mObject, mRequestCode);
        }

        //请求权限

        ActivityCompat.requestPermissions(getActivity(mObject), tempPermission.toArray(new String[tempPermission.size()]), mRequestCode);
    }



    public static void onRequestPermissionsResult(Activity activity, int requestCode, String[] permissions,int[] grantResults) {
//        requestResult(activity, requestCode, permissions, grantResults);
        Log.d("1111111111111","1111111111111 "+requestCode) ;
    }


    public static void onRequestPermissionsResult(Fragment activity, int requestCode, String[] permissions,int[] grantResults) {
//        requestResult(activity, requestCode, permissions, grantResults);
        Log.d("1111111111111","1111111111111");
    }


    /**
     * 成功执行
     */
    private void executeSuccessMethod(Object reflectObject, int requestCode){
        Method method = PermissionUtils.
        return null;
    }

    /**
     * 失败执行
     */
    private void executeFailureMethod(Object reflectObject, int requestCode){


    }

    private Activity getActivity(Object mObject){
        if(mObject instanceof Activity){
            return (Activity) mObject;
        }else if(mObject instanceof Fragment){
            return ((Fragment)mObject).getActivity();
        }
        return null;
    }


}
