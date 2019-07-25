package com.stcet.navdrawer;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import io.realm.RealmResults;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private RealmResults<User> userRealmResults;
    private Context context;

    public MyAdapter(RealmResults<User> realmResults, Context context) {
        this.userRealmResults = realmResults;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        User user = userRealmResults.get(position);


        for(int i = 0; i < user.title.size(); i++ ) {

            holder.name.setText(user.title.get(i));
            holder.Due_by.setText(user.due.get(i));
        }

    }

    public int getItemCount() {
        return userRealmResults.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView Due_by;


        public MyViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.title_tv);
            Due_by = itemView.findViewById(R.id.body_tv);


        }
    }

}
