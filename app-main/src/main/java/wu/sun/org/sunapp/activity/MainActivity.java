package wu.sun.org.sunapp.activity;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;
import wu.sun.org.sunapp.R;
import wu.sun.org.sunapp.okhttp.OkHttpTry;
import wu.sun.org.sunapp.permissions.PermissionsTry;
import wu.sun.org.sunapp.util.ITry;

@RuntimePermissions
public class MainActivity extends AppCompatActivity {

    ITry mTry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTry = createTry();

        //添加一个点击事件
        findViewById(R.id.btn_try).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTry.doTry(MainActivity.this);
            }
        });
    }

    private ITry createTry() {
//        return new GsonTry();
        return new OkHttpTry();
    }

    private ITry createPermisstionTry() {
        return new PermissionsTry() {
            @Override
            public void doTry(Context context) {
                super.doTry(context);
                MainActivityPermissionsDispatcher.showCameraWithCheck(MainActivity.this);
            }
        };
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        MainActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @NeedsPermission(Manifest.permission.CAMERA)
    void showCamera() {
        if (mTry instanceof PermissionsTry) {
//            mTry.
        }
        Toast.makeText(this, "show Camera", Toast.LENGTH_SHORT).show();
    }

    @OnShowRationale(Manifest.permission.CAMERA)
    void showRationaleForCamera(final PermissionRequest request) {
        new AlertDialog.Builder(this)
                .setMessage("show camera")
                .setPositiveButton("allow", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        request.proceed();
                    }
                })
                .setNegativeButton("deny", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        request.cancel();
                    }
                })
                .show();

    }

    @OnPermissionDenied(Manifest.permission.CAMERA)
    void showDeniedForCamera() {
        Toast.makeText(this, "deny!", Toast.LENGTH_SHORT).show();
    }

    @OnNeverAskAgain(Manifest.permission.CAMERA)
    void showNeverAskForCamera() {
        Toast.makeText(this, "never ask!", Toast.LENGTH_SHORT).show();
    }


}
