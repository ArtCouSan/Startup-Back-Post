package br.com.posts.ui;

import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class PostEditUiTest {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        baseUrl = "https://www.google.com/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testPostEditUi() throws Exception {
        driver.get("http://localhost:4200/");
        driver.findElement(By.cssSelector("svg.icon__projects > use")).click();
        driver.findElement(By.cssSelector("svg.icon__edit")).click();
        driver.findElement(By.name("title")).click();
        driver.findElement(By.name("title")).clear();
        driver.findElement(By.name("title")).sendKeys("Teste titulo alterado");
        driver.findElement(By.xpath("//textarea[@type='text']")).click();
        driver.findElement(By.xpath("//textarea[@type='text']")).clear();
        driver.findElement(By.xpath("//textarea[@type='text']")).sendKeys("Teste descricao alterado");
        driver.findElement(By.cssSelector("svg.icon__save > use")).click();
        driver.findElement(By.xpath("//app-snackbar/div/div[2]")).click();
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}
