package uk.ac.tees.w9567358.aad.roomrental;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class insert extends AppCompatActivity {
    private EditText editnpm,editnama,editkelas,editprodi;
    private Button btnsave;
    private AppDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        editnpm = findViewById(R.id.NPM);
        editnama = findViewById(R.id.NAMA);
        editkelas = findViewById(R.id.KELAS);
        editprodi = findViewById(R.id.PRODI);
        btnsave = findViewById(R.id.btn_save);
        database = AppDatabase.getInstance(getApplicationContext());
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database.userDao().insertAll(editnpm.getText().toString(),editnama.getText().toString(),
                        editkelas.getText().toString(),editprodi.getText().toString());
                finish();


            }
        });
    }
}