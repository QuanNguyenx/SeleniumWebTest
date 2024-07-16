package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.firefox.FirefoxDriver;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.setProperty("webdriver.gecko.driver", "/snap/bin/geckodriver");
        System.out.print("Hello and welcome!");
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();

        driver.get("https://www.google.com/");

        // Find the search box element by its name
        WebElement element
                = driver.findElement(By.name("q"));

        // Enter a search query
        element.sendKeys("GeeksforGeeks");

        // Submit the form
        element.submit();

        // Wait for the page to load
        try
        {
            Thread.sleep(5000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        // Find all the search result links using XPath
        List<WebElement> searchResults
                = driver.findElements(By.xpath(
                "//div[@class='tF2Cxc']//a[@href]"));

        // Check if there are search results
        if (searchResults.size() > 0)
        {
            // Click on the first link (index 0)
            searchResults.get(0).click();
        }
        else
        {
            System.out.println("No search results found.");
        }

        // Get the title of the current page
        String title = driver.getTitle();

        // Print the title
        System.out.println("Page Title: " + title);

        // Close the browser
        driver.quit();
    }
}