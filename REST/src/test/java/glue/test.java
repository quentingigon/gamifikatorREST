package glue;
import java.awt.*;
import java.util.concurrent.TimeUnit;


import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.minidev.json.JSONObject;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Assert;

/*
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

*/

public class test {

    public String URL = "http://localhost:8081/v1/";
    public String pathBadges = "badges";
    CloseableHttpClient httpClient = HttpClients.createDefault();

    public JSONObject jsonBadge;

    CloseableHttpResponse rawResponse;

    @Given("^user has API token$")
    public void user_has_API_token() throws Throwable {

        jsonBadge  = new JSONObject();
        jsonBadge.put("token", "token");
        jsonBadge.put("name", "Numero Uno");
        jsonBadge.put("icon","BadgeImage");

    }

    @When("^a badge is created$")
    public void badge_creation() throws Throwable {
        StringEntity requestEntity = new StringEntity(
                jsonBadge.toJSONString(),
                ContentType.APPLICATION_JSON);

        HttpPost postMethod = new HttpPost(URL + pathBadges);
        postMethod.setEntity(requestEntity);

        rawResponse = httpClient.execute(postMethod);

    }


    @Then("^the server should send a 200 OK$")
    public void get_server_answer() throws Throwable {
        System.out.println(rawResponse.getStatusLine());
        Assert.assertEquals("200", rawResponse.getStatusLine());
    }
}