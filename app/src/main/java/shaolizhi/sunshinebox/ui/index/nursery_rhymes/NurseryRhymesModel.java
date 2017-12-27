package shaolizhi.sunshinebox.ui.index.nursery_rhymes;

import android.support.annotation.NonNull;

/**
 * 由邵励治于2017/12/27创造.
 */

public class NurseryRhymesModel implements NurseryRhymesContract.Model {

    private NurseryRhymesContract.CallBack callBack;

    NurseryRhymesModel(@NonNull NurseryRhymesContract.CallBack callBack) {
        this.callBack = callBack;
    }
}
