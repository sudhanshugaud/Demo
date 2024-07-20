package utils;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
//import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.ExtentTest;
import com.google.common.io.Files;

import lombok.Getter;
import lombok.Setter;
import net.bytebuddy.utility.RandomString;

@Getter
@Setter
public class WebUtil {

	private Properties prop;
	private WebDriver driver;  
	public Actions act;
	public Select st;
	public WebUtil wt;

	public WebDriver getDriver() {
		return driver;
	}

	///======================Launch Browser===============================//
	//=========This method use LaunchBrowser ========//



	public void launchBrowser(String browserName) {
		try {
			if(browserName.equalsIgnoreCase("Chrome")) {
				driver=new ChromeDriver();
				driver.manage().window().maximize();
				//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
				System.out.println("Passed.launchBrowser Successfully");
			}else if(browserName.equalsIgnoreCase("firefox")) {
				driver=new FirefoxDriver();
				driver.manage().window().maximize();
				//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
				System.out.println("Passed.Firefox launchBrowser Successfully");
			}else if(browserName.equalsIgnoreCase("edge")) {
				System.setProperty("webdriver.edge.driver","driver//msedgedriver.exe");
				driver=new EdgeDriver();
				driver.manage().window().maximize();
				//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
				System.out.println("Passed.Firefox launchBrowser Successfully");
			}	
		}catch(Exception e) {
			e.printStackTrace();
		}	
	}

	public void takeSnapShot(String testcaseName) {
		TakesScreenshot tss=(TakesScreenshot) driver;
		File snapshotSourceFileObj=tss.getScreenshotAs(OutputType.FILE);
		DateFormat df=new SimpleDateFormat("MM_dd_yyyy hh_mm_ss a");
		String timeStamp=df.format(new Date());
		File snapShotDestinationFileObj=new File("test-Output\\"+testcaseName+timeStamp+".png");
		try {
			Files.copy(snapshotSourceFileObj, snapShotDestinationFileObj);
		} catch (IOException e) {
			e.printStackTrace();
		}
		snapShotDestinationFileObj.getAbsolutePath();
	}


	public void verifyInnerText(WebElement we, String expectedText) {

		String actualText=we.getText();
		if(actualText.equalsIgnoreCase(expectedText)) {
			System.out.println("Passed.actual"+actualText+"  &&  expected"+expectedText);	

		}else {
			System.out.println("failed.actual"+actualText+" &&  expected"+expectedText);	
		}
		Assert.assertEquals(actualText,expectedText);
	}

	public void attributeValue(WebElement we,String attributeName,String expectedAttribute) {
		String actualAttribute=we.getAttribute(attributeName);	
		if(actualAttribute.equalsIgnoreCase(expectedAttribute)) {
			System.out.println("passed.actualAttribute"+actualAttribute+"ExpectedAttribute"+expectedAttribute);
		}else {
			System.out.println("failed.actualAttribute"+actualAttribute+"ExpectedAttribute"+expectedAttribute);
		}
		Assert.assertEquals(actualAttribute,expectedAttribute);
	}

	public void verifyTitle(String expectedTitle) {
		String actualTitle=wt.myGetTitle();
		if(actualTitle.equalsIgnoreCase(expectedTitle)) {
			System.out.println("passed.actual"+actualTitle+"expected"+expectedTitle);
		}else {
			System.out.println("failed.actual"+actualTitle+"expected"+expectedTitle);
		}
		Assert.assertEquals(actualTitle,expectedTitle);
	}

	public void verifyCurrentUrl(String expectedUrl) {
		String actualUrl=wt.getCurrentUrl();
		if(actualUrl.equalsIgnoreCase(expectedUrl)) {
			System.out.println("passed.actual"+actualUrl+"expected"+expectedUrl);
		}else {
			System.out.println("failed.actual"+actualUrl+"expected"+expectedUrl);
		}
		Assert.assertEquals(actualUrl,expectedUrl);
	}

	public void verifyEnabled(WebElement we) {
		String elementName=we.getAccessibleName();
		boolean enable=we.isEnabled();
		if(enable==true) {
			System.out.println("Passed."+elementName+ "is Enabled");	
		}else {
			System.out.println("Failed."+elementName+ "is not Enabled");		
		}
		Assert.assertTrue(enable==true);
	}

