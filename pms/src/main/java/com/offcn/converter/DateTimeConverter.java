package com.offcn.converter;

import org.springframework.core.convert.converter.Converter;

import java.text.SimpleDateFormat;
import java.util.Date;


public class DateTimeConverter implements Converter<String, Date> {

    public Date convert(String source) {
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(source);
            return date;
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
}
