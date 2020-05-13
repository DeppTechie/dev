package stepDefinitions;

import io.cucumber.datatable.dependency.com.fasterxml.jackson.core.JsonParseException;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.databind.JsonMappingException;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.cucumber.junit.Cucumber;

import org.junit.runner.RunWith;

import com.rest.come.assureNG.WholeResponse;


@RunWith(Cucumber.class)
public class Steps {

    ObjectMapper m;
    WholeResponse response;

    public Steps() throws JsonParseException, JsonMappingException, IOException {
        m = new ObjectMapper();
    }

    @Given("^I like to holiday in Sydney$")
    public void i_like_to_holiday_in_sydney() throws ParseException, JsonParseException, JsonMappingException, IOException {
        response = m.readValue(given().get("http://api.openweathermap.org/data/2.5/forecast?id=2147714&units=metric&APPID=d49b8056ff6fbc8d249eecef9bf02a9c")
                .then().statusCode(200).extract().asString(), WholeResponse.class);

        System.out.println("DISCLAIMER--Hello !! With free subscription you are entitled for only a 5 days weather forecast starting today");
        System.out.println("Lets see how's the weather for " + response.getCity().getName());

    }

    @When("^I look up the weather forecast$")
    public void i_look_up_the_weather_forecast() throws Throwable {
        System.out.println("We are looking for the weather forecast for the next Thursday and get back shortly");

    }

    @Then("^I receive the weather forecast$")
    public void i_receive_the_weather_forecast() throws Throwable {

        System.out.println("Please find the Weather forecast ");
    }

    @And("^I only like to holiday on Thursdays$")
    public void i_only_like_to_holiday_on_thursdays() throws Throwable {
        System.out.println("We see you are interested only for Thursdays to plan your trip");

    }

    @And("^the temperature is warmer than 10 degrees$")
    public void the_temperature_is_warmer_than_10_degrees() throws Throwable {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        boolean Thur_Day_Present = false;

        for (com.rest.come.assureNG.List list : response.getList()) {
            Date date = dateFormat.parse(list.getDt_txt());
            if (date.getDay() == 4) {
                Thur_Day_Present = true;

                float temp = list.getMain().getTemp();

                if (temp > 10) {

                    System.out.println("Thursday weather where Sydney is  > 10 degrees C  on " + date + " and the temperature  is " + temp + "degree C");

                }
            }
        }
        if (Thur_Day_Present == false) {
            System.out.println("Oh seems like there is no Thursday in your search.Please refer disclaimer ");

        }

    }

}


