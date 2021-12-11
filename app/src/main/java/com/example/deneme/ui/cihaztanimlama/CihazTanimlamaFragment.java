package com.example.deneme.ui.cihaztanimlama;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.deneme.R;

public class CihazTanimlamaFragment extends Fragment {

    private CihazTanimlamaViewModel mViewModel;

    public static CihazTanimlamaFragment newInstance() {
        return new CihazTanimlamaFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.cihaz_tanimlama_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CihazTanimlamaViewModel.class);
        // TODO: Use the ViewModel
    }

}