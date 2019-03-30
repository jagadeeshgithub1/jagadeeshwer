/**
 * This is the action class , where all the functional methods are defined.
 * While adding a new action method to this library , please provide the documentation to understand the pupose of the method
 * 
 * 
 */
package driver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.opencsv.CSVReader;

import base.TestBaseClass;
import utilities.ExcelUtils;
import utilities.GeneralUtilities;

/**
 * @author deepa
 *
 */
public class ActionClass extends TestBaseClass {

	public static WebDriver driver;

	public ActionClass() throws Exception {

		super();
		// initialization();

		// TODO Auto-generated constructor stub
	}

	public boolean login() {
		/*
		 * @author :Deepa Panikkaveetil
		 * 
		 * @date :3/19/2019
		 * 
		 * @modified by:
		 * 
		 * @modified date:
		 * 
		 * @USEFOR :Method used for login to the website , by taking the parameters from
		 * the Properties file 'Properties'
		 * 
		 * @Parameters:locators of username , Password, and data for username and
		 * password from properties file
		 * 
		 * 
		 * 
		 * 
		 */
		boolean flag = false;

		try {
			driver.findElement(By.xpath(prop.getProperty("txtUserName"))).sendKeys(prop.getProperty("username"));
			driver.findElement(By.xpath(prop.getProperty("txtPassword"))).sendKeys(prop.getProperty("password"));
			driver.findElement(By.xpath(prop.getProperty("btnLogin"))).click();
			flag = true;

		} catch (Exception e) {
			flag = false;
			// TODO: handle exception
		}
		return flag;
	}

