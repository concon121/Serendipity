package uk.co.cbsoftware.serendipity.util.datetime;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public final class XmlGregorianCalendarHelper {

    public static final float MINUTES_PER_DAY = 60 * 24;
    public static final String XML_GREG_CAL_FORMAT = "yyyy-MM-dd";
    public static final String UTC_DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    public static final String LOCAL_DATE_TIME_FORMAT = "yyyy-MM-dd hh:mm a";
    
    private XmlGregorianCalendarHelper() {
    	
    }
    
    public static XMLGregorianCalendar convertStringDateToXmlGregorianCalendar(String dateStr, String dateFormat) {
        try {
            // this may throw DatatypeConfigurationException
            GregorianCalendar cal = new GregorianCalendar();

            Calendar parsedCalendar = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
            Date rawDate = sdf.parse(dateStr);
            parsedCalendar.setTime(rawDate);

            cal.set(parsedCalendar.get(Calendar.YEAR), parsedCalendar.get(Calendar.MONTH), parsedCalendar.get(Calendar.DATE));
            XMLGregorianCalendar xmlCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendarDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH), DatatypeConstants.FIELD_UNDEFINED);

            return xmlCalendar;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String convertXMLGregorianCalendarToString(XMLGregorianCalendar xmlGregorianCalendar, String dateFormat) {
        return DateHelper.convertDateToString(convertXMLGregorianCalendarToUtilDate(xmlGregorianCalendar), dateFormat);
    }

    public static Date convertXMLGregorianCalendarToUtilDate(XMLGregorianCalendar xmlGregorianCalendar) {

        Date carDate = new Date();
        if (xmlGregorianCalendar != null) {
            carDate = new Date(xmlGregorianCalendar.toGregorianCalendar().getTimeInMillis());
        }
        return carDate;
    }
    
    public static XMLGregorianCalendar convertDatetoUTCTimeZone(String dateStr, String dateFormat) {
        
    	try {
    	    dateStr = dateStr.trim();
    	    dateFormat = dateFormat.trim();
    	    DateTimeFormatter fmt = DateTimeFormat.forPattern(dateFormat);
    	    DateTime date = fmt.parseDateTime(dateStr); 
            
            XMLGregorianCalendar xml = convertStringDateToXmlGregorianCalendar(dateStr, dateFormat);
            xml.setHour(date.getHourOfDay());
            xml.setMinute(date.getMinuteOfHour());
            xml.setSecond(date.getSecondOfMinute());
            xml.setTimezone(0);
                      
            return xml;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }    

}
