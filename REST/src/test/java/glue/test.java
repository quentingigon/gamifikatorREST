package glue;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import net.minidev.json.JSONObject;
import org.apache.http.client.methods.*;
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

    //SETUP NEW FOR EACH RUN
    public String BADGENAME = "Numeroz2";
    public String RULENAME = "Rulefinal";
    //-------
    private String token = "token";

    CloseableHttpClient httpClient = HttpClients.createDefault();

    public JSONObject jsonData;

    CloseableHttpResponse rawResponse;

    @Given("^user has API token and badge$")
    public void user_has_API_token_badges() throws Throwable {
        jsonData = new JSONObject();
        jsonData.put("apiToken",token);
        jsonData.put("name", RULENAME);
        jsonData.put("icon","BadgeImage");


    }

    @Given("^user has API token and rule$")
    public void user_has_API_token_rules() throws Throwable {

        jsonData = new JSONObject();
        jsonData.put("apiToken",token);
        jsonData.put("name", RULENAME);
        jsonData.put("badgeId","1");
            JSONObject property = new JSONObject();
            property.put("name","testProperty");
            property.put("ruleName",RULENAME);
            property.put("value","42");
            property.put("operator","=");
        jsonData.put("property",property);

    }

    @When("^a badge is created$")
    public void badge_creation() throws Throwable {
        StringEntity requestEntity = new StringEntity(
                jsonData.toJSONString(),
                ContentType.APPLICATION_JSON);

        HttpPost postMethod = new HttpPost(URL + pathBadges);
        postMethod.setEntity(requestEntity);

        rawResponse = httpClient.execute(postMethod);

    }
    @When("^a rule is created$")
    public void rule_creation() throws Throwable {
        StringEntity requestEntity = new StringEntity(
                jsonData.toJSONString(),
                ContentType.APPLICATION_JSON);

        HttpPost putMethod = new HttpPost(URL + pathRules);
        putMethod.setEntity(requestEntity);

        rawResponse = httpClient.execute(putMethod);

    }
    @When("^a rule is updated$")
    public void rule_update() throws Throwable {
        StringEntity requestEntity = new StringEntity(
                jsonData.toJSONString(),
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



    @When("^the (.*) is fetched$")
    public void fetch(String obj) throws Throwable {

        HttpGet postMethod;
        if(obj.equals("rule")){
            postMethod = new HttpGet(URL + pathRules + RULENAME + "?apiToken="+token);

        }else {
             postMethod = new HttpGet(URL + pathBadges + BADGENAME + "?apiToken=" + token);
        }
        rawResponse = httpClient.execute(postMethod);

    }

    @When("^the rule is deleted$")
    public void rule_delete() throws Throwable {

        jsonData = new JSONObject();
        jsonData.put("apiToken",token);
        jsonData.put("name", RULENAME);

        StringEntity requestEntity = new StringEntity(
                jsonData.toJSONString(),
                ContentType.APPLICATION_JSON);

        HttpDelete postMethod = new HttpDelete(URL + pathRules + RULENAME );
    //    postMethod.setEntity(requestEntity);

        rawResponse = httpClient.execute(postMethod);

    }

    @Then("^the user get the previous (.*)$")
    public void get_badge(String name) throws Throwable {
        System.out.println(rawResponse.getStatusLine());
        String responseString = new BasicResponseHandler().handleResponse(rawResponse);
        String nameToTestAgainst = name.equals("rule") ? RULENAME : BADGENAME;
        Assert.assertTrue(responseString.contains(nameToTestAgainst));
    }
}