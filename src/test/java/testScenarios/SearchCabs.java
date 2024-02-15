package testScenarios;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import objectRepository.Hotels_Page;
import objectRepository.GiftCard_Page;
import objectRepository.SearchCabs_Page;
import userDefinedLibraries.DriverSetup;
import userDefinedLibraries.ExcelReadWrite;
import userDefinedLibraries.ExtentReportManager;
import userDefinedLibraries.FailReport;
import userDefinedLibraries.PropertiesRead;
import userDefinedLibraries.JSONReadWrite;
import userDefinedLibraries.ScreenShot;
import userDefinedLibraries.XMLReadWrite;

public class SearchCabs {

	public static SearchCabs_Page cabs;
	public static GiftCard_Page gift;
	public static Hotels_Page hotels;
	public static WebDriver driver;
	public String path;
	public PropertiesRead prop = new PropertiesRead();
	public static ExtentReports report;
	public static ExtentTest logger;
	public static String currentHandle;
	public static ArrayList<String> handles;
	public static String errorText;
	public static Row row;
	public static Cell cell;
	public static List<WebElement> list1, list2, list3;
	public static String[] adultsList, carname, carprice;
//	public Logger logger;

	@Parameters("browser")
	@BeforeGroups("Smoke Test one")
	public void driverconfig(String browser) {
		report = ExtentReportManager.getReportInstance();
		driver = DriverSetup.getWebDriver(browser);
	}

	@Test(priority = 1,groups= {"Smoke Test one"})
	public void testCaseRex1() {
		ExcelReadWrite.readexcel();
	}

	@Test(priority = 2,groups= {"Smoke Test one"})
	public void HandlingLoginAlert() {
		logger = report.createTest("Handling Login Alert");
		logger.log(Status.INFO, "Closed Login Alert");
		cabs = new SearchCabs_Page(driver);
		waitForPageLoad();
		cabs.LoginAlert();
		cabs.closePopup();
	}

	@Test(priority = 3,groups= {"Smoke Test one"})
	public void MenuSelect() {
		logger = report.createTest("Cabs Tab");
		logger.log(Status.INFO, "Select Cabs Tab");
		cabs.SelectingCarsMenu();
		String actual = ExcelReadWrite.category1;
		String expected = "Cabs";
		Assert.assertEquals(actual, expected);
		logger.log(Status.PASS, "Assertion Passed");
	}

	@Test(priority = 4,groups= {"Smoke Test one"})
	public void SelectOneWayOutstation() {
		logger = report.createTest("onewayradiobutton");
		logger.log(Status.INFO, "One-Way Radio Button");
		cabs.clickOneWayRadioButton();
	}

	@Test(priority = 5,groups= {"Smoke Test one"})
	public void SelectFromLocation() {
		logger = report.createTest("fromlocation");
		logger.log(Status.INFO, "From Location");
		cabs.clickFromLocation();
	}

	@Test(priority = 6,groups= {"Smoke Test one"})
	public void EnterDataInFromLocation() {
		logger = report.createTest("fromlocationautosugg");
		logger.log(Status.INFO, "From Location Auto Suggestion");
		cabs.fromSearch();
	}

	@Test(priority = 7,groups= {"Smoke Test one"})
	public void EnterDataInToLocation() {
		logger = report.createTest("tolocation");
		logger.log(Status.INFO, "To Location Selection Auto Suggestion");
		cabs.toSearch();
	}

	@Test(priority = 8,groups= {"Smoke Test one"})
	public void selectDate() {
		logger = report.createTest("depaturedate");
		logger.log(Status.INFO, "Departure Date");
		cabs.DepatureDate();
	}

	
	@Test(priority = 9,groups= {"Smoke Test one"})
	public void selectTime() {
		logger = report.createTest("depaturetime");
		logger.log(Status.INFO, "Departure Time");
		cabs.DepatureTime();
	}
	

	@Test(priority = 10,groups= {"Smoke Test one"})
	public void clickSearch() {
		logger = report.createTest("searchbtn");
		logger.log(Status.INFO, "Search Button");
		cabs.Search();
	}

	@Test(priority = 11,groups= {"Smoke Test one"})
	public void SelectCarType() {
		logger = report.createTest("cartype");
		logger.log(Status.INFO, "Car Type");
		waitForPageLoad();
		cabs.carType();
	}

	@Test(priority = 12,groups= {"Smoke Test one"})
	public void scrollToCabs() {
		logger = report.createTest("scrolltocabs");
		logger.log(Status.INFO, "Scroll To Cabs List");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", cabs.scrollTocarList);
	}

