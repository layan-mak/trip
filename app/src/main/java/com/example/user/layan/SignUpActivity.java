package com.example.user.layan;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    EditText username, password, email, phone_number;
    Button btSignUp;
    final String TAG="Firebase";
    TextView signUpTV;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signUpTV= (TextView) findViewById(R.id.signUpTV);
        username= (EditText) findViewById(R.id.username);
        password= (EditText) findViewById(R.id.password);
        email= (EditText) findViewById(R.id.email);
        phone_number= (EditText) findViewById(R.id.phone_number);

        mAuth = FirebaseAuth.getInstance();
        btSignUp= (Button) findViewById(R.id.btSignUp);
        btSignUp.setOnClickListener(this);
    }

    @Override
    public void onStart() {

            super.onStart();
            // Check if user is signed in (non-null) and update UI accordingly.
            FirebaseUser currentUser = mAuth.getCurrentUser();
            //updateUI(currentUser);

    }

    public void createUserWithEmailAndPassword(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
            if (task.isSuccessful()) {
                // Sign in success, update UI with the signed-in user's information
                Log.d(TAG, "createUserWithEmail:success");
                FirebaseUser user = mAuth.getCurrentUser();
                Intent i = new Intent(SignUpActivity.this, TripListActivity.class);
                startActivity(i);

                //updateUI(user);
            } else {
                // If sign in fails, display a message to the user.
                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                Toast.makeText(SignUpActivity.this, "Authentication Failed "+task.getException(),
                        Toast.LENGTH_SHORT).show();
                //updateUI(null);
            }

            // ...
        }});
    }

    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    @Override
    public void onClick(View v) {
      //  createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString());

        if ( v==btSignUp ) {
            String getEmail = email.getText().toString();

            if ((username.equals("") || password==null || email.equals("") || phone_number==null) || (!isEmailValid(getEmail))) {

                //Check if one or more fields are empty
                if (username.equals("") || password==null || email.equals("") || phone_number==null)
                    Toast.makeText(this, "There Are Empty Fields", Toast.LENGTH_SHORT).show();

                // Check if email id is valid or not
                if (!isEmailValid(getEmail))
                    Toast.makeText(this, "\"Your Email Id is Invalid.\"", Toast.LENGTH_SHORT).show();
            }

            else {
                createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString());
            }

        }
    }
}


