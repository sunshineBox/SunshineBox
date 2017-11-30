package shaolizhi.sunshinebox.ui.activation_code_verify;

/**
 * 由邵励治于2017/11/30创造.
 */

public class ActivationCodeVerifyPresenter implements ActivationCodeVerifyContract.Presenter, ActivationCodeVerifyContract.CallBack {

    private ActivationCodeVerifyContract.View view;

    private ActivationCodeVerifyContract.Model model;

    ActivationCodeVerifyPresenter(ActivationCodeVerifyContract.View view) {
        this.view = view;
        model = new ActivationCodeVerifyModel(this);
    }

    @Override
    public void start() {
        view.setUpView();
    }
}
