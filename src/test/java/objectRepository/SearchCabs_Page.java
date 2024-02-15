package objectRepository;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import userDefinedLibraries.FailReport;
import userDefinedLibraries.ExcelReadWrite;
import userDefinedLibraries.PropertiesRead;

public class SearchCabs_Page {

	public static String month, month_year, day;
	final WebDriver driver;
	public static PropertiesRead prop = new PropertiesRead();

	@FindBy(xpath = "//*[@id=\"SW\"]/div[1]/div[1]/ul/li[3]")
	public WebElement HandleAlert;

	@FindBy(xpath = "//a[@href='https://www.makemytrip.com/cabs/']")
	public List<WebElement> Menu;

	@FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div/div[1]/ul/li[1]")
	public WebElement oneWayRadioButton;

	@FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div/div[2]/div/div[1]")
	public WebElement FromLocation;

	@FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div/div[2]/div/div[1]/div[1]/div/div/div/input")
	public WebElement FromAutoSuggesstion;

	@FindBy(xpath = "//*[@id=\"react-autowhatever-1\"]/div/ul/li/div/p/span")
	public List<WebElement> FromAutoListBox;

	@FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div/div[2]/div/div[2]/div[1]/div/div/div/input")
	public WebElement ToAutoSuggesstion;

	@FindBy(xpath = "//*[@id=\"react-autowhatever-1\"]/div/ul/li/div/p/span")
	public List<WebElement> ToAutoListBox;
	
	@FindBy(xpath = "//span[@class='applyBtnText']")
	public WebElement TimeList;
	
	@FindBy(xpath= "//a[normalize-space()='Search']")
	public WebElement SearchButton;
	
	@FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div[2]/div[2]/div[1]/div/div[2]/div/span")
	public List<WebElement> CarTypeCheckBox;
	
	@FindBy(xpath = "//*[@id=\"List\"]/div[1]")
	public WebElement scrollTocarList;
	
	@FindBy(xpath = "//*[@id=\"List\"]/div/div[1]/div[2]/div[1]/div[1]/span[1]")
	public List<WebElement> CarNames;
	
	@FindBy(xpath = "//*[@id=\"List\"]/div/div[1]/div[3]/div/div[2]/div/p")
	public List<WebElement> CarPrice;
	
	@FindBy(xpath = "//span[@class='commonModal__close']")
	public WebElement Popup;
	
	//@FindBy(xpath="//span[@class='chNavIcon appendBottom2 chSprite chFlights']")
	//WebElement homepage;
	
	
	public SearchCabs_Page(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void LoginAlert() {
		HandleAlert.click();
	}

	public void SelectingCarsMenu() {
		for (WebElement element : Menu) {
			System.out.println(element.getText());
			if ((element).getText().equals(ExcelReadWrite.category1)) {
				element.click();
			}
		}
	}

	public void clickOneWayRadioButton() {
		oneWayRadioButton.click();
	}

	public void clickFromLocation() {
		FromLocation.click();
	}

	public void fromSearch() {
		FromAutoSuggesstion.sendKeys(prop.readProperties("from"));
		try {
			Thread.sleep(3 * 1000);
		} catch (InterruptedException e) {
			FailReport.reportFail(e.getMessage());
		}
		//;
		for (WebElement element : FromAutoListBox) {
			System.out.println(element.getText());
			if ((element).getText().equals(ExcelReadWrite.category2)) {
				System.out.println("enters");
				element.click();
				break;
			}
		}

	}

	public void toSearch() {
		ToAutoSuggesstion.sendKeys(prop.readProperties("to"));
		try {
			Thread.sleep(3 * 1000);
		} catch (InterruptedException e) {
			FailReport.reportFail(e.getMessage());
		}
		;
		for (WebElement element : ToAutoListBox) {
			System.out.println(element.getText());
			if ((element).getText().equals(ExcelReadWrite.category3)) {
				System.out.println("enters");
				element.click();
				break;
			}
		}

	}

	public void DepatureDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date()); // Using today's date
		c.add(Calendar.DATE, 5); // Adding 5 days
		String output = sdf.format(c.getTime());
		String splitter[] = output.split("-");
		month = splitter[1];
		month_year = splitter[2];
		day = splitter[0];
		System.out.println(month + day + month_year);
		try {
			Thread.sleep(3 * 1000);
		} catch (InterruptedException e) {
			FailReport.reportFail(e.getMessage());
		}
		WebElement dates = driver.findElement(By.xpath("//*[@class='DayPicker-Day'][contains(@aria-label," + "'" + month
				+ " " + day + " " + month_year + "'" + ")]"));
		dates.click();

	}
	
	
	public void DepatureTime() {
		/*for(WebElement element:TimeList) {
			System.out.println(element.getText());
			if ((element).getText().equals(prop.readProperties("departureTime"))) {
				System.out.println("enters");
				element.click();
				//break;
			}
		}*/
		TimeList.click();
		
		
	}
	
	
	public void Search() {
		SearchButton.click();
	}
	
	public void carType() {
		for(WebElement element:CarTypeCheckBox) {
			System.out.println(element.getText());
			if ((element).getText().equals(ExcelReadWrite.category4)) {
				System.out.println("enters");
				element.click();
				break;
			}
		}
	}
	
	public List<WebElement> carName() {
		return CarNames;
	}
	
	public List<WebElement> carPrice() {
		return CarPrice;
	}
	
	public void closePopup() {
		Popup.click();
	}
	
	
	public void navigateBack() {
		driver.navigate().back();
		driver.navigate().back();
	}
	
	

}
