package adapters;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.apache.http.protocol.HTTP;
import utils.PropertyReader;

import static io.restassured.RestAssured.given;

public class BaseAdapter {

    public static final String BASE_URL_API = System.getenv().getOrDefault("TESTRAIL_API_URL",
            PropertyReader.getProperty("testrail.apiurl"));
    public static final String BASIC_AUTH = System.getenv().getOrDefault("BASIC_AUTH",
            PropertyReader.getProperty("testrail.basicauth"));
    RequestSpecification specification;

    public BaseAdapter() {
        specification = given().
                header(HTTP.CONTENT_TYPE, ContentType.JSON).
                header("Authorization", BASIC_AUTH);
    }

    public String post(String body, String uri, int expectedStatusCode) {
        String response =
                given().spec(specification).
                        body(body).
                when().
                        post(BASE_URL_API + uri).
                then().
                log().body().
                        statusCode(expectedStatusCode).
                        extract().body().asString();
        return response;
    }

    public void delete(String uri, int expectedStatusCode) {
        given().spec(specification).
        when().
                post(BASE_URL_API + uri).
        then().
                log().body().
                statusCode(expectedStatusCode);
    }

    public String get(String uri, int expectedStatusCode) {
        String body =
                given().spec(specification).
                when().
                        get(BASE_URL_API + uri).
                then().
                        log().body().
                        statusCode(expectedStatusCode).
                        extract().body().asString();
        return body;
    }

    public void patch(String body, String uri, int expectedStatusCode) {
        given().spec(specification).
                body(body).
        when().
                post(BASE_URL_API + uri).
        then().
                log().body().
                statusCode(expectedStatusCode);
    }
}