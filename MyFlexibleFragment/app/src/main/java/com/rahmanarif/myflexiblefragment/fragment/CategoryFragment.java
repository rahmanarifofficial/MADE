package com.rahmanarif.myflexiblefragment.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.rahmanarif.myflexiblefragment.R;

public class CategoryFragment extends Fragment implements View.OnClickListener{


    public CategoryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button btnDetailCategory = view.findViewById(R.id.btn_detail_category);
        btnDetailCategory.setOnClickListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_category, container, false);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_detail_category){
            DetailCategoryFragment detailCategoryFragment = new DetailCategoryFragment();

            Bundle bundle = new Bundle();
            bundle.putString(DetailCategoryFragment.EXTRA_NAME, "Lifestyle");
            String description = "Kategori ini akan berisi produk-produk Lifestyle";

            detailCategoryFragment.setArguments(bundle);
            detailCategoryFragment.setDescription(description);

            FragmentManager fm = getFragmentManager();
            if (fm != null){
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frame_container, detailCategoryFragment, DetailCategoryFragment.class.getSimpleName());
                ft.addToBackStack(null);
                ft.commit();
            }
        }
    }
}
