package com.ahliandroid.belajarfirebase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

public class Post extends AppCompatActivity {

    EditText etPost;
    Button btnPost;

    FirebaseDatabase mDatabase;
    DatabaseReference mRoot, mRef, mUser;
    FirebaseAuth mAuth;

    String fullName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        etPost = (EditText) findViewById(R.id.etPost);
        btnPost = (Button) findViewById(R.id.btnPost);

        mDatabase = FirebaseDatabase.getInstance();
        mRoot = mDatabase.getReference();
        mAuth = FirebaseAuth.getInstance();
        final String userId = mAuth.getCurrentUser().getUid();
     //   mRef = mRoot.child("posts").child(userId);
        mUser = mRoot.child("users").child(userId).child("fullName");
        mUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                fullName = dataSnapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String post = etPost.getText().toString();
                mRef = mRoot.child("posts");
                final String postKey = mRef.push().getKey();
                mRef.child(postKey).child("timestamp").setValue(ServerValue.TIMESTAMP);
                Query query = mRef.child(postKey).child(userId).child("timestamp");
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        long timestamp = dataSnapshot.getValue(long.class);
                        PostData postData = new PostData(post, fullName, timestamp);
                        mRef.child(postKey).child(userId).setValue(postData);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
