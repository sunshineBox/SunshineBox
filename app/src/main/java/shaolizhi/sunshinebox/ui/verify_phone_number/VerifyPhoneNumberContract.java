package shaolizhi.sunshinebox.ui.verify_phone_number;

import android.support.annotation.NonNull;

import shaolizhi.sunshinebox.ui.base.BasePresenter;
import shaolizhi.sunshinebox.ui.base.BaseView;

/**
 * 由邵励治于2017/11/29创造.
 */

public interface VerifyPhoneNumberContract {
    interface View extends BaseView {
        void listenToTheSoftKeyboardAndKeepTheLayoutVisible(final android.view.View outerViewGroup, final android.view.View theBottomMostView);

        void setResendButtonEnable(Boolean clickable);

        void setResendButtonText(String text);

        String getPhoneNumber();

        String getCaptcha();
    }

    interface Presenter extends BasePresenter {
        void tryToRequestCaptcha();

        void tryToVerifyCaptcha();

        void startCountDown();
    }

    interface Model {
        void requestSendCaptchaBean(@NonNull String phoneNumber);

        void requestCheckCaptchaBean(@NonNull String phoneNumber, @NonNull String captcha);
    }

    interface CallBack {
        void requestSendCaptchaBeanSuccess(@NonNull SendCaptchaBean bean);

        void requestSendCaptchaBeanFailure();

        void requestCheckCaptchaBeanSuccess(@NonNull CheckCaptchaBean bean);

        void requestCheckCaptchaBeanFailure();
    }
}
