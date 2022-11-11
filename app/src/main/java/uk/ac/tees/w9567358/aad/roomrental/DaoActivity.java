package uk.ac.tees.w9567358.aad.roomrental;

import uk.ac.tees.w9567358.aad.roomrental.Data.DataMhsw;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DaoActivity extends AppCompatActivity {

    protected AppDatabase db_ext;
    protected AppDatabase db;
    protected EditText nama;
    protected EditText kelas;
    protected EditText npm;
    protected EditText prodi;
    protected Button savebut;
    protected Button showbut;
    protected Button showallbut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dao);
        db_ext = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "crot-kelas").build();
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "crot-kelas").allowMainThreadQueries().build();
//        npm = (EditText) findViewById(R.id.npm);
//        nama = (EditText) findViewById(R.id.nama);
//        prodi = (EditText) findViewById(R.id.prodi);
//        kelas = (EditText) findViewById(R.id.kelas);

        savebut = findViewById(R.id.saveBut);
        showbut = findViewById(R.id.showBut);
        showallbut = findViewById(R.id.showBut2);

        savebut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DaoActivity.this, insert.class));
            }
        });

//        savebut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                DataMhsw data = new DataMhsw().GenerateObject(
//                        Integer.parseInt(npm.getText().toString()),
//                        nama.getText().toString(),
//                        kelas.getText().toString(),
//                        prodi.getText().toString()
//                );
//                insertData(data);
//            }
//        });

        showbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataMhsw data = db.userDao().searchById(Integer.parseInt(npm.getText().toString()));
                nama.setText(data.nama);
                kelas.setText(data.kelas);
                prodi.setText(data.prodi);
            }
        });

        showallbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DaoActivity.this, RecycleActivity.class));
            }
        });

    }

    private void insertData(final DataMhsw barang){

        new AsyncTask<Void, Void, Long>(){
            @Override
            protected Long doInBackground(Void... voids) {
                // melakukan proses insert data
                db_ext.userDao().insert(barang);
                return null;
            }

            @Override
            protected void onPostExecute(Long status) {
                Toast.makeText(getApplicationContext(), "saved Sucess", Toast.LENGTH_SHORT).show();
            }
        }.execute();
    }
}