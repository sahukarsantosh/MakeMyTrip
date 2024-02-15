package userDefinedLibraries;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesRead {

	File file = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\testData\\config.properties");
	Properties prop = new Properties();

	public String readProperties(String key) {

		FileInputStream fileInput = null;

		try {

			fileInput = new FileInputStream(file);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// Load Properties File
		try {

			prop.load(fileInput);

		} catch (IOException e) {
			e.printStackTrace();
		}

		String value = prop.getProperty(key).toString();
		try {
			fileInput.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value;
	}

}
