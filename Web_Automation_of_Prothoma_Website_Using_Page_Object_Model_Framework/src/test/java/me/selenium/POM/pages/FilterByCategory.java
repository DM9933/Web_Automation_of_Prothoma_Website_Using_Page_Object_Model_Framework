package me.selenium.POM.pages;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import me.selenium.POM.baseDrivers.PageDriver;
import me.selenium.POM.utilities.CommonMethods;
import me.selenium.POM.utilities.Screenshots;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class FilterByCategory extends CommonMethods {

    ExtentTest test;

    public FilterByCategory(ExtentTest test) {
        PageFactory.initElements(PageDriver.getCurrentDriver(), this);
        this.test = test;
    }



    @FindBys({
            @FindBy(xpath = "//input[@id='category2']"),
    })
    WebElement categoryCheckbox;


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

    public void filterByCategoryCheckbox() throws InterruptedException, IOException {
        try {

            waitForElementToBeClickable(categoryCheckbox);


            scrollToElement(categoryCheckbox);


            categoryCheckbox.click();
            passCase("Clicked on 'কথাসাহিত্য' checkbox successfully!");


            waitForSeconds(5000);


            if (categoryCheckbox.isSelected()) {
                passCaseWithSC("Checkbox successfully selected!", "Checkbox_Selected");
            } else {
                failCase("Checkbox selection failed!", "Checkbox_Failed");
            }

        } catch (Exception e) {
            failCase("Error occurred while filtering by category: " + e.getMessage(), "Category_Filter_Error");
        }
    }
}

