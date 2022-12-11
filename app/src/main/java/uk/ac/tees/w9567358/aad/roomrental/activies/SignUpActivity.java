package uk.ac.tees.w9567358.aad.roomrental.activies;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import uk.ac.tees.w9567358.aad.roomrental.R;

public class SignUpActivity extends AppCompatActivity {

    private EditText phoneNumber,age,emailText,userName,passwordText;
    private FirebaseUser user;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private Button signUpBtn;
    private TextView goTo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        db = FirebaseFirestore.getInstance();

        phoneNumber = findViewById(R.id.phoneNumberSignUp);
        age = findViewById(R.id.ageSignUp);
        emailText = findViewById(R.id.emailSignUp);
        userName = findViewById(R.id.signUpUserName);
        passwordText =  findViewById(R.id.signUpPassword);
        goTo  = findViewById(R.id.haveAccount);
        signUpBtn = findViewById(R.id.signUpButton);


        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp(v);
            }
        });

        goTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(SignUpActivity.this, SignInButton.class);
                startActivity(a);
            }
        });



    }

    private void signUp(View view){
        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Snackbar.make(view,"Register Success",3000).show();
                    Intent intent = new Intent(SignUpActivity.this, HomeActivity.class);
                    startActivity(intent);
                    dbCall(phoneNumber.getText().toString(),age.getText().toString(),emailText.getText().toString(),userName.getText().toString(),passwordText.getText().toString());
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Snackbar.make(view,e.getMessage(),3000).show();
            }
        });
    }
    private void dbCall(String phoneNumber, String age, String email, String username, String password) {

        Map<String,Object> user = new HashMap<>();
        user.put("phoneNumber",phoneNumber);
        user.put("age",age);
        user.put("email",email);
        user.put("username",username);
        user.put("password",password);


        db.collection("User").add(user).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                System.out.println("Successfully User created.");

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                System.out.println("Failed with error." + e.getMessage());
            }
        });
    }


}