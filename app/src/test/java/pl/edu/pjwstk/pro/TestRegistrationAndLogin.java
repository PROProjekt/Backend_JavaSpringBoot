package pl.edu.pjwstk.pro;

import com.github.dockerjava.zerodep.shaded.org.apache.hc.core5.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import pl.edu.pjwstk.pro.config.IntegrationTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)
@IntegrationTest
public class TestRegistrationAndLogin {

    @BeforeClass
    public static void adminRegistration() throws JSONException {
        JSONObject requestParams = new JSONObject();
        requestParams.put("username", "Admin");
        requestParams.put("password", "adminPassword");
        given()
                .contentType("application/json")
                .body(requestParams.toString())
                .when()
                .post("/api/register")
                .thenReturn();
    }

    @BeforeClass
    public static void userRegistration() throws JSONException {
        JSONObject requestParams = new JSONObject();
        requestParams.put("username", "User");
        requestParams.put("password", "userPassword");
        given()
                .contentType("application/json")
                .body(requestParams.toString())
                .when()
                .post("/api/register")
                .thenReturn();
    }

    @Test
    public void testRegistrationShouldReturn200AndGetRoleAsUser() throws JSONException {
        JSONObject requestParams = new JSONObject();
        requestParams.put("username", "thisIsAnotherUser");
        requestParams.put("password", "cde3$RFV");
        given()
                .contentType("application/json")
                .body(requestParams.toString())
                .when()
                .post("/api/register")
                .then()
                .statusCode(200);

    }

    @Test
    public void wrongLoginParamsShouldReturnStatusCode401() throws JSONException {
        JSONObject requestParams = new JSONObject();
        requestParams.put("username", "thisUserDoesntExist");
        requestParams.put("password", "xxxxxxx");
        given()
                .contentType("application/json")
                .body(requestParams.toString())
                .when()
                .post("/api/login")
                .then()
                .statusCode(HttpStatus.SC_UNAUTHORIZED);
    }
    @Test
    public void siteShouldBeAllowedForEveryoneWithoutAuthenticate(){
        given()
                .get("/api/auth0/forEveryone")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }
    @Test
    public void thisIsForAdminMethodShouldReturn403AsUser() throws JSONException {
        JSONObject requestParams = new JSONObject();
        requestParams.put("username", "User");
        requestParams.put("password", "userPassword");
        var response = given()
                .contentType("application/json")
                .body(requestParams.toString())
                .when()
                .post("/api/login")
                .thenReturn();
        given()
                .cookies(response.getCookies())
                .get("/api/forAdmin")
                .then()
                .statusCode(HttpStatus.SC_FORBIDDEN);
    }
    @Test
    public void thisIsForAdminMethodShouldReturn200AsAdmin() throws JSONException {
        JSONObject requestParams = new JSONObject();
        requestParams.put("username", "Admin");
        requestParams.put("password", "adminPassword");
        var response = given()
                .contentType("application/json")
                .body(requestParams.toString())
                .when()
                .post("/api/login")
                .thenReturn();
        given()
                .cookies(response.getCookies())
                .get("/api/forAdmin")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

}
