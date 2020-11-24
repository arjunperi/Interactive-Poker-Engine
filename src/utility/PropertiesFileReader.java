package utility;

import java.util.Properties;

/**
 * This class is responsible for reading the properties files
 */
public final class PropertiesFileReader {

  /**
   * Method returns the properties file that corresponds to the string of the file name
   * @param fileName - string of the file name
   * @return a properties object of the corresponding file name
   */
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
