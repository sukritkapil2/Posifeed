package com.posifeed.posifeed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CommentActivity extends AppCompatActivity {

    private EditText name, comment;
    private String nameString, commentString;
    private RelativeLayout relativeLayout;
    private Button postComment;
    private String articleID;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collectionReference = db.collection("articles");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        name = findViewById(R.id.name);
        comment = findViewById(R.id.comment);
        relativeLayout = findViewById(R.id.rel);
        postComment = findViewById(R.id.post);
        articleID = getIntent().getStringExtra("articleID");

        DocumentReference dr = collectionReference.document(articleID);

        postComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameString = name.getText().toString();
                commentString = comment.getText().toString();
                if(nameString.length() != 0 && commentString.length() != 0) {
                    Map<String, String> m = new HashMap<>();
                    m.put("name", nameString);
                    m.put("comment", commentString);
                    m.put("verified", "false");
                    dr.update("comments", FieldValue.arrayUnion(m)).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Snackbar.make(relativeLayout, "Comment sent for verification. It takes some time!", Snackbar.LENGTH_LONG)
                                    .show();
                        }
                    });
                } else {
                    Snackbar.make(relativeLayout, "Please fill all the fields", Snackbar.LENGTH_SHORT)
                            .show();
                }
            }
        });
    }
}