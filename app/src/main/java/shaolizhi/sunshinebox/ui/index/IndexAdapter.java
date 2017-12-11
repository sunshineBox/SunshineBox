package shaolizhi.sunshinebox.ui.index;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import shaolizhi.sunshinebox.R;

/**
 * 由邵励治于2017/12/11创造.
 */

public class IndexAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private LayoutInflater layoutInflater;

    public IndexAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(this.context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = layoutInflater.inflate(R.layout.item_index, parent, false);
        return new MyViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder) {
            ((MyViewHolder) holder).textView.setText(String.valueOf(position));
        }
    }

    @Override
    public int getItemCount() {
        return 40;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.index_item_textview)
        TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
