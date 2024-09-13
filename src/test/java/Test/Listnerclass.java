package Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.model.Report;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Listnerclass implements ITestListener {
	public ExtentReporter extent;
	public ExtentSparkReporter spark;
	public ExtentTest test;
	

	@Override
	public void onTestStart(ITestResult result) {
	
		
		SimpleDateFormat sdf= new SimpleDateFormat("yyyymmddhhmmss");
		String format=sdf.format(new Date());
		// create empty reporter
		spark= new ExtentSparkReporter(".\\reports\\Mynewreport_"+format);
		Report report = spark.getReport();
		
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSuccess(result);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
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
		// TODO Auto-generated method stub
		ITestListener.super.onFinish(context);
	}

}
