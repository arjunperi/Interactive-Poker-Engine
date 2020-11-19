package utility;

import java.util.Properties;

public final class PropertiesFileReader {

  //TODO: Remove this and property file and retrieve cardSymbol from either JSON or manually based on unique characters
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
