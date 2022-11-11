package uk.ac.tees.w9567358.aad.roomrental;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SharedPrefActivity extends AppCompatActivity {
    private SharedPreferences sharepref;
    private SharedPreferences.Editor editor;
    private String key =  "ganteng";
    private final String sharedprefName = "mangsapbanget";
    private String temp_data = "empty";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sharepref);

        sharepref = getSharedPreferences(sharedprefName, Context.MODE_PRIVATE);
        editor = sharepref.edit();
        final TextView input = (TextView) findViewById(R.id.input);
        final TextView textView = (TextView) findViewById(R.id.textView3);
        final Button toastbut = (Button) findViewById(R.id.toast);
        final Button savebut = (Button) findViewById(R.id.save);


        toastbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                temp_data = sharepref.getString(key, "GantengBous");
                textView.setText(temp_data);
                Toast.makeText(
                        getApplicationContext(),
                        temp_data,
                        Toast.LENGTH_SHORT

                ).show();
            }
        });
        savebut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharepref = getSharedPreferences(sharedprefName, Context.MODE_PRIVATE);
                editor = sharepref.edit();
                editor.putString(key, input.getText().toString());
                editor.commit();
                Toast.makeText(getApplicationContext(), "data Saved", Toast.LENGTH_SHORT).show();

            }
        });
    }

    protected void setPref(){
        SharedPreferences sharepref = getSharedPreferences(sharedprefName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharepref.edit();
        TextView textView = (TextView) findViewById(R.id.input);
        editor.putString(key, textView.getText().toString());
        editor.commit();
        Toast.makeText(getApplicationContext(), "data Saved", Toast.LENGTH_SHORT).show();
    }

    protected void ShowPref(){
        SharedPreferences sharepref = getSharedPreferences(sharedprefName, Context.MODE_PRIVATE);
        TextView textView = (TextView) findViewById(R.id.textView3);
        temp_data = sharepref.getString(key, "GantengBous");
        textView.setText(temp_data);
        Toast.makeText(
                getApplicationContext(),
                temp_data,
                Toast.LENGTH_SHORT

        ).show();
    }
}