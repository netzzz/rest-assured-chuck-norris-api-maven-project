package helperfunctions;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Reporter;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import testmethods.ChuckNorrisApiTestMethods;

public class HelperFunctions {
	private static final Logger logger = LogManager.getLogger(ChuckNorrisApiTestMethods.class.getName());

	public static JsonPath convertRestAssuredResponseToJson(Response response) {
		JsonPath responseJson = response.jsonPath();
		return responseJson;
	}

	public static JSONObject readJsonFromFile(String jsonFilePath) {
		JSONObject jsonObject = null;

		try {
			JSONParser jsonParser = new JSONParser();
			FileReader fileReader = new FileReader(jsonFilePath);
			jsonObject = (JSONObject) jsonParser.parse(fileReader);
		} catch (FileNotFoundException e) {
			logger.info("Json File is not found: " + e.getMessage());
			Reporter.log("ERROR: Json File is not found: " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.info("Json File is not read successfully.: " + e.getMessage());
			Reporter.log("ERROR: Json File is not read successfully." + e.getMessage());
			e.printStackTrace();
		} catch (ParseException e) {
			logger.info("Json File is not parsed successfully: " + e.getMessage());
			Reporter.log("ERROR: Json File is not parsed successfully." + e.getMessage());
			e.printStackTrace();
		}

		return jsonObject;
	}

	public static int getRandomNumber(int min, int max) {
		Random random = new Random();
		return random.nextInt(max + 1) + min;
	}

	public static String getRandomString(int stringLength) {
		String Characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		SecureRandom Random = new SecureRandom();

		if (stringLength < 1)
			throw new IllegalArgumentException("Length must be greater than 0");

		StringBuilder sb = new StringBuilder(stringLength);
		for (int i = 0; i < stringLength; i++) {
			sb.append(Characters.charAt(Random.nextInt(Characters.length())));
		}
		return sb.toString();
	}
	
	public static void logToReportAndLog4j(String message) {
		logger.info(message);
		Reporter.log("INFO: " + message);
	}
	
	public static void logToReportAndLog4j(String message, Throwable t) {
		logger.error(message, t);
		Reporter.log("ERROR: " + message + "; "+ t.getMessage());
	}
}
