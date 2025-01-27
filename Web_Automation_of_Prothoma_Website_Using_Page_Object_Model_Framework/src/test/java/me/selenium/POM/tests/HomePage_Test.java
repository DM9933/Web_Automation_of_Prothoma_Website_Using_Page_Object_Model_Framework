package me.selenium.POM.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import me.selenium.POM.baseDrivers.PageDriver;
import me.selenium.POM.pages.HomePage;
import me.selenium.POM.utilities.CommonMethods;
import me.selenium.POM.utilities.ExtentFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class HomePage_Test extends CommonMethods {

    ExtentReports extentReports;
    ExtentTest parentTest;
    ExtentTest childTest;


    public void sleep() throws InterruptedException {
        Thread.sleep(5000);
    }

    @BeforeClass
    public void openURL() throws InterruptedException {
        PageDriver.getCurrentDriver().get(url);
        sleep();

        extentReports = ExtentFactory.getInstance();
        parentTest = extentReports.createTest("<p style = 'color:green; font-size:14px'> <b>Prothoma.com</b> </p>").assignAuthor("Dabarjun Mazumdar").assignDevice("Windows");
    }
    @Test
    public void prothoma_HomePage() throws IOException, InterruptedException {
        childTest = parentTest.createNode("<p style = 'color:green; font-size:14px'> <b>HomePage</b> </p>");
        HomePage homePage = new HomePage(childTest);
        homePage.select_writer();
    }

    @AfterClass
    public void report(){
        extentReports.flush();
    }

}
