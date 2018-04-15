package com.example.jamesli.codewarschallenge.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jamesli.codewarschallenge.R;
import com.example.jamesli.codewarschallenge.model.Constants;
import com.example.jamesli.codewarschallenge.model.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private final int MAX_ITEMS = 5;
    private ArrayList<User> mUserList = new ArrayList<>();
    private Context mContext;
    private OnUserItemListener mOnUserItemListener;

    public UserAdapter(Context context) {
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mUserName.setText(mContext.getString(R.string.username) + mUserList.get(position).getUsername());
        holder.mName.setText(mContext.getString(R.string.name) + mUserList.get(position).getName());
        holder.mScore.setText(mContext.getString(R.string.score) + mUserList.get(position).getRanks().getOverall().getScore());
        holder.mRank.setText(mContext.getString(R.string.rank) + mUserList.get(position).getRanks().getOverall().getRank());
    }

    @Override
    public int getItemCount() {
        return mUserList.size() > MAX_ITEMS ? MAX_ITEMS : mUserList.size();
    }

    public void setOnSelectedItemChangeListener(OnUserItemListener listener) {
        mOnUserItemListener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.tv_username)
        TextView mUserName;
        @BindView(R.id.tv_name)
        TextView mName;
        @BindView(R.id.tv_rank)
        TextView mRank;
        @BindView(R.id.tv_score)
        TextView mScore;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            User item = getItem(getAdapterPosition());
            mOnUserItemListener.onUserItemClick(item.getUsername());
        }

    }

    public void addData(User user) {
        mUserList.add(0, user);
        saveUserData(mUserList);
        notifyItemInserted(0);
    }

    public void reOrderByRank() {
        mUserList.clear();
        mUserList = getUserData();
        sortByRank(mUserList);
        notifyDataSetChanged();
    }

    public void loadLocalData() {
        if (getUserData() != null) {
            mUserList = getUserData();
        }
        notifyDataSetChanged();
    }

    private User getItem(int itemPosition) {
        return mUserList.get(itemPosition);
    }

    private void sortByRank(List<User> users) {
        for (int i = 0; i < users.size(); i++) {
            Collections.sort(users, (ob1, ob2) -> {
                if (ob1.getRanks().getOverall().getRank().compareTo(ob2.getRanks().getOverall().getRank()) == 0)
                    return 0;
                else if (ob1.getRanks().getOverall().getRank().compareTo(ob2.getRanks().getOverall().getRank()) > 0)
                    return 1;
                else return -1;
            });
        }
    }

    private void saveUserData(ArrayList<User> userArrayList) {
        String httpParamJSONList = new Gson().toJson(userArrayList);
        SharedPreferences prefs = mContext.getSharedPreferences(Constants.STRING_USER_ARRAYLIST, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(Constants.STRING_USER_ARRAYLIST, httpParamJSONList);
        editor.apply();
    }

    private ArrayList<User> getUserData() {
        SharedPreferences prefs = mContext.getSharedPreferences(Constants.STRING_USER_ARRAYLIST, Context.MODE_PRIVATE);
        String httpParamJSONList = prefs.getString(Constants.STRING_USER_ARRAYLIST, "");
        return new Gson().fromJson(httpParamJSONList, new TypeToken<List<User>>() {
        }.getType());
    }

    public interface OnUserItemListener {
        void onUserItemClick(String userName);
    }
}
