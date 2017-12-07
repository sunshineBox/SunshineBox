package shaolizhi.sunshinebox.ui.home_page;

import android.os.Bundle;

import shaolizhi.sunshinebox.R;
import shaolizhi.sunshinebox.ui.base.BaseActivity;
import shaolizhi.sunshinebox.utils.ActivityCollectorUtils;
import shaolizhi.sunshinebox.utils.UIUtils;

public class HomePageActivity extends BaseActivity {


    @Override
    protected int layoutId() {
        return R.layout.activity_home_page;
    }

    @Override
    protected void created(Bundle bundle) {
        ActivityCollectorUtils.finishAll();
//        SharedPreferencesUtils.clear(this);
    }

    @Override
    protected void resumed() {

    }

    @Override
    public void onBackPressed() {
        UIUtils.ifBackOut(this, getString(R.string.home_page_act_string1));
    }
}
