package shaolizhi.sunshinebox.ui.welcome;

import android.os.Bundle;
import android.support.v7.app.ActionBar;

import shaolizhi.sunshinebox.R;
import shaolizhi.sunshinebox.ui.base.BaseActivity;

public class WelcomeActivity extends BaseActivity {


    @Override
    protected int layoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void created(Bundle bundle) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    @Override
    protected void resumed() {

    }
}
