package com.ttn.rxjava.view.operators.transformation.flatMap_concatMap_switchMap_Operator;

/*
 * Created by Naveen Verma on 26/6/20.
 * Company name: To The New
 * Email ID: naveen.verma@tothenew.com
 */

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ttn.rxjava.R;
import com.ttn.rxjava.model.Post;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private static final String TAG = "RecyclerAdapter";

    private List<Post> posts = new ArrayList<>();
    private OnPostClickListener onPostClickListener;

    public RecyclerAdapter(OnPostClickListener onPostClickListener) {
        this.onPostClickListener = onPostClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_layout_post_list_item, null, false);
        return new MyViewHolder(view, onPostClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.bind(posts.get(position));
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
        notifyDataSetChanged();
    }

    public void updatePost(Post post) {
        posts.set(posts.indexOf(post), post);
        notifyItemChanged(posts.indexOf(post));
    }

    public List<Post> getPosts() {
        return posts;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title, numComments;
        ProgressBar progressBar;

        OnPostClickListener onPostClickListener;

        public MyViewHolder(@NonNull View itemView, OnPostClickListener onPostClickListener) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            numComments = itemView.findViewById(R.id.num_comments);
            progressBar = itemView.findViewById(R.id.progress_bar);

            this.onPostClickListener = onPostClickListener;

            itemView.setOnClickListener(this);

        }

        public void bind(Post post) {
            title.setText(post.getTitle());

            if(onPostClickListener == null) {
                if (post.getComments() == null) {
                    showProgressBar(true);
                    numComments.setText("");
                } else {
                    showProgressBar(false);
                    numComments.setText(String.valueOf(post.getComments().size()));
                }
            }
        }

        private void showProgressBar(boolean showProgressBar) {
            if (showProgressBar) {
                progressBar.setVisibility(View.VISIBLE);
            } else {
                progressBar.setVisibility(View.GONE);
            }
        }

        @Override
        public void onClick(View v) {
            onPostClickListener.onPostClick(getAdapterPosition());
        }
    }

    public interface OnPostClickListener {
        void onPostClick(int position);
    }
}



