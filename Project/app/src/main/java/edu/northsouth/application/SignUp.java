package edu.northsouth.application;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;

public class SignUp extends AppCompatActivity implements View.OnClickListener {


    private EditText fullNameSUEditText, userIdSUEditText, emailSUEditText, genderSUEditText, passSUEditText, cPassSUEditTExt; //Create EditText objects
    private Button signUpSUBtn; // Create Button Object
    private TextView signInSUTextView; //Create TextView Object
    private ProgressBar progressBarSUProgressBar; // Create ProgressBar object

    private FirebaseAuth mAuth; // Create FireBase object for Authentication

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        this.setTitle("Sign Up");

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        fullNameSUEditText = (EditText) findViewById(R.id.fullNameSUEditText);
        userIdSUEditText = (EditText) findViewById(R.id.userIdSUEditText);
        emailSUEditText = (EditText) findViewById(R.id.emailSUEditText);
        genderSUEditText = (EditText) findViewById(R.id.genderSUEditText);
        passSUEditText = (EditText) findViewById(R.id.passwordSUEditText);
        cPassSUEditTExt = (EditText) findViewById(R.id.conPasswordSUEditText);

        signUpSUBtn = (Button) findViewById(R.id.signUpSUBtn);

        progressBarSUProgressBar = (ProgressBar) findViewById(R.id.signUpProgressBar);

        signInSUTextView = (TextView) findViewById(R.id.signInSUEextView);

        signUpSUBtn.setOnClickListener(this);
        signInSUTextView.setOnClickListener(this);



    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.signUpSUBtn:
                userRegister();
                break;

            case R.id.signInSUEextView:
                Intent signIn = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(signIn);
                break;
        }
    }
    /*
    * register a user in FIREBASE database Existing API
    * */
    private void userRegister()
    {
        String email, password; // String types Email and Password variables

        email = emailSUEditText.getText().toString().trim();
        password = passSUEditText.getText().toString().trim();

        //checking the validity of the email
        if(email.isEmpty())
        {
            emailSUEditText.setError("Enter an email address");
            emailSUEditText.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            emailSUEditText.setError("Enter a valid email address");
            emailSUEditText.requestFocus();
            return;
        }

        //checking the validity of the password
        if(password.isEmpty())
        {
            passSUEditText.setError("Enter a password");
            passSUEditText.requestFocus();
            return;
        }

        if(password.length()<6)
        {
            passSUEditText.setError("Password should be at least 6 character long");
            passSUEditText.requestFocus();
            return;
        }

        progressBarSUProgressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                progressBarSUProgressBar.setVisibility(View.GONE);
                if (task.isSuccessful()) {
                // Sign in success, update UI with the signed-in user's information
                Toast.makeText(getApplicationContext(),"Register is Successfull",Toast.LENGTH_SHORT).show();
            } else {
                   
                // If sign in fails, display a message to the user.
                    Toast.makeText(getApplicationContext(),"Register is not successfull",Toast.LENGTH_SHORT).show();
            }
            }
        });

    }
}
