package sohage.example.com.numberverify.DButilites;

import android.provider.BaseColumns;

/**
 * Created by Mohamed_Ahmed on 16/04/2018.
 */
public class HistoryTable implements BaseColumns {
    public static final String TABLE_NAME = "History";
    public static final String VALID = "valid";
    public static final String MOBILE_NUMBER = "number";
    public static final String LOCAL_FORMAT = "local_format";
    public static final String INTERNATIONAL_FORMAT = "international_format";
    public static final String COUNTRY_PREFIX = "country_prefix";
    public static final String COUNTRY_CODE = "country_code";
    public static final String COUNTRY_NAME = "country_name";
    public static final String LOCATION = "location";
    public static final String CARRIER = "carrier";
    public static final String LINE_TYPE = "line_type";
}
