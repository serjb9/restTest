import io.restassured.response.Response;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.junit.Test;

import java.io.IOException;
import java.net.MalformedURLException;


import static io.restassured.RestAssured.get;
import static org.junit.Assert.assertTrue;

public class FirstTest extends ABase{

    private Utils utils = new Utils();

    public FirstTest() throws IOException {

    }

    @Test
    public void test1() throws MalformedURLException, JSONException{
        Response resp = get(utils.getEndpoint()
//                + Site.ALL.get()
                + String.format(Site.FULL_NAME.get(), "Belarus"));

        JSONArray jsonResponse = new JSONArray(resp.asString());


        assertTrue("Wrong response",
                StringUtils.equals(jsonResponse.getJSONObject(0).getString("capital"), "Minsk"));

    }

}
