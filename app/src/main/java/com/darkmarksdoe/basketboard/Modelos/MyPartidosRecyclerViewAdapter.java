package com.darkmarksdoe.basketboard.Modelos;

import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.darkmarksdoe.basketboard.Vistas.Fragmentos.PartidosFragment.OnListFragmentInteractionListener;
import com.darkmarksdoe.basketboard.R;
import com.darkmarksdoe.basketboard.dummy.DummyContent;
import com.darkmarksdoe.basketboard.dummy.DummyContent.DummyItem;

import java.util.List;
import java.util.Random;


public class MyPartidosRecyclerViewAdapter extends RecyclerView.Adapter<MyPartidosRecyclerViewAdapter.ViewHolder> {

    private final List<DummyItem> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyPartidosRecyclerViewAdapter(List<DummyItem> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_partidos, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).id);
        holder.mIdView2.setText(DummyContent.generarRandom());
        holder.mContentView.setText("LNBP 2019-2020    02/11/2019     FINAL");
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final View mView;
        private final TextView mIdView;
        private final TextView mIdView2;
        private final TextView mContentView;
        private final TextView equipo1;
        private final TextView equipo2;
        public DummyItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = view.findViewById(R.id.item_number);
            mIdView2 = view.findViewById(R.id.item_number2);
            mContentView = view.findViewById(R.id.content);
            equipo1 = view.findViewById(R.id.partidos_equipo1);
            equipo2 = view.findViewById(R.id.partidos_equipo2);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
