package beginner.taiwan.adoptiontest;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2017/12/8.
 */

public class ParseJsonData {

    public  String ParseJsonData(Context context) {
        AssetManager assetManager = context.getAssets();//用AssetManager調用assets資料夾
        InputStream inputStream;
        String string = null;
        try {
            inputStream = assetManager.open("json_data.txt");//調用資料
            int size = inputStream.available();//判斷資料中有多大
            byte[] bytes = new byte[size];//設定一個和資料一樣大的byte
            inputStream.read(bytes);//一個一個寫進去
            inputStream.close();//關閉
            string = new String(bytes);//把byte轉String
        } catch (IOException e) {
            e.printStackTrace();
        }
        return string;
    }
}
