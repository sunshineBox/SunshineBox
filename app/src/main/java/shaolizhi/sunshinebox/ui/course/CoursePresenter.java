package shaolizhi.sunshinebox.ui.course;

/**
 * 由邵励治于2018/1/4创造.
 */

public class CoursePresenter implements CourseContract.Presenter, CourseContract.CallBack {

    private CourseContract.View view;

    private CourseContract.Model model;

    CoursePresenter(CourseContract.View view) {
        this.view = view;
        model = new CourseModel(this);
    }

    @Override
    public void start() {
        view.setUpView();
    }
}
