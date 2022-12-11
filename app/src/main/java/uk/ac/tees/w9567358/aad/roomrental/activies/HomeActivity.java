package uk.ac.tees.w9567358.aad.roomrental.activies;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import uk.ac.tees.w9567358.aad.roomrental.R;

public class HomeActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private FloatingActionButton action;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        action = findViewById(R.id.fab);

        bottomNavigationView = findViewById(R.id.bottomNavigationBar);
        bottomNavigationView.getMenu().getItem(2).isEnabled();
        bottomNavigationView.getItemRippleColor();

        NavController navController = Navigation.findNavController(this,R.id.containerFragment);
        NavigationUI.setupWithNavController(bottomNavigationView,navController);

        action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(HomeActivity.this,AddActivity.class);
                startActivity(a);
            }
        });


    }
}