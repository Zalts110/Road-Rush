package com.example.task1;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



    public class MapFragment extends Fragment {
        private TextView map_LBL_title;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.map_fragment, container, false);
            findViews(view);
            return view;
        }

        private void findViews(View view) {
            map_LBL_title = view.findViewById(R.id.map_LBL_title);
        }

        public void zoomOnUser(String name) {
            map_LBL_title.setText(name);
        }
    }

