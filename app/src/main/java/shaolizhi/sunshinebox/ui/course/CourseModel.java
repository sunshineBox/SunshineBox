package shaolizhi.sunshinebox.ui.course;

/**
 * 由邵励治于2018/1/4创造.
 */

class CourseModel implements CourseContract.Model {

    private CourseContract.CallBack callBack;

    CourseModel(CourseContract.CallBack callBack) {
        this.callBack = callBack;
    }
}
