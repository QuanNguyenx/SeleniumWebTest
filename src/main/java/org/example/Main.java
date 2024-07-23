package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.Arrays;
import java.util.List;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.JavascriptExecutor;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.interactions.Actions;

import java.util.Date;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        WebDriver driver = configAndGetWebDriver();
        loginPage(driver);
//        verifyHistoryEvents(driver);
//        verifyVersion(driver);
        System.out.println("1 - " + (new Date()).toString());
        verifyVideoStream(driver);
//        driver.quit();
    }

    public static void waitElement(WebDriver driver, WebElement element, int seconds) {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(d -> element.isDisplayed());
    }

    public static void waitElementPresent(WebDriver driver, By byMethod, int seconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.presenceOfElementLocated(byMethod));
    }

    public static WebDriver configAndGetWebDriver() {
        System.setProperty("webdriver.gecko.driver", "/snap/bin/geckodriver");
        System.out.print("Hello and welcome!");
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        return driver;
    }

    public static void verifyVersion(WebDriver driver) {
        String versionUrl = "https://ivacloud.viettel.io/version";
        driver.get(versionUrl);

        String rightVersion = "1.1.6.3";
        int versionRow = 3;
        int versionCol = 2;
        String versionPath = String.format("//table/tbody/tr[%d]/td[%d]", versionRow, versionCol);
        WebElement thirdCell = driver.findElement(By.xpath(versionPath));
        String version = thirdCell.getText();
        String text = String.format("version: %s", version);
        System.out.println(text);
//        compare version vs version in env

//        int versionIndex = 2;
//        WebElement table = driver.findElement(By.xpath("//table"));
//        List<WebElement> tableRows = table.findElements(By.tagName("tr"));
//        String version = tableRows.get(versionIndex).getText();
//        for (WebElement row: tableRows) {
//            System.out.println(row.getText());
//        }
//        String version = versionEle.getText();
    }

    public static void verifyVideoStream(WebDriver driver) {
        try {
            String liveViewUrl = "https://ivacloud.viettel.io/liveView";
            driver.get(liveViewUrl);
            WebElement box = driver.findElement(By.xpath("//input[@placeholder='Select group']"));
//            waitElement(driver, box, 5);
            // Use JavaScript to click the element
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", box);

            By byMethod = By.className("MuiFormControlLabel-root");
            System.out.println("2 - " + (new Date()).toString());
            waitElementPresent(driver, byMethod, 2);

            WebElement checkbox = driver.findElement(By.className("MuiFormControlLabel-root"));
            checkbox.click();
            WebElement searchCamBox = driver.findElement(By.xpath("//input[@placeholder='Select cameras']"));
            searchCamBox.click();

//            WebElement cameraCheckbox = driver.findElement(By.className("MuiIconButton-label"));
//            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", cameraCheckbox);

            WebElement cameraCheckbox = driver.findElement(By.xpath("(//div[@role='button'])[3]"));

            cameraCheckbox.click();
            System.out.println("3 - " + (new Date()).toString());

            By videoByMethod = By.id("666f0a20add011eeab13baf7db86dba7");
            System.out.println("4 - " + (new Date()).toString());
            waitElementPresent(driver, videoByMethod, 2);

            System.out.println("5 - " + (new Date()).toString());
            WebElement videoElement = driver.findElement(videoByMethod);

            String initTimeStr = videoElement.getAttribute("currentTime");
            double initTime = Double.parseDouble(initTimeStr);
            Thread.sleep(5000);
            System.out.println("6 - " + (new Date()).toString());

            String currTimeStr = videoElement.getAttribute("currentTime");
            double currTime = Double.parseDouble(currTimeStr);
            System.out.println(initTimeStr + " - " + currTimeStr);

            if (currTime > initTime) {
                System.out.println("The video is playing.");
            } else {
                System.out.println("The video is NOT playing.");
            }
            driver.quit();
            return;
        } catch (Exception e) {
            System.out.println(e.toString());
//            driver.quit();
        }

    }

    public static boolean checkVideoPlaying(WebDriver driver, WebElement videoElement) {
//        System.out.println(videoElement.isDisplayed());
//        System.out.println(videoElement.getAttribute("currentTime"));
//        Thread.sleep(6000);
//        System.out.println(videoElement.getAttribute("currentTime"));

        //        JavascriptExecutor js = (JavascriptExecutor) driver;
//        // Get the initial playback time
//        Object initialTime = js.executeScript("return arguments[0].currentTime;", videoElement);
//        Double initTime = Double.parseDouble(initialTime.toString());
//        System.out.println(initTime);
//
////             Wait for a short duration (e.g., 1 second)
//        Thread.sleep(5000);
//
////            // Get the playback time again
//        Object currentTime = js.executeScript("return arguments[0].currentTime;", videoElement);
//        Double currTime = Double.parseDouble(currentTime.toString());
//        System.out.println(currTime);


        return true;
    }

    public static void loginPage(WebDriver driver) {
        String ivaHomeUrl = "https://ivacloud.viettel.io/";
        driver.get(ivaHomeUrl);

        String ivaHomeUserName = "quannh68";
        String ivaHomePass = "VhtRD1234@q";

        // Find the search box element by its name
//        WebElement element
//                = driver.findElement(By.name("q"));
        WebElement loginNameElement = driver.findElement(By.id("loginName"));
        WebElement passwordElement = driver.findElement(By.id("password"));

        loginNameElement.clear();
        passwordElement.clear();

        loginNameElement.sendKeys(ivaHomeUserName);
        passwordElement.sendKeys(ivaHomePass);

        WebElement button = driver.findElement((By.xpath("//button[text()='Login']")));
        button.click();
    }

    public static void verifyHistoryEvents(WebDriver driver) {
        String historyEventUrl = "https://ivacloud.viettel.io/historyEvent";
        driver.get(historyEventUrl);

        WebElement table = driver.findElement(By.className("MuiTable-root"));
//        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(2));
//        wait.until(d -> table.isDisplayed());
        waitElement(driver, table, 2);

        List<WebElement> tableRows = table.findElements(By.tagName("tr"));
        int count = 0;
        for (WebElement row : tableRows) {
            System.out.println(row.getText());
            count++;
        }
        System.out.println("count:" + count);
//        compare count vs 0
        return;

    }

}