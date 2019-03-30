/**
 * 
 */
package driver;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import base.TestBaseClass;
import utilities.ExcelUtils;

/**
 * @author
 *
 */
public class DriverEngine extends TestBaseClass {

	public static Method method[];
	public static String ActionKeyWord = null;
	public static String PageObject = null;
	public static String TestData = null;
	static ActionClass classAction = null;
	// public ExcelUtils excelUtils=new
	// ExcelUtils("TestDataAndResults\\SophieAutomation.xlsx");

	public DriverEngine() throws Exception {

		// method=actionClass.getClass().getMethods();
		// TODO Auto-generated constructor stub

	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		int iTotalCases = 0;
		String RunMode = null;
		String TestCaseID = null;
		int iTestStart = 0;
		int iTestEnd = 0;
		// ExcelUtils excelUtils = null;

		// actionClass classAction = null;
		/*
		 * try { classAction = new actionClass(); } catch (Exception e1) { // TODO
		 * Auto-generated catch block e1.printStackTrace(); }
		 */

		/*
		 * try { excelUtils = new
		 * ExcelUtils("TestDataAndResults\\SophieAutomation.xlsx"); } catch (Exception
		 * e) { // TODO Auto-generated catch block e.printStackTrace(); }
		 */
		ExcelUtils excelUtils = null;

		try {
			excelUtils = new ExcelUtils("TestDataAndResults\\SophieAutomation.xlsx");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// iTotalCases = excelUtils.getTotalScenarios("DriverSheet", 4, "TestCaseID");
		iTotalCases = excelUtils.getRowCount("DriverSheet");
		System.out.println("Total regression cases >>" + iTotalCases);
		for (int IdriverRow = 4; IdriverRow <= iTotalCases; IdriverRow++) {

			RunMode = excelUtils.getCellData("DriverSheet", IdriverRow, "RunMode");
			TestCaseID = excelUtils.getCellData("DriverSheet", IdriverRow, "TestCaseID");
			System.out.println("Run Mode>>" + RunMode + "Case id>>" + TestCaseID);
			if (RunMode.equalsIgnoreCase("Y")) {
				try {
					iTestStart = excelUtils.getRowContains(TestCaseID, "TestCaseID", "RegressionCases");
					System.out.println("Start>>" + iTestStart);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					// excelUtils.setCellData("DriverSheet", "Results", IdriverRow, "Fail");
					System.out.println("Test start is not getting>>");
					e.printStackTrace();
				}

				try {
					iTestEnd = excelUtils.getTestStepsCount("RegressionCases", TestCaseID, iTestStart, "TestCaseID");
					System.out.println("TestEnd>>" + iTestEnd);
				} catch (Exception e1) {
					System.out.println("Test end is not getting>>");
					e1.printStackTrace();
					// TODO Auto-generated catch block
					// excelUtils.setCellData("DriverSheet", "Results", IdriverRow, "Fail");
				}

				// for (int Irow = 4; Irow <= excelUtils.getRowCount("RegressionCases"); Irow++)
				// {
				for (int Irow = iTestStart; Irow < iTestEnd; Irow++) {

					if (Irow == 77) {
						System.out.println("start debugging");
					}
					try {
						classAction = new ActionClass();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					System.out.println("data sheet reading >>" + Irow);
					ActionKeyWord = excelUtils.getCellData("RegressionCases", Irow, "ActionKeyword");
					PageObject = excelUtils.getCellData("RegressionCases", Irow, "PageObject");
					TestData = excelUtils.getCellData("RegressionCases", Irow, "TestData");

					// executeReflectionActions(PageObject,ActionKeyWord,TestData);
					// executeReflectionActions();

					switch (ActionKeyWord) {

					case "openURL":
						if (classAction.openURL()) {
							excelUtils.setCellData("RegressionCases", "Results", Irow, "Pass");
						} else {
							excelUtils.setCellData("RegressionCases", "Results", Irow, "Fail");
						}
						break;
					case "login":
						if (classAction.login()) {
							excelUtils.setCellData("RegressionCases", "Results", Irow, "Pass");
						} else {
							excelUtils.setCellData("RegressionCases", "Results", Irow, "Fail");
						}
						break;
					case "enterText":
						if (classAction.enterText(PageObject, TestData)) {
							excelUtils.setCellData("RegressionCases", "Results", Irow, "Pass");
						} else {
							excelUtils.setCellData("RegressionCases", "Results", Irow, "Fail");
						}
						break;
					case "click":
						if (classAction.click(PageObject)) {
							excelUtils.setCellData("RegressionCases", "Results", Irow, "Pass");
						} else {
							excelUtils.setCellData("RegressionCases", "Results", Irow, "Fail");
						}
						break;
					case "SwitchNclick":
						if (classAction.SwitchNclick(PageObject))

						{
							excelUtils.setCellData("RegressionCases", "Results", Irow, "Pass");
						} else {
							excelUtils.setCellData("RegressionCases", "Results", Irow, "Fail");
						}
						break;
					case "ToggleButtonClick":
						if (classAction.ToggleButtonClick(PageObject))

						{
							excelUtils.setCellData("RegressionCases", "Results", Irow, "Pass");
						} else {
							excelUtils.setCellData("RegressionCases", "Results", Irow, "Fail");
						}
						break;
					case "SelectListItem":
						if (classAction.SelectListItem(PageObject, TestData))

						{
							excelUtils.setCellData("RegressionCases", "Results", Irow, "Pass");
						} else {
							excelUtils.setCellData("RegressionCases", "Results", Irow, "Fail");
						}
						break;

					case "sleepTime":

						if (classAction.sleepTime((long) (Float.parseFloat(TestData.trim())))) {
							excelUtils.setCellData("RegressionCases", "Results", Irow, "Pass");
						} else {
							excelUtils.setCellData("RegressionCases", "Results", Irow, "Fail");
						}
						break;
					case "gettingHandle":

						if (classAction.gettingHandle()) {
							excelUtils.setCellData("RegressionCases", "Results", Irow, "Pass");
						} else {
							excelUtils.setCellData("RegressionCases", "Results", Irow, "Fail");
						}
						break;
					case "elementPropertyCheck":
						if (classAction.elementPropertyCheck(PageObject, TestData)) {
							excelUtils.setCellData("RegressionCases", "Results", Irow, "Pass");
						} else {
							excelUtils.setCellData("RegressionCases", "Results", Irow, "Fail");
						}

						break;
					case "VerifyEngineStatus":
						if (classAction.elementPropertyCheck(PageObject, TestData)) {
							excelUtils.setCellData("RegressionCases", "Results", Irow, "Pass");
						} else {
							excelUtils.setCellData("RegressionCases", "Results", Irow, "Fail");
						}
						break;
					case "ValidateBatchDecisionOutputCSV":
						if (classAction.ValidateBatchDecisionOutputCSV()) {

							excelUtils.setCellData("RegressionCases", "Results", Irow, "Pass");

						} else {
							excelUtils.setCellData("RegressionCases", "Results", Irow, "Fail");
						}

						break;
					case "closeBrowser":
						if (classAction.closeBrowser()) {

							excelUtils.setCellData("RegressionCases", "Results", Irow, "Pass");

						} else {
							excelUtils.setCellData("RegressionCases", "Results", Irow, "Fail");
						}

						break;
					default:
						break;
					}
				}
			}
		}
	}

	public static void executeReflectionActions() {
		// This is a loop which will run for the number of actions in the Action Keyword
		// class
		// method variable contain all the method and method.length returns the total
		// number of methods
		for (int i = 0; i < method.length; i++) {
			// This is now comparing the method name with the ActionKeyword value got from
			// excel
			if (method[i].getName().equals(ActionKeyWord)) {
				// In case of match found, it will execute the matched method
				try {
					method[i].invoke(ActionKeyWord, PageObject, TestData);
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// Once any method is executed, this break statement will take the flow outside
				// of for loop
				break;
			}
		}

	}

}
