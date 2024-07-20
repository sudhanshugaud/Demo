package testscript;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import pages.CommonPage;
import utils.WebUtil;

public class BaseTest {

	WebUtil wt= new WebUtil(); 
	CommonPage cp=new CommonPage(wt);
	private ExtentReports extReport;
	ExtentTest extText;

	@BeforeSuite
	public void beforeSuite() {
		wt.loadProperties("config");
		ExtentSparkReporter spark=new ExtentSparkReporter(System.getProperty("user.dir")+"/Report/Result.html");
		extReport=new ExtentReports();
		extReport.attachReporter(spark);

	}

	@BeforeClass
	public void BeforeClass() {
		String brName=wt.getPropertyData("browserName");
		String url=wt.getPropertyData("url");
		wt.launchBrowser(brName);
		wt.openUrl(url);
	}

	@BeforeMethod
	public void beforeMethod(Method methname) {
		extText= extReport.createTest(methname.getName());

	}

	@AfterMethod
	public void afterMethod(ITestResult result) {
		if(result.getStatus()==result.FAILURE) {
			wt.takeSnapShot(result.getName());
			extText.log(Status.FAIL, "method is failed");
		}
	//	cp.logOutbutton();
		extReport.flush();
		System.out.println("logOut successfully");
	}	

}


