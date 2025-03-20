package tests;

import org.testng.annotations.Test;

import io.restassured.response.Response;

import data.HttpResponseStatusCodes;
import helperfunctions.HelperFunctions;
import testmethods.ChuckNorrisApiTestMethods;

public class ChuckNorrisApiTests {
	@Test
	public void getRandomJoke() {
		Response getRandomJoke = ChuckNorrisApiTestMethods.getRandomJoke();
		ChuckNorrisApiTestMethods.responseValidation("Get Random Joke", getRandomJoke,
				HttpResponseStatusCodes.OK.getCode());
	}

	@Test
	public void getCategories() {
		Response getCategories = ChuckNorrisApiTestMethods.getCategories();
		ChuckNorrisApiTestMethods.responseValidation("Get Categories", getCategories,
				HttpResponseStatusCodes.OK.getCode());
	}

	@Test
	public void getRandomJokeFromCategory() {
		Response getCategories = ChuckNorrisApiTestMethods.getCategories();

		Response getRandomJokeFromCategory = ChuckNorrisApiTestMethods
				.getRandomJokeFromCategory(ChuckNorrisApiTestMethods.getRandomCategory(getCategories));
		ChuckNorrisApiTestMethods.responseValidation("Get Random Joke From Category", getRandomJokeFromCategory,
				HttpResponseStatusCodes.OK.getCode());
	}

	@Test
	public void getRandomJokeInvalidCategory() {
		Response getRandomJokeInvalidCategory = ChuckNorrisApiTestMethods.getRandomJokeFromCategory("invalid_category");
		ChuckNorrisApiTestMethods.responseValidation("Get Random Joke From Invalid Category",
				getRandomJokeInvalidCategory, HttpResponseStatusCodes.NOTFOUND.getCode());
	}

	@Test
	public void searchJokesByText() {
		Response getRandomJoke = ChuckNorrisApiTestMethods.getRandomJoke();

		Response searchJokesByText = ChuckNorrisApiTestMethods
				.searchJokesByText(ChuckNorrisApiTestMethods.getPartOfJoke(getRandomJoke));
		ChuckNorrisApiTestMethods.responseValidation("Search Jokes By Text", searchJokesByText,
				HttpResponseStatusCodes.OK.getCode());
	}

	@Test
	public void searchJokesByNotExistingText() {
		Response searchJokesByNotExistingText = ChuckNorrisApiTestMethods.searchJokesByText("invalid_text");
		ChuckNorrisApiTestMethods.responseValidation("Search Jokes By Invalid Text", searchJokesByNotExistingText,
				HttpResponseStatusCodes.OK.getCode());
		ChuckNorrisApiTestMethods.validateNumberOfSearchResults(searchJokesByNotExistingText, 0);
	}

	@Test
	public void searchJokesByTooShortText() {
		Response searchJokesByTooShortText = ChuckNorrisApiTestMethods
				.searchJokesByText(HelperFunctions.getRandomString(1));
		ChuckNorrisApiTestMethods.responseValidation("Search Jokes By Too Short Text", searchJokesByTooShortText,
				HttpResponseStatusCodes.BADREQUEST.getCode());
		ChuckNorrisApiTestMethods.validateResponseMessage("Search Jokes By Too Short Text", searchJokesByTooShortText,
				"search.query: size must be between 3 and 120");
	}

	@Test
	public void searchJokesByTooLongText() {
		Response searchJokesByTooShortText = ChuckNorrisApiTestMethods
				.searchJokesByText(HelperFunctions.getRandomString(121));
		ChuckNorrisApiTestMethods.responseValidation("Search Jokes By Too Long Text", searchJokesByTooShortText,
				HttpResponseStatusCodes.BADREQUEST.getCode());
		ChuckNorrisApiTestMethods.validateResponseMessage("Search Jokes By Too Long Text", searchJokesByTooShortText,
				"search.query: size must be between 3 and 120");
	}
}
