package utility;

import java.util.Properties;

public final class PropertiesFileReader {

  public static Properties getPropertyFile(String fileName) {
    Properties propertyFile = new Properties();
    try {
      propertyFile
          .load(PropertiesFileReader.class.getClassLoader()
              .getResourceAsStream(fileName + ".properties"));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return propertyFile;
  }

}
