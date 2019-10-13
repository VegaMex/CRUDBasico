package com.vegamex.crudbasico;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Conversores {

    public long CalendarALong(Calendar calendar) {
        if (calendar != null){
            return calendar.getTimeInMillis();
        }else{
            return 0;
        }
    }

    public Calendar LongACalendar (long date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date);
        return calendar;
    }

    public String longAString(long date){
        Date dateDate = new Date(date);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy");
        String dateText = simpleDateFormat.format(date);
        return dateText;
    }

    public String CalendarAString(Calendar calendar){
        int dia = calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH);
        int mes = calendar.get(Calendar.MONTH);
        int año = calendar.get(Calendar.YEAR);
        return dia + "/" + mes + "/" + año;
    }
}
