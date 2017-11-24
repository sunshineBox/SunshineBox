package shaolizhi.sunshinebox.utils;

import android.app.Activity;
import android.widget.Toast;

/**
 * 由邵励治于2016/6/20创造.
 */
public class ToastUtils {

    private static Toast mToast;

    /**
     * 确保运行在主线程
     */
    public static void showToast(final Activity context, final String message) {
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mToast == null) {
                    mToast = Toast.makeText(App.mAppContext, message, Toast.LENGTH_SHORT);
                }
                mToast.setText(message);
                mToast.show();
            }
        });
    }

    public static void showLongToast(final Activity context, final String message) {
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mToast == null) {
                    mToast = Toast.makeText(App.mAppContext, message, Toast.LENGTH_LONG);
                }
                mToast.setText(message);
                mToast.show();
            }
        });
    }

    /**
     * 使用前请确认已经运行在UI线程
     */
    public static void showToast(String message) {
        if (mToast == null) {
            mToast = Toast.makeText(App.mAppContext, message, Toast.LENGTH_SHORT);
        }
        mToast.setText(message);
        mToast.show();
    }
}
