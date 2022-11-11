package uk.ac.tees.w9567358.aad.roomrental;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button shareprefbut = findViewById(R.id.sharepref);
        final Button roomdbbut = findViewById(R.id.roomdb);

        shareprefbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SharedPrefActivity.class));
                finish();
            }
        });

        roomdbbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, DaoActivity.class));
                finish();
            }
        });

    }
}