package regressionTests;

import org.testng.annotations.Test;

import driver.DriverEngine;

public class RegressionCases {

	DriverEngine driverEngine = null;

	@Test
	public void DefineBrainTests() {

		try {
			driverEngine = new DriverEngine();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		driverEngine.mainMethod("CSVChkPostDefineBrain");

	}
}
