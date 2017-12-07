package shaolizhi.sunshinebox.ui.home_page;

import android.os.Bundle;

import shaolizhi.sunshinebox.R;
import shaolizhi.sunshinebox.data.CacheData;
import shaolizhi.sunshinebox.ui.base.BaseActivity;
import shaolizhi.sunshinebox.utils.SharedPreferencesUtils;

public class HomePageActivity extends BaseActivity {



    @Override
    protected int layoutId() {
        return R.layout.activity_home_page;
    }

    @Override
    protected void created(Bundle bundle) {
        String uuid = SharedPreferencesUtils.get(this, CacheData.UUID);
    }

    @Override
    protected void resumed() {

    }
}
