package com.swag;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;


public class LogIn {



    private WebDriver driver;

    @BeforeClass
    public void setUp() {

       // WebDriverManager.chromedriver().setup();
        System.setProperty("webdriver.chrome.driver", "src/main/Driver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testLogin() throws InterruptedException {

        String username = Utility.getProperty("username");
        String password = Utility.getProperty("password");
        String loginUrl = Utility.getProperty("swagUri");

        driver.get(loginUrl);

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("user-name")));

        driver.findElement(By.id("user-name")).sendKeys(username);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("password")));

        driver.findElement(By.id("password")).sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='login-button']")));

        try{
        driver.findElement(By.xpath("//input[@id='login-button']")).click();

        String currentUrl = driver.getCurrentUrl();

        Assert.assertTrue(currentUrl.equals("https://www.saucedemo.com/inventory.html"), "Login failed");
        } catch (AssertionError e) {
            String errorMsg = driver.findElement(By.xpath("//h3")).getText();
            Reporter.log("Test failed: " + errorMsg, true);
            throw e;
        }

        Reporter.log("Login successful", true);

    }

    @AfterClass
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}
