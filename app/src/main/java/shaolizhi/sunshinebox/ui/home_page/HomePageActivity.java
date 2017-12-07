package shaolizhi.sunshinebox.ui.home_page;

import android.os.Bundle;

import shaolizhi.sunshinebox.R;
import shaolizhi.sunshinebox.ui.base.BaseActivity;
import shaolizhi.sunshinebox.utils.ActivityCollectorUtils;

public class HomePageActivity extends BaseActivity {


    @Override
    protected int layoutId() {
        return R.layout.activity_home_page;
    }

    @Override
    protected void created(Bundle bundle) {
        ActivityCollectorUtils.finishAll();

    }

    @Override
    protected void resumed() {

    }
}
