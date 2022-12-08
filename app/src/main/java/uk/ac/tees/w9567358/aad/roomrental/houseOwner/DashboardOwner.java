package uk.ac.tees.w9567358.aad.roomrental.houseOwner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import uk.ac.tees.w9567358.aad.roomrental.R;

public class DashboardOwner extends AppCompatActivity {

    Button btn_addHouse, btn_seeHouse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_owner);

        btn_addHouse = findViewById(R.id.btn_addHouse);
        btn_seeHouse = findViewById(R.id.btn_seeHouse);

        btn_seeHouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardOwner.this, SeeHouse.class));
            }
        });

        btn_addHouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardOwner.this, AddHouse.class));
            }
        });
    }
}