	public void verifyDisabled(WebElement we) {
		String elementName=we.getAccessibleName();
		boolean disable=we.isEnabled();
		if(disable==false) {
			System.out.println("Passed."+elementName+"is displed");
		}else {
			System.out.println("Failed."+elementName+"is not displed");
		}
		Assert.assertFalse(disable==false);
	}

	public void verifyDropDownSelectValue(WebElement we, String selectValue) {
		String selectedOption= getSelectedText(we);
		if(selectedOption.equalsIgnoreCase(selectValue)) {
			System.out.println("Passed.DropDownSelectValue is selected");  
		}else {
			System.out.println("Failed.DropDownSelectValue is not selected");    
		}
		Assert.assertEquals(selectedOption,selectValue);

	}

	public void verifyElementVisible(WebElement we) {
		String elementName=we.getAccessibleName();
		boolean  isVisible=we.isDisplayed();
		if(isVisible==true) {
			System.out.println("Passed."+elementName+"is ElementVisible");
		}
		Assert.assertTrue(isVisible==true);
	}

	public  void saveButton() {
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']"));
	}


	///=====================URL Hit =========================================////

	public  void openUrl(String url) {
		driver.get(url);
		System.out.println("Passed. url Open Sccessfully");

	}

	//===================================================================================================//

	public  String myGetTitle() {
		String pagetitle=null;
		try {	
			pagetitle = driver.getTitle();
			System.out.println("Passed."+pagetitle+"titlePage page Successfully");
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("failed."+pagetitle+"failed.titlePage page not Successfully");
		}
		return pagetitle;
	}


	//==================================================================================================//


	public  String getCurrentUrl() {

		String CurrentUrl=null;
		try {
			CurrentUrl=driver.getCurrentUrl();
			System.out.println("Passed.page CurrentUrl Open Successfully ");
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Passed.page CurrentUrl not Open Successfully ");
			throw e;
		}
		return CurrentUrl;
	}

	///=========================FindElement Method============================///


	///==============Sendkeys===========////

	public   void Sendkeys(WebElement webObj, String inputValue) {
		//WebElement webObj= myFindElement(xpath, elementName);
		String elementName=webObj.getAccessibleName();
		try {
			webObj.sendKeys(inputValue);
			System.out.println("Passed. "+inputValue+" value is passed in "+elementName+" textbox successfully");
		}catch(ElementNotInteractableException e) {
			JavascriptExecutor jse=(JavascriptExecutor)driver;
			jse.executeScript("arguments[0].value='"+inputValue+"'",webObj );
			System.out.println("Passed."+inputValue+" value has entered in "+elementName+" textbox successfully");

		}catch(StaleElementReferenceException e) {
			webObj=driver.findElement(By.xpath("//input[@name='"+inputValue+"']"));
			System.out.println("Passed. "+"we have found "+elementName+" successfully");
			webObj.sendKeys(inputValue);
			System.out.println("Passed."+inputValue+" value has entered in "+elementName+" textbox successfully");

		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("failed."+inputValue+" value hasn't entered in "+elementName+" textbox successfully");
			throw e;
		}

	}

	////=========================Click Method===================================////
	public  void Click(WebElement  webObj) {
		String elementName =webObj.getAccessibleName();
		try {
			webObj.click();
			System.out.println("passed."+elementName +" element is clicked successfully");

		}catch(ElementClickInterceptedException e) {
			JavascriptExecutor js=(JavascriptExecutor)driver;
			js.executeScript("arguments[0].click()", webObj);
			System.out.println("passed."+elementName +" element is clicked successfully");

		}catch(ElementNotInteractableException e) {
			JavascriptExecutor js=(JavascriptExecutor)driver;
			js.executeScript("arguments[0].click()", webObj);
			System.out.println("passed."+elementName +" element is clicked successfully");
		}
		catch(StaleElementReferenceException e) {
			webObj.click();
			System.out.println("Passed."+elementName +" element is clicked successfully");
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("failed."+elementName +" element is not clicked successfully");
			throw e;
		}
	}
	public String randomString() {
		RandomString rs=new RandomString();
		String randomName=rs.nextString();
		return randomName;
	}


