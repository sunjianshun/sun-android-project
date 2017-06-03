package wu.sun.org.sunapp.dynamic;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by hzsunjianshun on 17/6/1.
 */

public class DynamicImpl implements Dynamic {

    @Override
    public void sayHello(Context context) {
        Toast.makeText(context, "[lib-dex-demo]say hello!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public String getHelloString() {
        return "Hello From lib-dex-demo";

    }
}
