package com.example.amei;

import com.example.amei.Negocios.DateUtils;

import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtilsTest {

    @Test
    public void testFormatDateToString() {
        // Given
        Date date = createDate(2023, 7, 4, 12, 30, 0);
        String expectedDateString = "2023-07-04T12:30:00";

        // When
        String actualDateString = DateUtils.formatDateToString(date);

        // Then
        Assert.assertEquals(expectedDateString, actualDateString);
    }

    @Test
    public void testParseStringToDate() {
        // Given
        String dateString = "2023-07-04T12:30:00";
        Date expectedDate = createDate(2023, 7, 4, 12, 30, 0);

        // When
        Date actualDate = DateUtils.parseStringToDate(dateString);

        // Then
        Assert.assertEquals(expectedDate, actualDate);
    }

    private Date createDate(int year, int month, int day, int hour, int minute, int second) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
        String dateString = String.format(Locale.getDefault(), "%04d-%02d-%02dT%02d:%02d:%02d",
                year, month, day, hour, minute, second);
        try {
            return format.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