	public boolean click(String object) {
		/*
		 * @author :Deepa Panikkaveetil
		 *
		 * 
		 * @date :3/19/2019
		 * 
		 * @modified by:
		 * 
		 * @modified date:
		 * 
		 * @USEFOR :The method is to clik on any web element
		 * 
		 * @Parameters:Passing the xpath locator from the properties file
		 * 
		 * 
		 * 
		 */
		boolean flag = false;
		try {

			boolean ele = new WebDriverWait(driver, 300).until(ExpectedConditions.and(
					ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty(object))),
					ExpectedConditions.elementToBeClickable(By.xpath(prop.getProperty(object)))));
			if (ele == true) {

				WebElement element = driver.findElement(By.xpath(prop.getProperty(object)));

				Actions action = new Actions(driver);
				System.out.println("is this the element???>>" + element);
				action.moveToElement(element).click(element).build().perform();
				// action.moveToElement(element).sendKeys(Keys.RETURN);
				// driver.findElement(By.xpath(prop.getProperty(object))).click();

				flag = true;
			} else {
				System.out.println("run engine is not clicked..");
				flag = false;
			}

		} catch (TimeoutException e) {

			System.out.println("Are you sure the element is present");
			flag = false;
			// TODO: handle exception
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
			// TODO: handle exception
		}

		// driver.findElement(By.xpath(prop.getProperty(object))).click();

		// using action class
		/*
		 * WebElement element = driver.findElement(By.xpath(prop.getProperty(object)));
		 * Actions action = new Actions(driver);
		 * action.moveToElement(element).click().perform();
		 */

		/*
		 * using javascript execurtor WebElement element=
		 * driver.findElement(By.xpath(prop.getProperty(object)));
		 * 
		 * JavascriptExecutor executor = (JavascriptExecutor) driver;
		 * executor.executeScript("arguments[0].click();", element);
		 */

		// TODO: handle exception
		return flag;
	}

	public boolean ToggleButtonClick(String object) {

		boolean flag = false;
		try {
			WebElement ele = new WebDriverWait(driver, 30)
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath(prop.getProperty(object))));
			ele.click();
			flag = true;
		} catch (Exception e) {
			flag = false;
			// TODO: handle exception
		}
		return flag;

	}

	public boolean SwitchNclick(String object) {

		boolean flag = false;

		/*
		 * @author :Deepa Panikkaveetil
		 * 
		 * @date :3/19/2019
		 * 
		 * @modified by:
		 * 
		 * @modified date:
		 * 
		 * @USEFOR :The method is to clik on any web element inside a frame, separated
		 * from the click method , because the 'switch to frame' is doing inside this
		 * method
		 * 
		 * @Parameters:Passing the xpath locator from the properties file
		 * 
		 * 
		 * 
		 */
		driver.switchTo().frame(1);
		// Object ele=object;
		try {
			new WebDriverWait(driver, 300)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(prop.getProperty(object))));
			driver.findElement(By.xpath(prop.getProperty(object))).click();
			flag = true;
		} catch (Exception e) {

			flag = false;
			// TODO: handle exception
		}
		return flag;
	}

	public boolean enterText(String object, String data) {

		boolean flag = false;

		/*
		 * @author:Deepa Panikkaeetil
		 * 
		 * @date:3/20/2019
		 * 
		 * @modified by:
		 * 
		 * @modified date:
		 * 
		 * @USED_FOR:Method used for typing the data in the web element/input text
		 * 
		 * @Parameter:Passing the web element xpath, and the data to be typed
		 */
		try {
			WebElement ele = new WebDriverWait(driver, 20)
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath(prop.getProperty(object))));
			ele.clear();
			ele.sendKeys(data.trim());
			flag = true;
		} catch (Exception e) {
			flag = false;
			// TODO: handle exception
		}

		return flag;
	}

	public boolean SelectListItem(String object, String item) {

		boolean flag = false;

		try {
			WebElement ele = new WebDriverWait(driver, 30)
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath(prop.getProperty(object))));

			Select selectItem = new Select(ele);

			selectItem.selectByVisibleText(item.trim());
			flag = true;
		} catch (Exception e) {
			flag = false;
			// TODO: handle exception
		}

		return flag;

	}

	public boolean openURL() {
		/*
		 * @author:Deepa Panikkaeetil
		 * 
		 * @date:3/20/2019
		 * 
		 * @modified by:
		 * 
		 * @modified date:
		 * 
		 * @USED_FOR:Method used for launching the web url , the url has be specified in
		 * the properties file
		 * 
		 * @Parameter:NA
		 * 
		 * 
		 */
		// initialization();
		boolean flag = false;
		try {
			System.setProperty("webdriver.chrome.driver", "Drivers\\chromedriver.exe");

			// String downloadFilepath = prop.getProperty("DOWNLOADPATH");
			String downloadFilepath = System.getProperty("user.dir") + "\\Downloads";
			HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
			chromePrefs.put("profile.default_content_settings.popups", 0);
			chromePrefs.put("download.default_directory", downloadFilepath);
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", chromePrefs);
			driver = new ChromeDriver(options);

			driver.manage().window().maximize();

			driver.manage().deleteAllCookies();
			driver.get(prop.getProperty("url"));
			flag = true;
		} catch (Exception e) {
			flag = false;
			// TODO: handle exception
		}
		return flag;
	}

	public boolean closeBrowser() {

		boolean flag = false;

		/*
		 * @author:Deepa Panikkaeetil
		 * 
		 * @date:3/20/2019
		 * 
		 * @modified by:
		 * 
		 * @modified date:
		 * 
		 * @USED_FOR:Method used for closing the browser
		 * 
		 * @Parameter:NA
		 * 
		 * 
		 * 
		 */
		try {
			driver.quit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			// TODO: handle exception
		}
		return flag;
	}

	public boolean gettingHandle() {

		/*
		 * @author:Deepa Panikkaeetil
		 * 
		 * @date:3/20/2019
		 * 
		 * @modified by:
		 * 
		 * @modified date:
		 * 
		 * @USED_FOR:Method used for getting the current handle of the browser to switch
		 * the driver instance
		 * 
		 * @Parameter:NA
		 * 
		 * 
		 * 
		 */

		boolean flag = false;
		try {

			Set<String> handles = driver.getWindowHandles();
			String currentHandle = driver.getWindowHandle();

			System.out.println(currentHandle);

			for (String handle : handles) {
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// System.out.println(handle);

				if (!handle.equals(currentHandle)) {
					driver.switchTo().window(handle);
					System.out.println(handle);
				}
			}
			flag = true;
		} catch (Exception e) {

			flag = false;
			// TODO: handle exception
		}
		return flag;
	}

	public boolean sleepTime(long time) {

		boolean flag = true;
		/*
		 * @author:Deepa Panikkaeetil
		 * 
		 * @date:3/20/2019
		 * 
		 * @modified by:
		 * 
		 * @modified date:
		 * 
		 * @USED_FOR:Method used for putting the wait during the run
		 * 
		 * @Parameter:NA
		 * 
		 * 
		 * 
		 */
		try {
			Thread.sleep(time);
			flag = true;
		} catch (InterruptedException e) {
			flag = false;
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	public boolean elementPropertyCheck(String object, String expProperty) {

		/*
		 * @author:Deepa Panikkaveetil
		 * 
		 * @date:3/20/2019
		 * 
		 * @modified by:
		 * 
		 * @modified date:
		 * 
		 * @USED_FOR:Method used for checking the text in the web element
		 * 
		 * @Parameter:Passing the xpath of the web element , and the expected text in
		 * the element
		 * 
		 * 
		 * 
		 */
		boolean flag = false;
		try {
			new WebDriverWait(driver, 300)
					.until(ExpectedConditions.textToBe(By.xpath(prop.getProperty(object)), expProperty.trim()));
			/*
			 * if (driver.findElement(By.xpath(prop.getProperty(object))).getText().equals(
			 * expProperty.trim())) { flag = true;
			 * 
			 * } else { flag = false; }
			 */
			flag = true;
		} catch (TimeoutException e) {
			flag = false;
			System.out.println("Are you sure the element text has changed to??" + expProperty);
			// TODO: handle exception
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
			// TODO: handle exception
		}

		return flag;
	}

	public boolean ValidateBatchDecisionOutputCSV() {

		GeneralUtilities generalUtilities = null;
		String ColumnToBeValidated = null;
		String ValueToBeChecked = null;
		String ExpectedResult = null;
		try {
			generalUtilities = new GeneralUtilities();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ExcelUtils ObjTestdataFile = null;
		try {
			ObjTestdataFile = new ExcelUtils("TestDataAndResults\\SophieAutomation.xlsx");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean flag = false;
		System.out.println("This is the csv validation");

		File file = generalUtilities.getLatestFileFromDir(System.getProperty("user.dir") + "\\Downloads");
		int CsvColumnCount = 0;

		// ObjdownloadFile=new
		// ExcelUtils("Downloads\\BatchDecisionOutput_20190312T091111.537 GMT.xlsx");
		// ObjTestdataFile=new ExcelUtils("TestData\\SophieTestData.xlsx");

		// Getting the column count for the downloaded csv
		try {
			CsvColumnCount = generalUtilities.toGetTheNumberOfFieldsInCSV(file);
			System.out.println(CsvColumnCount);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		CSVReader csvReader = null;
		try {
			csvReader = new CSVReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<String[]> records = null;
		try {
			records = csvReader.readAll();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {

			for (int i = 4; i <= ObjTestdataFile.getRowCount("BatchDecisionOutputValidations"); i++) {
				ColumnToBeValidated = ObjTestdataFile.getCellData("BatchDecisionOutputValidations", i,
						"ColumnToBeValidated");

				ValueToBeChecked = ObjTestdataFile.getCellData("BatchDecisionOutputValidations", i, "ValueToBeChecked");
				ExpectedResult = ObjTestdataFile.getCellData("BatchDecisionOutputValidations", i, "Expected");

				for (int j = 0; j < CsvColumnCount; j++) {

					if (records.get(0)[j].equals(ColumnToBeValidated.trim())) {
						for (int k = 1; k < records.size(); k++) {
							if (records.get(k)[j].equals(ValueToBeChecked.trim())) {
								// ObjTestdataFile.setCellData("BatchDecisionOutput_Test", "Actual", i, "Pass");
								if (ExpectedResult.trim().equalsIgnoreCase("Available")) {
									flag = true;
								} else {
									flag = false;
								}
								break;
							} else {
								// ObjTestdataFile.setCellData("BatchDecisionOutput_Test", "Actual", i, "Fail");
								if (ExpectedResult.trim().equalsIgnoreCase("Not Available")) {
									flag = true;
								} else {
									flag = false;
								}

							}
						}

						break;
					}
				}

				if (flag == true) {
					ObjTestdataFile.setCellData("BatchDecisionOutputValidations", "Actual", i, "Pass");
				} else {
					ObjTestdataFile.setCellData("BatchDecisionOutputValidations", "Actual", i, "Fail");
				}
			}

			return true;
		}

		catch (Exception e) {

			return false;
			// TODO: handle exception
		}

	}

}
