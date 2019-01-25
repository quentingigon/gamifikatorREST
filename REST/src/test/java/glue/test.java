package glue;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import net.minidev.json.JSONObject;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Assert;

public class test {

    public String URL = "http://localhost:8081/v1/";
    public String pathBadges = "badges/";
    public String pathRules = "rules/";
    public String pathEvents = "events/";

    public String BADGENAME = "Numerot";
    public String RULENAME = "Rule1";

    //-------
    private String token = "token";

    CloseableHttpClient httpClient = HttpClients.createDefault();

    public JSONObject jsonBadge;

    CloseableHttpResponse rawResponse;

    @Given("^user has API token and badge$")
    public void user_has_API_token_badges() throws Throwable {

        jsonBadge = new JSONObject();
        jsonBadge.put("apiToken",token);
        jsonBadge.put("name", BADGENAME);
        jsonBadge.put("icon","BadgeImage");

    }

    @Given("^user has API token and rule$")
    public void user_has_API_token_rules() throws Throwable {

        jsonBadge = new JSONObject();
        jsonBadge.put("apiToken",token);
        jsonBadge.put("name", RULENAME);
        jsonBadge.put("badgeId", 1);
        JSONObject property = new JSONObject();
            property.put("name","testProperty");
            property.put("value", 42);
            property.put("operator","==");
            property.put("ruleName", RULENAME);
        jsonBadge.put("property",property);

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
    @When("^a rule is created$")
    public void rule_creation() throws Throwable {
        StringEntity requestEntity = new StringEntity(
                jsonBadge.toJSONString(),
                ContentType.APPLICATION_JSON);

        HttpPost putMethod = new HttpPost(URL + pathRules);
        putMethod.setEntity(requestEntity);

        rawResponse = httpClient.execute(putMethod);

    }
    @When("^an event is created$")
    public void event_creation() throws Throwable {

        jsonBadge.put("value", 42);
        jsonBadge.put("userId", 1);
        jsonBadge.put("ruleName", RULENAME);
        jsonBadge.put("timestamp", "12:00:00");

        StringEntity requestEntity = new StringEntity(
            jsonBadge.toJSONString(),
            ContentType.APPLICATION_JSON);

        HttpPost putMethod = new HttpPost(URL + pathEvents);
        putMethod.setEntity(requestEntity);

        rawResponse = httpClient.execute(putMethod);
    }

    @When("^an event is created with false rule name$")
    public void event_creation_false_rule_name() throws Throwable {

        jsonBadge.put("value", 42);
        jsonBadge.put("userId", 1);
        jsonBadge.put("ruleName", "not a real name");
        jsonBadge.put("timestamp", "12:00:00");

        StringEntity requestEntity = new StringEntity(
            jsonBadge.toJSONString(),
            ContentType.APPLICATION_JSON);

        HttpPost putMethod = new HttpPost(URL + pathEvents);
        putMethod.setEntity(requestEntity);

        rawResponse = httpClient.execute(putMethod);
    }

    @When("^an event is created with false user id$")
    public void event_creation_false_user_id() throws Throwable {

        jsonBadge.put("value", 42);
        jsonBadge.put("userId", 100000);
        jsonBadge.put("ruleName", RULENAME);
        jsonBadge.put("timestamp", "12:00:00");

        StringEntity requestEntity = new StringEntity(
            jsonBadge.toJSONString(),
            ContentType.APPLICATION_JSON);

        HttpPost putMethod = new HttpPost(URL + pathEvents);
        putMethod.setEntity(requestEntity);

        rawResponse = httpClient.execute(putMethod);
    }

    @When("^an event is created with false property name$")
    public void event_creation_false_property_name() throws Throwable {

        jsonBadge.put("value", 42);
        jsonBadge.put("userId", 1);
        jsonBadge.put("ruleName", RULENAME);
        jsonBadge.put("timestamp", "12:00:00");

        JSONObject property = new JSONObject();
            property.put("name","not a property name");
            property.put("ruleName", RULENAME);
        jsonBadge.put("property",property);

        StringEntity requestEntity = new StringEntity(
            jsonBadge.toJSONString(),
            ContentType.APPLICATION_JSON);

        HttpPost putMethod = new HttpPost(URL + pathEvents);
        putMethod.setEntity(requestEntity);

        rawResponse = httpClient.execute(putMethod);
    }

    @When("^an event is created with false api token$")
    public void event_creation_false_api_token() throws Throwable {

        jsonBadge.put("apiToken", "not a valid token");
        jsonBadge.put("value", 42);
        jsonBadge.put("userId", 1);
        jsonBadge.put("ruleName", RULENAME);
        jsonBadge.put("timestamp", "12:00:00");

        StringEntity requestEntity = new StringEntity(
            jsonBadge.toJSONString(),
            ContentType.APPLICATION_JSON);

        HttpPost putMethod = new HttpPost(URL + pathEvents);
        putMethod.setEntity(requestEntity);

        rawResponse = httpClient.execute(putMethod);
    }


    @When("^a rule is updated$")
    public void rule_update() throws Throwable {
        StringEntity requestEntity = new StringEntity(
                jsonBadge.toJSONString(),
                ContentType.APPLICATION_JSON);

        HttpPut putMethod = new HttpPut(URL + pathRules);
        putMethod.setEntity(requestEntity);

        rawResponse = httpClient.execute(putMethod);

    }

    @Then("^the server should send a (.*)$")
    public void get_server_answer(String code) throws Throwable {
        System.out.println(rawResponse.getStatusLine());
        Assert.assertEquals("HTTP/1.1 " +code+ " ", rawResponse.getStatusLine().toString());
    }



    @When("^the badge is fetched$")
    public void badge_fetch() throws Throwable {

        HttpGet postMethod = new HttpGet(URL + pathBadges + BADGENAME + "?apiToken="+token);

        rawResponse = httpClient.execute(postMethod);

    }

    @When("^the badge is deleted$")
    public void badge_delete() throws Throwable {

        HttpGet postMethod = new HttpGet(URL + pathBadges + BADGENAME + "?apiToken="+token);

        rawResponse = httpClient.execute(postMethod);

    }

    @Then("^the user get the previous badge$")
    public void get_badge() throws Throwable {
        System.out.println(rawResponse.getStatusLine());
        String responseString = new BasicResponseHandler().handleResponse(rawResponse);

        Assert.assertTrue(responseString.contains(BADGENAME));
    }
}