package shaolizhi.sunshinebox.ui.activation_code;

import android.support.annotation.NonNull;

/**
 * 由邵励治于2017/11/29创造.
 */

public class ActivationCodeModel implements ActivationCodeContract.Model {

    ActivationCodeContract.CallBack callBack;

    public ActivationCodeModel(ActivationCodeContract.CallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    public void requestVerificationCodeBean(@NonNull String phoneNumber) {

    }
}
