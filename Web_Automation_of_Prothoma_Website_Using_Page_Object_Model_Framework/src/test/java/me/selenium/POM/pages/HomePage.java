package me.selenium.POM.pages;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import me.selenium.POM.baseDrivers.PageDriver;
import me.selenium.POM.utilities.CommonMethods;
import me.selenium.POM.utilities.Screenshots;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import javax.swing.*;
import java.io.IOException;

public class HomePage extends CommonMethods {

    ExtentTest test;

    public HomePage(ExtentTest test){
        PageFactory.initElements(PageDriver.getCurrentDriver(),this);
        this.test = test;
    }

    //locators
    @FindBys({
            @FindBy(xpath = "//li[@id='writer']/a[1]"),
    })
    WebElement writer;

    @FindBys({
            @FindBy(xpath = "//div[@class='col-xs-12']/ul/li[2]/ul/li[8]/a"),
    })
    WebElement writer_name;

    @FindBys({
            @FindBy(xpath = "//button[@type = 'submit']")
    })
    WebElement login_button;

    public void passCase(String message){
        test.pass("<b><p style = 'color:green; font-size:14px'>" + message + "</p></b>");
    }

    public void passCaseWithSC(String message, String screenshotName) throws IOException {
        test.pass("<b><p style = 'color:green; font-size:14px'>" + message + "</p></b>");
        String screenshotPath = Screenshots.capture(PageDriver.getCurrentDriver(),""+ screenshotName +"");
        String dest = System.getProperty("user.dir") + "\\screenshots\\" +""+ screenshotName + ".png";
        test.info(dest);
        test.pass(MediaEntityBuilder.createScreenCaptureFromPath(dest).build());
    }

    public void failCase(String message, String screenshotName) throws IOException {
        test.fail("<b><p style = 'color:red; font-size:14px'>" + message + "</p></b>");

        Throwable t = new InterruptedException("Exception");
        test.fail(t);

        String screenshotPath = Screenshots.capture(PageDriver.getCurrentDriver(),""+ screenshotName +"");
        String dest = System.getProperty("user.dir") + "\\screenshots\\" +""+ screenshotName + ".png";

        test.fail(MediaEntityBuilder.createScreenCaptureFromPath(dest).build());
        PageDriver.getCurrentDriver().quit();
    }

    public void select_writer() throws IOException, InterruptedException {
        test.info("Selecting Writer");

        try {
            // Hover over the writer element
            Actions action = new Actions(PageDriver.getCurrentDriver());
            action.moveToElement(writer).perform();
            passCaseWithSC("You Have Successfully Hovered Over Writer", "Hover_Writer_Success");
            Thread.sleep(5000);
        } catch (Exception e) {
            String screenshotPath = Screenshots.capture(PageDriver.getCurrentDriver(), "Hover_Writer_Fail");
            failCase("Failed to hover over writer element. Please check the log.", "Hover_Writer_Fail");
        }

        try {
            // Click on the writer name
            test.info("Clicking on Writer Name");
            writer_name.click();
            passCaseWithSC("You Have Successfully Selected Writer", "Select_Writer_Success");
            Thread.sleep(5000);
        } catch (Exception e) {
            String screenshotPath = Screenshots.capture(PageDriver.getCurrentDriver(), "Select_Writer_Fail");
            failCase("Failed to select writer name. Please check the log.", "Select_Writer_Fail");
        }
    }



}
