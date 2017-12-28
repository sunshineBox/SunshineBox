package shaolizhi.sunshinebox.ui.index.nursery_rhymes;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import shaolizhi.sunshinebox.R;
import shaolizhi.sunshinebox.objectbox.courses.Courses;
import shaolizhi.sunshinebox.ui.base.BaseFragment;
import shaolizhi.sunshinebox.ui.index.IndexAdapter;
import shaolizhi.sunshinebox.widget.MyRefreshLayout;

/**
 * 由邵励治于2017/12/11创造.
 */

public class NurseryRhymesFragment extends BaseFragment implements MyRefreshLayout.OnRefreshListener, NurseryRhymesContract.View {

    @BindView(R.id.nursery_rhymes_fgm_my_refreshlayout)
    MyRefreshLayout myRefreshLayout;

    @BindView(R.id.nursery_rhymes_fgm_recyclerview)
    RecyclerView recyclerView;

    NurseryRhymesContract.Presenter presenter;

    IndexAdapter adapter;

    Activity activity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity = (Activity) context;
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_nursery_rhymes;
    }

    @Override
    protected void created(Bundle bundle) {
        presenter = new NurseryRhymesPresenter(this);
        presenter.start();
    }

    @Override
    protected void resumed() {

    }

    @Override
    public void onRefresh() {
        presenter.tryToLoadDataIntoRecyclerView();
    }

    @Override
    public void setUpView() {
        //set up recyclerview
        adapter = new IndexAdapter(activity);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));
        recyclerView.setAdapter(adapter);

        //set up refreshlayout
        myRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void setRefresh(boolean refresh) {
        myRefreshLayout.setRefreshing(refresh);
    }

    @Override
    public void setDataInAdapter(@NonNull List<Courses> coursesList) {

    }

    @Override
    public Activity getFuckingActivity() {
        return activity;
    }
}
