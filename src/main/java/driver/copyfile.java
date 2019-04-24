package driver;

import java.io.File;
import java.io.IOException;

import utilities.ExcelUtils;
import utilities.GeneralUtilities;

public class copyfile {

	public copyfile() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws IOException {

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

		// File file =
		// generalUtilities.getLatestFileFromDir(System.getProperty("user.dir") +
		// "\\Downloads");
		File file = generalUtilities.getLatestFileFromDir("Downloads", "BatchDecisionOutput");
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

		// CSVReader csvReader = null;
		// try {
		// csvReader = new CSVReader(new FileReader(file));
		// } catch (FileNotFoundException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		// List<String[]> records = null;
		// try {
		// records = csvReader.readAll();
		// } catch (IOException e) {
		//
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		try {

			// here should do the code to move the file to Archive

			File dest = new File("Downloads\\Archive\\");

			if (generalUtilities.CopyFile(file, dest)) {
				System.out.println("File moved to archive");
			} else {
				System.err.println("File move failed");
			}

			// return true;
		}

		catch (Exception e) {

			// return false;
			// TODO: handle exception
		}

		// TODO Auto-generated method stub

	}

}
