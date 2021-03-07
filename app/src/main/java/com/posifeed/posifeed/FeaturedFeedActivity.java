package com.posifeed.posifeed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class FeaturedFeedActivity extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collectionReference = db.collection("featured-articles");
    private ArticleAdapter articleAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_featured_feed);

        recyclerView = findViewById(R.id.recycler_view);

        setUpFeedRecyclerView();
    }

    private void setUpFeedRecyclerView() {
        Query query = collectionReference.orderBy("uploadDate", Query.Direction.ASCENDING);

        FirestoreRecyclerOptions<Article> options = new FirestoreRecyclerOptions.Builder<Article>()
                .setQuery(query, Article.class)
                .build();

        articleAdapter = new ArticleAdapter(options);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(articleAdapter);

        articleAdapter.setOnArticleClickListener(new ArticleAdapter.OnArticleClickListener() {
            @Override
            public void onArticleClick(DocumentSnapshot documentSnapshot, int position) {
                String articleID = documentSnapshot.getId();

                Intent intent = new Intent(FeaturedFeedActivity.this, FeaturedArticleActivity.class);
                intent.putExtra("articleID", articleID);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        articleAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        articleAdapter.stopListening();
    }
}