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

import userDefinedLibraries.FailReport;
import userDefinedLibraries.PropertiesRead;

public class GiftCard_Page {

	final WebDriver driver;
	public PropertiesRead prop = new PropertiesRead();


	@FindBy(xpath = "//span[normalize-space()='Gift Cards']")
	public List<WebElement> RecommendMenu;

	@FindBy(xpath = "//div[@class='gc__right']//div[2]//ul[1]//li[2]//div[1]//img[1]")
	public WebElement Cards;
	
	@FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div[1]/div/div[2]/div[2]")
	public WebElement GiftCardScrollElement;

	@FindBy(xpath = "//span[normalize-space()='E-Mail']")
	public List<WebElement> EmailButton;
	
	@FindBy(xpath = "//*[@id=\"deliveredSection\"]/div")
	public WebElement GiftFormScrollElement;
	
	@FindBy(xpath = "//*[@id=\"deliveredSection\"]/div/div/div/div[1]/div/div[1]/div/input")
	public WebElement RecipientName;
	
	@FindBy(xpath = "//*[@id=\"deliveredSection\"]/div/div/div/div[1]/div/div[2]/div[2]/div/input")
	public WebElement RecipientNumber;
	
	@FindBy(xpath = "//*[@id=\"deliveredSection\"]/div/div/div/div[1]/div/div[3]/div/input")
	public WebElement RecipientEmail;
	
	@FindBy(xpath = "//*[@id=\"deliveredSection\"]/div/div/div/div[2]/div[1]/div/input")
	public WebElement SenderName;
	
	@FindBy(xpath = "//*[@id=\"deliveredSection\"]/div/div/div/div[2]/div[2]/div[2]/div/input")
	public WebElement SenderNumber;
	
	@FindBy(xpath = "//*[@id=\"deliveredSection\"]/div/div/div/div[2]/div[3]/div/input")
	public WebElement SenderEmail;
	
	@FindBy(xpath = "//button[@class='prime__btn font16 prime__btn__text']")
	public WebElement BuyNow;
	
	@FindBy(xpath = "//*[@id=\"deliveredSection\"]/div/div/div/div[1]/div/div[3]/p")
	public WebElement EmailErrorText;
	
	
	
	
	
	public GiftCard_Page(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void GiftCards() {
		
		for (WebElement element : RecommendMenu) {
			System.out.println(element.getText());
			if ((element).getText().equals(prop.readProperties("GiftTab"))) {
				element.click();
			}
		}
		
		
		//RecommendMenu.click();
		
	}

	public void SelectGiftCard() {
		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait1.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//body[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[2]/div[2]/ul[1]/li[2]/div[1]/img[1]")));
		try {
			
			/*for (WebElement element : Cards) {
				System.out.println(element.getText());
				if ((element).getText().equals(prop.readProperties("GiftCard"))) {
					element.click();
				}
			}*/
			
			Cards.click();
		}
		catch(StaleElementReferenceException e) {
			
			/*for (WebElement element : Cards) {
				System.out.println(element.getText());
				if ((element).getText().equals(prop.readProperties("GiftCard"))) {
					element.click();
				}
			}*/
			
			Cards.click();
		}
	}

	public void ClickEmailButton() {
		for (WebElement element : EmailButton) {
			System.out.println(element.getText());
			if ((element).getText().equals(prop.readProperties("ButtonType"))) {
				element.click();
			}
		}
	}
	
	public void RecipientName(String RecipientName) {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			FailReport.reportFail(e.getMessage());
		}
		this.RecipientName.click();
		this.RecipientName.sendKeys(RecipientName);
	}
	
	public void RecipientNumber(String RecipientNumber) {
		this.RecipientNumber.click();
		this.RecipientNumber.sendKeys(RecipientNumber);
	}
	
	public void RecipientEmail(String RecipientEmail) {
		this.RecipientEmail.click();
		this.RecipientEmail.sendKeys(RecipientEmail);
	}
	
	public void SenderName(String SenderName) {
		this.SenderName.click();
		this.SenderName.sendKeys(SenderName);
	}
	
	public void SenderNumber(String SenderNumber) {
		this.SenderNumber.click();
		this.SenderNumber.sendKeys(SenderNumber);
	}
	
	public void SenderEmail(String SenderEmail) {
		this.SenderEmail.click();
		this.SenderEmail.sendKeys(SenderEmail);
	}
	
	public void clickBuyNow() {
		BuyNow.click();
	}
	
	public String ExtractErrorText() {
		System.out.println(EmailErrorText.getText());
		return EmailErrorText.getText();
	}
	
	

}