	public  void myClear(WebElement weClear) {
		String elementName=weClear.getAccessibleName();
		try {
			weClear.clear();
			System.out.println("passed."+elementName+" element is clear successfully");

		}catch(StaleElementReferenceException e) {
			weClear.clear();
			System.out.println("failed."+elementName+" element is clear successfully");

		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("failed."+elementName+" element is not clear successfully");
			throw e;
		}
	}

	//====================================FindElements Method==============================================//

	//	public  String myfindElements(String elemtntName) {
	//		List<WebElement>wefindElements=driver.findElements(By.xpath(xpath));
	//		for(int i=0;i<wefindElements.size();i++) {
	//			WebElement weget =wefindElements.get(i);
	//			weget.click();
	//			String text=weget.getText();
	//			System.out.println(text);
	//		}
	//		return elemtntName;
	//	}

	///======================GetAttribute====================================////

	public  String myGetAttribute(WebElement webObj,String inputValue) {
		String elementName=webObj.getAccessibleName();
		String str;
		try {
			str=webObj.getAttribute(inputValue);
			System.out.println("Passed."+elementName+"inputValue successfully");
		}catch(StaleElementReferenceException e) {

			str=webObj.getAttribute(inputValue);
			System.out.println("Passed."+elementName+"inputValue successfully");
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("failed."+elementName+"inputValue  not successfully");
			throw e;
		}
		return str;
	}


	///==============================GetText======================================////

	public  String myGetText(WebElement webObj, String elementName) {
		String  innerText=webObj.getAccessibleName();
		try {
			innerText=webObj.getText();
			System.out.println("Passed."+innerText+" - innertext of "+elementName+" got successfully");
		}catch(StaleElementReferenceException e) {
			innerText=webObj.getText();
			System.out.println("Passed."+innerText+" - innertext of "+elementName+" got successfully");
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("failed. innertext not found");
			throw e;
		}
		return innerText;
	}

	public  void verifyString(String actualText, String expectedText) {
		if(actualText.equalsIgnoreCase(expectedText)) {
			System.out.println("Passed.actual and expected matched ");
		}else {
			System.out.println("Failed.actual and expected not matched ");
		}
	}


	//// =============================Select Class Method ===================================///

	public  void mySelectByVisible( WebElement webObj,String selectByVisisble) {
		String elementName=webObj.getAccessibleName();
		try {
			st=new Select(webObj);
			st.selectByVisibleText(selectByVisisble);
			System.out.println("Passed."+elementName+" selectByVisisble  Successfully ");
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("failed."+elementName+" not Selected");	
			throw e;
		}
	}

	public  void mySelectByIndex( WebElement webObj,int selectByIndex) {
		String elementName=webObj.getAccessibleName();
		try {
			st=new Select(webObj);
			st.selectByIndex(selectByIndex);
			System.out.println("Passed."+elementName+"selectByIndex  Successfully");
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("failed."+elementName+"selectByIndex  Successfully");
			throw e;
		}

	}
	public  void mySelectByValue(WebElement webObj,String inputValue) {
		String elementName=webObj.getAccessibleName();
		try {
			st=new Select(webObj);
			st.selectByValue(inputValue);
			System.out.println("Passed."+elementName+"selectByValue  Successfully");
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("failed."+elementName+"selectByValue not Successfully");
			throw e;
		}
	}

	public  String getSelectedText(WebElement webObj) {
		String elementName=webObj.getAccessibleName();
		String selectedText=null;
		try {
			st=new Select(webObj);
			WebElement weSelectobj=st.getFirstSelectedOption();
			selectedText=weSelectobj.getText();
			System.out.println("passed."+elementName+"successfully");
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("failed."+elementName+"successfully");
			throw e;
		}
		return selectedText;
	}

	public  int getAllOptionCount(WebElement webObj) {
		String elementName=webObj.getAccessibleName();
		int itemCount=0;
		try {
			st=new Select(webObj);
			List<WebElement> listElement=st.getOptions();
			itemCount=listElement.size();
			System.out.println("Passed."+elementName+"itemcount successfully");
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("failed."+elementName+"itemcount not successfully");
			throw e;
		}
		return itemCount;
	}

