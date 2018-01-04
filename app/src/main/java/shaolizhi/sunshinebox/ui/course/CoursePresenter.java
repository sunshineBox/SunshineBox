package shaolizhi.sunshinebox.ui.course;

import android.support.design.widget.Snackbar;

import shaolizhi.sunshinebox.R;
import shaolizhi.sunshinebox.objectbox.courses.Courses;

/**
 * 由邵励治于2018/1/4创造.
 */

public class CoursePresenter implements CourseContract.Presenter, CourseContract.CallBack {

    private CourseContract.View view;

    private CourseContract.Model model;

    private String courseId = null;

    private Boolean isAudioDownloading = false;

    private Boolean isVideoDownloading = false;

    CoursePresenter(CourseContract.View view) {
        this.view = view;
        model = new CourseModel(this, view.getActivity());
        try {
            courseId = view.getCourseIdFromIntent();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start() {
        view.setUpView();
        setUpButtonTextWhenThereIsNoDownload();
    }

    private void setUpButtonTextWhenThereIsNoDownload() {
        Courses courses = null;
        if (courseId != null) {
            courses = model.getCourseByCourseId(courseId);
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

    @Override
    public void tryToPlayAudio() {
        Courses courses = null;
        if (courseId != null) {
            courses = model.getCourseByCourseId(courseId);
        }

        if (courses != null) {
            if (courses.getIs_audio_downloaded()) {
                //音频已下载
                playAudio();
            } else {
                //音频未下载
                downloadAudio();
            }
        }
    }

    private void playAudio() {
    }

    private void downloadAudio() {
        isAudioDownloading = true;
        //弹出SnackBar
        Snackbar.make(view.getCoordinatorLayout(), R.string.course_act_string8, Snackbar.LENGTH_SHORT).show();

    }

    @Override
    public void tryToPlayVideo() {
        Courses courses = null;
        if (courseId != null) {
            courses = model.getCourseByCourseId(courseId);
        }
        if (courses != null) {
            if (courses.getIs_audio_downloaded()) {
                //音频已下载
                playVideo();
            } else {
                //音频未下载
                downloadVideo();
            }
        }
    }

    private void playVideo() {

    }

    private void downloadVideo() {
        isVideoDownloading = true;
        //弹出SnackBar
        Snackbar.make(view.getCoordinatorLayout(), R.string.course_act_string8, Snackbar.LENGTH_SHORT).show();

    }
}
