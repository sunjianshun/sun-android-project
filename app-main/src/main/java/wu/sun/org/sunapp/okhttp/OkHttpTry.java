package wu.sun.org.sunapp.okhttp;

import android.content.Context;
import android.util.Log;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import wu.sun.org.sunapp.util.ITry;

/**
 * Created by hzsunjianshun on 17/8/15.
 */

public class OkHttpTry implements ITry {

    private final static String TAG = "OkHttpTry";

    private final OkHttpClient client = new OkHttpClient();

    @Override
    public void doTry(Context context) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpTry.this.run();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }



    private void run() throws Exception {
        Request request = new Request.Builder()
                .url("http://publicobject.com/helloworld.txt")
                .build();

        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new IOException("Unexpected code " + response);
        }

        Headers responseHeaders = response.headers();
        for (int i = 0; i < responseHeaders.size(); i++) {
            Log.d(TAG, responseHeaders.name(i) + ": " + responseHeaders.value(i));
        }

        Log.d(TAG, response.body().string());
    }
}
