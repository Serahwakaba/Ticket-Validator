package com.example.ticketvalidator;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Context;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class TicketEventAdapter extends RecyclerView.Adapter<TicketEventAdapter.ViewHolder>{

    private Context context;
    private List<TicketEvent> list;
    LayoutInflater inflater;

    public TicketEventAdapter(Context context, List<TicketEvent> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }


//    @NonNull
    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        ViewHolder holder = new ViewHolder(inflater.inflate(R.layout.list_custom_item, parent));
        return  holder;
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {
        TicketEvent ticketEvent = list.get(position);

        holder.name.setText(ticketEvent.getName());
        holder.date.setText(ticketEvent.getDate());
        holder.number.setText(ticketEvent.getTotal());
        holder.ticket.setText(ticketEvent.getTickets());
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
