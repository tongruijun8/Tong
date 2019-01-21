package com.trj.tlib.uils;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class AssestUtil {

    public String getAssestString(Context context,String assestFileName){
        try {
            return toString(context.getAssets().open(assestFileName));
        } catch (IOException e) {
            Logger.e("AssestUtil","assest 文件数据异常");
            return "";
        }
    }

    public InputStream getAssestInputStream(Context context,String assestFileName){
        try {
            return context.getAssets().open(assestFileName);
        } catch (IOException e) {
            Logger.e("AssestUtil","assest 文件数据异常");
            return null;
        }
    }


    public String toString(InputStream is) {
        return toString(is, "utf-8");
    }

    public String toString(InputStream is, String charset) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, charset));
            while(true) {
                String line = reader.readLine();
                if (line == null) {
                    reader.close();
                    is.close();
                    break;
                }

                sb.append(line).append("\n");
            }
        } catch (IOException var5) {
            Logger.e("AssestUtil","var5 = " + var5);
        }
        return sb.toString();
    }

}
