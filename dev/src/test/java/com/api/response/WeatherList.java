package com.api.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import  com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class WeatherList implements Serializable {

    @JsonProperty("dt_txt")
    private String dt_txt;

    @JsonProperty("main")
    private MainWeather main;

    public String getDt_txt() {
        return dt_txt;
    }

    public void setDt_txt(String dt_txt) {
        this.dt_txt = dt_txt;
    }

    public MainWeather getMain() {
        return main;
    }

    public void setMain(MainWeather main) {
        this.main = main;

    }

    @Override
    public String toString() {
        return "WeatherList{" +
                "dt_txt='" + dt_txt + '\'' +
                ", main=" + main +
                '}';
    }
}
