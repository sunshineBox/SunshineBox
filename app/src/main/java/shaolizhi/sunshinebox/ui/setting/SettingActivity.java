package shaolizhi.sunshinebox.ui.setting;

import android.os.Bundle;

import shaolizhi.sunshinebox.R;
import shaolizhi.sunshinebox.ui.base.BaseActivity;

public class SettingActivity extends BaseActivity {

    @Override
    protected int layoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void created(Bundle bundle) {

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    @Override
    protected void resumed() {

    }
}
