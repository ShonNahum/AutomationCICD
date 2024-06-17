package shonautomations.TestComponents;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import shonautomations.resources.ExtentReporterNG;

public class Listeners implements ITestListener{
	ExtentTest test;
	ExtentReports extent = ExtentReporterNG.GetReportObject();
	
	ThreadLocal<ExtentTest> extentParallelTest = new ThreadLocal<ExtentTest>(); // this class for make the object thread safe = no each object create has his own thread, wont interapt overriding variable while running tests parallel
	
	
	@Override
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
		extentParallelTest.set(test);// will give every test unique thread id (so it can used parallel)
		}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, "GG its passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		extentParallelTest.get().fail(result.getThrowable());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}	

}
