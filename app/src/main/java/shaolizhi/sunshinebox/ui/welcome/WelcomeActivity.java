package shaolizhi.sunshinebox.ui.welcome;

import android.content.Intent;
import android.os.Bundle;

import butterknife.OnClick;
import shaolizhi.sunshinebox.R;
import shaolizhi.sunshinebox.ui.base.BaseActivity;
import shaolizhi.sunshinebox.ui.activation_code.ActivationCodeActivity;

public class WelcomeActivity extends BaseActivity {

    @OnClick(R.id.welcome_button)
    public void openActivity() {
        Intent intent = new Intent(WelcomeActivity.this, ActivationCodeActivity.class);
        startActivity(intent);
    }


    @Override
    protected int layoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void created(Bundle bundle) {

    }

    @Override
    protected void resumed() {

    }
}
