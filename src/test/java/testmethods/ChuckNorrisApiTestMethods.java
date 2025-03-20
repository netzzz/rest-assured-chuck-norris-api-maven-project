package testmethods;

import java.util.ArrayList;

import org.testng.Assert;

import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import helperfunctions.HelperFunctions;

public class ChuckNorrisApiTestMethods {
	//private static final Logger logger = LogManager.getLogger(ChuckNorrisApiTestMethods.class.getName());
	private static String baseURI = "https://api.chucknorris.io/";

	// ----------------------------------
	// Get Random Joke Methods

	public static Response getRandomJoke() {
		RestAssured.baseURI = baseURI;

		return given().when().get("jokes/random").then().extract().response();
	}

	// ----------------------------------
	// Get Categories Methods

	public static Response getCategories() {
		RestAssured.baseURI = baseURI;
		return given().when().get("jokes/categories").then().extract().response();
	}

	public static String getRandomCategory(Response getCategories) {
		JsonPath getCategoriesJson = getCategories.jsonPath();
		ArrayList<String> Categories = getCategoriesJson.getJsonObject("$");

		return Categories.get(HelperFunctions.getRandomNumber(0, Categories.size() - 1));
	}

	// ----------------------------------
	// Get Joke From Category

	public static Response getRandomJokeFromCategory(String category) {
		RestAssured.baseURI = baseURI;

		return given().queryParam("category", category).when().get("jokes/random").then().extract().response();
	}

	// ----------------------------------
	// Text Search Methods

	public static Response searchJokesByText(String text) {
		RestAssured.baseURI = baseURI;

		return given().queryParam("query", text).when().get("jokes/search").then().extract().response();
	}

	public static String getPartOfJoke(Response getJokeResponse) {
		JsonPath getJokeResponseJson = getJokeResponse.jsonPath();
		String joke = getJokeResponseJson.getString("value");
		return joke.substring(joke.length() - 15);
	}

	// ----------------------------------
	// Response Validation Methods

	public static void responseValidation(String requestName, Response response, int statusCode) {
		Assert.assertEquals(response.getStatusCode(), statusCode,
				String.format("%s Response Status Code is %d as Not Expected", requestName, response.getStatusCode()));
		HelperFunctions.logToReportAndLog4j(String.format("%s Response Status Code is %d as Expected", requestName,
				statusCode));
	}
	
	public static void validateNumberOfSearchResults(Response searchJokesByText, int expectedNumberOfResults) {
		JsonPath searchJokesByTextJson = searchJokesByText.jsonPath();
		Assert.assertEquals(searchJokesByTextJson.getInt("total"), expectedNumberOfResults,
				String.format("Number of Search Results is %d as Not Expected", searchJokesByTextJson.getInt("total")));
		HelperFunctions.logToReportAndLog4j(String.format("Number of Search Results is %d as Expected", expectedNumberOfResults));
	}
	
	public static void validateResponseMessage(String requestName, Response response,String expectedResponseMessage) {
		JsonPath responseJson = response.jsonPath();
		Assert.assertEquals(responseJson.getString("message"), expectedResponseMessage,
				String.format("%s Response Message is '%s' as Not Expected", requestName, responseJson.getString("message")));
		HelperFunctions.logToReportAndLog4j(String.format("%s Response Message is '%s' as Expected", 
				requestName, expectedResponseMessage));
	}

}
