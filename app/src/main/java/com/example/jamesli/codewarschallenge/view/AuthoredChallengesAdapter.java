package com.example.jamesli.codewarschallenge.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jamesli.codewarschallenge.R;
import com.example.jamesli.codewarschallenge.model.AuthoredChallenge;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AuthoredChallengesAdapter extends RecyclerView.Adapter<AuthoredChallengesAdapter.ViewHolder> {

    private List<AuthoredChallenge> mAuthoredChallengeList = new ArrayList<>();
    private Context mContext;
    private OnAuthoredChallengeItemListener mOnAuthoredChallengeItemListener;

    public AuthoredChallengesAdapter(Context context) {
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.authored_challenges_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mId.setText(mContext.getString(R.string.id) + mAuthoredChallengeList.get(position).getId());
        holder.mName.setText(mContext.getString(R.string.name) + mAuthoredChallengeList.get(position).getName());
        holder.mRank.setText(mContext.getString(R.string.rank) + mAuthoredChallengeList.get(position).getRank());
    }

    @Override
    public int getItemCount() {
        return mAuthoredChallengeList.size();
    }

    public void setOnSelectedItemChangeListener(OnAuthoredChallengeItemListener listener) {
        mOnAuthoredChallengeItemListener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.tv_id)
        TextView mId;
        @BindView(R.id.tv_name)
        TextView mName;
        @BindView(R.id.tv_description)
        TextView mDescription;
        @BindView(R.id.tv_rank)
        TextView mRank;

        public ViewHolder(View view)  {
            super(view);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            AuthoredChallenge item = getItem(getAdapterPosition());
            mOnAuthoredChallengeItemListener.onAuthoredChallengeItemClick(item);
        }
    }

    public interface OnAuthoredChallengeItemListener {
        void onAuthoredChallengeItemClick(AuthoredChallenge authoredChallenge);
    }

    public void addData(List<AuthoredChallenge> authoredChallenges) {
        mAuthoredChallengeList.addAll(authoredChallenges);
        notifyDataSetChanged();
    }

    private AuthoredChallenge getItem(int itemPosition) {
        return mAuthoredChallengeList.get(itemPosition);
    }
}
