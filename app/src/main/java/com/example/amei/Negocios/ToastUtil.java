package com.example.amei.Negocios;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.amei.R;

public class ToastUtil {

    public static void showTopToast(Context context, String message) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View layout = inflater.inflate(R.layout.toast_layout, null);

        TextView toastText = layout.findViewById(R.id.toastText);
        toastText.setText(message);

        Toast toast = new Toast(context);
        toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }
}
