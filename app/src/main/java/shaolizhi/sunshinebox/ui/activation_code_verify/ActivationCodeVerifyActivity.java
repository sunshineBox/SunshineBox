package shaolizhi.sunshinebox.ui.activation_code_verify;

import android.app.Activity;
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
import butterknife.OnClick;
import shaolizhi.sunshinebox.R;
import shaolizhi.sunshinebox.ui.base.BaseActivity;

public class ActivationCodeVerifyActivity extends BaseActivity implements ActivationCodeVerifyContract.View {

    @BindView(R.id.activation_code_verify_act_relativelayout)
    RelativeLayout relativeLayout;

    @OnClick(R.id.activation_code_verify_act_button)
    public void commit() {

    }

    @OnClick(R.id.activation_code_verify_act_imagebutton)
    public void back() {
        finish();
    }

    @BindView(R.id.activation_code_verify_act_edittext1)
    EditText activationCodeEdt;

    @BindView(R.id.activation_code_verify_act_button)
    Button commitButton;

    ActivationCodeVerifyContract.Presenter presenter;


    @Override
    protected int layoutId() {
        return R.layout.activity_activation_code_verify;
    }

    @Override
    protected void created(Bundle bundle) {
        presenter = new ActivationCodeVerifyPresenter(this);
        presenter.start();
    }

    @Override
    protected void resumed() {

    }

    @Override
    public void setUpView() {
        listenToTheSoftKeyboardAndKeepTheLayoutVisible(relativeLayout, commitButton);
    }

    //获取软键盘高度
    private int getSoftKeyBoardHeight() {
        Rect rect = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        return getWindow().getDecorView().getBottom() - rect.bottom;
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
            if (ev.getX() > x
                    && ev.getX() < (x + view.getWidth())
                    && ev.getY() > y
                    && ev.getY() < (y + view.getHeight())) {
                return true;
            }
        }
        return false;
    }

    //Activity的点击事件分发
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //若屏幕上有按下操作
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            if (isTouchView(filterViewByIds(), ev)) {
                return super.dispatchTouchEvent(ev);
            }
            if (hideSoftByEditViewIds() == null || hideSoftByEditViewIds().length == 0) {
                return super.dispatchTouchEvent(ev);
            }
            View v = getCurrentFocus();
            if (isFocusEditText(v, hideSoftByEditViewIds())) {
                //隐藏键盘
                hideSoftKeyboard(this);
                clearViewFocus(v, hideSoftByEditViewIds());
            }
        }
        return super.dispatchTouchEvent(ev);

    }

    public int[] hideSoftByEditViewIds() {
        return new int[]{R.id.activation_code_verify_act_edittext1};
    }

    public View[] filterViewByIds() {
        return new View[]{activationCodeEdt};
    }

    //隐藏软键盘
    public static void hideSoftKeyboard(Activity activity) {
        if (activity == null || activity.getCurrentFocus() == null) {
            return;
        }
        InputMethodManager inputMethodManager = ((InputMethodManager) activity.getSystemService(INPUT_METHOD_SERVICE));
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus()
                    .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public void listenToTheSoftKeyboardAndKeepTheLayoutVisible(final View outerViewGroup, final View theBottomMostView) {
        outerViewGroup.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int l = theBottomMostView.getBottom();
                int ll = getWindow().getDecorView().getBottom() - getSoftKeyBoardHeight();
                int scrollY = l - ll;
                if (getSoftKeyBoardHeight() == 0) {
                    //软键盘没有弹出
                    outerViewGroup.scrollTo(0, 0);
                } else {
                    //软键盘弹出
                    if (outerViewGroup.getScaleY() != 0) {
                        scrollY += outerViewGroup.getScrollY();
                    }
                    outerViewGroup.scrollTo(0, scrollY);
                }
            }
        });
    }

}
