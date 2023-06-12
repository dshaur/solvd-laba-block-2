package com.solvd.block2.xml;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DateAdapter extends XmlAdapter<String, Date> {
    private static final String DATE_FORMAT = "yyyy-MM-dd";

    @Override
    public String marshal(Date date) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        return dateFormat.format(date);
    }

    @Override
    public Date unmarshal(String dateString) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        return dateFormat.parse(dateString);
    }
}

