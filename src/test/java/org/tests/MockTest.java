package org.tests;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.BaseTest;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;

import org.watchers.TestWatchman;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.Assert.assertTrue;


public class MockTest extends BaseTest {

    private final String endpoint = RestAssured.basePath = "http://localhost:8080/myUrl/secondPart";

    private TestWatchman ruleTwm = new TestWatchman();
    private WireMockRule ruleWM = new WireMockRule();

    @Rule
    public RuleChain ch = RuleChain.outerRule(ruleTwm).around(ruleWM);

    @Test
    public void basicTest(){

        stubFor(WireMock.get(urlPathMatching("/myUrl/secondPart"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withHeader("some-custom-header", "test")
                        .withBody("Hellou, i'm the body!")));


        Response resp = RestAssured.get(endpoint);
        assertTrue("Not expected header.", resp.getHeader("some-custom-header").equals("test"));
        assertTrue("Not expected body.", resp.getBody().asString().equals("Hellou, i'm the body!"));
    }
}
