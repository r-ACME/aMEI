package com.example.amei.Interfaces.FAQ;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.amei.Modelos.FAQ;
import com.example.amei.Negocios.API.LocalDataManager;
import com.example.amei.Negocios.Banco.FAQDAO;
import com.example.amei.Negocios.Banco.SchedulesDAO;
import com.example.amei.Modelos.Schedule;
import com.example.amei.R;
import com.example.amei.Negocios.ToastUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FAQList  extends ListFragment {

    List<FAQ> allFAQs = new ArrayList<>();
    private static final int REQUEST_CREATE_SCHEDULE = 1;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_schedule_list, container, false);

        FAQDAO faqdao = new FAQDAO(super.getContext());

        FAQAdapter adapter;

        allFAQs = faqdao.getAll();

        adapter = new FAQAdapter(this.getActivity(), allFAQs);

        setListAdapter(adapter);

        adapter.notifyDataSetChanged();

        return view;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        if(this.allFAQs.size() == 0){
            ToastUtil.showTopToast(getActivity(), "Não há perguntas frequentes!");
        }
        else {
            FAQ faq = this.allFAQs.get(position);
            Configuration configuration = getResources().getConfiguration();
            int orientation = configuration.orientation;

            if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                Bundle sendData = new Bundle();
                sendData.putSerializable("faqToShow", faq);
                Intent intent = new Intent( getActivity(), FAQDetailView.class);
                intent.putExtras(sendData);
                startActivity(intent);
            } else{
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                FAQListItem faqListItemFragment = (FAQListItem) fragmentManager.findFragmentById(R.id.frag_faq_details);
                faqListItemFragment.updateFAQ(faq);

                fragmentTransaction.commit();

            }



        }
    }
}