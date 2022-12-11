package uk.ac.tees.w9567358.aad.roomrental.activies;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import uk.ac.tees.w9567358.aad.roomrental.R;

public class LogInActivity extends AppCompatActivity {

    private EditText userNameText,passwordText;
    private TextView noHaveAccount;
    private FirebaseAuth mAuth;
    private FirebaseUser firebaseUser;
    private Button logInBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        userNameText=findViewById(R.id.logInUserName);
        passwordText = findViewById(R.id.logInPassword);
        noHaveAccount = findViewById(R.id.notHaveAccount);

        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();

        if(firebaseUser !=  null){
            Intent intent = new Intent(LogInActivity.this, HomeActivity.class);
            startActivity(intent);
        }

        noHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(LogInActivity.this,SignUpActivity.class);
                startActivity(a);
            }
        });

        logInBtn = findViewById(R.id.logInButton);

        logInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(v);
            }
        });
    }
    private void login(View view){
        String userName = userNameText.getText().toString();
        String password = passwordText.getText().toString();

        mAuth.signInWithEmailAndPassword(userName,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Snackbar.make(view,"Success",3000).show();
                    Intent intent = new Intent(LogInActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Snackbar.make(view,e.getMessage(),3000).show();
            }
        });
    }
}