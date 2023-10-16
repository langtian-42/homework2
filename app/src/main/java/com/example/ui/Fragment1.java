package com.example.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class Fragment1 extends Fragment {
    RecyclerView recyclerView;
    List<String>list;
    MyAdapter adapter;
    Context context ;
    View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.tab1, container, false);
        recyclerView=(RecyclerView) view.findViewById(R.id.recyclerview_weixin);

        list=new ArrayList();
        for(int i=1;i<10;i++) {
            list.add("这是用户"+i+"");
        }
        context=getActivity();
        adapter=new MyAdapter (context,list);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager manager=new LinearLayoutManager(context);
        manager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(manager);
        return view;
    }
}