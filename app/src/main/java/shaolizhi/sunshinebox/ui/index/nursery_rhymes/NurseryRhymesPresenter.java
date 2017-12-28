package shaolizhi.sunshinebox.ui.index.nursery_rhymes;

import android.support.annotation.NonNull;

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
        model.requestDataFromDatabase();
    }

    @Override
    public void requestDataFromNetSuccess(@NonNull NurseryRhymesBean bean) {
        model.storedInTheDatabase(bean);
    }

    @Override
    public void requestDataFromNetFailure() {
        view.setRefresh(false);
    }

}
