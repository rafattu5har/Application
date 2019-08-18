package edu.northsouth.application;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Declaring the variables
    private EditText userEmailSIEditText, userPassSIEditText;
    private TextView forgotPassSITextView, createACSITextView;
    private Button logInSIBtn;

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

        //Set onClick Listener for neccessary objects
        logInSIBtn.setOnClickListener(this);
        forgotPassSITextView.setOnClickListener(this);
        createACSITextView.setOnClickListener(this);




        /*
        * TextView forgotPassTextView = (TextView) findViewById(R.id.forgotPassSITextView);
        forgotPassTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent passRecovery = new Intent(getApplicationContext(),PasswordRecovery.class);

                startActivity(passRecovery);
            }
        });

        TextView createAcTextView = (TextView) findViewById(R.id.createAcSITextView);
        createAcTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent signUp = new Intent(getApplicationContext(),SignUp.class);
                startActivity(signUp);
            }
        });
        *
        * */

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
}
