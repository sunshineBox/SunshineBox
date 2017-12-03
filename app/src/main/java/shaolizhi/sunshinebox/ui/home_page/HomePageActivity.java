package shaolizhi.sunshinebox.ui.home_page;

import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import shaolizhi.sunshinebox.R;
import shaolizhi.sunshinebox.data.CacheData;
import shaolizhi.sunshinebox.ui.base.BaseActivity;
import shaolizhi.sunshinebox.utils.SharedPreferencesUtils;

public class HomePageActivity extends BaseActivity {

    @BindView(R.id.home_page_act_textview)
    TextView textView;

    @Override
    protected int layoutId() {
        return R.layout.activity_home_page;
    }

    @Override
    protected void created(Bundle bundle) {
        String uuid = SharedPreferencesUtils.get(this, CacheData.UUID);
        if (uuid != null) {
            textView.setText(uuid);
        }
    }

    @Override
    protected void resumed() {

    }
}
