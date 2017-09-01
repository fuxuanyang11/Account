package com.example.account.util;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.example.account.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.Calendar;

public class DialogUtil {

    public static Dialog showDateDialog(Context context, View.OnClickListener listener, OnDateSelectedListener onDateSelectedListener) {

        Dialog dialog = new Dialog(context, R.style.ActionSheetDialogStyle);
        View inflate = LayoutInflater.from(context).inflate(R.layout.dialog_layout_calendar, null);

        MaterialCalendarView widget = (MaterialCalendarView) inflate.findViewById(R.id.dialog_calendar);
        Button cancel = (Button) inflate.findViewById(R.id.btn_cancel);
        Button confirm = (Button) inflate.findViewById(R.id.btn_confirm);

        cancel.setOnClickListener(listener);
        confirm.setOnClickListener(listener);
        widget.setOnDateChangedListener(onDateSelectedListener);

//        Calendar cal = Calendar.getInstance();
//        int y = cal.get(Calendar.YEAR);
//        int m = cal.get(Calendar.MONTH);
//        int d = cal.get(Calendar.DATE);
//        widget.setSelectedDate(cal);//设置默认选中日期
//        widget.state().edit()
//                .setMaximumDate(CalendarDay.from(y, m, d))
//                .commit();

        dialog.setContentView(inflate);
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.CENTER);
        dialog.show();
        return dialog;
    }
}
