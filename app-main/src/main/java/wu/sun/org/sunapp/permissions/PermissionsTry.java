package wu.sun.org.sunapp.permissions;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;
import wu.sun.org.sunapp.activity.MainActivity;
import wu.sun.org.sunapp.util.ITry;

/**
 * Created by hzsunjianshun on 17/6/3.
 */

public class PermissionsTry implements ITry {

    private Context mContext;

    @Override
    public void doTry(Context context) {
        mContext = context;
    }

    void showCamera() {
        Toast.makeText(mContext, "show Camera", Toast.LENGTH_SHORT).show();
    }

    void showRationaleForCamera(final PermissionRequest request) {
        new AlertDialog.Builder(mContext)
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

    void showDeniedForCamera() {
        Toast.makeText(mContext, "deny!", Toast.LENGTH_SHORT).show();
    }

    void showNeverAskForCamera() {
        Toast.makeText(mContext, "never ask!", Toast.LENGTH_SHORT).show();
    }
}
