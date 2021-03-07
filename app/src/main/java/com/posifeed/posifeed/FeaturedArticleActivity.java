package com.posifeed.posifeed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FeaturedArticleActivity extends AppCompatActivity {

    private String articleID;
    private FirebaseFirestore db;
    private DocumentReference dr;
    private ImageView image1, authorImage;
    private TextView heading, desc, authorName;
    private LinearLayout linearLayout, linearLayout3;
    private Button addComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        articleID = getIntent().getStringExtra("articleID");
        db = FirebaseFirestore.getInstance();
        dr = db.collection("featured-articles").document(articleID);
        heading = findViewById(R.id.heading);
        desc = findViewById(R.id.desc);
        image1 = findViewById(R.id.image1);
        linearLayout = findViewById(R.id.linear);
        linearLayout3 = findViewById(R.id.linear3);
        authorImage = findViewById(R.id.profile_image);
        authorName = findViewById(R.id.author_name);
        addComment = findViewById(R.id.comment);

        addComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FeaturedArticleActivity.this, CommentActivity.class);
                intent.putExtra("articleID", articleID);
                startActivity(intent);
            }
        });

        dr.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()) {
                    DocumentSnapshot ds = task.getResult();
                    if(ds.exists()) {
                        heading.setText(ds.getData().get("heading").toString());
                        desc.setText(ds.getData().get("articleBrief").toString());
                        Glide.with(getApplicationContext()).load(ds.getData().get("articlePic")).centerCrop().into(image1);

                        List<Map<String, String>> l = (List<Map<String, String>>) ds.get("paras");

                        //Article Content Section
                        if(l != null) {
                            for(int i = 0;i < l.size(); i++) {

                                TextView paraHeading = makeSubheadingTextView(l.get(i).get("heading"));
                                TextView paraContent = makeContentTextView(l.get(i).get("content"), i);

                                if(l.get(i).get("image") != null) {
                                    ImageView imageView = new ImageView(getApplicationContext());
                                    LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(
                                            LinearLayout.LayoutParams.MATCH_PARENT,
                                            600
                                    );
                                    imageParams.setMargins(0, 60, 0, 0);
                                    Glide.with(getApplicationContext()).load(l.get(i).get("image")).centerCrop().into(imageView);
                                    imageView.setLayoutParams(imageParams);
                                    linearLayout.addView(imageView);
                                }
                                linearLayout.addView(paraHeading);
                                linearLayout.addView(paraContent);
                            }
                        }

                        Map<String, String> m = (Map<String, String>) ds.get("video");

                        //Youtube Video Section
                        if(m != null) {
                            TextView youHeading = makeSubheadingTextView("Featured Youtube Video");
                            linearLayout.addView(youHeading);

                            TextView youtubeVid = makeLinkTextView(m.get("name"),0);
                            linearLayout.addView(youtubeVid);

                            youtubeVid.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(m.get("url"))));
                                }
                            });
                        }

                        //Author Details
                        Glide.with(getApplicationContext()).load(ds.get("authorPic").toString()).fitCenter().into(authorImage);
                        authorName.setText(ds.get("authorName").toString());

                        //Load Comments
                        List<Map<String, String>> comments = (List<Map<String, String>>) ds.get("comments");
                        if(comments != null) {
                            for(int i = 0;i < comments.size(); i++) {
                                if(comments.get(i).get("verified").equals("true")) {
                                    TextView user = makeCommentHeadTextView(comments.get(i).get("name"));
                                    TextView comment = makeCommentContentTextView(comments.get(i).get("comment"), i);

                                    linearLayout3.addView(user);
                                    linearLayout3.addView(comment);
                                }
                            }
                        }

                    } else {

                    }
                } else {

                }
            }
        });
    }

    private TextView makeSubheadingTextView(String text) {

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(0, 80, 0, 0);

        TextView heading = new TextView(getApplicationContext());
        heading.setText(text);
        heading.setTextSize(24);
        heading.setTypeface(ResourcesCompat.getFont(getApplicationContext(), R.font.mclaren));
        heading.setTextColor(Color.rgb(98,0,238));
        heading.setLayoutParams(params);

        return heading;
    }

    private TextView makeContentTextView(String text, int id) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(0, 10, 0, 0);

        TextView paraContent = new TextView(getApplicationContext());
        paraContent.setText(text.replace("\\n", "\n"));
        paraContent.setId(id);
        paraContent.setTextSize(17);
        paraContent.setTypeface(ResourcesCompat.getFont(getApplicationContext(), R.font.mclaren));
        paraContent.setLayoutParams(params);

        return paraContent;
    }

    private TextView makeLinkTextView(String text, int id) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(0, 40, 0, 0);

        TextView linkContent = new TextView(getApplicationContext());
        linkContent.setText(text);
        linkContent.setId(id);
        linkContent.setTextSize(17);
        linkContent.setTextColor(Color.BLUE);
        linkContent.setTypeface(ResourcesCompat.getFont(getApplicationContext(), R.font.mclaren));
        linkContent.setLayoutParams(params);

        return linkContent;
    }

    private TextView makeCommentHeadTextView(String text) {

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(20, 30, 0, 0);

        TextView heading = new TextView(getApplicationContext());
        heading.setText(text);
        heading.setTextSize(18);
        heading.setTypeface(ResourcesCompat.getFont(getApplicationContext(), R.font.mclaren));
        heading.setTextColor(Color.BLACK);
        heading.setLayoutParams(params);

        return heading;
    }

    private TextView makeCommentContentTextView(String text, int id) {

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(20, 10, 0, 0);

        TextView paraContent = new TextView(getApplicationContext());
        paraContent.setText(text);
        paraContent.setId(id);
        paraContent.setTextSize(14);
        paraContent.setTypeface(ResourcesCompat.getFont(getApplicationContext(), R.font.mclaren));
        paraContent.setLayoutParams(params);

        return paraContent;
    }
}