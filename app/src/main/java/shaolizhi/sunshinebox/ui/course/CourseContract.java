package shaolizhi.sunshinebox.ui.course;

import android.app.Activity;

import shaolizhi.sunshinebox.objectbox.courses.Courses;
import shaolizhi.sunshinebox.ui.base.BasePresenter;
import shaolizhi.sunshinebox.ui.base.BaseView;

/**
 * 由邵励治于2018/1/4创造.
 */

class CourseContract {
    interface View extends BaseView {
        String getCourseIdFromIntent() throws Exception;

        void setAudioButtonText(String text);

        void setAudioButtonText(int resId);

        void setVideoButtonText(String text);

        void setVideoButtonText(int resId);

        Activity getActivity();
    }

    interface Presenter extends BasePresenter {
    }

    interface Model {
        Courses getCourseByCourseId(String courseId);
    }

    interface CallBack {

    }
}
