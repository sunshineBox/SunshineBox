package shaolizhi.sunshinebox.objectbox.courses;

import android.app.Activity;

import io.objectbox.Box;
import io.objectbox.BoxStore;
import shaolizhi.sunshinebox.utils.App;

/**
 * 由邵励治于2017/12/27创造.
 */

public class CoursesUtils {
    private static CoursesUtils coursesUtils;

    public static CoursesUtils getInstance() {
        if (coursesUtils == null) {
            coursesUtils = new CoursesUtils();
        }
        return coursesUtils;
    }

    private CoursesUtils() {

    }

    public Box<Courses> getCoursesBox(Activity activity) {
        BoxStore boxStore = ((App) activity.getApplication()).getBoxStore();
        return boxStore.boxFor(Courses.class);
    }

    //数据库中是否存在数据
    public Boolean isCoursesBoxHasData(Box<Courses> coursesBox) {
        if (coursesBox != null) {
            long count = coursesBox.count();
            return count != 0;
        } else {
            return false;
        }
    }
}
