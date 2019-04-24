package regressionTests;

import static org.testng.Assert.fail;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import driver.DriverEngine;

public class RegressionCases {

	DriverEngine driverEngine = null;
	SoftAssert softAssert = new SoftAssert();

	@BeforeClass
	public void setUp() {

		File srcFile = new File("TestDataAndResults\\TestData\\SophieAutomation.xlsx");
		File destDir = new File("TestDataAndResults\\Run1\\");
		try {
			driverEngine = new DriverEngine();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			System.out.println("failed to instantiate the driver class");
		}
		try {
			FileUtils.copyFileToDirectory(srcFile, destDir);

		} catch (IOException e) {

			System.out.println("file copy failed");
			// TODO Auto-generated catch block

		}
	}

	@Test(priority = 1)
	// First test to validate the CSV after engine run
	public void VerifyDownloadedCSV() {

		if (!driverEngine.mainMethod("Cases_CSVvalidations")) {
			fail();
			Reporter.log("failed to find test sheet");
		}

		// TODO Auto-generated catch block

	}

	@Test(priority = 2)
	// Second suite to with API integration
	public void VerifyWithAPICalls() {

		if (!driverEngine.mainMethod("Cases_APIIntegration")) {

			// TODO Auto-generated catch block
			fail();
			Reporter.log("failed to find the testsheet");
		}

	}
}
