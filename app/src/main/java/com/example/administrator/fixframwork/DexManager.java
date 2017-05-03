package com.example.administrator.fixframwork;

import android.content.Context;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import dalvik.system.DexFile;

/**
 * Created by Administrator on 2017/5/2 0002.
 */

public class DexManager {
    private String TAG="dongnao";
    private Context context;

    public DexManager(Context context) {
        this.context = context;
    }

    public void loadDex(File dexFilePath) {
        File optFile=new File(context.getFilesDir(),dexFilePath.getName());
        if(optFile.exists())
        {
            optFile.delete();
        }
        try {
            //将外置的dex文件加载内存
            DexFile dexFile=DexFile.loadDex(dexFilePath.getAbsolutePath(),optFile.getAbsolutePath(),Context.MODE_PRIVATE);
            Enumeration<String> entry=dexFile.entries();
            //遍历dex里面的class类
            while (entry.hasMoreElements()) {
                String  className=entry.nextElement();
                Class realClazz=dexFile.loadClass(className,context.getClassLoader());
                if (realClazz != null) {
                    Log.i(TAG,"----找到类    "+className);
                    fixMethod(realClazz);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void fixMethod(Class realClazz) {
        Method[] methods=realClazz.getMethods();
        for (Method method : methods) {
            //拿到注解
            Replace replace=  method.getAnnotation(Replace.class);
            if (replace == null) {
                continue;
            }
            String wrongClazz=replace.clazz();
            String wrongMethodName=replace.method();
            try {
                Class wrongClass=Class.forName(wrongClazz);
                Method wrongMethod=wrongClass.getMethod(wrongMethodName,method.getParameterTypes());
                replaceNative(wrongMethod,method);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }
    static {
        System.loadLibrary("native-lib");
    }
    public static native void replaceNative(Method wrongMethod,Method method);
}
