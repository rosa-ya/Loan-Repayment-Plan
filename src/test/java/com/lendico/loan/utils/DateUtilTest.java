package com.lendico.loan.utils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.format.DateTimeParseException;

public class DateUtilTest {

    private DateUtil dateUtilTest;

    @Before
    public void setup() {
        dateUtilTest = new DateUtil();
    }

    //success scenario
    @Test
    public void testAddMonthsToDate(){
        Assert.assertEquals("2018-02-01T00:00:01Z",dateUtilTest.addMonthsToDate("2018-01-01T00:00:01Z", 1));
        Assert.assertEquals("2020-01-01T00:00:01Z",dateUtilTest.addMonthsToDate("2018-01-01T00:00:01Z", 24));
    }

    //failed scenario
    @Test(expected = DateTimeParseException.class)
    public void shouldFailToParseInvalidDate() {
        dateUtilTest.addMonthsToDate("INVALID", 1);
    }
}
