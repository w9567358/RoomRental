package uk.ac.tees.w9567358.aad.roomrental.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

import uk.ac.tees.w9567358.aad.roomrental.R;
import uk.ac.tees.w9567358.aad.roomrental.adapters.HomeFragmentAdapter;
import uk.ac.tees.w9567358.aad.roomrental.models.Post;
import uk.ac.tees.w9567358.aad.roomrental.viewmodel.HomeFragmentViewModel;

public class HomeFragment extends Fragment {

    private FirebaseFirestore db;
    private HomeFragmentAdapter adapter;
    private ArrayList<Post> posts;
    private RecyclerView rv;
    private HomeFragmentViewModel homeFragmentViewModel;
    private TextView result;
    private SearchView searchView;
    private ArrayList<Post> searchPost;

    public HomeFragment(){}

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view= inflater.inflate(R.layout.fragment_home, container, false);

        result = view.findViewById(R.id.result_count);
        db = FirebaseFirestore.getInstance();
        searchView = view.findViewById(R.id.searchView);
        homeFragmentViewModel= new ViewModelProvider((ViewModelStoreOwner) requireContext()).get(HomeFragmentViewModel.class);

        posts = new ArrayList<>();
        searchPost = new ArrayList<>();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText != null){
                    search(newText);
                }
                return true;
            }
        });

        getDataFromFirebase();
        rv = view.findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(requireContext()));
        adapter = new HomeFragmentAdapter(posts,requireContext());

        rv.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        return  view;
    }

    public void search(String searchText){
        searchText = "%"+searchText+"%";
        CollectionReference collectionReference = db.collection("Post");
        collectionReference.whereEqualTo("post",searchText).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
      @Override
      public void onComplete(@NonNull Task<QuerySnapshot> task) {
          if (task.isSuccessful()) {
              for (QueryDocumentSnapshot document : task.getResult()) {
                  Log.d("Home", document.getId() + " => " + document.getData());
              }
          } else {
              Log.d("Home", "Failed: ", task.getException());
          }
      }
  });


    }

    public void getDataFromFirebase(){
        CollectionReference collectionReference = db.collection("Post");

        collectionReference.orderBy("price", Query.Direction.DESCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                    if(value != null){
                        for (DocumentSnapshot snapshot : value.getDocuments()){
                            Map<String,Object> data = snapshot.getData();

                            String price = data.get("price").toString();
                            String imageUrl = data.get("imageUrl").toString();
                            String attribute = data.get("attribute").toString();
                            String phone = data.get("phone").toString();
                            String bathCount = data.get("bathCount").toString();
                            String bedCount = data.get("bedCount").toString();
                            String description = data.get("description").toString();
                            String rentOrSale = data.get("rentOrSale").toString();
                            String sq = data.get("sq").toString();
                            String location = data.get("location").toString();
                            String latitude = data.get("latitude").toString();
                            String longitude = data.get("longitude").toString();
                            Post p = new Post(phone,description,attribute,sq,bedCount,rentOrSale,bathCount,imageUrl,price,location,latitude,longitude);

                            posts.add(p);
                            adapter.notifyDataSetChanged();

                            result.setText(posts.size()+" post found.");
                            Log.d("size", String.valueOf(posts.size()));

                        }
                    }
            }
        });
    }
}