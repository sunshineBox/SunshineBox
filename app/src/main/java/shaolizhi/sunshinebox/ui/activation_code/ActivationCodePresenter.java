package shaolizhi.sunshinebox.ui.activation_code;

import android.support.annotation.NonNull;

/**
 * 由邵励治于2017/11/29创造.
 */

public class ActivationCodePresenter implements ActivationCodeContract.Presenter, ActivationCodeContract.CallBack {

    private ActivationCodeContract.View view;

    public ActivationCodePresenter(ActivationCodeContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {
        view.setUpView();
    }

    @Override
    public void tryToRequestVerificationCode() {

    }

    @Override
    public void requestVerificationCodeBeanSuccess(@NonNull VerificationCodeBean bean) {

    }

    @Override
    public void requestVerificationCodeBeanFailure() {

    }
}
