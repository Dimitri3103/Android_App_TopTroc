package com.example.toptroc.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.toptroc.R;
import com.example.toptroc.SQLiteHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class InformationsFragment extends Fragment {

    public InformationsFragment() {
        // Required empty public constructor
    }

    private View view;
    public static SQLiteHelper sqLiteHelper;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_informations, container, false);

        sqLiteHelper = new SQLiteHelper(getActivity());

        return view;
    }
}