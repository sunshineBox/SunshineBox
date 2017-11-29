package shaolizhi.sunshinebox.ui.activation_code;

import android.os.CountDownTimer;
import android.support.annotation.NonNull;

import shaolizhi.sunshinebox.R;
import shaolizhi.sunshinebox.ui.base.BaseActivity;
import shaolizhi.sunshinebox.ui.base.BaseFragment;
import shaolizhi.sunshinebox.utils.App;

/**
 * 由邵励治于2017/11/29创造.
 */

public class ActivationCodePresenter implements ActivationCodeContract.Presenter, ActivationCodeContract.CallBack {

    private ActivationCodeContract.View view;

    private ActivationCodeContract.Model model;

    ActivationCodePresenter(ActivationCodeContract.View view) {
        this.view = view;
        model = new ActivationCodeModel(this);
    }

    private Boolean isTimerRunning = false;

    private CountDownTimer timer = new CountDownTimer(60000, 1000) {
        @Override
        public void onTick(long l) {
            if (view != null) {
                view.setResendButtonEnable(false);
                view.setResendButtonText(String.valueOf(l / 1000));
                isTimerRunning = true;
            }
        }

        @Override
        public void onFinish() {
            if (view != null) {
                view.setResendButtonEnable(true);
                view.setResendButtonText(App.mAppContext.getString(R.string.activation_code_act_string4));
                isTimerRunning = false;
            }
        }
    };

    @Override
    public void start() {
        view.setUpView();
    }

    @Override
    public void tryToRequestVerificationCode() {
        startCountDown();
        model.requestVerificationCodeBean(view.getPhoneNumber());
    }

    @Override
    public void startCountDown() {
        if (!isTimerRunning) {
            timer.start();
        }
    }

    @Override
    public void requestVerificationCodeBeanSuccess(@NonNull VerificationCodeBean bean) {

    }

    @Override
    public void requestVerificationCodeBeanFailure() {
        timer.cancel();
        view.setResendButtonEnable(true);
        view.setResendButtonText(App.mAppContext.getString(R.string.activation_code_act_string4));
        isTimerRunning = false;

        if (view instanceof BaseFragment) {
            ((BaseFragment) view).showToastForRequestResult("403");
        }
        if (view instanceof BaseActivity) {
            ((BaseActivity) view).showToastForRequestResult("403");
        }
    }
}
