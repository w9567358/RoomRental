package uk.ac.tees.w9567358.aad.roomrental.activies;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import uk.ac.tees.w9567358.aad.roomrental.R;
import uk.ac.tees.w9567358.aad.roomrental.models.Post;
import uk.ac.tees.w9567358.aad.roomrental.viewmodel.HomeFragmentViewModel;

public class AddActivity extends AppCompatActivity {

    Bitmap selectedImage;
    private ImageView imageView,back;;
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;
    private Button addPostButton;
    Uri imageData;
    private TextInputEditText phone,description,rentOrSale,price,attribute,latitude,longitude,locationText;
    private EditText sq , bedCount, bathCount;

    private HomeFragmentViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        db = FirebaseFirestore.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();

        back = findViewById(R.id.backAdd);

        imageView = findViewById(R.id.uploadImage);
        phone = findViewById(R.id.phoneAdd);
        description = findViewById(R.id.descriptionAdd);
        attribute = findViewById(R.id.textInputEditText);
        rentOrSale = findViewById(R.id.rentOrSaleAdd);
        sq = findViewById(R.id.add_sq);
        bedCount = findViewById(R.id.add_beds);
        bathCount = findViewById(R.id.add_bath);
        price = findViewById(R.id.priceAdd);


        viewModel = new ViewModelProvider(this).get(HomeFragmentViewModel.class);
        addPostButton = findViewById(R.id.addPostBtn);



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(AddActivity.this, HomeActivity.class);
                startActivity(a);
            }
        });

        addPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharePost(phone.getText().toString(),description.getText().toString(),attribute.getText().toString(),sq.getText().toString(),rentOrSale.getText().toString(),bedCount.getText().toString(),bathCount.getText().toString(),price.getText().toString()
                ,locationText.getText().toString(),latitude.getText().toString(),longitude.getText().toString()
                );


            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage(v);
            }
        });

    }

    private void sharePost(String phoneNumber,
                           String description, String attribute,
                           String sq, String rentOrSale, String bedCount,
                           String bathCount,String price,String location,String latitude,String longitude) {


        Toast.makeText(this,attribute.trim(),Toast.LENGTH_SHORT).show();
        if(imageData != null){

            UUID uuid = UUID.randomUUID();
            final String imageName = "images/"+uuid+".jpg";
            storageReference.child(imageName).putFile(imageData).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    StorageReference newReference = FirebaseStorage.getInstance().getReference(imageName);
                    newReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {

                            String downLoadUrl = uri.toString();
                            HashMap<String, Object> post = new HashMap<>();
                            post.put("phone",phoneNumber);
                            post.put("description",description);
                            post.put("attribute",attribute);
                            post.put("sq",sq);
                            post.put("rentOrSale",rentOrSale);
                            post.put("bedCount",bedCount);
                            post.put("bathCount",bathCount);
                            post.put("price",price);
                            post.put("imageUrl",downLoadUrl);
                            post.put("location",location);
                            post.put("latitude",latitude);
                            post.put("longitude",longitude);

                            Post p = new Post(phoneNumber,description,attribute,sq,bedCount,rentOrSale,bathCount,downLoadUrl,price,location,latitude,longitude);
                            db.collection("Post").add(post).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentReference> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText  (AddActivity.this,"Success",Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(AddActivity.this, HomeActivity.class);
                                        startActivity(i);
                                        try {
                                            TimeUnit.SECONDS.sleep(3);
                                            viewModel.insert(p);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        viewModel.insert(p);

                                    }

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    e.printStackTrace();
                                }
                            });

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                       e.printStackTrace();
                        }
                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    e.printStackTrace();
                }
            });
        }else{
            String downLoadUrl = "";
            HashMap<String, Object> post = new HashMap<>();
            post.put("phone",phoneNumber);
            post.put("description",description);
            post.put("attribute",attribute);
            post.put("sq",sq);
            post.put("rentOrSale",rentOrSale);
            post.put("bedCount",bedCount);
            post.put("bathCount",bathCount);
            post.put("price",price);
            post.put("imageUrl",downLoadUrl);
            post.put("location",location);
            post.put("latitude",latitude);
            post.put("longitude",longitude);

            Post p = new Post(phoneNumber,description,attribute,sq,bedCount,rentOrSale,bathCount,downLoadUrl,price,location,latitude,longitude);
            db.collection("Post").add(post).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                @Override
                public void onComplete(@NonNull Task<DocumentReference> task) {
                    if(task.isSuccessful()){
                        Toast.makeText  (AddActivity.this,"Success",Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(AddActivity.this, HomeActivity.class);
                        startActivity(i);
                        try {
                            TimeUnit.SECONDS.sleep(3);
                            viewModel.insert(p);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        viewModel.insert(p);

                    }

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    e.printStackTrace();
                }
            });
        }

    }


    public void selectImage(View view) {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},1);
        } else {
            Intent intentToGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intentToGallery,2);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {


        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intentToGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intentToGallery,2);
            }
        }


        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == 2 && resultCode == RESULT_OK && data != null ) {

            imageData = data.getData();

            try {

                if (Build.VERSION.SDK_INT >= 28) {
                    ImageDecoder.Source source = ImageDecoder.createSource(this.getContentResolver(),imageData);
                    selectedImage = ImageDecoder.decodeBitmap(source);
                    imageView.setImageBitmap(selectedImage);
                } else {
                    selectedImage = MediaStore.Images.Media.getBitmap(this.getContentResolver(),imageData);
                    imageView.setImageBitmap(selectedImage);
                }


            } catch (IOException e) {
                e.printStackTrace();
            }


        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}