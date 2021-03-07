package com.posifeed.posifeed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class FeedListActivity extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collectionReference = db.collection("articles");
    private ArticleAdapter articleAdapter;
    private RecyclerView recyclerView;
    private FloatingActionButton floatingActionButton, floatingActionButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_list);

        recyclerView = findViewById(R.id.recycler_view);
        floatingActionButton = findViewById(R.id.fab);
        floatingActionButton2 = findViewById(R.id.fab2);

        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialAlertDialogBuilder(FeedListActivity.this)
                        .setTitle("Send your article to us!")
                        .setMessage("Please format your article request as follows: \n\n" +
                                "1. Article Heading\n2. Article Brief\n3. Article Content\n" +
                                "4. Article links (if any)\n5. Video links (if any)\n" +
                                "6. Your name and photo\n\n\nSend the article here:\n\nGMAIL: posifeedsk@gmail.com\n\nFeel free to send any photos you want to add as well!").show();
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FeedListActivity.this, FeaturedFeedActivity.class);
                startActivity(intent);
            }
        });

        setUpFeedRecyclerView();
    }

    private void setUpFeedRecyclerView() {
        Query query = collectionReference.orderBy("uploadDate", Query.Direction.DESCENDING);

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

                Intent intent = new Intent(FeedListActivity.this, ArticleActivity.class);
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

    public void onBackPressed(){
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }
}