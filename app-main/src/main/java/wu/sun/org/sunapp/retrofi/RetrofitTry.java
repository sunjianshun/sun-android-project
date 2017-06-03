package wu.sun.org.sunapp.retrofi;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import wu.sun.org.sunapp.util.ITry;

/**
 * Created by jianshunsun on 17/3/30.
 */
public class RetrofitTry implements ITry {

    public interface Baidu {
        @NonNull
        @GET("https://www.baidu.com/s?wd=%E4%B8%AD%E5%9B%BD")
        Call<String> call();
    }

    @Override
    public void doTry(Context context) {
        try {
            tryBaidu();
//        tryGitHub();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void tryBaidu() throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.baidu.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Baidu baidu = retrofit.create(Baidu.class);
        Call<String> call = baidu.call();
        String value = call.execute().body();

        Log.i("RetrofitTry", value);
    }

    private void tryGitHub() throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create an instance of our GitHub API interface.
        GitHub github = retrofit.create(GitHub.class);

        // Create a call instance for looking up Retrofit contributors.
        Call<List<Contributor>> call = github.contributors("square", "retrofit");

        // Fetch and print a list of the contributors to the library.
        List<Contributor> contributors = call.execute().body();
        for (Contributor contributor : contributors) {
            System.out.println(contributor.login + " (" + contributor.contributions + ")");
        }
    }

    public static final String API_URL = "https://api.github.com";

    public static class Contributor {
        public final String login;
        public final int contributions;

        public Contributor(String login, int contributions) {
            this.login = login;
            this.contributions = contributions;
        }
    }

    public interface GitHub {
        @NonNull
        @GET("/repos/{owner}/{repo}/contributors")
        Call<List<Contributor>> contributors(
                @Path("owner") String owner,
                @Path("repo") String repo);
    }


}
