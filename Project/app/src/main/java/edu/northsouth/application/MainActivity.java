package edu.northsouth.application;

import android.content.Intent;
import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Declaring the variables
    private EditText userEmailSIEditText, userPassSIEditText;
    private TextView forgotPassSITextView, createACSITextView;
    private Button logInSIBtn;

    private ProgressBar logInSIProgressBar;

    private FirebaseAuth mAuth;

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
     * On click events
     */
    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.logInSIBtn:
                userLogIn();
                break;

            case R.id.createAcSITextView:
                Intent signUp = new Intent(getApplicationContext(),SignUp.class);
                startActivity(signUp);
                break;

            case R.id.forgotPassSITextView:
                Intent passRecovery = new Intent(getApplicationContext(),PasswordRecovery.class);
                startActivity(passRecovery);
                break;
        }

    }

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
                if(task.isSuccessful())
                {
                    finish();
                    Intent UserBusiness = new Intent(getApplicationContext(), edu.northsouth.application.UserBusiness.class);
                    UserBusiness.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(UserBusiness);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Incorrect Email or Password",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
