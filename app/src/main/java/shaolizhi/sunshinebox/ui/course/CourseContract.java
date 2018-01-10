package shaolizhi.sunshinebox.ui.course;

import android.app.Activity;
import android.support.design.widget.CoordinatorLayout;

import shaolizhi.sunshinebox.objectbox.courses.Courses;
import shaolizhi.sunshinebox.ui.base.BasePresenter;
import shaolizhi.sunshinebox.ui.base.BaseView;

/**
 * 由邵励治于2018/1/4创造.
 */

class CourseContract {
    interface View extends BaseView {
        String getCourseIdFromIntent() throws Exception;

        void setAudioButtonEnable(Boolean enable);

        void setVideoButtonEnable(Boolean enable);

        void setAudioButtonText(String text);

        void setAudioButtonText(int resId);

        void setVideoButtonText(String text);

        void setVideoButtonText(int resId);

        CoordinatorLayout getCoordinatorLayout();

        Activity getActivity();
    }

    interface Presenter extends BasePresenter {
        void tryToPlayAudio();

        void tryToPlayVideo();

        void exit();
    }

    interface Model {
        Courses getCourseByCourseId(String courseId);

        void requestVideoByCourseId(String courseId);

        void requestAudioByCourseId(String courseId);
    }

    interface CallBack {
        void downloadVideoSuccess();

        void downloadAudioSuccess();

        void updateAudioDownloadProgress(Long percent);

        void updateVideoDownloadProgress(Long percent);
    }
}
