package me.selenium.POM.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import me.selenium.POM.baseDrivers.PageDriver;
import me.selenium.POM.pages.HomePage;
import me.selenium.POM.pages.ScrollDown;
import me.selenium.POM.utilities.CommonMethods;
import me.selenium.POM.utilities.ExtentFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class ScrollDown_Test extends CommonMethods {

    ExtentReports extentReports;
    ExtentTest parentTest;
    ExtentTest childTest;


    public void sleep() throws InterruptedException {
        Thread.sleep(5000);
    }

    @BeforeClass
    public void openURL() throws InterruptedException {
        extentReports = ExtentFactory.getInstance();
        parentTest = extentReports.createTest("<p style = 'color:green; font-size:14px'> <b> Prothoma.com </b> </p>").assignAuthor("Dabarjun Mazumdar").assignDevice("Windows");
    }
    @Test
    public void scrollDownToLocator() throws IOException, InterruptedException {
        childTest = parentTest.createNode("<p style = 'color:green; font-size:14px'> <b>Scroll Down To Specific Locator</b> </p>");
        ScrollDown scrollDown = new ScrollDown(childTest);
        scrollDown.scrollDownToSpecificLocator();
    }

    @AfterClass
    public void report(){
        extentReports.flush();
    }

}
