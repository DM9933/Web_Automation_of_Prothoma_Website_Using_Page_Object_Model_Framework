package me.selenium.POM.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import me.selenium.POM.utilities.CommonMethods;
import me.selenium.POM.utilities.ExtentFactory;
import me.selenium.POM.pages.FilterByCategory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class FilterByCategory_Test extends CommonMethods {

    ExtentReports extentReports;
    ExtentTest parentTest;
    ExtentTest childTest;

    @BeforeClass
    public void openURL() {
        extentReports = ExtentFactory.getInstance();
        parentTest = extentReports.createTest("<p style = 'color:green; font-size:14px'> <b> Filter Books by Category </b> </p>")
                .assignAuthor("Debarjun Mazumdar")
                .assignDevice("Windows");
    }

    @Test
    public void filterInIframe() throws InterruptedException, IOException {
        childTest = parentTest.createNode("<p style = 'color:green; font-size:14px'> <b>Filter by 'কথাসাহিত্য'</b> </p>");
        FilterByCategory filterByCategory = new FilterByCategory(childTest);
        filterByCategory.filterByCategoryCheckbox();
    }

    @AfterClass
    public void report() {
        extentReports.flush();
    }
}
