package com.example.toptroc.Fragments;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.toptroc.MainActivity;
import com.example.toptroc.ObjectDepositActivity;
import com.example.toptroc.Profile.PageAdapter;
import com.example.toptroc.R;
import com.example.toptroc.SQLiteHelper;
import com.example.toptroc.Sessions.SessionManager;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment implements View.OnClickListener {

    public ProfileFragment() {
        // Required empty public constructor
    }

    private View view;
    TabLayout tabLayoutProfile;
    TabItem itemObjects,itemParameters,itemInformations;
    ViewPager viewPagerProfile;
    PageAdapter pageAdapter;
    SessionManager sessionManager;
    public static SQLiteHelper sqLiteHelper;
    TextView tvEmailUtilisateur,tv_logout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_profile, container, false);

        sessionManager = new SessionManager(getContext());

        sqLiteHelper = new SQLiteHelper(getActivity());

        tvEmailUtilisateur = (TextView) view.findViewById(R.id.tvEmailUtilisateur);
        tv_logout = (TextView) view.findViewById(R.id.tv_logout);



        String user_email= sessionManager.getUserEmail();
        tvEmailUtilisateur.setText(user_email);

        tv_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Déconnexion");
                builder.setMessage("Etes vous sûr de vouloir vous déconnecter ?");
                builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Logout();
                    }
                });
                builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        tabLayoutProfile = (TabLayout) view.findViewById(R.id.tabLayoutProfile);
        itemObjects = (TabItem) view.findViewById(R.id.itemObjects);
        itemParameters = (TabItem) view.findViewById(R.id.itemParameters);
        itemInformations = (TabItem) view.findViewById(R.id.itemInformations);
        viewPagerProfile = (ViewPager) view.findViewById(R.id.viewPagerProfile);

        pageAdapter = new PageAdapter(getFragmentManager(),tabLayoutProfile.getTabCount());
        viewPagerProfile.setAdapter(pageAdapter);

        tabLayoutProfile.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPagerProfile.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return view;
    }

    @Override
    public void onClick(View v) {

    }

    public void Logout(){
        sessionManager.editor.clear();
        sessionManager.editor.commit();

        Intent intent = new Intent(getContext(), MainActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

}