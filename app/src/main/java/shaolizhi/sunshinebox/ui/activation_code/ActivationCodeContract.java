package shaolizhi.sunshinebox.ui.activation_code;

import android.support.annotation.NonNull;

import shaolizhi.sunshinebox.ui.base.BasePresenter;
import shaolizhi.sunshinebox.ui.base.BaseView;

/**
 * 由邵励治于2017/11/29创造.
 */

public interface ActivationCodeContract {
    interface View extends BaseView {
        void listenToTheSoftKeyboardAndKeepTheLayoutVisible(final android.view.View outerViewGroup, final android.view.View theBottomMostView);

        void setResendButtonEnable(Boolean clickable);

        void setResendButtonText(String text);

        String getPhoneNumber();
    }

    interface Presenter extends BasePresenter {
        void tryToRequestVerificationCode();

        void startCountDown();
    }

    interface Model {
        void requestVerificationCodeBean(@NonNull String phoneNumber);
    }

    interface CallBack {
        void requestVerificationCodeBeanSuccess(@NonNull VerificationCodeBean bean);

        void requestVerificationCodeBeanFailure();
    }
}
