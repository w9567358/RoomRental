package uk.ac.tees.w9567358.aad.roomrental;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import uk.ac.tees.w9567358.aad.roomrental.houseOwner.LoginOwner;
import uk.ac.tees.w9567358.aad.roomrental.user.LoginUser;

public class MainActivity extends AppCompatActivity {

    Button btn_houseOwner, btn_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_houseOwner = findViewById(R.id.btn_houseOwner);
        btn_user = findViewById(R.id.btn_user);

        btn_houseOwner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginOwner.class));
            }
        });

        btn_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginUser.class));
            }
        });

    }
}