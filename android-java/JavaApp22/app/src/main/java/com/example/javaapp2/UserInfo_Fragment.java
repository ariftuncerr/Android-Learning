
package com.example.javaapp2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.javaapp2.databinding.FragmentUserInfoBinding;

public class UserInfo_Fragment extends Fragment {
    private FragmentUserInfoBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        UserInfo_FragmentArgs bundle = UserInfo_FragmentArgs.fromBundle(getArguments());
        binding.textViewUserName.setText(bundle.getUserName());
        binding = FragmentUserInfoBinding.inflate(inflater,container,false);


        return binding.getRoot();
    }
}