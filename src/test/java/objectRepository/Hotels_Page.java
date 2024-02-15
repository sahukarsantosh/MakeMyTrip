package objectRepository;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import userDefinedLibraries.ExcelReadWrite;
import userDefinedLibraries.FailReport;
import userDefinedLibraries.PropertiesRead;

public class Hotels_Page {
	
	final WebDriver driver;
	public PropertiesRead prop = new PropertiesRead();
	
	
	//@FindBy(xpath = "//a[normalize-space()='Home']")
	//public WebElement Menu;
	
	@FindBy(xpath="//a[@href='https://www.makemytrip.com/hotels/']")
	WebElement HotelSelecting;
	
	@FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div/div[2]/div/div[1]/div[4]/label")
	public WebElement Rooms;
	
	@FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div/div[2]/div/div[1]/div[4]/div[1]/div[1]/div/div[2]/div/ul[1]/li")
	public List<WebElement> AdultsList;
	
	public Hotels_Page(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	/*
	public void SelectingHotelsMenu() {
		/*for (WebElement element : Menu) {
			System.out.println(element.getText());
			if ((element).getText().equals(prop.readProperties("HotelTab"))) {
				element.click();
			}
		}
		//Menu.click();
		
	}*/
	
	public void hotelSelecting()
	{
		HotelSelecting.click();
	}
	
	public void ClickingRooms() {
		Rooms.click();
	}
	
	public List<WebElement> adultsOptions(){
		return AdultsList;
	}

	

}
