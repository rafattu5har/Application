package edu.northsouth.application;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class UserBusiness extends AppCompatActivity {

    Button profileUsBuBtn,bookingUsBuBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_business);
        this.setTitle("New Feed");

        profileUsBuBtn = (Button) findViewById(R.id.profileUsBuBtn);
        bookingUsBuBtn = (Button) findViewById(R.id.bookingUsBuBtn);

        profileUsBuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),BusinessProfile.class));
            }
        });

        bookingUsBuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),StoreList.class));
            }
        });


    }
}
