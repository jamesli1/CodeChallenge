package com.example.jamesli.codewarschallenge.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.jamesli.codewarschallenge.R;
import com.example.jamesli.codewarschallenge.model.CompletedChallenge;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CompletedChallengesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int VIEW_ITEM = 0;
    private final int VIEW_PROG = 1;
    private List<CompletedChallenge> mCompletedChallengeList = new ArrayList<>();
    private Context mContext;
    private OnCompletedChallengeItemListener mOnCompletedChallengeItemListener;

    public CompletedChallengesAdapter(Context context) {
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.completed_challenges_item, parent, false);
            return new ViewHolder(view);
        } else if (viewType == VIEW_PROG) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.progressbar, parent, false);
            return new LoadingViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            ((ViewHolder) holder).mId.setText(mContext.getString(R.string.id) + mCompletedChallengeList.get(position).getId());
            ((ViewHolder) holder).mName.setText(mContext.getString(R.string.name) + mCompletedChallengeList.get(position).getName());
            ((ViewHolder) holder).mSlug.setText(mContext.getString(R.string.slug) + mCompletedChallengeList.get(position).getSlug());
            ((ViewHolder) holder).mCompletedAt.setText(mContext.getString(R.string.completedat) + mCompletedChallengeList.get(position).getCompletedAt());
        } else if (holder instanceof LoadingViewHolder) {
            ((LoadingViewHolder) holder).mProgressBar.setIndeterminate(true);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return mCompletedChallengeList.get(position) != null ? VIEW_ITEM : VIEW_PROG;
    }

    @Override
    public int getItemCount() {
        return mCompletedChallengeList.size();
    }

    public void setOnSelectedItemChangeListener(OnCompletedChallengeItemListener listener) {
        mOnCompletedChallengeItemListener = listener;
    }

    public void addData(List<CompletedChallenge> completedChallenge) {
        mCompletedChallengeList.addAll(completedChallenge);
        notifyDataSetChanged();
    }

    public void addNullItem() {
        mCompletedChallengeList.add(null);
        notifyItemInserted(getItemCount() - 1);
    }

    public void removeNullItem() {
        mCompletedChallengeList.remove(getItemCount() - 1);
        notifyItemRemoved(getItemCount());
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.tv_id)
        TextView mId;
        @BindView(R.id.tv_name)
        TextView mName;
        @BindView(R.id.tv_description)
        TextView mSlug;
        @BindView(R.id.tv_completed_at)
        TextView mCompletedAt;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            CompletedChallenge item = getItem(getAdapterPosition());
            mOnCompletedChallengeItemListener.onCompletedChallengeItemClick(item);
        }
    }

    public class LoadingViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.progressBar)
        ProgressBar mProgressBar;

        public LoadingViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, itemView);
        }
    }

    private CompletedChallenge getItem(int itemPosition) {
        return mCompletedChallengeList.get(itemPosition);
    }

    public interface OnCompletedChallengeItemListener {
        void onCompletedChallengeItemClick(CompletedChallenge completedChallenge);
    }
}
