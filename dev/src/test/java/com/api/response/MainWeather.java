package com.api.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class MainWeather implements Serializable {

    @JsonProperty("feels_like")
    private Float feels_like;

    @JsonProperty("temp_min")
    private Float temp_min;

    public Float getFeels_like() {
        return feels_like;
    }

    public void setFeels_like(Float feels_like) {
        this.feels_like = feels_like;
    }

    public Float getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(Float temp_min) {
        this.temp_min = temp_min;
    }

    @Override
    public String toString() {
        return "MainWeather{" +
                "feels_like=" + feels_like +
                ", temp_min=" + temp_min +
                '}';
    }
}


