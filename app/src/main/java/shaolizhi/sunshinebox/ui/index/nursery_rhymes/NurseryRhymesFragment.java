package shaolizhi.sunshinebox.ui.index.nursery_rhymes;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import shaolizhi.sunshinebox.R;
import shaolizhi.sunshinebox.ui.base.BaseFragment;
import shaolizhi.sunshinebox.ui.index.IndexAdapter;

/**
 * 由邵励治于2017/12/11创造.
 */

public class NurseryRhymesFragment extends BaseFragment {

    @BindView(R.id.nursery_rhymes_fgm_recyclerview)
    RecyclerView recyclerView;

    IndexAdapter adapter;

    Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_nursery_rhymes;
    }

    @Override
    protected void created(Bundle bundle) {
        adapter = new IndexAdapter(context);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void resumed() {

    }
}
