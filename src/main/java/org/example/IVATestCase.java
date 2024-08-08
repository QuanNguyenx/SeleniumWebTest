package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class IVATestCase {
    public WebDriver driver;

    @BeforeSuite
    public void configAndGetWebDriver() {
//        System.setProperty("webdriver.gecko.driver", "/snap/bin/geckodriver");
//        this.driver = new FirefoxDriver();
//        this.driver.manage().window().maximize();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
    }

    @AfterSuite
    public void closeDriver() {
        this.driver.quit();
    }

    @BeforeSuite
    public void loginPage() {
        WebDriver driver = this.driver;
        String ivaHomeUrl = "https://ivacloud.viettel.io/";
        driver.get(ivaHomeUrl);

        String ivaHomeUserName = "quannh68";
        String ivaHomePass = "VhtRD1234@q";

        WebElement loginNameElement = driver.findElement(By.id("loginName"));
        WebElement passwordElement = driver.findElement(By.id("password"));

        loginNameElement.clear();
        passwordElement.clear();

        loginNameElement.sendKeys(ivaHomeUserName);
        passwordElement.sendKeys(ivaHomePass);

        WebElement button = driver.findElement((By.xpath("//button[text()='Login']")));
        button.click();
    }

    @Test
    public void verifyVersion() {
        WebDriver driver = this.driver;
        String versionUrl = "https://ivacloud.viettel.io/version";
        driver.get(versionUrl);

        String rightVersion = "1.1.6.8";
        int versionRow = 3;
        int versionCol = 2;
        String versionPath = String.format("//table/tbody/tr[%d]/td[%d]", versionRow, versionCol);
        WebElement thirdCell = driver.findElement(By.xpath(versionPath));
        String version = thirdCell.getText();
        System.out.println("version: " + version);
        Assert.assertEquals(version, rightVersion);
    }

    @Test
    public void verifyVideoStream() {
        try {
            WebDriver driver = this.driver;
            String liveViewUrl = "https://ivacloud.viettel.io/liveView";
            driver.get(liveViewUrl);
            WebElement box = driver.findElement(By.xpath("//input[@placeholder='Select group']"));
            // Use JavaScript to click the element
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", box);

            By byMethod = By.className("MuiFormControlLabel-root");
            waitElementPresent(driver, byMethod, 2);

            WebElement checkbox = driver.findElement(By.className("MuiFormControlLabel-root"));
            checkbox.click();
            WebElement searchCamBox = driver.findElement(By.xpath("//input[@placeholder='Select cameras']"));
            searchCamBox.click();

            WebElement cameraCheckbox = driver.findElement(By.xpath("(//div[@role='button'])[3]"));
            cameraCheckbox.click();

            By videoByMethod = By.id("666f0a20add011eeab13baf7db86dba7");
            waitElementPresent(driver, videoByMethod, 2);
            WebElement videoElement = driver.findElement(videoByMethod);

            String initTimeStr = videoElement.getAttribute("currentTime");
            double initTime = Double.parseDouble(initTimeStr);
            int minutes = 1;
            int seconds = 15;
            Thread.sleep(seconds * 1000);

            String currTimeStr = videoElement.getAttribute("currentTime");
            double currTime = Double.parseDouble(currTimeStr);

            System.out.println("currTime: " + currTime + " - " + "initTime: " + initTime);
            Assert.assertTrue(currTime > initTime, "The video is NOT playing");
        } catch (Exception e) {
            Assert.assertEquals(true, false);
            System.out.println(e.toString());
        }

    }

    @Test
    public void verifyHistoryEvents() {
        WebDriver driver = this.driver;
        String historyEventUrl = "https://ivacloud.viettel.io/historyEvent";
        driver.get(historyEventUrl);

        WebElement table = driver.findElement(By.className("MuiTable-root"));
        waitElement(driver, table, 2);

        WebElement tbody = table.findElement(By.tagName("tbody"));
        waitElement(driver, tbody, 10);
        List<WebElement> tableRows = tbody.findElements(By.tagName("tr"));
        int count = 0;
        for (WebElement row : tableRows) {
            waitElement(driver, row, 1);
            System.out.println(row.getText());
            count++;
        }
        System.out.println("count: " + count);
//        compare count vs 0
        Assert.assertTrue(count > 1, "The table has 0 row");
    }

    public void waitElement(WebDriver driver, WebElement element, int seconds) {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(d -> element.isDisplayed());
    }

    public void waitElementPresent(WebDriver driver, By byMethod, int seconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.presenceOfElementLocated(byMethod));
    }

    public boolean checkVideoPlaying(WebDriver driver, WebElement videoElement) {
        return true;
    }

}