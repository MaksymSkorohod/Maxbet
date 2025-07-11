package io.maxbet;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DriverManager {
    private static ThreadLocal<WebDriver> threadDriver = new ThreadLocal();

    private DriverManager(){}

    public static WebDriver getDriver(){
        if(threadDriver.get()==null){
            initDriver();
        }
        return threadDriver.get();
    }

    private static void initDriver() {
        String browser = System.getProperty("browser", "chrome");
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            threadDriver.set(new ChromeDriver());
        }
        if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            threadDriver.set(new FirefoxDriver());
        }
        if (browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            threadDriver.set(new EdgeDriver());
        }
    }

    public static void killDriver(){
        threadDriver.get().close();
        threadDriver.remove();
    }

    public static void goTo(String url) {
        getDriver().navigate().to(url);
    }

    public void WebDriverWait(){
    }
}
