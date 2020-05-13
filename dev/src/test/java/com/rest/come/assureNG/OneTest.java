package com.rest.come.assureNG;

import io.cucumber.datatable.dependency.com.fasterxml.jackson.databind.DeserializationFeature;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpHeaders;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static java.lang.Math.min;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import io.cucumber.datatable.dependency.com.fasterxml.jackson.core.JsonParseException;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.databind.JsonMappingException;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.databind.ObjectMapper;


public class OneTest {


    @Test
    public void Satyadeep123() throws ParseException, JsonParseException, JsonMappingException, IOException {

        ObjectMapper m = new ObjectMapper();
        m.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        com.api.response.WholeResponse responseJson = m.readValue(given().get("http://api.openweathermap.org/data/2.5/forecast?id=2147714&units=metric&mode=json&APPID=d49b8056ff6fbc8d249eecef9bf02a9c")
                .then().statusCode(200).
                        extract().asString(),com.api.response.WholeResponse.class);


        System.out.println("City Name = " + responseJson.getCity().getName());

        boolean Thur_Day_Present = false;

        for (com.api.response.WeatherList list : responseJson.getList()) {

            Date date = dateFormat.parse(list.getDt_txt());

            if (date.getDay() == 4) {
                Thur_Day_Present = true;

                float temp  = min(list.getMain().getTemp_min(),list.getMain().getFeels_like());

                if (temp > 10) {

                    System.out.println("Comfortable day for visit @ " + date);

                }
            }
        }
        if (Thur_Day_Present == false) {
            System.out.println("Thursday Not Found");
        }

    }

}
