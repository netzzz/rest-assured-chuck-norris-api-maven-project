package helperfunctions;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
	@Override
	public void onTestSkipped(ITestResult result) {
		HelperFunctions.logToReportAndLog4j(String.format("Test Skipped: %s", result.getName()));
	}

	@Override
	public void onTestStart(ITestResult result) {
		HelperFunctions.logToReportAndLog4j(String.format("Test Started: %s", result.getName()));
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		HelperFunctions.logToReportAndLog4j(String.format("Test Passed: %s", result.getName()));
	}

	@Override
	public void onTestFailure(ITestResult result) {
		Throwable error = result.getThrowable();
		if (error != null) {
			String.format("Test Skipped: %s - %s", result.getName(),error.getMessage());
			HelperFunctions.logToReportAndLog4j(String.format("Test Skipped: %s - %s", 
					result.getName(),error.getMessage()), error);
		}
	}



}