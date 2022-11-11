package uk.ac.tees.w9567358.aad.roomrental;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import uk.ac.tees.w9567358.aad.roomrental.Adapter.RecyAdap;
import uk.ac.tees.w9567358.aad.roomrental.Data.DataMhsw;


public class RecycleActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Button btnTambah;
    private AppDatabase database;
    private RecyAdap userAdapter;
    private final List<DataMhsw> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_database);

        database = AppDatabase.getInstance(getApplicationContext());
        list.clear();
        list.addAll(database.userDao().getAll());
        Toast.makeText(getApplicationContext(), list.toString(), Toast.LENGTH_SHORT).show();
        userAdapter = new RecyAdap(getApplicationContext(), list);
        recyclerView = findViewById(R.id.recycler_view);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(userAdapter);


    }

    @Override
    protected void onStart() {
        super.onStart();
        list.clear();
        list.addAll(database.userDao().getAll());
        userAdapter.notifyDataSetChanged();

    }
}