package uk.ac.tees.w9567358.aad.roomrental.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import uk.ac.tees.w9567358.aad.roomrental.Data.DataMhsw;
import uk.ac.tees.w9567358.aad.roomrental.R;
import uk.ac.tees.w9567358.aad.roomrental.Data.DataMhsw;

public class RecyAdap extends RecyclerView.Adapter<RecyAdap.ViewAdapter> {

    private List<DataMhsw> list;
    private Context ctx;


    public RecyAdap(Context context, List<DataMhsw> list){
        ctx = context;
        this.list = list;
    }
    @NonNull
    @Override
    public ViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent,false);
        return new ViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewAdapter holder, int position) {
        holder.npmlist.setText(Integer.toString(list.get(position).npm));
        holder.namalist.setText(list.get(position).nama);
        holder.kelaslist.setText(list.get(position).kelas);
        holder.prodilist.setText(list.get(position).prodi);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewAdapter extends RecyclerView.ViewHolder{
        TextView npmlist, namalist, kelaslist, prodilist;
        public ViewAdapter(@NonNull View itemView) {
            super(itemView);
            npmlist = itemView.findViewById(R.id.npmlist);
            namalist = itemView.findViewById(R.id.namalist);
            kelaslist = itemView.findViewById(R.id.kelaslist);
            prodilist = itemView.findViewById(R.id.prodilist);

        }
    }
}