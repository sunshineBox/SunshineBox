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
        model = new NurseryRhymesModel(this);
    }

    @Override
    public void start() {
        view.setUpView();
    }
}
