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

public class CourseActivity extends BaseActivity implements CourseContract.View {

    private Long courseId;

    private CourseContract.Presenter presenter;

    private Boolean isAudioDownloading;

    private Boolean isVideoDownloading;

    @OnClick(R.id.course_act_imagebutton1)
    public void back() {
        finish();
    }

    @BindView(R.id.course_act_textview1)
    TextView courseNameTextView;

    @BindView(R.id.course_act_button1)
    Button audioButton;

    @OnClick(R.id.course_act_button1)
    public void clickAudioButton() {

    }

    @BindView(R.id.course_act_button2)
    Button videoButton;

    @OnClick(R.id.course_act_button2)
    public void clickVideoButton() {

    }

    @BindView(R.id.course_act_button3)
    Button lastLessonButton;

    @OnClick(R.id.course_act_button3)
    public void clickLastLessonButton() {

    }

    @BindView(R.id.course_act_button4)
    Button nextLessonButton;

    @OnClick(R.id.course_act_button4)
    public void clickNextLessonButton() {

    }

    @Override
    protected int layoutId() {
        return R.layout.activity_course;
    }

    @Override
    protected void created(Bundle bundle) {
        presenter = new CoursePresenter(this);
        presenter.start();
    }

    @Override
    protected void resumed() {

    }

    @Override
    public void setUpView() {
        Courses courses = (Courses) getIntent().getSerializableExtra(ConstantData.COURSE);
        courseNameTextView.setText(courses.getCourse_name());

        if (courses.getIs_audio_downloaded()) {
            audioButton.setText(R.string.course_act_string2);
        } else {
            audioButton.setText(R.string.course_act_string6);
        }

        if (courses.getIs_video_downloaded()) {
            videoButton.setText(R.string.course_act_string3);
        } else {
            videoButton.setText(R.string.course_act_string7);
        }
    }

    @Override
    public Long getCourseIdFromIntent() throws Exception {
        Courses courses = (Courses) getIntent().getSerializableExtra(ConstantData.COURSE);
        if (courses != null) {
            courseId = courses.getId();
            return courseId;
        } else {
            if (courseId != null) {
                return courseId;
            } else {
                throw new Exception("Can not get courseId.");
            }
        }
    }
}
