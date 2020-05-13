package stepDefinitions;

import com.api.response.WeatherList;
import com.api.response.WholeResponse;
import com.sun.media.jfxmedia.logging.Logger;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.core.JsonParseException;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.databind.DeserializationFeature;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.databind.JsonMappingException;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;

import static io.restassured.RestAssured.given;
import static java.lang.Math.min;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


import io.cucumber.junit.Cucumber;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import com.api.response.WholeResponse;

@RunWith(Cucumber.class)
public class Steps {

    ObjectMapper m;
    WholeResponse response;
    String cityId;
    WholeResponse responseJson;
    Boolean thursdayWeatherStatus = false;


    public Steps() throws JsonParseException, JsonMappingException, IOException {
        m = new ObjectMapper();
        m.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Given("I like to holiday in {string}")

    public void i_like_to_holiday_in_sydney(String city) {
        Map<String, String> cityMap = new HashMap<String, String>() {
            {
                put("Sydney", "2147714");
                put("Melbourne", "2145672");
            }
        };

        cityId = cityMap.get(city);
    }

    @When("^I look up the weather forecast$")
    public void i_look_up_the_weather_forecast() throws Throwable {
        String BaseURI = "http://api.openweathermap.org/data/2.5/forecast?id=" + cityId + "&units=metric&mode=json&APPID=d49b8056ff6fbc8d249eecef9bf02a9c";

        // API response assert to verify 20OK  and capture whole response body
        responseJson = m.readValue(given().get(BaseURI)
                .then().statusCode(200).
                        extract().asString(), WholeResponse.class);

        // The API chosen gives response for 5 days
        System.out.println("DISCLAIMER--Hello !! With free subscription you are entitled for only a 5 days weather forecast starting today  ");

        // To Confirm the response is for City-Sydney
        System.out.println("Lets see how's the weather for " + responseJson.getCity().getName());

        // Just to have good Customer Experience while the program runs
        System.out.println("We are looking for the weather forecast for the next Thursday and get back shortly");

    }

    @Then("^I receive the weather forecast$")
    public void i_receive_the_weather_forecast() throws Throwable {

        // To Confirm the user gets a correct response for the City looking for-Sydney in our case
        assertEquals("Sydney", responseJson.getCity().getName());

    }

    @And("^I only like to holiday on Thursdays$")
    public void i_only_like_to_holiday_on_thursdays() throws Throwable {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        boolean Thur_Day_Present = false;

        for (WeatherList list : responseJson.getList()) {
            // Finding the Thursday of the week in  coming week
            Date date = dateFormat.parse(list.getDt_txt());
            if (date.getDay() == 4) {
                Thur_Day_Present = true;

                // capture the temperature where user feels the warmth above 10 deg Cel

                float temp = min(list.getMain().getTemp_min(), list.getMain().getFeels_like());
                if (temp > 10) {
                    thursdayWeatherStatus = true;
                    System.out.println("Comfortable day and time for visit @ " + date);
                }
            }
        }
        if (Thur_Day_Present == false) {
            System.out.println("Thursday Not Found");
        }
    }


    @And("^the temperature is warmer than 10 degrees$")
    public void the_temperature_is_warmer_than_10_degrees() throws Throwable {
        // Verify that user gets back a day where real feel temperature or lowest temperature is above 10-C
        assertTrue(thursdayWeatherStatus);


    }


}


