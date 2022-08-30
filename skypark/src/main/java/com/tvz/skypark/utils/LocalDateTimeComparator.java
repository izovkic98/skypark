package com.tvz.skypark.utils;

import java.time.LocalDateTime;
import java.util.Comparator;

public class LocalDateTimeComparator implements Comparator < LocalDateTime >
{
    @Override
    public int compare ( LocalDateTime o1 , LocalDateTime o2 )
    {
        // Compare the date portion first. If equal, then look at time-of-day.
        int result = o2.toLocalDate().compareTo( o1.toLocalDate() ); // Consider only the date portion first.
        result = ( ( - 1 ) * result ); // Flip the positive/negative sign of the int, to get ascending order. Or more simply: `= - result ;`.
        if ( 0 == result ) // If dates are equal, look at the time-of-day.
        {
            result = o2.toLocalTime().compareTo( o1.toLocalTime() );
        }
        return result;
    }
}