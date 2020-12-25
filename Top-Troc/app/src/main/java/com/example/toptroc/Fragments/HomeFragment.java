package com.example.toptroc.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.toptroc.Adaptaters.VerticalSliderAdapter;
import com.example.toptroc.LoginActivity;
import com.example.toptroc.MainActivity;
import com.example.toptroc.MapsActivity;
import com.example.toptroc.Models.VerticalSliderModel;
import com.example.toptroc.ObjectDepositActivity;
import com.example.toptroc.R;
import com.example.toptroc.Sessions.SessionManager;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {

    public HomeFragment() {
        // Required empty public constructor
    }

    private DrawerLayout drawerLayout;
    private ImageView navigationBar,imageViewDeposit;
    private NavigationView navigationView;
    private View view;
    private RelativeLayout Ad,Search,Selection,Contact;
    private TextView Historical,FeedBack,FAQ,textViewLogin,textViewLogout;
    SessionManager sessionManager;

    //Vertical Slider
    private RecyclerView recyclerViewVertical;
    private VerticalSliderAdapter verticalSliderAdapter;
    private List<VerticalSliderModel> verticalSliderModelList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);

        sessionManager = new SessionManager(getContext());

        //bouton de dépôt d'objet
        imageViewDeposit = (ImageView) view.findViewById(R.id.imageViewDeposit);
        imageViewDeposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ObjectDepositActivity.class);
                startActivity(intent);
            }
        });
        //

        //

        //Vertical Slider
        recyclerViewVertical = (RecyclerView) view.findViewById(R.id.recyclerViewVertical);
        LinearLayoutManager layoutManagerVerticalSLider = new LinearLayoutManager(getContext());
        layoutManagerVerticalSLider.setOrientation(RecyclerView.VERTICAL);
        recyclerViewVertical.setLayoutManager(layoutManagerVerticalSLider);

        verticalSliderModelList = new ArrayList<>();
        verticalSliderModelList.add(new VerticalSliderModel(R.drawable.montre,R.drawable.messages,"Simon Dimitri","50119915","Montre","Description Objet"));
        verticalSliderModelList.add(new VerticalSliderModel(R.drawable.iphone,R.drawable.messages,"Ahmed J.","57896452","Iphone 11Pro","Légèrement fissuré"));
        verticalSliderModelList.add(new VerticalSliderModel(R.drawable.sac,R.drawable.messages,"Jean P","78963021","Sac à dos","Sac neuf"));
        verticalSliderModelList.add(new VerticalSliderModel(R.drawable.pc,R.drawable.messages,"Anonyme X","47895632","Pc portable","PC Asus CORE i3"));
        verticalSliderModelList.add(new VerticalSliderModel(R.drawable.scooter,R.drawable.messages,"Mike B","99628205","Scooter","Encore Neuf"));
        verticalSliderModelList.add(new VerticalSliderModel(R.drawable.montre,R.drawable.messages,"Simon Dimitri","50119915","Montre","Description Objet"));
        verticalSliderModelList.add(new VerticalSliderModel(R.drawable.iphone,R.drawable.messages,"Ahmed J.","57896452","Iphone 11Pro","Légèrement fissuré"));
        verticalSliderModelList.add(new VerticalSliderModel(R.drawable.sac,R.drawable.messages,"Jean P","78963021","Sac à dos","Sac neuf"));
        verticalSliderModelList.add(new VerticalSliderModel(R.drawable.pc,R.drawable.messages,"Anonyme X","47895632","Pc portable","PC Asus CORE i3"));
        verticalSliderModelList.add(new VerticalSliderModel(R.drawable.scooter,R.drawable.messages,"Mike B","99628205","Scooter","Encore Neuf"));
        verticalSliderModelList.add(new VerticalSliderModel(R.drawable.montre,R.drawable.messages,"Simon Dimitri","50119915","Montre","Description Objet"));
        verticalSliderModelList.add(new VerticalSliderModel(R.drawable.iphone,R.drawable.messages,"Ahmed J.","57896452","Iphone 11Pro","Légèrement fissuré"));
        verticalSliderModelList.add(new VerticalSliderModel(R.drawable.sac,R.drawable.messages,"Jean P","78963021","Sac à dos","Sac neuf"));
        verticalSliderModelList.add(new VerticalSliderModel(R.drawable.pc,R.drawable.messages,"Anonyme X","47895632","Pc portable","PC Asus CORE i3"));
        verticalSliderModelList.add(new VerticalSliderModel(R.drawable.scooter,R.drawable.messages,"Mike B","99628205","Scooter","Encore Neuf"));

        verticalSliderAdapter = new VerticalSliderAdapter(verticalSliderModelList,getContext());
        recyclerViewVertical.setAdapter(verticalSliderAdapter);

        onSetNavigationDrawerEvents();
        return view;
    }
    private void onSetNavigationDrawerEvents() {
        drawerLayout = (DrawerLayout) view.findViewById(R.id.drawerLayout);
        navigationView = (NavigationView) view.findViewById(R.id.navigationView);
        navigationBar = (ImageView) view.findViewById(R.id.navigationBar);

        textViewLogin = (TextView) view.findViewById(R.id.textViewLogin);
        textViewLogout = (TextView) view.findViewById(R.id.textViewLogout);

        Ad = (RelativeLayout) view.findViewById(R.id.relativeLayoutAd);
        Search = (RelativeLayout) view.findViewById(R.id.relativeLayoutSearch);
        Selection = (RelativeLayout) view.findViewById(R.id.relativeLayoutSelection);
        Contact = (RelativeLayout) view.findViewById(R.id.relativeLayoutContact);

        Historical = (TextView) view.findViewById(R.id.historical);
        FeedBack = (TextView) view.findViewById(R.id.feedback);
        FAQ = (TextView) view.findViewById(R.id.FAQ);

        navigationBar.setOnClickListener(this);

        textViewLogin.setOnClickListener(this);
        textViewLogout.setOnClickListener(this);

        Ad.setOnClickListener(this);
        Search.setOnClickListener(this);
        Selection.setOnClickListener(this);
        Contact.setOnClickListener(this);

        Historical.setOnClickListener(this);
        FeedBack.setOnClickListener(this);
        FAQ.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Fragment selectedFragment = null;
        switch (v.getId()){
            case R.id.navigationBar:
                drawerLayout.openDrawer(navigationView, true);
                break;
            case R.id.textViewLogin:
                Login();
                break;
            case R.id.textViewLogout:
                Logout();
                break;
            case R.id.relativeLayoutAd:
                Maps();
                break;
            case R.id.relativeLayoutSearch:
                Toast.makeText(getContext(),"Recherche",Toast.LENGTH_SHORT).show();
                break;
            case R.id.relativeLayoutSelection:
                Toast.makeText(getContext(),"Ma sélecion",Toast.LENGTH_SHORT).show();
                break;
            case R.id.relativeLayoutContact:
                Toast.makeText(getContext(),"Contact",Toast.LENGTH_SHORT).show();
                break;
            case R.id.historical:
                Toast.makeText(getContext(),"Historique de TopTroc",Toast.LENGTH_SHORT).show();
                break;
            case R.id.feedback:
                Toast.makeText(getContext(),"Feedback",Toast.LENGTH_SHORT).show();
                break;
            case R.id.FAQ:
                Toast.makeText(getContext(),"FAQ",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void Maps() {
        Intent intent = new Intent(getContext(), MapsActivity.class);
        startActivity(intent);
        getActivity().finish();

    }

    private void Login() {
        Intent intent = new Intent(getContext(), LoginActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    private void Logout() {

        sessionManager.editor.clear();
        sessionManager.editor.commit();

        Intent intent = new Intent(getContext(), MainActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void onStart() {
        super.onStart();

        if (sessionManager.isLogin()){

            textViewLogin.setVisibility(View.GONE);
            textViewLogout.setVisibility(View.VISIBLE);
        }
    }
}