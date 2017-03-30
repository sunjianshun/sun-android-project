package wu.sun.org.sunapp.retrofi;

import android.util.Log;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;

/**
 * Created by jianshunsun on 17/3/30.
 */
public class TryRetrofit {

    public interface Baidu {
        @GET("")
        Call<String> call();
    }

    public void toTry() throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.baidu.com")
                .build();

        Baidu baidu = retrofit.create(Baidu.class);
        Call<String> call = baidu.call();
        String value = call.execute().body();

        Log.i("TryRetrofit", value);
    }
}
