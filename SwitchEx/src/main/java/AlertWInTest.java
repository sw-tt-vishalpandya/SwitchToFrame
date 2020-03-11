import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class AlertWInTest {


    private WebDriver driver;
    private JavascriptExecutor jse;
    Alert alert;

    private By simpleAlert = By.xpath("//button[@id='alertBox']");
    private By confirmPopu = By.xpath("//button[@id='confirmBox']");
    private By promptPopu= By.xpath("//button[@id='promptBox']");
    //private By promptPopu= By.xpath("//button[contains(text(),'Prompt Pop up')]");
    private By newWind = By.xpath("//button[@id='win1']");
    private By newWind1 = By.xpath("//button[@id='win2']");
    private By searchBox = By.xpath("//input[@name='q']");


    @BeforeClass
    public void launchBrowser() {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\drivers\\chromedriver_win32 (1)\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(60L, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(60L, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        driver.get("https://chandanachaitanya.github.io/selenium-practice-site/?languages=Java&enterText=");
    }

    @Test(priority = 0)
    public void verifySimpleAlert() throws InterruptedException {

        driver.findElement(simpleAlert).click();
        Thread.sleep(5000);
        alert = driver.switchTo().alert();
        System.out.println(alert.getText());
        alert.accept();

    }



    @Test(priority = 1)
    public void verifyConfirmAlert() throws InterruptedException {

        Thread.sleep(5000);
        driver.findElement(confirmPopu).click();
        Thread.sleep(5000);
        alert = driver.switchTo().alert();
        alert.dismiss();
        System.out.println("Dismissed");
    }



    @Test(priority = 2)
    public void verifyPromptAlert() throws InterruptedException, AWTException {
        JavascriptExecutor jse = ((JavascriptExecutor)driver);


        Thread.sleep(5000);
        driver.findElement(promptPopu).click();

        //Thread.sleep(8000);

        Robot rb = new Robot();
        //Enter user name by ctrl-v
        StringSelection text = new StringSelection("Hello There");
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(text, null);
        rb.keyPress(KeyEvent.VK_CONTROL);
        rb.keyPress(KeyEvent.VK_V);
        rb.keyRelease(KeyEvent.VK_V);
        rb.keyRelease(KeyEvent.VK_CONTROL);

        //tab to password entry field
        rb.keyPress(KeyEvent.VK_TAB);
        rb.keyRelease(KeyEvent.VK_TAB);

        //Thread.sleep(10000);

        Thread.sleep(5000);
       /*alert= driver.switchTo().alert();
        Thread.sleep(1500);
        alert.sendKeys("Hello There");*/
        alert.accept();

    }


    @Test(priority = 3)
    public void verifySwitchWin() throws InterruptedException {

        driver.findElement(newWind).click();
        Thread.sleep(1500);

        // Store the current window handle
        String winHandleBefore = driver.getWindowHandle();
        System.out.println("Parent ID "+ winHandleBefore);
        System.out.println("Before Switching "+driver.getTitle());

        // Switch to new window opened
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
            System.out.println("Child window ID"+ winHandle);
        }
        // Perform the actions on new window
        driver.findElement(searchBox).sendKeys("Selenium Webdriver");
        driver.findElement(searchBox).sendKeys(Keys.ENTER);
        System.out.println("After Switching: " + driver.getTitle());

        // Switch back to original browser (first window)
        driver.switchTo().window(winHandleBefore);

        // Continue with original browser (first window)
        System.out.println("Parent Window Title is: " + driver.getTitle());
    }


    @Test(priority = 4)
    public void verifyNewWin() {

        driver.findElement(newWind1).click();

        Set<String> allHandles = driver.getWindowHandles();

        //count the handles Here count is=2
        System.out.println("Count of windows:" + allHandles.size());

        //Get current handle or default handle
        String currentWindowHandle = allHandles.iterator().next();
        System.out.println("currentWindow Handle" + currentWindowHandle);

        //Remove first/default Handle
       // allHandles.remove(allHandles.iterator().next());

        //get the last Window Handle
        String lastHandle = allHandles.iterator().next();
        System.out.println("last window handle" + lastHandle);


        driver.switchTo().window(lastHandle);
        System.out.println(driver.getTitle());
    }


}