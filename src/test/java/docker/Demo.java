package docker;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Browser;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class Demo {
    public WebDriver driver;

    @Parameters("browser")
    @BeforeTest
    public void setup(String browserType) throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        if (browserType.equalsIgnoreCase("chrome")){
            capabilities.setBrowserName((Browser.CHROME.browserName()));
            System.out.println("###Test Case Execution Started On ==> " + browserType + "###");
        } else if (browserType.equalsIgnoreCase("firefox")) {
            capabilities.setBrowserName((Browser.FIREFOX.browserName()));
            System.out.println("###Test Case Execution Started On ==> " + browserType + "###");
        } else {
            capabilities.setBrowserName((Browser.EDGE.browserName()));
            System.out.println("###Test Case Execution Started On ==> " + browserType + "###");
        }

        driver = new RemoteWebDriver(new URL("http://44.202.10.193:4445/wd/hub"), capabilities);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void googleOnChromeTest(){
        try{
            driver.get("https://www.google.com");
            Assertion a = new Assertion();
            a.assertEquals(driver.getTitle(), "Google", "Wrong Page Title");
            Thread.sleep(8000);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @AfterTest
    public void closeDriver(){
        System.out.println("###Test Case Execution Ended###");
        driver.quit();
    }
}
