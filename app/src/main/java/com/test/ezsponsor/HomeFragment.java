package com.test.ezsponsor;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class HomeFragment extends Fragment {

    ListView listView;
    ArrayList<String> al;
    ArrayAdapter<String> aa;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        listView = (ListView) view.findViewById(R.id.home_listView);
        al = new ArrayList<String>();
        aa = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_activated_1, al);
        listView.setAdapter(aa);
        al.add("Test 1");
        al.add("Test 2");
        al.add("Test 3");


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // TODO: Add activity reference.
//                Intent intent = new Intent(getActivity(), <activityName>.class);
//                startActivity(intent);
                Toast.makeText(getActivity().getApplicationContext(), "Test", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
