package regressionTests;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.testng.Assert;
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
	public void Verify_DownloadedCSV_For_Existing_Version() {

		if (!driverEngine.mainMethod("VerifyCSVForExistingVersion")) {
			Assert.assertTrue(false);
			Reporter.log("failed to find test sheet");
		}

		// TODO Auto-generated catch block

	}

	@Test(priority = 2)
	// Second case to verify the event API integration
	public void Verify_DownloadedCSV_For_New_version() {

		if (!driverEngine.mainMethod("VerifyCSVForNewVersion")) {

			// TODO Auto-generated catch block

			Assert.assertTrue(false);
			Reporter.log("failed to find the testsheet");
		}

	}

	@Test(priority = 3)
	public void Verify_RealTimeEvent_API_Response_For_NewEvent() {
		if (!driverEngine.mainMethod("VerifyEventAPI")) {

			// TODO Auto-generated catch block
			Assert.assertTrue(false);
			Reporter.log("failed to find the testsheet");
		}

	}

	@Test(priority = 4)
	public void Verify_Deleted_Offer_Is_Not_Present_In_CSV() {
		if (!driverEngine.mainMethod("VerifyDeleteOffer")) {

			// TODO Auto-generated catch block
			Assert.assertTrue(false);
			Reporter.log("failed to find the testsheet");
		}

	}

}
