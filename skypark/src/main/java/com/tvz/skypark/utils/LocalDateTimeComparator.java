package com.tvz.skypark.utils;

import java.time.LocalDateTime;
import java.util.Comparator;

public class LocalDateTimeComparator implements Comparator < LocalDateTime >
{
    @Override
    public int compare ( LocalDateTime o1 , LocalDateTime o2 )
    {
        int result = o1.toLocalDate().compareTo( o2.toLocalDate() ); 
        result = ( ( - 1 ) * result ); 
        if ( 0 == result ) 
        {
            result = o2.toLocalTime().compareTo( o1.toLocalTime() );
        }
        return result;
    }
}