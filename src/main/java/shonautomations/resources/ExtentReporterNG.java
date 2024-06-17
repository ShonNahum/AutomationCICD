package shonautomations.resources;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	public static ExtentReports GetReportObject() {
		
	//2 classes for generate reports , ExtentReports, ExtentSparkReporter
		String path = System.getProperty("user.dir")+"//reports/index.html"; // getting project path untill extendreports
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web automations result");
		reporter.config().setDocumentTitle("test results");
		ExtentReports extent = new ExtentReports();	
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "shon");
		extent.createTest(path);
		return extent;

	}
}
