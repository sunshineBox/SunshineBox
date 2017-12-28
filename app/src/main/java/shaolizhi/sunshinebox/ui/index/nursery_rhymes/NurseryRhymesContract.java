package shaolizhi.sunshinebox.ui.index.nursery_rhymes;

import android.app.Activity;
import android.support.annotation.NonNull;

import java.util.List;

import shaolizhi.sunshinebox.objectbox.courses.Courses;
import shaolizhi.sunshinebox.ui.base.BasePresenter;
import shaolizhi.sunshinebox.ui.base.BaseView;

/**
 * 由邵励治于2017/12/27创造.
 */

public class NurseryRhymesContract {
    interface View extends BaseView {
        void setRefresh(boolean refresh);

        void setDataInAdapter(@NonNull List<Courses> coursesList);

        Activity getFuckingActivity();
    }


    interface Presenter extends BasePresenter {
        void tryToLoadDataIntoRecyclerView();
    }

    interface Model {
        Boolean isThereAnyDataInTheDatabase();

        void requestDataFromNet(@NonNull String courseType, @NonNull String maxLastModificationTime);

        List requestDataFromDatabase();

        void storedInTheDatabase(@NonNull NurseryRhymesBean bean);

        String getMaxModificationTime();
    }

    interface CallBack {
        void requestDataFromNetSuccess(@NonNull NurseryRhymesBean bean);

        void requestDataFromNetFailure();
    }
}
