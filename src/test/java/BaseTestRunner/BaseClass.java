package BaseTestRunner;

import java.lang.reflect.Method;
import java.util.Properties;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import Webutil.Layer.WebUtil;


public class BaseClass {


	protected WebUtil util = WebUtil.getWebUtilObj();
	protected Properties propObj; 

	@BeforeSuite
	public void initExtentReports() {
		util.initExtentReports("/Report/result.html");
		propObj=util.readDataFromConfig("/src/main/resources/config.properties");
	}


	@BeforeClass
	public void openUrl() {
		util.openBroser(propObj.getProperty("browserName"));
		util.openUrl(propObj.getProperty("Url"));
	}

	@BeforeMethod
	public void methodName(Method name) {
		util.setMethodName(name.getName());
	}

	@AfterMethod
	public void saveFailureScreenShot(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		if (ITestResult.FAILURE==result.getStatus()) {
			util.getExtentObj()
			.fail("Test Case " + methodName + " Status is FAILED:");
			String image= util.Snapshort(methodName);
			util.attachSnapShortCaptureReport(image);
		}
		util.flushReport();
	}


	@AfterClass
	public void closeBrowser() {
		//util.closeBrowser();
	}


}
