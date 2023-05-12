package com.example.task1;


import android.os.Bundle;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;

import java.util.Collection;
import java.util.Collections;

public class ListFragment extends Fragment {

    private RecyclerView scoreListView;
    private ScoreList scoreList = new ScoreList();
    private MapFragment mapFragment;
    private MapCallback mapCallback;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.highscore_list_fragment, container, false);
        findViews(view);
        String fromSP =  Sp.getInstance().getString("com.example.task1","");
        scoreList = new Gson().fromJson(fromSP,ScoreList.class );
        if(scoreList == null)
        {
            scoreList = new ScoreList();
        }
        Collections.sort(scoreList.getScore());
        Log.d("Tag","mapcallback" + mapCallback);
        scoreAdapter scoreAdapter = new scoreAdapter(view.getContext(), scoreList,mapCallback);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        scoreListView.setLayoutManager(linearLayoutManager);
        scoreListView.setAdapter(scoreAdapter);
        Log.d("TAG", "onCreateView: " + scoreList);


        return view;

    }
    private void findViews (View view)
    {
        scoreListView = view.findViewById(R.id.mRecyclerView);
    }
    public void setMap_callback(MapCallback map_callback) {
        this.mapCallback = map_callback;
    }
}