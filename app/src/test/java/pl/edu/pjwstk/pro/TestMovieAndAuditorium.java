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

@RunWith(SpringRunner.class)
@IntegrationTest
public class TestMovieAndAuditorium {
    @BeforeClass
    public static void adminRegistration() throws JSONException {
        JSONObject requestParams = new JSONObject();
        requestParams.put("email", "admin123@wp.pl");
        requestParams.put("firstname", "admin");
        requestParams.put("lastname", "adminLastname");
        requestParams.put("password", "adminPassword");
        requestParams.put("birth_date", "10-11-2002");
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
        requestParams.put("email", "user123@wp.pl");
        requestParams.put("firstname", "user");
        requestParams.put("lastname", "userLastname");
        requestParams.put("password", "userPassword");
        requestParams.put("birth_date", "11-11-2002");
        given()
                .contentType("application/json")
                .body(requestParams.toString())
                .when()
                .post("/api/register")
                .thenReturn();
    }

    @Test
    public void adminGetMoviesShouldReturn200() throws JSONException {
        JSONObject requestParams = new JSONObject();
        requestParams.put("email", "admin123@wp.pl");
        requestParams.put("password", "adminPassword");
        var response = given()
                .contentType("application/json")
                .body(requestParams.toString())
                .when()
                .post("/api/login")
                .thenReturn();
        given()
                .cookies(response.getCookies())
                .get("/api/getMovies")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }
    @Test
    public void userGetMoviesShouldReturn200() throws JSONException {
        JSONObject requestParams = new JSONObject();
        requestParams.put("email", "user123@wp.pl");
        requestParams.put("password", "userPassword");
        var response = given()
                .contentType("application/json")
                .body(requestParams.toString())
                .when()
                .post("/api/login")
                .thenReturn();
        given()
                .cookies(response.getCookies())
                .get("/api/getMovies")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }
    @Test
    public void userGetSeatsInAuditoriumShouldReturn200() throws JSONException {
        JSONObject requestParams = new JSONObject();
        requestParams.put("email", "user123@wp.pl");
        requestParams.put("password", "userPassword");
        var response = given()
                .contentType("application/json")
                .body(requestParams.toString())
                .when()
                .post("/api/login")
                .thenReturn();
        given()
                .cookies(response.getCookies())
                .get("/api/getSeatsInAuditoriums")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }
    @Test
    public void adminAddingScreeningShouldReturn200() throws JSONException {
        JSONObject requestParams = new JSONObject();
        requestParams.put("email", "admin123@wp.pl");
        requestParams.put("password", "adminPassword");
        JSONObject screen = new JSONObject();
        screen.put("day", "12-10-2021");
        screen.put("time", "10:30");
        var response = given()
                .contentType("application/json")
                .body(requestParams.toString())
                .when()
                .post("/api/login")
                .thenReturn();
        given()
                .cookies(response.getCookies())
                .contentType("application/json")
                .body(screen.toString())
                .when()
                .post("/api/addScreening")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }
    @Test
    public void userAddingScreeningShouldReturn403() throws JSONException {
        JSONObject requestParams = new JSONObject();
        requestParams.put("email", "user123@wp.pl");
        requestParams.put("password", "userPassword");
        JSONObject screen = new JSONObject();
        screen.put("day", "12-10-2021");
        screen.put("time", "10:30");
        var response = given()
                .contentType("application/json")
                .body(requestParams.toString())
                .when()
                .post("/api/login")
                .thenReturn();
        given()
                .cookies(response.getCookies())
                .contentType("application/json")
                .body(screen.toString())
                .when()
                .post("/api/addScreening")
                .then()
                .statusCode(HttpStatus.SC_FORBIDDEN);
    }
    @Test
    public void userAddingScreeningShouldReturn200() throws JSONException {
        JSONObject requestParams = new JSONObject();
        requestParams.put("email", "user123@wp.pl");
        requestParams.put("password", "userPassword");
        JSONObject screen = new JSONObject();
        screen.put("day", "12-10-2021");
        screen.put("time", "10:30");
        var response = given()
                .contentType("application/json")
                .body(requestParams.toString())
                .when()
                .post("/api/login")
                .thenReturn();
        given()
                .cookies(response.getCookies())
                .contentType("application/json")
                .body(screen.toString())
                .when()
                .post("/api/addScreening")
                .then()
                .statusCode(HttpStatus.SC_FORBIDDEN);
    }
    @Test
    public void adminAddMovieShouldReturn200() throws JSONException {
        JSONObject requestParams = new JSONObject();
        requestParams.put("email", "admin123@wp.pl");
        requestParams.put("password", "adminPassword");
        JSONObject movie = new JSONObject();
        movie.put("title", "Title");
        movie.put("year", "2022");
        movie.put("description", "opis opis");
        movie.put("type", "horror");
        movie.put("poster", "link");

        var response = given()
                .contentType("application/json")
                .body(requestParams.toString())
                .when()
                .post("/api/login")
                .thenReturn();
        given()
                .cookies(response.getCookies())
                .contentType("application/json")
                .body(movie.toString())
                .when()
                .post("/api/addMovie")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }
    @Test
    public void adminEditMovieShouldReturn200() throws JSONException {
        JSONObject requestParams = new JSONObject();
        requestParams.put("email", "admin123@wp.pl");
        requestParams.put("password", "adminPassword");
        JSONObject movieParam = new JSONObject();
        movieParam.put("title", "Title");
        movieParam.put("year", "2022");
        movieParam.put("description", "opis opis");
        movieParam.put("type", "horror");
        movieParam.put("poster", "link");
        JSONObject editMovieParam = new JSONObject();
        editMovieParam.put("title", "Title");
        editMovieParam.put("year", "2022");
        editMovieParam.put("description", "opis opis");
        editMovieParam.put("type", "horror");
        editMovieParam.put("poster", "link");
        var response = given()
                .contentType("application/json")
                .body(requestParams.toString())
                .when()
                .post("/api/login")
                .thenReturn();
        given()
                .cookies(response.getCookies())
                .contentType("application/json")
                .body(movieParam.toString())
                .when()
                .post("/api/addMovie")
                .thenReturn();
        given()
                .cookies(response.getCookies())
                .contentType("application/json")
                .body(editMovieParam.toString())
                .when()
                .put("/api/editMovie/1")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }
    @Test
    public void userEditMovieShouldReturn403() throws JSONException {
        JSONObject requestParams = new JSONObject();
        requestParams.put("email", "user123@wp.pl");
        requestParams.put("password", "userPassword");
        JSONObject movieParam = new JSONObject();
        movieParam.put("title", "Title");
        movieParam.put("year", "2022");
        movieParam.put("description", "opis opis");
        movieParam.put("type", "horror");
        movieParam.put("poster", "link");
        JSONObject editMovieParam = new JSONObject();
        editMovieParam.put("title", "Title");
        editMovieParam.put("year", "2022");
        editMovieParam.put("description", "opis opis");
        editMovieParam.put("type", "horror");
        editMovieParam.put("poster", "link");
        var response = given()
                .contentType("application/json")
                .body(requestParams.toString())
                .when()
                .post("/api/login")
                .thenReturn();
        given()
                .cookies(response.getCookies())
                .contentType("application/json")
                .body(movieParam.toString())
                .when()
                .post("/api/addMovie")
                .thenReturn();
        given()
                .cookies(response.getCookies())
                .contentType("application/json")
                .body(editMovieParam.toString())
                .when()
                .put("/api/editMovie/1")
                .then()
                .statusCode(HttpStatus.SC_FORBIDDEN);
    }
}