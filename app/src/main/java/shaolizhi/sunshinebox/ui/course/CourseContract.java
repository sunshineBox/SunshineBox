package shaolizhi.sunshinebox.ui.course;

import shaolizhi.sunshinebox.ui.base.BasePresenter;
import shaolizhi.sunshinebox.ui.base.BaseView;

/**
 * 由邵励治于2018/1/4创造.
 */

class CourseContract {
    interface View extends BaseView {
        Long getCourseIdFromIntent() throws Exception;
    }

    interface Presenter extends BasePresenter {
    }

    interface Model {

    }

    interface CallBack {

    }
}
