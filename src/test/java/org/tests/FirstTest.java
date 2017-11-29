package org.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.BaseTest;
import org.Site;
import org.assertj.core.api.SoftAssertions;
import org.calls.model.response.CCYResponse;
import org.calls.model.response.FullTextResponse;
import org.calls.model.response.CodeResponse;
import org.calls.model.response.LanguageResponse;
import org.specs.ResponseSpecs;
import org.apache.commons.lang3.StringUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.watchers.TestWatchman;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static io.restassured.RestAssured.get;
import static junit.framework.TestCase.assertTrue;


/**
 * Created by serjb on 20.10.2017.
 */
public class FirstTest extends BaseTest {

    @Rule
    public TestWatcher tw = new TestWatchman();
    private final String endpoint = RestAssured.basePath = utils.getEndpoint();
    private ResponseSpecs respSp = new ResponseSpecs();

    @Test
    public void testSingleCapital() throws IOException {
        final String country = "Belarus";
        final String capital = "Minsk";

        Response resp =
                get(endpoint + format(Site.FULL_TEXT, country));

        resp.then().spec(respSp.getJsonSpec());
        // toDo: generify
        FullTextResponse[] response = new ObjectMapper().readValue(resp.asString(), FullTextResponse[].class);

        assertTrue("Expected country is different than in the response.",
                StringUtils.equals(response[0].getCapital(), capital));
    }

    @Test
    public void testCODE() throws IOException {
        final String countryCode = "blr";
        final String capital = "Minsk";

        Response resp =
                get(endpoint + format(Site.CODE, countryCode));

        resp.then().spec(respSp.getJsonSpec());

        CodeResponse response = new ObjectMapper().readValue(resp.asString(), CodeResponse.class);

        assertTrue("Expected country differs from the response.",
                StringUtils.equals(response.getCapital(), capital));
    }

    @Test
    public void testListOfCodes() throws IOException {
        final Iterator<String> capitals = new LinkedList<>(Arrays.asList("Minsk", "Moscow", "Kiev")).iterator();
        final String countries = "blr;ru;ua";

        Response resp =
                get(endpoint + format(Site.LIST_OF_CODES, countries));

        resp.then().spec(respSp.getJsonSpec());

        Stream.of(new ObjectMapper().readValue(resp.asString(), CodeResponse[].class))
                .forEach(i -> {
                    String expectedCountry = capitals.next().toString();
                    assertTrue("Expected country is different than in the response." +
                                    "\nReceived: \"" + i.getCapital() + "\" country" +
                                    "\nExpected: \"" + expectedCountry + "\" country",
                            StringUtils.equals(i.getCapital(), expectedCountry));
                });
    }

    @Test
    public void testRequestedCountriesContainsCCY() throws IOException {
        final String ccy = "USD";
        final String cFullName = "United States of America";

        Response resp =
                get(endpoint + format(Site.CURRENCY, ccy));

        resp.then().spec(respSp.getJsonSpec());
        // toDo: generify
        CCYResponse[] response = new ObjectMapper().readValue(resp.asString(), CCYResponse[].class);

        SoftAssertions softly = new SoftAssertions();
        Arrays.stream(response)
                .filter(i -> i.getName().equals(cFullName))
                .collect(Collectors.toList())
                .forEach(i -> i.getCurrencies().forEach(j ->
                        softly.assertThat(j.getCode()).isEqualTo(ccy)));
        softly.assertAll();
    }

    @Test
    public void testTranslationCount() throws IOException {
        final String locale = "ru";
        final int retCount = 9;

        Response resp =
                get(endpoint + format(Site.LANGUAGE, locale));

        resp.then().spec(respSp.getJsonSpec());
        // toDo: generify
        LanguageResponse[] response = new ObjectMapper().readValue(resp.asString(), LanguageResponse[].class);


        assertTrue("Expected country differs from the response.",
                Arrays.stream(response).count() == retCount);
    }

    private String format(Site structure, String str) {
        return String.format(structure.get(), str);
    }

}
