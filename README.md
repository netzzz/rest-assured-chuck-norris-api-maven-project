<h3>Chuck Norris API Testing Project written using REST Assured and POM Design Pattern</h3>  
    
* [Test Cases](#test-cases)
* [Test Suites](#test-suites)
* [Test Log and Test Report](#test-log-and-test-report)
* [Instructions to Run the Test Suite](#instructions-to-run-the-test-suite)
* [API Documentation](#api-documentation)

### Test Cases

Each Test Case verifies different API functionalities, ensuring the expected responses and statuses are returned.  
  
1. Get Random Joke

Method: getRandomJoke()  
Description: This test validates the retrieval of a random joke from the Chuck Norris API.  
Expected Response: HTTP 200 OK    
  
2. Get Categories  
  
Method: getCategories()  
Description: This test verifies that all joke categories can be retrieved successfully.  
Expected Response: HTTP 200 OK    
  
3. Get Random Joke From Category  
  
Method: getRandomJokeFromCategory()  
Description: This test first fetches available categories and then requests a random joke from a randomly selected category.  
Expected Response: HTTP 200 OK  
  
4. Get Random Joke From Invalid Category  
  
Method: getRandomJokeFromCategory("invalid_category")  
Description: This test attempts to fetch a joke from an invalid category and verifies that the API returns the appropriate error response.  
Expected Response: HTTP 404 Not Found   
  
5. Search Jokes By Text  
  
Method: searchJokesByText()  
Description: This test searches for jokes containing a substring extracted from a randomly retrieved joke.  
Expected Response: HTTP 200 OK  
  
6. Search Jokes By Not Existing Text  
  
Method: searchJokesByText("invalid_text")  
Description: This test searches for jokes using a non-existing keyword to confirm that the API returns zero results.  
Expected Response: HTTP 200 OK, Number of results = 0  
  
7. Search Jokes By Too Short Text  
  
Method: searchJokesByText(HelperFunctions.getRandomString(1))  
Description: This test attempts to search with a too short text (less than 3 characters) to ensure the API enforces the minimum length restriction.  
Expected Response: HTTP 400 Bad Request, Message: "search.query: size must be between 3 and 120"   
  
8. Search Jokes By Too Long Text  
  
Method: searchJokesByText(HelperFunctions.getRandomString(121))  
Description: This test attempts to search with a too long text (more than 120 characters) to confirm the API enforces the maximum length restriction.  
Expected Response: HTTP 400 Bad Request, Message: "search.query: size must be between 3 and 120"    
  
### Test Suites   

1. ChuckNorrisApiRegressionSuite

The TestNG suite ChuckNorrisApiRegressionSuite is designed to execute all test cases for the Chuck Norris API.

2. ChuckNorrisApiRegressionPositiveTestsSuite

The TestNG suite ChuckNorrisApiRegressionPositiveTestsSuite is a subset of regression tests focused on positive test cases.    
It ensures that the core API functionalities are working as expected.  

3. huckNorrisApiRegressionNegativeTestsSuite
  
The TestNG suite ChuckNorrisApiRegressionNegativeTestsSuite is a subset of regression tests focused on negative test cases.  
It ensures that the API properly handles invalid inputs and edge cases.

### Test Log and Test Report  
  
* After each run, Test Log is stored in a timestamped .log file within the 'test_logs' directory,  
  while the Test Report is stored in a timestamped directory within the 'test_reports' directory.  
* Each report includes an 'html' directory where the report is presented with a more modern design.

### Instructions to Run the Test Suite
  
1) Clone the Repository  
2) Open the Project in an IDE  
3) Update the Maven Project  
4) Install TestNG (if not already installed)  
5) Run the Test Suite via the Command Line:  
```
mvn -Dlog4j.configurationFile=src\test\resources\loggerconfig\log4j2.xml -Dsurefire.suiteXmlFiles=src\test\resources\suites\ChuckNorrisApiRegressionSuite.xml test
```

### API Documentation  

[Chuck Norris API Documentation](https://api.chucknorris.io/)
