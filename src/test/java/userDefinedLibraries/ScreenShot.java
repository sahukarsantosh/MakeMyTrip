package userDefinedLibraries;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenShot {

	public static String screenShotTC(WebDriver driver) {
		File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File destFile = new File(
				System.getProperty("user.dir") + "\\screenshot\\" + System.currentTimeMillis() + ".png");
		try {
			FileUtils.copyFile(sourceFile, destFile);

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		return destFile.getPath();
	}

}
