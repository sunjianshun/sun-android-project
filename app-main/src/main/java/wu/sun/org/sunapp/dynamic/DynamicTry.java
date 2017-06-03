package wu.sun.org.sunapp.dynamic;

import android.content.Context;

import java.io.File;
import java.io.IOException;

import dalvik.system.DexClassLoader;
import wu.sun.org.sunapp.util.FileUtils;
import wu.sun.org.sunapp.util.ITry;

/**
 * Created by hzsunjianshun on 17/6/2.
 */

public class DynamicTry implements ITry {

    @Override
    public void doTry(Context context) {
        loadDexClass(context);
    }

    /**
     * 加载dex文件中的class，并调用其中的sayHello方法
     */
    private void loadDexClass(Context context) {
        File cacheFile = FileUtils.getCacheDir(context.getApplicationContext());
        String internalPath = cacheFile.getAbsolutePath() + File.separator + "plugin-demo-release-unsigned.apk";
        File desFile = new File(internalPath);
        try {
            if (!desFile.exists()) {
                desFile.createNewFile();
                FileUtils.copyFiles(context, "plugin-demo-release-unsigned.apk", desFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //下面开始加载dex class
        DexClassLoader dexClassLoader = new DexClassLoader(internalPath, cacheFile.getAbsolutePath(), null, context.getClassLoader());
        try {
            dexClassLoader.getResource("app_name");

            Class libClazz = dexClassLoader.loadClass("wu.sun.org.sunapp.dynamic.DynamicImpl");
            Dynamic dynamic = (Dynamic) libClazz.newInstance();
            if (dynamic != null) {
                dynamic.sayHello(context);
//                Toast.makeText(this, dynamic.sayHello(), Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
