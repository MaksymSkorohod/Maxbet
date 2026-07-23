package io.maxbet;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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
//    private static void initDriver() {
//        boolean headless = Boolean.parseBoolean(
//                System.getProperty("headless", "false"));
//        ChromeOptions options = new ChromeOptions();
//        // common settings
//        options.addArguments("--disable-dev-shm-usage");
//        options.addArguments("--no-sandbox");
//        options.addArguments("--disable-gpu");
//        if (headless) {
//            options.addArguments("--headless=new");
//            options.addArguments("--window-size=1920,1080");
//        }
//        WebDriverManager.chromedriver().setup();
//        threadDriver.set(new ChromeDriver(options));
//
//        if (!headless) {
//            threadDriver.get().manage().window().maximize();
//        }
//    }

    private static void initDriver() {
        String browser = System.getProperty("browser", "chrome");
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            threadDriver.set(new ChromeDriver());
        }
        threadDriver.get().manage().window().maximize();
    }
//    Headless only option
//    private static void initDriver() {
//    ChromeOptions options = new ChromeOptions();
//    options.addArguments("--headless=new");
//    options.addArguments("--window-size=1920,1080");
//    options.addArguments("--disable-dev-shm-usage");
//    options.addArguments("--no-sandbox");
//
//    WebDriverManager.chromedriver().setup();
//    threadDriver.set(new ChromeDriver(options));
//    }

    public static void killDriver() {
        if (threadDriver.get() != null) {
            threadDriver.get().quit();
            threadDriver.remove();
        }
    }

    public static void goTo(String url) {
        getDriver().navigate().to(url);
    }

    public void WebDriverWait(){
    }
}
