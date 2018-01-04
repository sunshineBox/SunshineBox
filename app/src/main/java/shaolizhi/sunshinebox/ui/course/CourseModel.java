package shaolizhi.sunshinebox.ui.course;

import android.app.Activity;
import android.support.annotation.NonNull;

import io.objectbox.Box;
import io.objectbox.query.Query;
import io.objectbox.query.QueryBuilder;
import shaolizhi.sunshinebox.objectbox.courses.Courses;
import shaolizhi.sunshinebox.objectbox.courses.CoursesUtils;
import shaolizhi.sunshinebox.objectbox.courses.Courses_;

/**
 * 由邵励治于2018/1/4创造.
 */

class CourseModel implements CourseContract.Model {

    private CourseContract.CallBack callBack;

    private Box<Courses> coursesBox;

    CourseModel(@NonNull CourseContract.CallBack callBack, @NonNull Activity activity) {
        this.callBack = callBack;
        //get courses-box
        coursesBox = CoursesUtils.getInstance().getCoursesBox(activity);
    }

    @Override
    public Courses getCourseByCourseId(String courseId) {
        QueryBuilder<Courses> builder = coursesBox.query();
        Query<Courses> query = builder.equal(Courses_.course_id, courseId).build();
        return query.findUnique();
    }
}
