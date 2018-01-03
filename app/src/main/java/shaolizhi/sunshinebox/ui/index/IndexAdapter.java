package shaolizhi.sunshinebox.ui.index;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import shaolizhi.sunshinebox.data.ConstantData;
import shaolizhi.sunshinebox.objectbox.courses.Courses;
import shaolizhi.sunshinebox.ui.course.CourseActivity;

/**
 * 由邵励治于2017/12/11创造.
 */

public class IndexAdapter extends RecyclerView.Adapter<IndexAdapter.IndexViewHolder> {

    private LayoutInflater layoutInflater;

    private List<Courses> coursesList;

    private Activity activity;

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
        layoutInflater = LayoutInflater.from(context);
        activity = (Activity) context;
    }

    //for view
    void setCoursesList(List<Courses> coursesList) {
        this.coursesList = coursesList;
        notifyDataSetChanged();
    }


    @Override
    public IndexViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = layoutInflater.inflate(R.layout.item_index, parent, false);
        return new IndexViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(IndexViewHolder holder, int position) {
        if (holder != null) {
            holder.bind(coursesList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        if (coursesList != null) {
            return coursesList.size();
        } else {
            return 0;
        }
    }

    class IndexViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.index_item_textview1)
        TextView isDownloadTextview;

        @BindView(R.id.index_item_textview2)
        TextView courseNameTextview;

        private Courses courses;

        void bind(Courses courses) {
            this.courses = courses;
            courseNameTextview.setText(this.courses.getCourse_name());
            if (this.courses.getDownload()) {
                isDownloadTextview.setText("已下载");
            } else {
                isDownloadTextview.setText("未下载");
            }
        }

        IndexViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(activity, CourseActivity.class);
            intent.putExtra(ConstantData.COURSE, this.courses);
            activity.startActivity(intent);
        }
    }
}
