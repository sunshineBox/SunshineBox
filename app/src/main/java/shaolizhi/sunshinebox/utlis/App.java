package shaolizhi.sunshinebox.utlis;

import android.app.Application;
import android.content.Context;

/**
 * 由邵励治于2017/10/23创造.
 */

public class App extends Application {
    public static Context mAppContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppContext = this;
    }
}
