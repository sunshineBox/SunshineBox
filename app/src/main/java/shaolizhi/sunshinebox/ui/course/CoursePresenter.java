package shaolizhi.sunshinebox.ui.course;

import shaolizhi.sunshinebox.R;
import shaolizhi.sunshinebox.objectbox.courses.Courses;

/**
 * 由邵励治于2018/1/4创造.
 */

public class CoursePresenter implements CourseContract.Presenter, CourseContract.CallBack {

    private CourseContract.View view;

    private CourseContract.Model model;

    CoursePresenter(CourseContract.View view) {
        this.view = view;
        model = new CourseModel(this, view.getActivity());
    }

    @Override
    public void start() {
        view.setUpView();
        setUpButtonTextWhenThereIsNoDownload();
    }

    private void setUpButtonTextWhenThereIsNoDownload() {
        Courses courses = null;
        try {
            courses = model.getCourseByCourseId(view.getCourseIdFromIntent());
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (courses != null) {
            if (courses.getIs_audio_downloaded()) {
                view.setAudioButtonText(R.string.course_act_string2);
            } else {
                view.setAudioButtonText(R.string.course_act_string6);
            }

            if (courses.getIs_video_downloaded()) {
                view.setVideoButtonText(R.string.course_act_string3);
            } else {
                view.setVideoButtonText(R.string.course_act_string7);
            }
        }
    }
}
