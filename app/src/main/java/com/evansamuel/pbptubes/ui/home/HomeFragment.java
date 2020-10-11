package com.evansamuel.pbptubes.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.evansamuel.pbptubes.HomeActivity;
import com.evansamuel.pbptubes.ProfileActivity;
import com.evansamuel.pbptubes.R;

public class HomeFragment extends Fragment  {

    public HomeFragment(){

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        return inflater.inflate(R.layout.activity_edit_profile,container, false);
    }
}