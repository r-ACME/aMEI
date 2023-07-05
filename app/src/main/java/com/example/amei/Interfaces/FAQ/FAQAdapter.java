package com.example.amei.Interfaces.FAQ;

import android.content.Context;
import android.content.res.Configuration;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.amei.Modelos.FAQ;
import com.example.amei.Modelos.Schedule;

import java.util.List;

public class FAQAdapter extends BaseAdapter {

    private List<FAQ> faqs;
    private Context context;

    public FAQAdapter(Context context, List<FAQ> faqs) {
        this.faqs = faqs;
        this.context = context;
    }

    @Override
    public int getCount() {
        return this.faqs.size();
    }

    @Override
    public Object getItem(int position) {
        return this.faqs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FAQ faq = this.faqs.get(position);
        TextView txtQuestion;

        if (convertView == null) {
            txtQuestion = new TextView(parent.getContext());
        } else {
            txtQuestion = (TextView) convertView;
        }

        txtQuestion.setText(faq.getQuestion());

        Configuration configuration = context.getResources().getConfiguration();
        int orientation = configuration.orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            txtQuestion.setTextSize(20);
        }
        else {
            txtQuestion.setTextSize(15);
        }
        return txtQuestion;
    }

}
