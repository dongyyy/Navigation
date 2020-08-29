package com.dongy.navigation;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("MainFragment");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final MainFragmentDirections.ActionMainFragmentToSecondFragment action =
                MainFragmentDirections.actionMainFragmentToSecondFragment("From MainFragment"); //화살표 객체 == R.id.action_mainFragment_to_secondFragment

        view.findViewById(R.id.button).setOnClickListener(view1 -> Navigation.findNavController(view1).navigate(action));

        view.findViewById(R.id.button2).setOnClickListener(view1 -> Navigation.findNavController(view1).navigate(R.id.action_mainFragment_to_thirdFragment));
    }
}