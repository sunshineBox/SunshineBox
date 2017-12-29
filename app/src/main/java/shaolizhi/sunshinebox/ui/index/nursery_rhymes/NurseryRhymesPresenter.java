package shaolizhi.sunshinebox.ui.index.nursery_rhymes;

import android.support.annotation.NonNull;

import java.util.List;

import shaolizhi.sunshinebox.objectbox.courses.Courses;
import shaolizhi.sunshinebox.ui.base.BaseActivity;
import shaolizhi.sunshinebox.ui.base.BaseFragment;

/**
 * 由邵励治于2017/12/27创造.
 */

public class NurseryRhymesPresenter implements NurseryRhymesContract.Presenter, NurseryRhymesContract.CallBack {

    private NurseryRhymesContract.View view;
    private NurseryRhymesContract.Model model;

    NurseryRhymesPresenter(@NonNull NurseryRhymesContract.View view) {
        this.view = view;
        model = new NurseryRhymesModel(this, view.getFuckingActivity());
    }

    @Override
    public void start() {
        view.setUpView();
        tryToLoadDataIntoRecyclerView();
    }

    @Override
    public void tryToLoadDataIntoRecyclerView() {
        if (model.isThereAnyDataInTheDatabase()) {
            model.requestDataFromNet("rhymes", model.getMaxModificationTime());
        } else {
            model.requestDataFromNet("rhymes", "0");
        }
    }

    @Override
    public void requestDataFromNetSuccess(@NonNull NurseryRhymesBean bean) {
        if (bean.getFlag() != null) {
            switch (bean.getFlag()) {
                case "001":
                    if (bean.getMessage() != null) {
                        if (bean.getMessage().equals("failure")) {
                            if (view instanceof BaseFragment) {
                                ((BaseFragment) view).showToastForRequestResult("402");
                            }
                            if (view instanceof BaseActivity) {
                                ((BaseActivity) view).showToastForRequestResult("402");
                            }
                        } else if (bean.getMessage().equals("success")) {
                            model.storedInTheDatabase(bean);
                            List<Courses> coursesList = model.requestDataFromDatabase();
                            view.setDataInAdapter(coursesList);
                            view.setRefresh(false);
                        }
                    }
                    break;
                default:
                    if (view instanceof BaseFragment) {
                        ((BaseFragment) view).showToastForRequestResult(bean.getFlag());
                    }
                    if (view instanceof BaseActivity) {
                        ((BaseActivity) view).showToastForRequestResult(bean.getFlag());
                    }
                    break;
            }
        }
    }

    @Override
    public void requestDataFromNetFailure() {
        if (view instanceof BaseFragment) {
            ((BaseFragment) view).showToastForRequestResult("403");
        }
        if (view instanceof BaseActivity) {
            ((BaseActivity) view).showToastForRequestResult("403");
        }
        view.setRefresh(false);
    }

}
