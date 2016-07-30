package uk.co.cbsoftware.serendipity.util.datetime;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHelper {
    
    public static String convertDateToString(Date inputDate, String dateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.format(inputDate);
    }

}
