package com.example.ticketvalidator;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Context;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyListAdapter  extends RecyclerView.Adapter<MyListAdapter.ViewHolder>{

    private Context context;
    private List<MyListData> list;

    public MyListAdapter(Context context, List<MyListData> list) {
        this.context = context;
        this.list = list;
    }


  @NonNull
    @Override
    public MyListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View v = LayoutInflater.from(context).inflate(R.layout.list_custom_item, parent, false);
            return new RecyclerView.ViewHolder(v);
        }



    @Override
    public void onBindViewHolder(@NonNull MyListAdapter.ViewHolder holder, int position) {
        MyListData myListData = list.get(position);

        holder.name.setText(myListData.getName());
        holder.date.setText(myListData.getDate());
        holder.number.setText(myListData.getTotal());
        holder.ticket.setText(myListData.getTickets());
//        holder.image.setImageIcon(myListData.getId());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView date;
        public TextView number;
        public TextView ticket;
        public ImageView image;


        public ViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            date = itemView.findViewById(R.id.date);
            number = itemView.findViewById(R.id.number);
            ticket = itemView.findViewById(R.id.ticket);
            image = itemView.findViewById(R.id.image);

        }
    }
}
