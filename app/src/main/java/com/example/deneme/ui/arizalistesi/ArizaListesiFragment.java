package com.example.deneme.ui.arizalistesi;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.deneme.R;

public class ArizaListesiFragment extends Fragment {

    private ArizaListesiViewModel mViewModel;
    private RecyclerView arizalist;
    public static ArizaListesiFragment newInstance() {
        return new ArizaListesiFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
     View view = inflater.inflate(R.layout.ariza_listesi_fragment, container, false);
      arizalist = view.findViewById(R.id.arizalistrcycler);



        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ArizaListesiViewModel.class);
        // TODO: Use the ViewModel
    }

}