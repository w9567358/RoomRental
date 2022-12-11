package uk.ac.tees.w9567358.aad.roomrental.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import uk.ac.tees.w9567358.aad.roomrental.R;
import uk.ac.tees.w9567358.aad.roomrental.models.Post;
import uk.ac.tees.w9567358.aad.roomrental.ui.HomeFragmentDirections;

public class HomeFragmentAdapter extends RecyclerView.Adapter<HomeFragmentAdapter.MyViewHolder> {

     List<Post> postList;
    private Context contextA;
    public HomeFragmentAdapter(List<Post> postList,Context context){
        this.contextA = context;
        this.postList = postList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item,parent,false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Post postModel = postList.get(position);
        if(postModel.getImageUrl() !=null && !postModel.getImageUrl().equals("")){
            Picasso.get().load(postModel.getImageUrl()).into(holder.homeImage);
        }

        holder.price.setText(postModel.getPrice()+" TL");
        holder.location.setText(postModel.getLocation());
        holder.homeAttribute.setText(postModel.getAttribute());
        holder.bath.setText(postModel.getBathCount()+"Baths");
        holder.bed.setText(postModel.getBedCount()+"Bed");
        holder.sq.setText(postModel.getSq()+"Sqft");


        final NavController navController = Navigation.findNavController((Activity) contextA,R.id.containerFragment);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HomeFragmentDirections.ActionHomeFragmentToDescriptionFragment action = HomeFragmentDirections.actionHomeFragmentToDescriptionFragment(postModel);
                navController.navigate(action);
            }
        });
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView homeAttribute,location,price,bath,bed,sq;
        public ImageView homeImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            homeAttribute = itemView.findViewById(R.id.card_home_description);
            location = itemView.findViewById(R.id.card_location);
            price = itemView.findViewById(R.id.card_price);
            bath = itemView.findViewById(R.id.card_bath_textView);
            bed = itemView.findViewById(R.id.card_bed_textView);
            sq = itemView.findViewById(R.id.card_sq_textView);

            homeImage = itemView.findViewById(R.id.card_imageView);

        }
    }


}
