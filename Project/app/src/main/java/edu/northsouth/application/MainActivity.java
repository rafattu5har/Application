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
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    //Declaring the variables
    private EditText userEmailSIEditText, userPassSIEditText;
    private TextView forgotPassSITextView, createACSITextView;
    private Button logInSIBtn;

    private ProgressBar logInSIProgressBar;

    public FirebaseAuth mAuth;
    //private FirebaseAuth.AuthStateListener mAuthListemer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("Sign In");

        //Creating the object
        userEmailSIEditText = (EditText) findViewById(R.id.userEmailSIEditText);
        userPassSIEditText = (EditText) findViewById(R.id.userPassSIEditText);

        forgotPassSITextView = (TextView) findViewById(R.id.forgotPassSITextView);
        createACSITextView = (TextView) findViewById(R.id.createAcSITextView);

        logInSIBtn = (Button) findViewById(R.id.logInSIBtn);

        logInSIProgressBar = (ProgressBar) findViewById(R.id.logInSIprogressBar);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        //Set onClick Listener for neccessary objects
        logInSIBtn.setOnClickListener(this);
        forgotPassSITextView.setOnClickListener(this);
        createACSITextView.setOnClickListener(this);


    }

    /**
     * Description: Set a on click listener for each of the touchable event
     */
    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.logInSIBtn:
                userLogIn();
                mAuth.signOut();
                Toast.makeText(getApplicationContext(),"Sign Out",Toast.LENGTH_SHORT);
                break;

            case R.id.createAcSITextView:
                mAuth.signOut();
                Intent signUp = new Intent(getApplicationContext(),SignUp.class);
                startActivity(signUp);
                break;

            case R.id.forgotPassSITextView:
                Intent passRecovery = new Intent(getApplicationContext(),PasswordRecovery.class);
                startActivity(passRecovery);
                break;
        }

    }

    /**
     * userLohIn method simply log in firebase account
     * @param Takes no parameter as input
     */
    private void userLogIn()
    {
        String email, password; // String types Email and Password variables

        email = userEmailSIEditText.getText().toString().trim();
        password = userPassSIEditText.getText().toString().trim();

        //checking the validity of the email
        if(email.isEmpty())
        {
            userEmailSIEditText.setError("Enter an email address");
            userEmailSIEditText.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            userEmailSIEditText.setError("Enter a valid email address");
            userEmailSIEditText.requestFocus();
            return;
        }

        //checking the validity of the password
        if(password.isEmpty())
        {
            userPassSIEditText.setError("Enter a password");
            userPassSIEditText.requestFocus();
            return;
        }

        if(password.length()<6)
        {
            userPassSIEditText.setError("Password should be at least 6 character long");
            userPassSIEditText.requestFocus();
            return;
        }

        logInSIProgressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                logInSIProgressBar.setVisibility(View.GONE);
                if(task.isComplete())
                {
                    if(task.isSuccessful())
                    {
                        Intent UserBusiness = new Intent(getApplicationContext(), edu.northsouth.application.UserBusiness.class);
                        Toast.makeText(getApplicationContext(),"Log in successful",Toast.LENGTH_SHORT).show();
                        UserBusiness.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(UserBusiness);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Incorrect Email or Password",Toast.LENGTH_SHORT).show();
                    }

                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Error: "+task.getResult(),Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    /**
     * onStart method monitor the log in state of a user
     * @param no parameter
     */
    @Override
    public void onStart()
    {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    /**
     * description: updateUI method contains codes for update of the UI on sign in state
     * @param currentUser FirebaseUser type parameter passes
     */
    private void updateUI(FirebaseUser currentUser)
    {
        if (currentUser != null) {
            //Code for signed in stage
            Log.d(TAG, "onAuthStateChange:signed_in:"+currentUser.getUid());
        } else {
            //Code for sighned out stage
            Log.d(TAG,"onAuthStateChange:signed_out");
        }
    }


}