	public  List<String> getTextofAllOptions(WebElement webObj,String elementName) {
		List<String> listOptionText=new ArrayList<String>();


		try {

			st=new Select(webObj);
			List<WebElement> weOptionsList=st.getOptions();
			for(int i=0;i<=weOptionsList.size()-1;i++) {
				WebElement weOption=weOptionsList.get(i);
				String OptionText=weOption.getText();
				listOptionText.add(OptionText);
				System.out.println("Passed.");
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("failed.");
		}
		return listOptionText;
	}

	public  void SelectByTextContains(WebElement webObj,String selectText) {
		int indexNumber=-1;

		st=new Select(webObj);
		try {
			List<WebElement> weListOption=st.getOptions();
			for(int i=0;i<=weListOption.size();i++) {
				WebElement weOption=weListOption.get(i);
				String optionText=weOption.getText();
				boolean status=optionText.contains(selectText);
				if(status==true) {
					indexNumber=0;
					System.out.println("Passed");
					break;
				}	
			}
			st.selectByIndex(indexNumber);

		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("failed");
		}
	}

	//// ==============All window close====================///

	public  void myQuit() {
		try {
			driver.quit();
			System.out.println("Passed.window quit successfully");
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}


	///==================Alert Msg==============================/////

	public  Alert myAlertWindow() {
		Alert alt=null;
		try {
			alt=driver.switchTo().alert();
			System.out.println("Passed. ");
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("failed. ");
			throw e;
		}
		return alt;
	}
	public  void myDefaultContent() {
		try {
			driver.switchTo().defaultContent();
			System.out.println("Passed.return to main page");
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("failed.not return to main page");
			throw e;
		}
	}


	public  String myGetAlertText() {
		String altText=null;
		try {
			altText=driver.switchTo().alert().getText();
			System.out.println("Passed"); 

		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("failed");
			throw e;
		}
		return altText;
	}

	////===============================Actions Method =========================================\\\\


	public  void ActionClick(WebElement webObj,String elementName) {

		try {
			act=new Actions(driver);
			act.click(webObj).build().perform();
			System.out.println("Passed");
		}catch(StaleElementReferenceException e) {

			System.out.println("Passed");
		}catch(ElementClickInterceptedException e) {
			System.out.println("failed");
			//javascript
		}
	}

	public  void ActionSendkeys(WebElement webObj,String inputValue) {

		try {
			act=new Actions(driver);
			act.sendKeys(webObj, inputValue).build().perform();
			System.out.println("Passed");
		}catch(StaleElementReferenceException e) {

			act.sendKeys(webObj, inputValue).build().perform();
			System.out.println("Passed");
		}catch(ElementClickInterceptedException e) {
			System.out.println("failed");
			//javascript
		}
	}

	public  void ActionDoubleClick(WebElement webObj,String elementName) {

		try {
			act=new Actions(driver);
			act.doubleClick(webObj).build().perform();
			System.out.println("Passed");
		}catch(StaleElementReferenceException e) {

			act.doubleClick(webObj).build().perform();
			System.out.println("Passed");
		}catch(ElementClickInterceptedException e){
			e.printStackTrace();
			System.out.println("failed");
			throw e;
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("failed");
			throw e;
		}
	}
	public  void ActionRightClick(WebElement webObj,String elementName) {

		try {
			act=new Actions(driver);
			act.contextClick(webObj).build().perform();
			System.out.println("Passed");
		}catch(StaleElementReferenceException e) {
			act.contextClick(webObj).build().perform();	
			System.out.println("Passed");
		}catch(ElementClickInterceptedException e) {
			System.out.println("failed");

		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("failed");
			throw e;
		}

	}
	public  void mouseOver(WebElement webObj,String elementNeme) {


		try {
			act=new Actions(driver);
			act.moveToElement(webObj).build().perform();
			System.out.println("Passed"+elementNeme+"mouseOver Successfully");
		}catch(StaleElementReferenceException e) {

			act.moveToElement(webObj).build().perform();	
			System.out.println("Passed");
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("failed");
			throw e;
		}
	}
	public void myDragAndDrop(WebElement webDrag,WebElement webDrop,String dropElementName) {

		try {
			act=new Actions(driver);
			act.dragAndDrop(webDrag, webDrop).build().perform();
			System.out.println("Passed");
		}catch(StaleElementReferenceException e) {

			act.dragAndDrop(webDrag, webDrop).build().perform();
			System.out.println("Passed");
		}catch(ElementNotInteractableException e) {
			e.printStackTrace();
			System.out.println("failed");
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("failed");
			throw e;
		}

	}
	public  void mySwitchToWindowByTitle(String expectedTitle) {
		Set<String> handles=driver.getWindowHandles();
		for (String handlevalue : handles) {
			driver.switchTo().window(handlevalue);
			String actualTitle=driver.getTitle();
			if(actualTitle.equalsIgnoreCase(expectedTitle)) {
				System.out.println("passed.actualTitle and expected matched");
				break;
			}
		}
	}
	public  void mySwitchToWindowByTitleContains(String expectedTitle) {

		Set<String> handles=driver.getWindowHandles();
		for (String handlevalue : handles) {
			driver.switchTo().window(handlevalue);
			String actualTitle=driver.getTitle();
			if(actualTitle.contains(expectedTitle)) {
				System.out.println("passed.actualTitle and expected matched");
				break;
			}
		}	
	}

	public  void switchTowindowByURL(String expectedURL) {
		Set<String> handles=driver.getWindowHandles();
		for (String handlevalue : handles) {
			driver.switchTo().window(handlevalue);
			String actualURL=driver.getCurrentUrl();
			if(actualURL.equalsIgnoreCase(expectedURL)) {
				System.out.println("passed.actualURL and expectedurl matched");
				break;
			}
		}
	}
	public  void switchTowindowByUrlContains(String expectedURL) {
		Set<String> handles=driver.getWindowHandles();
		for (String handlevalue : handles) {
			driver.switchTo().window(handlevalue);
			String actualURL=driver.getCurrentUrl();
			if(actualURL.contains(expectedURL)) {
				System.out.println("passed.actualURL and expectedurl matched");
				break;

			}

		}
	}


	public  void mySwitchToIframe(WebElement weIframe,String elementName) {
		try {
			driver.switchTo().frame(weIframe);
			System.out.println("Passed.iframe handled successfully");
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("failed.iframe not handled successfully");
			throw e;
		}

	}

	////=============================findElements==========================================////

	public  void checkAllCheckBoxes(String xpath) {
		List<WebElement> weListCheckBoxes =driver.findElements(By.xpath(xpath));
		for(int i=0;i<weListCheckBoxes.size();i++) {
			WebElement weCheckBox=weListCheckBoxes.get(i);
			if(weCheckBox.isSelected()==false) {
				weCheckBox.click();
			}	
		}
	}

	public  void UncheckAllCheckBoxes(String xpath) {
		List<WebElement> weListUnCheckBoxes =driver.findElements(By.xpath(xpath));
		for(int i=0;i<weListUnCheckBoxes.size();i++) {
			WebElement weUnCheckBox=weListUnCheckBoxes.get(i);
			if(weUnCheckBox.isSelected()==true) {
				weUnCheckBox.click();
			}
		}
	}	

	public  int getTableRowCount(String tableXpath, String elementName) {		
		List<WebElement> weRowsList=driver.findElements(By.xpath(tableXpath+"//tr"));
		int rowCount=weRowsList.size()-1;
		return rowCount;
	}

	public  int getTableColumnHeaderCount(String tableXpath, String elementName) {
		List<WebElement> weListColumns=driver.findElements(By.xpath(tableXpath+"//tr[1]//td"));
		int columnCount=weListColumns.size();
		return columnCount;
	}

	/*  in this method we want all the column header names in a list<String>   */

	public  List<String>  getTableColumnNamesList(String tableXpath, String elementName) {
		List<WebElement> weListColumns=driver.findElements(By.xpath(tableXpath+"//tr[1]//td"));
		List<String> listColumnNames=new ArrayList<String>();
		int columnCount=weListColumns.size();
		for(int i=0; i<=columnCount-1;i++) {
			WebElement  weColumnHeader=weListColumns.get(i);
			String columnName=weColumnHeader.getText();
			listColumnNames.add(columnName);
		}
		return listColumnNames;
	}

	/*  this method returns column number on the basis of column name*/
	public  int getColumnNumberByColumnName(String tableXpath, String tableName, String columnName) {
		int columnNumber=-1;
		List<WebElement> listColumnNames=driver.findElements(By.xpath(tableXpath+"//tr[1]//td"));
		int columnCount=listColumnNames.size();
		for(int i=0; i<=columnCount;i++) {
			WebElement weTableColumn=listColumnNames.get(i);
			String tablColumnName=weTableColumn.getText();
			if(tablColumnName.equalsIgnoreCase(columnName)==true) {
				columnNumber=i+1;
				break;
			}
		}

		return columnNumber;
	}

	/* this method returns row data in list on the basis of row number*/

	public  List<String> getRowDataListByRowNumber(String tableXpath, String tableName, int rowNumber) {
		List<WebElement> weListRowData=driver.findElements(By.xpath(tableXpath+"//tr["+(rowNumber+1)+"]//td"));
		List<String> rowDataList=new ArrayList<String>();
		for(int i=0;i<=weListRowData.size()-1;i++) {
			WebElement weRowData=weListRowData.get(i);
			String data=weRowData.getText();
			rowDataList.add(data);
		}
		return rowDataList;
	}

	public  List<String> getColumnDataListByColumnNumber(String tablexpath,String ColumnData,int ColumnNumber ) {
		List<WebElement> weListColumnNumber=driver.findElements(By.xpath(tablexpath+"//tr//td["+ColumnNumber+"]"));
		ArrayList<String> ListColum=new ArrayList<String>();
		for(int i=1;i<=weListColumnNumber.size()-1;i++) {
			WebElement weColumnData=weListColumnNumber.get(i);
			String data=weColumnData.getText();
			ListColum.add(data);
		}
		return ListColum; 
	}

	public   ArrayList<String> getColumnDataListByColumnName(String tablexpath,String tableName,String ColumnName) {
		List<WebElement> weColumnDataListByColumnName = driver.findElements(By.xpath(tablexpath+"//tr[1]//td"));
		ArrayList<String> ListColumnName=new ArrayList<String>();
		for(int i=0;i<=weColumnDataListByColumnName.size()-1;i++) {

			WebElement weObj=weColumnDataListByColumnName.get(i);
			String getName=weObj.getText();

			if(getName.equalsIgnoreCase(ColumnName)){

				List<WebElement> weColumnDataList = driver.findElements(By.xpath(tablexpath+"//tr//td["+(i+1)+"]"));
				for(int j=0;j<weColumnDataList.size();j++) {

					String getName1=weColumnDataList.get(j).getText();
					ListColumnName.add(getName1);
				}
			}
		}
		return ListColumnName;
	}

	public  int getRowNumberByUniqueColumnRowID(String tableXpath, String tableName, String uniqueData, String uniqueColumnName) {
		int rowNumber=-1;
		List<String> columnDataList=getColumnDataListByColumnName(tableXpath, tableName,uniqueColumnName);
		for(int i=0; i<=columnDataList.size()-1;i++) {
			String uniqueColumnData=columnDataList.get(i);
			if(uniqueColumnData.equalsIgnoreCase(uniqueData)) {
				rowNumber=i;
				break;
			}
		}
		return rowNumber;	
	}

	public  List<String> getRowDataListByRowID(String tableXpath, String tableName, String uniqueData, String uniqueColumnName) {
		List<String>listRowData=null;
		try {
			int rowNumber=getRowNumberByUniqueColumnRowID(tableXpath, tableName, uniqueData, uniqueColumnName);
			listRowData=getRowDataListByRowNumber(tableXpath, tableName, rowNumber);
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		return listRowData;
	}
	public  void printAllTableData(String tableXpath) {
		List<WebElement> weAlltableList=driver.findElements(By.xpath(tableXpath+"//tr[1]//td"));
		for(int i=1;i<=weAlltableList.size()-1;i++) {
			String weget=weAlltableList.get(i).getText();
			System.out.println(weget);
			List<WebElement> weAlltableList1=driver.findElements(By.xpath(tableXpath+"//tr//td["+(i+1)+"]"));
			for(int j=1;j<=weAlltableList1.size()-1;j++) {
				String weget1=weAlltableList1.get(j).getText();
				System.out.println(weget1);
			}
		}

	}
	public void loadProperties(String fileName) {
		FileInputStream fis=null;
		try {
			fis=new FileInputStream("src\\main\\resources\\"+fileName+".properties");
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		prop=new Properties();
		try {
			prop.load(fis);
		}catch(IOException e) {
			e.printStackTrace();
		}

	}
	public String getPropertyData(String keyName) {
		String data=prop.getProperty(keyName);
		return data;
	}
}





















