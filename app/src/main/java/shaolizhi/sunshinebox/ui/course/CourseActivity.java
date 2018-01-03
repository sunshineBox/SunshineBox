package shaolizhi.sunshinebox.ui.course;

import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import shaolizhi.sunshinebox.R;
import shaolizhi.sunshinebox.data.ConstantData;
import shaolizhi.sunshinebox.objectbox.courses.Courses;
import shaolizhi.sunshinebox.ui.base.BaseActivity;

public class CourseActivity extends BaseActivity {

    private Courses courses;

    @OnClick(R.id.course_act_imagebutton1)
    public void back() {
        finish();
    }

    @BindView(R.id.course_act_textview1)
    TextView courseNameTextView;


    @Override
    protected int layoutId() {
        return R.layout.activity_course;
    }

    @Override
    protected void created(Bundle bundle) {
        courses = (Courses) getIntent().getSerializableExtra(ConstantData.COURSE);
        courseNameTextView.setText(courses.getCourse_name());
    }

    @Override
    protected void resumed() {

    }
}
