import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class SwitchToFrame {


    private WebDriver driver;

    By userName= By.xpath("//input[@name='txtUsername']");
    By password= By.xpath("//input[@name='txtPassword']");
    //By orangeHRMLink= By.xpath("//a[contains(text(),'OrangeHRM')]");
    By openSourcesLink= By.xpath("(//a[contains(@href,'opensourcecms.com')])[1]");



    @BeforeClass
    public void launchBrowser() {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\drivers\\chromedriver_win32 (1)\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(60L, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(60L, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        driver.get("https://s1.demo.opensourcecms.com/s/44");
    }

    @Test
    public void verifyFrameControl(){

        driver.switchTo().frame("preview-frame");
        driver.findElement(userName).sendKeys("opensourcecms");
        driver.findElement(password).sendKeys("opensourcecms");
        //driver.findElement(orangeHRMLink).click();
        driver.switchTo().defaultContent();
        driver.findElement(openSourcesLink).click();

    }

}
