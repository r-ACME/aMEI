package com.example.amei.Interfaces.FAQ;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.amei.Modelos.FAQ;
import com.example.amei.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FAQListItem#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FAQListItem extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_f_a_q_list_item, container, false);
    }

    public void updateFAQ(FAQ faq){

        TextView txtQuestion = getView().findViewById(R.id.lbl_faq_list_question);
        txtQuestion.setText(faq.getQuestion());

        TextView txtAnswer = getView().findViewById(R.id.lbl_faq_list_answer);
        txtAnswer.setText(faq.getAnswer());

    }


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FAQListItem() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FAQListItem.
     */
    // TODO: Rename and change types and number of parameters
    public static FAQListItem newInstance(String param1, String param2) {
        FAQListItem fragment = new FAQListItem();
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

}