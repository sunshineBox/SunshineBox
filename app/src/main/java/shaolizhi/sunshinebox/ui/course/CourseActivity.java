package shaolizhi.sunshinebox.ui.course;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import shaolizhi.sunshinebox.R;
import shaolizhi.sunshinebox.data.ConstantData;
import shaolizhi.sunshinebox.objectbox.courses.Courses;
import shaolizhi.sunshinebox.ui.base.BaseActivity;

public class CourseActivity extends BaseActivity implements CourseContract.View {

    private String courseId;

    private CourseContract.Presenter presenter;

    @OnClick(R.id.course_act_imagebutton1)
    public void back() {
        finish();
    }

    @BindView(R.id.course_act_coordinatorLayout)
    CoordinatorLayout coordinatorLayout;

    @BindView(R.id.course_act_textview1)
    TextView courseNameTextView;

    @BindView(R.id.course_act_button1)
    Button audioButton;

    @OnClick(R.id.course_act_button1)
    public void clickAudioButton() {
        presenter.tryToPlayAudio();
    }

    @BindView(R.id.course_act_button2)
    Button videoButton;

    @OnClick(R.id.course_act_button2)
    public void clickVideoButton() {
        presenter.tryToPlayVideo();
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
    }

    @Override
    public String getCourseIdFromIntent() throws Exception {
        Courses courses = (Courses) getIntent().getSerializableExtra(ConstantData.COURSE);
        if (courses != null) {
            courseId = courses.getCourse_id();
            return courseId;
        } else {
            if (courseId != null) {
                return courseId;
            } else {
                throw new Exception("Can not get courseId.");
            }
        }
    }

    @Override
    public void setAudioButtonText(String text) {
        audioButton.setText(text);
    }

    @Override
    public void setAudioButtonText(int resId) {
        audioButton.setText(resId);
    }

    @Override
    public void setVideoButtonText(String text) {
        videoButton.setText(text);
    }

    @Override
    public void setVideoButtonText(int resId) {
        videoButton.setText(resId);
    }

    @Override
    public CoordinatorLayout getCoordinatorLayout() {
        return coordinatorLayout;
    }

    @Override
    public Activity getActivity() {
        return this;
    }
}
