package org.testVagrant.interviewAssignment.API;

import static io.restassured.RestAssured.given;
import java.io.IOException;
import java.util.Map;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;


public class HelperMethods {
	private final String apiKey = "7fe67bf08c80ded756e598d6f8fedaea";
	
	public void setBaseURI() {
		RestAssured.baseURI = "http://api.openweathermap.org/";
	}

	//GET Request
	public Response getResponse(String path, Map<String, String> queryParams) throws IOException {
        RestAssured.defaultParser = Parser.JSON;
        return given().queryParam("appid", apiKey).queryParams(queryParams).log().all().get(path).then().contentType(ContentType.JSON).statusCode(200)
        		.log().all().extract().response();

    }
	
	//POST Request
    public Response postPayload(String payload, String path) throws IOException {
        return given().queryParam("apiid", apiKey).contentType(ContentType.JSON).when().log().all().body(payload).post(path)
                .then().log().all().extract().response();
    }
    
    //PUT Request
    public Response putJsonPayload(String payload, String path) throws IOException {
        return given().queryParam("apiid", apiKey).contentType(ContentType.JSON).when().log().all().body(payload).when().put(path)
                .then().log().all().extract().response();
    }

}
