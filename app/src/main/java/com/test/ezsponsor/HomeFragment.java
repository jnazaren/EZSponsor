package com.test.ezsponsor;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Collection;

public class HomeFragment extends Fragment {

    ListView listView;
    ArrayList<String> al;
    ArrayAdapter<String> aa;
    ArrayList<CardView> cardViewList = null;

    private View.OnClickListener openUserDetails = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getActivity(), DonationInfo.class);
            startActivity(intent);
        }
    };
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        CardView card1 = view.findViewById(R.id.card_1);
        card1.setOnClickListener(openUserDetails);

        CardView card2 = view.findViewById(R.id.card_2);
        card2.setOnClickListener(openUserDetails);

        CardView card3 = view.findViewById(R.id.card_3);
        card3.setOnClickListener(openUserDetails);

        CardView card4 = view.findViewById(R.id.card_4);
        card4.setOnClickListener(openUserDetails);

        CardView card5 = view.findViewById(R.id.card_5);
        card5.setOnClickListener(openUserDetails);


        return view;
    }
}
