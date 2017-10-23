package org.tests;

import io.restassured.response.Response;
import org.BaseTest;
import org.Site;
import org.Utils;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.watchers.TestWatchman;

import java.io.IOException;

import java.util.*;
import static io.restassured.RestAssured.get;
import static org.junit.Assert.assertTrue;

/**
 * Created by serjb on 20.10.2017.
 */
public class FirstTest extends BaseTest {

    @Rule
    public TestWatcher tw = new TestWatchman();
    private Utils utils = new Utils();

    private Response resp;

    @Test
    public void isCapital() throws IOException {
        resp = get(utils.getEndpoint()
//                + Site.ALL.get()
                + format(Site.FULL_NAME, "Belarus"));
        resp.then().statusCode(200);

        JSONArray jsonResponse = new JSONArray(resp.asString());
        assertTrue("Wrong response",
                StringUtils.equals(jsonResponse.getJSONObject(0).getString("capital"), "Minsk"));

    }

    @Test
    public void isISO(){
        resp = get(utils.getEndpoint()
                + format(Site.CODE, "BLR"));

        resp.then().statusCode(200);
        JSONObject jsonResponse = new JSONObject(resp.asString());
        assertTrue("Wrong response",
                StringUtils.equals(jsonResponse.getString("capital"), "Minsk"));
    }

    @Test
    public void listOfCodes(){
        final Iterator capitals = new LinkedList<>(Arrays.asList("Minsk", "Moscow", "Kiev")).iterator();

        resp = get(utils.getEndpoint()
                + format(Site.LIST_OF_CODES, "blr;ru;ua"));

        resp.then().statusCode(200);
        JSONArray jsonResponse = new JSONArray(resp.asString());
        jsonResponse.forEach((i -> {
            String currCountry = ((JSONObject) i).getString("capital");
            assertTrue("Not this country expected while asserting - \"" + currCountry + "\" country",
                    StringUtils.equals(currCountry, capitals.next().toString()));
        }));
    }

    private String format(Site structure, String str){
        return String.format(structure.get(), str);
    }

}
