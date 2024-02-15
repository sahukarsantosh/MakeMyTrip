package userDefinedLibraries;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class DriverSetup {
	public static WebDriver driver;
//	public static String exePath;
//	public static String browserType;

	public static WebDriver getWebDriver(String browser) {
		PropertiesRead prop = new PropertiesRead();
//		Defining Browser Type chrome or edge
//		browserType = browser;
		if (browser.equalsIgnoreCase("chrome")) {

			//exePath = System.getProperty("user.dir") + "\\src\\test\\resources\\Drivers\\chromedriver.exe";
			//System.setProperty("webdriver.chrome.driver", exePath);
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			//exePath = System.getProperty("user.dir") + "\\src\\test\\resources\\Drivers\\msedgedriver.exe";
			//System.setProperty("webdriver.edge.driver", exePath);
			driver = new EdgeDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get(prop.readProperties("URL"));
		driver.manage().deleteAllCookies();
		return driver;
	}

	public static void driverClose() {
		driver.close();
	}

}
