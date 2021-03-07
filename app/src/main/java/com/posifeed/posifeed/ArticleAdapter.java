package com.posifeed.posifeed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.Calendar;

public class ArticleAdapter extends FirestoreRecyclerAdapter<Article, ArticleAdapter.ArticleHolder> {

    private ArticleAdapter.OnArticleClickListener listener;
    private Context myContext;

    public ArticleAdapter(@NonNull FirestoreRecyclerOptions<Article> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ArticleAdapter.ArticleHolder holder, int position,
                                    @NonNull Article model) {
        holder.article_heading.setText(model.getHeading());
        holder.uploadDate.setText(model.getUploadDate().toString());
        holder.article_brief.setText(model.getArticleBrief());
        if(!model.getNew()) {
            holder.isNew.setVisibility(View.GONE);
        }
        Glide.with(myContext.getApplicationContext()).load(model.getArticlePic()).fitCenter().into(holder.author_pic);
    }

    @NonNull
    @Override
    public ArticleAdapter.ArticleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.feed_article_card, parent, false);
        myContext = parent.getContext();
        return new ArticleHolder(v);
    }

    class ArticleHolder extends RecyclerView.ViewHolder {

        TextView article_heading, article_brief, uploadDate, isNew;
        ImageView author_pic;

        public ArticleHolder(@NonNull View itemView) {
            super(itemView);
            article_heading = itemView.findViewById(R.id.heading);
            article_brief = itemView.findViewById(R.id.brief);
            uploadDate = itemView.findViewById(R.id.date);
            author_pic = itemView.findViewById(R.id.profile_image);
            isNew = itemView.findViewById(R.id.isNew);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION && listener != null) {
                        listener.onArticleClick(getSnapshots().getSnapshot(position), position);
                    }
                }
            });
        }
    }

    public interface OnArticleClickListener {
        void onArticleClick(DocumentSnapshot documentSnapshot, int position);
    }

    public void setOnArticleClickListener(ArticleAdapter.OnArticleClickListener listener) {
        this.listener = listener;
    }
}
