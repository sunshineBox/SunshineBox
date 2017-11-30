package shaolizhi.sunshinebox.ui.activation_code_verify;

/**
 * 由邵励治于2017/11/30创造.
 */

public class ActivationCodeVerifyModel implements ActivationCodeVerifyContract.Model{
    private ActivationCodeVerifyContract.CallBack callBack;

    public ActivationCodeVerifyModel(ActivationCodeVerifyContract.CallBack callBack) {
        this.callBack = callBack;
    }
}
