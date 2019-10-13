package com.vegamex.crudbasico;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Conversores {

    public long CalendarALong(int year, int month, int date) {
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.DAY_OF_MONTH, date);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.YEAR, year);
        return calendar.getTimeInMillis();
    }

    public Calendar LongACalendar (long date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date);
        return calendar;
    }

    public String CalendarAString(Calendar calendar){
        int dia = calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH);
        int mes = calendar.get(Calendar.MONTH);
        int año = calendar.get(Calendar.YEAR);
        return dia + "/" + mes + "/" + año;
    }
}
