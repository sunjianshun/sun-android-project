package wu.sun.org.sunapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.io.IOException;

import wu.sun.org.sunapp.R;
import wu.sun.org.sunapp.retrofi.TryRetrofit;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            new TryRetrofit().toTry();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
