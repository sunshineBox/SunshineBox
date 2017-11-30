package shaolizhi.sunshinebox.ui.phone_number_verify;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.OnClick;
import shaolizhi.sunshinebox.R;
import shaolizhi.sunshinebox.ui.base.BaseActivity;
import shaolizhi.sunshinebox.ui.activation_code_verify.ActivationCodeVerifyActivity;
import shaolizhi.sunshinebox.utils.ToastUtils;

public class PhoneNumberVerifyActivity extends BaseActivity implements PhoneNumberVerifyContract.View {
    @BindView(R.id.phone_number_verify_act_edittext1)
    EditText phoneNumberEdt;

    @BindView(R.id.phone_number_verify_act_edittext2)
    EditText captchaEdt;

    @BindView(R.id.phone_number_verify_act_button1)
    Button sendVerificationCodeButton;

    @OnClick(R.id.phone_number_verify_act_button1)
    public void sendVerificationCode() {
        if (checkPhoneNumber) {
            presenter.tryToRequestCaptcha();
        } else {
            ToastUtils.showToast(getString(R.string.phone_number_verify_act_string5));
        }
    }

    @BindView(R.id.phone_number_verify_act_button2)
    Button commitButton;

    @OnClick(R.id.phone_number_verify_act_button2)
    public void commit() {
        if (checkPhoneNumber) {
            if (checkCaptcha) {
                presenter.tryToVerifyCaptcha();
            } else {
                ToastUtils.showToast(getString(R.string.phone_number_verify_act_string6));
            }
        } else {
            ToastUtils.showToast(getString(R.string.phone_number_verify_act_string5));
        }
    }

    @BindView(R.id.phone_number_verify_act_relativelayout1)
    RelativeLayout relativeLayout;

    PhoneNumberVerifyContract.Presenter presenter;

    private boolean checkPhoneNumber = false;

    private boolean checkCaptcha = false;


    @OnClick(R.id.phone_number_verify_act_imagebutton1)
    public void back() {
        finish();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_phone_number_verify;
    }

    @Override
    protected void created(Bundle bundle) {
        presenter = new PhoneNumberVerifyPresenter(this);
        presenter.start();
    }

    @Override
    protected void resumed() {

    }

    /**
     * 监听软键盘变化，并使Layout一直不被遮挡
     *
     * @param outerViewGroup    传入顶层ViewGroup
     * @param theBottomMostView 处于布局最下面的View
     */

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

    @Override
    public void setResendButtonEnable(Boolean clickable) {
        sendVerificationCodeButton.setEnabled(clickable);
        if (clickable) {
            sendVerificationCodeButton.setBackgroundResource(R.drawable.shape_white_button);
            sendVerificationCodeButton.setTextColor(Color.parseColor("#6bcab2"));
        } else {
            sendVerificationCodeButton.setBackgroundResource(R.drawable.shape_trans_button);
            sendVerificationCodeButton.setTextColor(Color.parseColor("#f9f9f9"));
        }
    }

    @Override
    public void setResendButtonText(String text) {
        sendVerificationCodeButton.setText(text);
    }

    @Override
    public void openVerifyActivationCodeActivity() {
        Intent intent = new Intent(PhoneNumberVerifyActivity.this, ActivationCodeVerifyActivity.class);
        startActivity(intent);
    }

    @Override
    public String getPhoneNumber() {
        return phoneNumberEdt.getText().toString();
    }

    @Override
    public String getCaptcha() {
        return captchaEdt.getText().toString();
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
        return new int[]{R.id.phone_number_verify_act_edittext1, R.id.phone_number_verify_act_edittext2};
    }

    public View[] filterViewByIds() {
        return new View[]{phoneNumberEdt, captchaEdt};
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

    @Override
    public void setUpView() {
        listenToTheSoftKeyboardAndKeepTheLayoutVisible(relativeLayout, commitButton);
        phoneNumberEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                phoneNumberEdt.removeTextChangedListener(this);
                int maxInputPhoneNumber = 11;
                if (editable.length() > maxInputPhoneNumber) {
                    editable.replace(maxInputPhoneNumber, editable.length(), "", 0, 0);
                }
                phoneNumberEdt.addTextChangedListener(this);

                //输入检测
                //输入检测
                String phoneNumberRule = "^1[0-9]{10}$";
                checkPhoneNumber = Pattern.matches(phoneNumberRule, editable);
            }
        });
        captchaEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                captchaEdt.removeTextChangedListener(this);
                int maxInputNumber = 4;
                if (editable.length() > maxInputNumber) {
                    editable.replace(maxInputNumber, editable.length(), "", 0, 0);
                }
                captchaEdt.addTextChangedListener(this);

                String rule = "^[0-9]{4}$";
                checkCaptcha = Pattern.matches(rule, editable);
            }
        });
    }
}
