package com.example.amei.Interfaces;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.amei.R;

import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WaitForUpdate#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WaitForUpdate extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private ImageView ivUnderConstruction;

    private String mParam1;
    private String mParam2;

    public WaitForUpdate() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WaitForUpdate.
     */
    // TODO: Rename and change types and number of parameters
    public static WaitForUpdate newInstance(String param1, String param2) {
        WaitForUpdate fragment = new WaitForUpdate();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wait_for_update, container, false);

        ivUnderConstruction = view.findViewById(R.id.iv_under_construction); // Obtenha a referência do ImageView dentro do layout inflado

        return view;

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Random random = new Random();
        int numero = random.nextInt(100);

        Drawable underConstruction1 = getResources().getDrawable(R.drawable.under_construction_v1);
        Drawable underConstruction2 = getResources().getDrawable(R.drawable.under_construction_v2);


        if (numero % 2 == 0) {
            ivUnderConstruction.setImageDrawable(underConstruction2);
        } else {
            ivUnderConstruction.setImageDrawable(underConstruction1);
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        Random random = new Random();
        int numero = random.nextInt(100);

        Drawable underConstruction1 = getResources().getDrawable(R.drawable.under_construction_v1);
        Drawable underConstruction2 = getResources().getDrawable(R.drawable.under_construction_v2);


        if (numero % 2 == 0) {
            ivUnderConstruction.setImageDrawable(underConstruction2);
        } else {
            ivUnderConstruction.setImageDrawable(underConstruction1);
        }
    }

}