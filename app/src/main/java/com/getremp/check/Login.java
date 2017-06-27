package com.getremp.check;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.google.firebase.auth.FirebaseAuth.*;

public class Login extends AppCompatActivity {
    private static final String TAG="MainActivity";

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        final Button btnLogin = (Button) findViewById(R.id.btnLogin);
        final Button btnLogout=(Button)findViewById(R.id.btnLogOut);
        final Button btnAddToDB=(Button)findViewById(R.id.btnAddItemtoDB);
        final EditText etUserName=(EditText)findViewById(R.id.etUserName);
        final EditText etPassword=(EditText)findViewById(R.id.etPassword);
        final Button btnViewUserInfo=(Button)findViewById(R.id.btnViewDbInfo);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    makeToast("succesfully login with "+user.getEmail());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                    makeToast("Succesfully signed out");
                }
                // ...
            }
        };



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email=etUserName.getText().toString();
                String pass=etPassword.getText().toString();
                if (!email.equals("")&&!pass.equals(""))
                {
                    mAuth.signInWithEmailAndPassword(email,pass);
                }
                else
                {
                    makeToast("you didnt fill all fields");

                }

                final FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myrootRef = database.getReference();
                DatabaseReference Users = myrootRef.child("Users");

                btnLogout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mAuth.signOut();
                        makeToast("Signed out...");
                    }
                });

                btnAddToDB.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(Login.this,AddToDb.class);
                        startActivity(intent);
                    }
                });

                btnViewUserInfo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(Login.this,ViewDatabase.class);
                        startActivity(intent);
                    }
                });

//                myRef.setValue("Hello, World!");
//                Intent intent=new Intent(getBaseContext(),SettingsActivity.class);
//              startActivity(intent);


            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);

//        User_1.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
////do nothing fow now
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
    }
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    private void makeToast(String toastMessage)
    {
        Toast.makeText(this,toastMessage,Toast.LENGTH_SHORT).show();
    }
}
