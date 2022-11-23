package uk.ac.tees.w9567358.aad.roomrental.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import uk.ac.tees.w9567358.aad.roomrental.HouseModel;
import uk.ac.tees.w9567358.aad.roomrental.R;
import uk.ac.tees.w9567358.aad.roomrental.user.HouseDetails;

import java.util.ArrayList;

public class SeeHouseAdapterUser extends RecyclerView.Adapter<SeeHouseAdapterUser.viewholder>  implements Filterable {
    Context context;
    ArrayList<HouseModel> houseModelArrayList = new ArrayList<>();
    ArrayList<HouseModel> backup;

    public SeeHouseAdapterUser(Context context, ArrayList<HouseModel> houseModelArrayList){
        this.context = context;
        this.houseModelArrayList = houseModelArrayList;
        backup=new ArrayList<>(houseModelArrayList);
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.seehouse, null, false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        HouseModel model = houseModelArrayList.get(position);

        holder.tv_location.setText(model.getHouseLocation());
        holder.tv_rentPerRoom.setText(model.getRentPerRoom());
        holder.tv_noOfRoom.setText(model.getNoOfRoom());
        Glide.with(context).load(model.getHouseImage()).into(holder.iv_houseImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, HouseDetails.class);

                intent.putExtra("houseId", model.getHouseId());
                intent.putExtra("noOfRoom", model.getNoOfRoom());
                intent.putExtra("rentPerRoom", model.getRentPerRoom());
                intent.putExtra("houseDescription", model.getHouseDescription());
                intent.putExtra("houseLocation", model.getHouseLocation());
                intent.putExtra("houseImage", model.getHouseImage());
                intent.putExtra("userId", model.getUserId());
                intent.putExtra("ownerContact",model.getownerContact());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return houseModelArrayList.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }
    Filter filter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence keyword) {

            ArrayList<HouseModel> filteredData=new ArrayList<>();
            if(keyword.toString().isEmpty())
                filteredData.addAll(backup);
            else
            {
                for(HouseModel obj:backup)
                {
                    if(obj.getHouseLocation().toString().toLowerCase().contains(keyword.toString().toLowerCase()))
                        filteredData.add(obj);
                }
            }
            FilterResults results=new FilterResults();
            results.values=filteredData;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            houseModelArrayList.clear();
            houseModelArrayList.addAll((ArrayList<HouseModel>)results.values);
            notifyDataSetChanged();
        }
    };

    class viewholder extends RecyclerView.ViewHolder {
        ImageView iv_houseImage;
        TextView tv_noOfRoom, tv_rentPerRoom, tv_location;

        public viewholder(@NonNull View itemView) {
            super(itemView);

            iv_houseImage = itemView.findViewById(R.id.imageview);
            tv_noOfRoom = itemView.findViewById(R.id.tv_noOfRooms);
            tv_rentPerRoom = itemView.findViewById(R.id.tv_rentPerRoom);
            tv_location = itemView.findViewById(R.id.tv_location);

        }
    }
}
