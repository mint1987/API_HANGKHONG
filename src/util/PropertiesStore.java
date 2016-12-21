package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesStore {
	public static String getProperty(String property) throws IOException {
		FileInputStream objfile = null;
		Properties obj = new Properties();
		try {
			objfile = new FileInputStream(System.getProperty("user.dir") + "\\src\\objects.properties");
			obj.load(objfile);
		} catch (IOException e) {
			if (objfile != null)
				objfile.close();
		}
		return obj.getProperty(property);
	}
}