package shaolizhi.sunshinebox.ui.course;

import android.os.Bundle;
import android.widget.Button;
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

    @BindView(R.id.course_act_button1)
    Button playAudioButton;

    @BindView(R.id.course_act_button2)
    Button playVideoButton;

    @BindView(R.id.course_act_button3)
    Button lastLessonButton;

    @BindView(R.id.course_act_button4)
    Button nextLessonButton;


    @Override
    protected int layoutId() {
        return R.layout.activity_course;
    }

    @Override
    protected void created(Bundle bundle) {
        courses = (Courses) getIntent().getSerializableExtra(ConstantData.COURSE);
        courseNameTextView.setText(courses.getCourse_name());

        if (courses.getIs_audio_downloaded()) {
            playAudioButton.setText(R.string.course_act_string2);
        } else {
            playAudioButton.setText(R.string.course_act_string6);
        }

        if (courses.getIs_video_downloaded()) {
            playVideoButton.setText(R.string.course_act_string3);
        } else {
            playVideoButton.setText(R.string.course_act_string7);
        }
    }

    @Override
    protected void resumed() {

    }
}
