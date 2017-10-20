package org.tests;

import com.sun.org.glassfish.gmbal.Description;
import io.restassured.response.Response;
import org.BaseTest;
import org.Site;
import org.Utils;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.watchers.TestWatchman;

import java.net.MalformedURLException;

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
    public void isCapital() throws MalformedURLException, JSONException {
        Response resp = get(utils.getEndpoint()
//                + Site.ALL.get()
                + String.format(Site.FULL_NAME.get(), "Belarus"));

        JSONArray jsonResponse = new JSONArray(resp.asString());

        assertTrue("Wrong response",
                StringUtils.equals(jsonResponse.getJSONObject(0).getString("capital"), "Minsk"));

    }
}
