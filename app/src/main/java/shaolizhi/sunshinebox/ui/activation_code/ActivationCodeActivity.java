package shaolizhi.sunshinebox.ui.activation_code;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import butterknife.BindView;
import shaolizhi.sunshinebox.R;
import shaolizhi.sunshinebox.ui.base.BaseActivity;

public class ActivationCodeActivity extends BaseActivity {
    @BindView(R.id.activation_code_act_edittext1)
    EditText phoneNumberEdt;

    @BindView(R.id.activation_code_act_edittext2)
    EditText verificationCodeEdt;

    @BindView(R.id.activation_code_act_button2)
    Button commitButton;

    @BindView(R.id.activation_code_act_relativelayout1)
    RelativeLayout relativeLayout;

    @Override
    protected int layoutId() {
        return R.layout.activity_activation_code;
    }

    @Override
    protected void created(Bundle bundle) {
        controlKeyboardLayout(this, relativeLayout, commitButton);
    }

    @Override
    protected void resumed() {

    }

    private boolean flag = true;

    public void controlKeyboardLayout(Context context, final View root, final View scrollToView) {
        final int navigationBarHeight = getNavigationBarHeight(context);

        root.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect rect = new Rect();
                //获取root在窗体的可视区域
                root.getWindowVisibleDisplayFrame(rect);
                //获取root在窗体的不可视区域高度(被其他View遮挡的区域高度)
                int rootInvisibleHeight = root.getRootView().getHeight() - rect.bottom;
                //若不可视区域高度大于100，则键盘显示
                if (rootInvisibleHeight > navigationBarHeight && flag) {
                    int[] location = new int[2];
                    //获取scrollToView在窗体的坐标
                    scrollToView.getLocationInWindow(location);
                    //计算root滚动高度，使scrollToView在可见区域
                    int scrollHeight = (location[1] + scrollToView.getHeight()) - rect.bottom;
                    if (root.getScrollY() != 0) {// 如果已经滚动，要根据上次滚动，重新计算位置。
                        scrollHeight += root.getScrollY();
                    }
                    root.scrollTo(0, scrollHeight);
                } else {
                    //键盘隐藏
                    root.scrollTo(0, 0);
                }
            }
        });
    }

    //获取虚拟按键高度
    private int getNavigationBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        return resources.getDimensionPixelSize(resourceId);
    }

    //让View失去焦点
    public void clearViewFocus(View v, int... ids) {
        if (null != v && null != ids && ids.length > 0) {
            for (int id : ids) {
                if (v.getId() == id) {
                    v.clearFocus();
                    break;
                }
            }
        }
    }

    public boolean isFocusEditText(View v, int... ids) {
        if (v instanceof EditText) {
            EditText tmp_et = (EditText) v;
            for (int id : ids) {
                if (tmp_et.getId() == id) {
                    return true;
                }
            }
        }
        return false;
    }


    public boolean isTouchView(View[] views, MotionEvent ev) {
        if (views == null || views.length == 0) return false;
        int[] location = new int[2];
        for (View view : views) {
            view.getLocationOnScreen(location);
            int x = location[0];
            int y = location[1];
            if (ev.getX() > x && ev.getX() < (x + view.getWidth())
                    && ev.getY() > y && ev.getY() < (y + view.getHeight())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            if (isTouchView(filterViewByIds(), ev)) return super.dispatchTouchEvent(ev);
            if (hideSoftByEditViewIds() == null || hideSoftByEditViewIds().length == 0)
                return super.dispatchTouchEvent(ev);
            View v = getCurrentFocus();
            if (isFocusEditText(v, hideSoftByEditViewIds())) {
                //隐藏键盘
                hideInputForce(this);
                clearViewFocus(v, hideSoftByEditViewIds());
            }
        }
        return super.dispatchTouchEvent(ev);

    }

    public int[] hideSoftByEditViewIds() {
        return new int[]{R.id.activation_code_act_edittext1, R.id.activation_code_act_edittext2};
    }

    public View[] filterViewByIds() {
        return new View[]{phoneNumberEdt, verificationCodeEdt};
    }

    public static void hideInputForce(Activity activity) {
        if (activity == null || activity.getCurrentFocus() == null)
            return;

        InputMethodManager inputMethodManager = ((InputMethodManager) activity.getSystemService(INPUT_METHOD_SERVICE));
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus()
                    .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
