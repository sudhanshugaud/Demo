package Webutil.Layer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.google.common.io.Files;


public class WebUtil {

	private ExtentReports extReportsObj;
	private ExtentTest extentTestObj;
	private WebDriver driver;
	private Properties propsObj;
	private Workbook workbookObj;
	private Sheet sheetObj;
	private static WebUtil WebUtilobj;


	private WebUtil() {

	}

	public static WebUtil getWebUtilObj() {
		if (WebUtilobj == null) {
			WebUtilobj = new WebUtil();
		}
		return WebUtilobj;
	}


	public WebDriver getDriver() {
		return driver;
	}

	
	
	
	
	
	public Properties readDataFromConfig(String configFilePath) {
		propsObj = new Properties();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(System.getProperty("user.dir") + configFilePath);
		} catch (Exception e) {

		}
		try {
			propsObj.load(fis);
		} catch (Exception e) {

		}

		return propsObj;
	}


	public Properties getProObj() {
		return propsObj;
	}

	public void initExtentReports(String repotFilePath) {

		ExtentSparkReporter ExtHtmlRport = new ExtentSparkReporter(System.getProperty("user.dir") + repotFilePath);
		ExtHtmlRport.config().setDocumentTitle("Selenium Assignment");
		ExtHtmlRport.config().setReportName("Selenium Assignment Automation Report");
		ExtHtmlRport.config().setReportName("Sarvesh");
		ExtHtmlRport.config().setTheme(Theme.STANDARD);
		extReportsObj = new ExtentReports();
		extReportsObj.attachReporter(ExtHtmlRport);
		extReportsObj.setSystemInfo("Company Name", "Tech Carrel Pvt. Ltd.");
		extReportsObj.setSystemInfo("UserName", System.getProperty("user.name"));
		extReportsObj.setSystemInfo("OS", System.getProperty("os.name"));
	}



	public void setMethodName(String testCaseName) {
		extentTestObj = extReportsObj.createTest(testCaseName);
	}


	public void flushReport() {
		extReportsObj.flush();
	}

	public ExtentTest getExtentObj() {
		return extentTestObj;
	}

	public void openBroser(String browserName) {

		if (browserName.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();			
		} else if (browserName.equalsIgnoreCase("firefox")) {	
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("ie")) {
			driver = new InternetExplorerDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}



	public void openUrl(String url) {
		driver.get(url);
		System.out.println(" This URL == " + url + " is open successfully on browser");
	}



	public void closeBrowser() {
		driver.close();
		extentTestObj.log(Status.INFO, "Browser is closed successfully");
	}


	public void quitBrowser() {
		driver.quit();
		extentTestObj.log(Status.INFO, "Browser is quit successfully");
	}


	public void sendKeys(WebElement Webelement, String inputValue, String elementName) {		
		Webelement.sendKeys(inputValue);
		extentTestObj.log(Status.INFO, inputValue + " is entered successfully on the " + elementName + " input box ");		
	}


	public void click(WebElement webEle, String elemtName) {
		webEle.click();
		extentTestObj.log(Status.INFO, elemtName + " is clicked successfully  ");

	}



	public void clear(WebElement webEle, String elemtName) {
		webEle.clear();
		extentTestObj.log(Status.INFO,elemtName + " is clear successfully  ");
	}

	public String getTextFromElemet(WebElement webEle) {
		String actualtext =	webEle.getText();
		return actualtext;
	}

	public String getTextFromAttributeElement(WebElement webEle, String attribvalue) {
		String actualtext =	webEle.getAttribute(attribvalue);
		return actualtext;
	}

	public void verifyText(WebElement element, String expectedText) {
		String actualText = element.getText();
		if (actualText.equalsIgnoreCase(expectedText)) {
			extentTestObj.log(Status.PASS,String.format(" Where actual inner text value is (" + actualText
					+ ") & expected inner text value is (" + expectedText + ")"));
		} else {
			extentTestObj.log(Status.FAIL,String.format(" Where actual text value is (" + actualText
					+ ") & expected text value is (" + expectedText + ")"));
		}
		try {
			Assert.assertEquals(actualText, expectedText);
		}catch (Throwable t) {
			//extentTestObj.log(Status.FAIL, t);
		}
	}

	public void verifyText(String actualText, String expectedText) {
		if (actualText.equalsIgnoreCase(expectedText)) {
			extentTestObj
			.log(Status.PASS,
					
							String.format("Test case is passed! Where actual text value is (" + actualText
									+ ") & expected text value is (" + expectedText + ")")
							);

		} else {
			extentTestObj
			.log(Status.FAIL,
					
							String.format("Test case is failled! Where actual text value is (" + actualText
									+ ") & expected text value is (" + expectedText + ")")
							);
		}
		try {
			Assert.assertEquals(actualText, expectedText);
		}catch (Throwable t) {
			//extentTestObj.log(Status.FAIL, t);
		}
	}


	public String Snapshort(String snapshortname) {
		TakesScreenshot scrtsho=(TakesScreenshot) driver;
		File soursefile=scrtsho.getScreenshotAs(OutputType.FILE);
		String Time=getCurDateTime();
		File distinationFile = new File("Rapifuzz_Automation_ExtentReprts//"+snapshortname+Time+".jpg");
		try {
			Files.copy(soursefile, distinationFile);
		}catch(IOException e) {

		}
		return distinationFile.getAbsolutePath();
	}



	public void attachSnapShortCaptureReport(String imgPath) {
		try {
			extentTestObj.addScreenCaptureFromPath(imgPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public String getCurDateTime(){
		SimpleDateFormat sft=new SimpleDateFormat("dd-MM-yyyy hh_mm_ss");
		String Time=sft.format(new Date());
		return Time;
	}

	public void sleepWait(int count) {
		try {
			Thread.sleep(count*100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}




	public String readOneDataFromExcel(String filepath, String sheetName, int Row, int Cell) {

		File file = new File(filepath);
		XSSFWorkbook workbook = null;
		try {
			workbook = new XSSFWorkbook(file);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		XSSFSheet sheet = workbook.getSheet(sheetName);
		XSSFRow row = sheet.getRow(Row);
		XSSFCell cell = row.getCell(Cell,MissingCellPolicy.CREATE_NULL_AS_BLANK);
		String value = cell.getStringCellValue();
		try {
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return value;
	}


	public void writeOneDataFromExcel(String filePath,String sheetName,String value,int rowNum,int cellNum) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(filePath);
		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
		}
		XSSFWorkbook workbook = null;
		try {
			workbook = new XSSFWorkbook(fis);
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		XSSFSheet sheet;
		XSSFRow row = null;
		if (workbook.getSheet(sheetName) == null) {
			sheet = workbook.createSheet(sheetName);
		} else {
			sheet = workbook.getSheet(sheetName);
		}
		row = sheet.getRow(rowNum);
		if(row==null)
		{ row=sheet.createRow(rowNum);
		}
		XSSFCell cell = null;
		cell = row.createCell(cellNum);
		cell.setCellValue(value);
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(filePath);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			workbook.write(fos);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public int getTotalRowNumber(Sheet sheetobj,String testcaseIdName) {
		int activateRowNum=sheetobj.getLastRowNum();
		int count=0;
		for (int i = 1; i <=activateRowNum; i++) {
			Row rowObj=sheetobj.getRow(i);
			Cell cellObj=rowObj.getCell(0,MissingCellPolicy.CREATE_NULL_AS_BLANK);
			String actualCellvalue=cellObj.getStringCellValue();
			if (actualCellvalue.contains(testcaseIdName)) {
				count++;
			}
		}
		return count;

	}


	public  Object[][] readDynamicDataFromExcel(String filepath, String sheetName, String expTestCaseId) {
		File file = new File(System.getProperty("user.dir") + filepath);
		FileInputStream fis = null;		
		try {
			fis = new FileInputStream(file);
			workbookObj = new XSSFWorkbook(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheetObj = workbookObj.getSheet(sheetName);
		Object[][] arrObj = new Object[getTotalRowNumber(sheetObj, expTestCaseId)][1];
		int rowcount = sheetObj.getLastRowNum();
		Row keyRow = sheetObj.getRow(0);
		int dataCount = 0;
		for (int i = 1; i <= rowcount; i++) {
			Row rowObj = sheetObj.getRow(i);
			Cell xcellObj = rowObj.getCell(0, MissingCellPolicy.CREATE_NULL_AS_BLANK);
			int cellCount = rowObj.getLastCellNum();
			String actualTcId = xcellObj.getStringCellValue();
			if (actualTcId.contains(expTestCaseId)) {
				Map<String, String> dataMap = new HashMap<String, String>();
				for (int j = 0; j < cellCount; j++) {
					Cell cellDataName = keyRow.getCell(j, MissingCellPolicy.CREATE_NULL_AS_BLANK);
					Cell cellDataValue = rowObj.getCell(j, MissingCellPolicy.CREATE_NULL_AS_BLANK);
					String dataKey = cellDataName.getStringCellValue();
					String dataValue = cellDataValue.getStringCellValue();
					dataMap.put(dataKey, dataValue);
				}
				arrObj[dataCount++][0] = dataMap;
			}

		}
		try {
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			workbookObj.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return arrObj;
	}



}