	@Test(priority = 13,groups= {"Smoke Test one"})
	public void carDetails() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger = report.createTest("cabdetails");
		logger.log(Status.INFO, "Cab Details");
		ScreenShot.screenShotTC(driver);
		list2 = cabs.carName();
		carname = new String[2];
		for (int i = 0; i < 2; i++) {
			carname[i] = list2.get(i).getText();
		}

		list3 = cabs.carPrice();
		carprice = new String[2];
		for (int i = 0; i < 2; i++) {
			carprice[i] = list3.get(i).getText();
		}

	}

	@Test(priority = 14,groups= {"Smoke Test one"})
	public void CabsValuesIntoExcel() {
		logger = report.createTest("Display cab details in excel");
		try {
			ExcelReadWrite.readexcel();
			int t = 1;
			for (int k = 0; k < 2; k++) {
				row = ExcelReadWrite.sheet.getRow(t);
				if (row == null) {
					row = ExcelReadWrite.sheet.createRow(t);
					System.out.println(errorText + "one");
				}
				cell = row.getCell(4);
				if (cell == null) {
					cell = row.createCell(4);
					System.out.println(errorText);
				}
				cell.setCellValue(carname[k]);
				ExcelReadWrite.writeexcel();
				t++;

			}

			t = 1;
			for (int k = 0; k < 2; k++) {
				row = ExcelReadWrite.sheet.getRow(t);
				if (row == null) {
					row = ExcelReadWrite.sheet.createRow(t);
					System.out.println(errorText + "one");
				}
				cell = row.getCell(5);
				if (cell == null) {
					cell = row.createCell(5);
					System.out.println(errorText);
				}
				cell.setCellValue(carprice[k]);
				ExcelReadWrite.writeexcel();
				t++;

			}

		} catch (Exception e) {
			System.out.println("lol");
			e.printStackTrace();
		}

	}

	
	@Test(priority = 15,groups= {"Smoke Test one"})
	public void navigateBack() {
		logger = report.createTest("navigate");
		logger.log(Status.INFO, "Navigate Back to Home Page");
		cabs.navigateBack();
	}
	

	@Test(priority = 16,groups= {"Regression Test one"})
	public void navigateGiftCardPage() {
		logger = report.createTest("giftcard");
		logger.log(Status.INFO, "Gift Card");
		waitForPageLoad();
		gift = new GiftCard_Page(driver);
		gift.GiftCards();
		logger.log(Status.INFO, "Gift Card Button Clicked");
	}

	@Test(priority = 17,groups= {"Regression Test one"})
	public void mangingTabs() {
		logger = report.createTest("handlingtabs");
		logger.log(Status.INFO, "Handling Browser Tabs");
		handles = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(handles.get(1));

	}

	@Test(priority = 18,groups= {"Regression Test one"})
	public void ScrollToGiftCards() {
		logger = report.createTest("giftcardpagescroll");
		logger.log(Status.INFO, "Scrolling to Gift Cards List");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", gift.GiftCardScrollElement);

	}

	@Test(priority = 19,groups= {"Regression Test one"})
	public void ClickGiftCard() {
		logger = report.createTest("giftcardselect");
		logger.log(Status.INFO, "Gift Card Selection");
		gift.SelectGiftCard();
	}

	@Test(priority = 20,groups= {"Regression Test one"})
	public void ClickEmailButton() {
		logger = report.createTest("emailbutton");
		logger.log(Status.INFO, "Email Button Selection");
		waitForPageLoad();
		gift.ClickEmailButton();
	}

	@Test(priority = 21,groups= {"Regression Test one"})
	public void ScrollToGiftForm() {
		logger = report.createTest("scrolltogiftform");
		logger.log(Status.INFO, "Scrolling Gift Card Form");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", gift.GiftFormScrollElement);
	}

	@Test(priority = 22, dependsOnMethods = "ScrollToGiftForm",groups= {"Regression Test one"})
	public void EnterDataGiftForm_XML() {
		logger = report.createTest("Form Values Entered Using XML");
		XMLReadWrite.readxml();
		gift.RecipientName(XMLReadWrite.getRecipientName());
		gift.RecipientNumber(XMLReadWrite.getRecipientNumber());
		gift.RecipientEmail(XMLReadWrite.getRecipientEmail());
	}

	@Test(priority = 23, dependsOnMethods = "ScrollToGiftForm",groups= {"Regression Test one"})
	public void EnterDataGiftForm_JSON() {
		logger = report.createTest("Form Values Entered Using JSON");

		gift.SenderName(JSONReadWrite.readJSONData("SenderName"));
		gift.SenderNumber(JSONReadWrite.readJSONData("SenderNumber"));
		gift.SenderEmail(JSONReadWrite.readJSONData("SenderEmail"));
	}

	@Test(priority = 24,groups= {"Regression Test one"})
	public void selectBuyNow() {
		logger = report.createTest("buynow");
		logger.log(Status.INFO, "Buy Now Selected");
		gift.clickBuyNow();
	}

	@Test(priority = 25,groups= {"Regression Test one"})
	public void EmailError() {
		logger = report.createTest("errorText");
		logger.log(Status.INFO, "Extract Error Text");
		ScreenShot.screenShotTC(driver);
		errorText = gift.ExtractErrorText();
	}

	@Test(priority = 26,groups= {"Regression Test one"})
	public void ErrorValueIntoExcel() {
		logger = report.createTest("Display Gift Card Error in Excel");
		try {
			ExcelReadWrite.readexcel();
			int t = 5;
			for (int k = 0; k < 1; k++) {
				row = ExcelReadWrite.sheet.getRow(t);
				if (row == null) {
					row = ExcelReadWrite.sheet.createRow(t);
					System.out.println(errorText + "one");
				}
				cell = row.getCell(1);
				if (cell == null) {
					cell = row.createCell(1);
					System.out.println(errorText);
				}
				cell.setCellValue(errorText);
				ExcelReadWrite.writeexcel();
				t++;

			}

		} catch (Exception e) {
			System.out.println("lol");
			e.printStackTrace();
		}
	}

	
	@Test(priority = 27,groups= {"Regression Test one"})
	public void SwitchingToDefaultTab() {
		logger = report.createTest("closetab");
		logger.log(Status.INFO, "Closing Selected Broswer Tab");
		driver.close();
		System.out.println(handles.size());
		driver.switchTo().window(handles.get(0));
	}
	

	@Test(priority = 28,groups= {"Smoke Test two"})
	public void ClickHotelsTab() {
		logger = report.createTest("hotels");
		logger.log(Status.INFO, "Hotels Tab Selected");
		hotels = new Hotels_Page(driver);
		//hotels.SelectingHotelsMenu();
		hotels.hotelSelecting();
	}

	@Test(priority = 29,groups= {"Smoke Test two"})
	public void ClickRooms() {
		logger = report.createTest("roomsbutton");
		logger.log(Status.INFO, "Rooms Selected");
		hotels.ClickingRooms();
	}

	@Test(priority = 30,groups= {"Smoke Test two"})
	public void CheckAdultsLimit() {
		logger = report.createTest("adultslist");
		logger.log(Status.INFO, "Checking Adults List");
		ScreenShot.screenShotTC(driver);
		list1 = hotels.adultsOptions();
		adultsList = new String[list1.size()];
		int i = 0;
		for (WebElement ele : list1) {
			adultsList[i] = ele.getText();
			i++;
		}
	}

	@Test(priority = 31,groups= {"Smoke Test two"})
	public void adultsListValuesIntoXml() {
		logger = report.createTest("Display Hotels Details in Excel");
		try {
			ExcelReadWrite.readexcel();
			int t = 8;
			for (int k = 0; k < adultsList.length; k++) {
				row = ExcelReadWrite.sheet.getRow(t);
				if (row == null) {
					row = ExcelReadWrite.sheet.createRow(t);
					System.out.println(errorText + "one");
				}
				cell = row.getCell(1);
				if (cell == null) {
					cell = row.createCell(1);
					System.out.println(errorText);
				}
				cell.setCellValue(adultsList[k]);
				ExcelReadWrite.writeexcel();
				t++;

			}

		} catch (Exception e) {
			System.out.println("lol");
			e.printStackTrace();
		}

	}
	
	@AfterMethod
	public void tearDown(ITestResult result) {
		if(result.getStatus() == ITestResult.FAILURE) {
			logger.log(Status.FAIL, "Assertion Failed");
			path = ScreenShot.screenShotTC(driver);
			try {
				logger.addScreenCaptureFromPath(path);
			}catch(IOException e) {
				FailReport.reportFail(e.getMessage());
			}
		}
	}
	
	@AfterGroups("Smoke Test two")
	public void driverexit() {
		report.flush();
		DriverSetup.driverClose();
	}

	public void waitForPageLoad() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		int i = 0;
		while (i != 180) {
			String pageState = (String) js.executeScript("return document.readyState;");
			if (pageState.equals("complete")) {
				break;
			} else {
				waitLoad(1);
			}
		}
	}

	public void waitLoad(int i) {
		try {
			Thread.sleep(i * 1000);
		} catch (InterruptedException e) {
			FailReport.reportFail(e.getMessage());
		}
	}

}
