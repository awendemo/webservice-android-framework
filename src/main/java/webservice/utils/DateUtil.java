package webservice.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
 

public final class DateUtil {
    private static DateUtil instance = new DateUtil();
 
    private Map<String, SimpleDateFormat> formats = null;
 
    public DateUtil() {
        resetFormats();
    }
 
    public static DateUtil getInstance() {
        return instance;
    }
 
    public void resetFormats() {
        formats = new HashMap<String,SimpleDateFormat>();
 
        // alternative formats
        formats.put("yyyy-MM-dd HH:mm:ss", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        formats.put("yyyy-MM-dd", new SimpleDateFormat("yyyy-MM-dd"));
        formats.put("MM/dd/yyyy HH:mm:ss a", new SimpleDateFormat("MM/dd/yyyy HH:mm:ss a"));
        formats.put("yyyy-MM-dd HH:mm:ss a", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss a"));
 
        // ISO formats
        formats.put("yyyy-MM-dd'T'HH:mm:ss'Z'", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'"));
        formats.put("yyyy-MM-dd'T'HH:mm:ssZ", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ"));
        formats.put("yyyy-MM-dd'T'HH:mm:ssz", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz"));
    }
 
    public Date parse(String dateString) {
        Iterator<SimpleDateFormat> iter = formats.values().iterator();
        while (iter.hasNext()) {
            try {
                return ((DateFormat) iter.next()).parse(dateString);
            } catch (ParseException e) {
                // do nothing
            }
        }
        return null;
    }
}