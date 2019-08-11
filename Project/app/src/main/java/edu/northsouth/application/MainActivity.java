package edu.northsouth.application;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView forgotPassTextView = (TextView) findViewById(R.id.forgotPassTextView);
        forgotPassTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent passRecovery = new Intent(getApplicationContext(),PasswordRecovery.class);

                startActivity(passRecovery);
            }
        });
    }



}
