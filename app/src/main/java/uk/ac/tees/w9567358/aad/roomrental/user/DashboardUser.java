package uk.ac.tees.w9567358.aad.roomrental.user;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import uk.ac.tees.w9567358.aad.roomrental.HouseModel;
import uk.ac.tees.w9567358.aad.roomrental.R;
import uk.ac.tees.w9567358.aad.roomrental.adapter.SeeHouseAdapterUser;
import uk.ac.tees.w9567358.aad.roomrental.houseOwner.RegisterOwner;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DashboardUser extends AppCompatActivity {

    private RecyclerView rv_showAllFood;
    private SeeHouseAdapterUser adapter;
    private ArrayList<HouseModel> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_user);

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        rv_showAllFood = findViewById(R.id.recyclerView);
        rv_showAllFood.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DashboardUser.this);
        rv_showAllFood.setLayoutManager(linearLayoutManager);
        getAllArticle();

    }

    private void getAllArticle() {

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String userId = firebaseUser.getUid();
        if (firebaseUser.getUid() != null) {
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child(RegisterOwner.HOUSES);
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    mList.clear();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            HouseModel article = dataSnapshot1.getValue(HouseModel.class);
                            mList.add(article);
                        }
                    }
                    Log.d("TAG1", "onDataChange: " + mList.get(0));
                    adapter = new SeeHouseAdapterUser(DashboardUser.this, mList);
                    rv_showAllFood.setAdapter(adapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.searchmenu,menu);
        MenuItem item=menu.findItem(R.id.search);
        SearchView searchView=(SearchView) item.getActionView();
        searchView.setQueryHint("Search by Area");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                adapter.getFilter().filter(newText);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }


}