package com.example.kkiran.weather.Model;

import java.util.Date;

/**
 * Created by kkiran on 05/07/16.
 */

public class Data {


    public double getTemperature() {
        return mTemperature;
    }

    public void setTemperature(double temperature) {
        mTemperature = temperature;
    }

    public String getTimeZone() {
        return mTimeZone;
    }

    public void setTimeZone(String timeZone) {
        mTimeZone = timeZone;
    }

    public double getHumidity() {
        return mHumidity;
    }

    public void setHumidity(double humidity) {
        mHumidity = humidity;
    }

    public double getPrecipitation() {
        return mPrecipitation;
    }

    public void setPrecipitation(double precipitation) {
        mPrecipitation = precipitation;
    }

    public String getSummary() {
        return mSummary;
    }

    public void setSummary(String summary) {
        mSummary = summary;
    }

    public long getTime() {
        return mTime;
    }

    public void setTime(long time) {
        mTime = time;
    }
    private double mTemperature;
    private String mTimeZone;
    private double mHumidity;
    private double mPrecipitation;
    private String mSummary;
    private long mTime;
    private Date mDate;
    public String toString()
    {
        return "temp: "+mTemperature+" \n Timezone :"+ mTimeZone+"\n Humidity: "+mHumidity+" \n "
                +" Precipitation "+mPrecipitation+"\n Summary: "+mSummary+"\n Time "+mTime;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(long time) {
        this.mDate = new Date(time);

    }
}
