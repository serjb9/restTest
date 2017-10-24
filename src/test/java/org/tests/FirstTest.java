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

import java.util.*;
import java.util.stream.StreamSupport;

import static io.restassured.RestAssured.get;
import static org.junit.Assert.assertTrue;

/**
 * Created by serjb on 20.10.2017.
 */
public class FirstTest extends BaseTest {

    @Rule
    public TestWatcher tw = new TestWatchman();
    private Utils utils = new Utils();

    @Test
    public void testSingleCapital() {
        final String country = "Belarus";
        final String capital = "Minsk";

        Response resp = get(utils.getEndpoint()
                + format(Site.FULL_NAME, country));
        resp.then().statusCode(200);

        JSONArray jsonResponse = new JSONArray(resp.asString());
        assertTrue("Expected country is different than in the response.",
                StringUtils.equals(jsonResponse.getJSONObject(0).getString("capital"), capital));

    }

    @Test
    public void testISO() {
        Response resp = get(utils.getEndpoint()
                + format(Site.CODE, "BLR"));

        resp.then().statusCode(200);
        JSONObject jsonResponse = new JSONObject(resp.asString());
        assertTrue("Expected country is different than in the response.",
                StringUtils.equals(jsonResponse.getString("capital"), "Minsk"));
    }

    @Test
    public void testListOfCodes() {
        final Iterator capitals = new LinkedList<>(Arrays.asList("Minsk", "Moscow", "Kiev")).iterator();
        final String countries = "blr;ru;ua";

        Response resp = get(utils.getEndpoint()
                + format(Site.LIST_OF_CODES, countries));

        resp.then().statusCode(200);
        new JSONArray(resp.asString()).forEach((i -> {
            String currCountry = ((JSONObject) i).getString("capital");
            assertTrue("Expected country is different than in the response. Received \"" + currCountry + "\" country",
                    StringUtils.equals(currCountry, capitals.next().toString()));
        }));
    }

    @Test
    public void testISOCCY() {
        final String ccy = "USD";
        final String cFullName = "United States of America";
        final String cName = "United States";

        Response resp = get(utils.getEndpoint()
                + format(Site.CURRENCY, ccy));
        resp.then().statusCode(200);

        assertTrue("The native country for ccy: " + ccy + " is invalid",
                StringUtils.equals(
                        StreamSupport.stream((new JSONArray(resp.asString())).spliterator(), false)
                                .map(JSONObject.class::cast)
                                .filter(i -> i.getString("name").contains(cFullName))
                                .findFirst()
                                .get()
                                .getString("nativeName")
                        , cName));
    }

    @Test
    public void isISOLangCount() {
        final String locale = "ru";
        final int retCount = 9;

        Response resp = get(utils.getEndpoint()
                + format(Site.LANGUAGE, locale));

        resp.then().statusCode(200);

        assertTrue("Count of returned locales is incorrect. Expected: " + retCount,
                ((int) StreamSupport.stream((new JSONArray(resp.asString())).spliterator(), false)
                        .map(JSONObject.class::cast)
                        .map(i -> i.getJSONArray("languages"))
                        .count() == retCount));


    }

    private String format(Site structure, String str) {
        return String.format(structure.get(), str);
    }

}
