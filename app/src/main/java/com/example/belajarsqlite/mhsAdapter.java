package com.example.belajarsqlite;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class mhsAdapter extends RecyclerView.Adapter<mhsAdapter.mhsHV> {
    private ArrayList<mhsModel> mhslist;
    private final onItemClickListener listener;

    public mhsAdapter(ArrayList<mhsModel> mhslist, onItemClickListener listener) {
        this.mhslist = mhslist;
        this.listener = listener;
    }

    @NonNull
    @Override
    public mhsHV onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.item_listmhs, parent,false);
        return new mhsHV(v);

    }

    @Override
    public void onBindViewHolder(@NonNull mhsHV holder, int position) {
        holder.tvNamaVal.setText(mhslist.get(position).getNama());
        holder.tvNIMVal.setText(mhslist.get(position).getNim());
        holder.tvNoHpVal.setText(mhslist.get(position).getNohp());

        holder.bind(mhslist, position, listener);

    }
    public interface onItemClickListener {
        void onItemClick(ArrayList<mhsModel> mhslist , int position);
    }
    public void removeItem(int position){
        this.mhslist.remove(position);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mhslist.size();
    }

    public class mhsHV extends RecyclerView.ViewHolder {
        private TextView tvNamaVal, tvNIMVal, tvNoHpVal;
        private CardView CvItem;

        public mhsHV(@NonNull View itemView) {
            super(itemView);
            tvNamaVal = itemView.findViewById(R.id.tvNamaVal);
            tvNIMVal = itemView.findViewById(R.id.tvNIMVal);
            tvNoHpVal = itemView.findViewById(R.id.tvNoHpval);

            CvItem = itemView.findViewById(R.id.CvItem);

        }

        public void bind( ArrayList<mhsModel> mhslist, int position, onItemClickListener listener) {
            CvItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(mhslist, position);
                    notifyDataSetChanged();
                }
            });
        }

    }
    }

