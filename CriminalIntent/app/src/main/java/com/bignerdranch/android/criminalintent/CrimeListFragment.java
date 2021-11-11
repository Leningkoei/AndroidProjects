package com.bignerdranch.android.criminalintent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CrimeListFragment extends Fragment {

    public static CrimeListFragment newInstance() {
        return new CrimeListFragment();
    }

    private CrimeListViewModel crimeListViewModel = null;
    private RecyclerView crimeRecyclerView = null;
    private CrimeAdapter crimeAdapter = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.crimeListViewModel
            = new ViewModelProvider(this).get(CrimeListViewModel.class);
    }
    @Override
    public View onCreateView(
        LayoutInflater inflater,
        ViewGroup container,
        Bundle savedInstanceState
    ) {
        View view = inflater.inflate(
            R.layout.fragment_crime_list,
            container,
            false
        );

        this.crimeRecyclerView = view.findViewById(R.id.crime_recycler_view);
        // LinearLayoutManager linerLayoutManager
        //     = new LinearLayoutManager(this.getContext());
        // this.crimeRecyclerView.setLayoutManager(linerLayoutManager);
        this.crimeRecyclerView.setLayoutManager(
            new LinearLayoutManager(this.getContext())
        );
        this.updateUI();

        return view;
    }

    private void updateUI() {
        List<Crime> crimes = this.crimeListViewModel.crimes;
        this.crimeAdapter = new CrimeAdapter(crimes);
        this.crimeRecyclerView.setAdapter(crimeAdapter);
    }

    private class CrimeHolder extends RecyclerView.ViewHolder {

        public TextView textView = null;
        public TextView dateTextView = null;

        // Constructor matching super;
        public CrimeHolder(@NonNull View itemView) {
            super(itemView);

            this.textView = itemView.findViewById(R.id.crime_title);
            this.dateTextView = itemView.findViewById(R.id.crime_date);
        }
    }
    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder> {

        private List<Crime> crimes = null;

        public CrimeAdapter(List<Crime> crimes) {
            this.crimes = crimes;
        }

        // 至少要覆盖3个函数;
        @Override
        public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(
                R.layout.list_item_crime,
                parent,
                false
            );
            return new CrimeHolder(view);
        }
        @Override
        public void onBindViewHolder(CrimeHolder holder, int position) {
            Crime crime = this.crimes.get(position);
            holder.textView.setText(crime.title);
            holder.dateTextView.setText(crime.date.toString());
        }
        @Override
        public int getItemCount() {
            return this.crimes.size();
        }
    }
}
