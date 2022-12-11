package uk.ac.tees.w9567358.aad.roomrental.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

import uk.ac.tees.w9567358.aad.roomrental.R;
import uk.ac.tees.w9567358.aad.roomrental.adapters.FavoriteFragmentAdapter;
import uk.ac.tees.w9567358.aad.roomrental.models.Post;

public class FavoriteFragment extends Fragment {

    private RecyclerView rv;
    private FavoriteFragmentAdapter favoriteFragmentAdapter;
    private ArrayList<Post> favList;
    private FirebaseFirestore db;
    private CollectionReference reference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_favorite, container, false);

       db= FirebaseFirestore.getInstance();
       favList = new ArrayList<>();

       reference = db.collection("Favorite");
        getDataFavoriteDatabase();

        rv = view.findViewById(R.id.recyclerViewFav);

       rv.setLayoutManager(new LinearLayoutManager(requireContext()));
       favoriteFragmentAdapter = new FavoriteFragmentAdapter(favList,favList);
        rv.setAdapter(favoriteFragmentAdapter);

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                                  RecyclerView.ViewHolder target) {
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {

                int layout = viewHolder.getLayoutPosition();
                Post p = favoriteFragmentAdapter.favoriteList.get(layout);

                db.collection("Favorite").document(reference.getId()).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                    }
                });
                favoriteFragmentAdapter.notifyDataSetChanged();
                Toast.makeText(requireContext(),"Successfully",Toast.LENGTH_SHORT).show();
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(rv);

        return view;
    }

    private void getDataFavoriteDatabase() {
        CollectionReference collectionReference = db.collection("Favorite");
        collectionReference.orderBy("attribute",Query.Direction.DESCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(value != null){
                    for (DocumentSnapshot snapshot : value.getDocuments()){
                        Map<String,Object> data = snapshot.getData();

                        String price = data.get("price").toString();
                        String imageUrl = data.get("imageURl").toString();
                        String attribute = data.get("attribute").toString();
                        String phone = data.get("phoneNumber").toString();
                        String bathCount = data.get("bathCount").toString();
                        String bedCount = data.get("bedCount").toString();
                        String description = data.get("description").toString();
                        String rentOrSale = data.get("rentOrSale").toString();
                        String sq = data.get("sq").toString();
                        String location = data.get("location").toString();
                        String latitude = data.get("latitude").toString();
                        String longitude = data.get("longitude").toString();
                        Post p = new Post(phone,description,attribute,sq,bedCount,rentOrSale,bathCount,imageUrl,price,location,latitude,longitude);
                        favList.add(p);
                        favoriteFragmentAdapter.notifyDataSetChanged();

                    }
                }
            }
        });
    }
}