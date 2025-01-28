package me.selenium.POM.pages;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import me.selenium.POM.baseDrivers.PageDriver;
import me.selenium.POM.utilities.CommonMethods;
import me.selenium.POM.utilities.Screenshots;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class ScrollDown extends CommonMethods {

    ExtentTest test;

    public ScrollDown(ExtentTest test){
        PageFactory.initElements(PageDriver.getCurrentDriver(),this);
        this.test = test;
    }

    //locators
    @FindBys({
            @FindBy(xpath = "//ul[@class='pagination']/li[3]/a"),
    })
    WebElement specificLocator;

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

    public void scrollDownToSpecificLocator() throws IOException, InterruptedException {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            test.info("Scroll Down To The Page Of A Specific Locator");

            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", specificLocator);
            Thread.sleep(2000);


            if (specificLocator.isDisplayed()) {
                passCaseWithSC("Successfully scrolled to the specific locator", "Scroll_Success");

                // Click on the locator
                specificLocator.click();
                Thread.sleep(3000);
                passCaseWithSC("Successfully clicked on the specific locator", "Click_Success");
            } else {
                failCase("Locator not visible after scrolling", "Locator_Not_Visible");
            }
        } catch (Exception e) {
            failCase("Failed to scroll to and interact with the specific locator. Error: " + e.getMessage(), "Scroll_Fail");
        }
    }




}
