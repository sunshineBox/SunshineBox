package shaolizhi.sunshinebox.ui.index;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import shaolizhi.sunshinebox.R;
import shaolizhi.sunshinebox.objectbox.courses.Courses;

/**
 * 由邵励治于2017/12/11创造.
 */

public class IndexAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private LayoutInflater layoutInflater;

    //data from coursesList
    private List<String> courseNameList;
    private List<Boolean> isDownloadList;

    //data from coursesList's setter
    private void setCourseNameList(List<Courses> coursesList) {
        List<String> list = new ArrayList<>();
        for (Courses item : coursesList) {
            list.add(item.getCourse_name());
        }
        courseNameList = list;
    }

    //data from coursesList's setter
    private void setIsDownloadList(List<Courses> coursesList) {
        List<Boolean> list = new ArrayList<>();
        for (Courses item : coursesList) {
            list.add(item.getDownload());
        }
        isDownloadList = list;
    }

    IndexAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(this.context);
    }

    //for view
    void setCoursesList(List<Courses> coursesList) {
        setCourseNameList(coursesList);
        setIsDownloadList(coursesList);
        notifyDataSetChanged();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = layoutInflater.inflate(R.layout.item_index, parent, false);
        return new MyViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder) {
            ((MyViewHolder) holder).courseNameTextview.setText(courseNameList.get(position));
            if (isDownloadList.get(position)) {
                ((MyViewHolder) holder).isDownloadTextview.setText("已下载");
            } else {
                ((MyViewHolder) holder).isDownloadTextview.setText("未下载");
            }
        }
    }

    @Override
    public int getItemCount() {
        if (courseNameList != null && isDownloadList != null) {
            if (courseNameList.size() == isDownloadList.size()) {
                return courseNameList.size();
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.index_item_textview1)
        TextView isDownloadTextview;

        @BindView(R.id.index_item_textview2)
        TextView courseNameTextview;

        MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
