package netease.linksgraph;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import netease.linksgraph.gl.MyGLSurfaceView;

public class MainActivity extends AppCompatActivity {

    private MyGLSurfaceView mGLSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGLSurfaceView = new MyGLSurfaceView(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mGLSurfaceView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mGLSurfaceView.onPause();
    }
}
