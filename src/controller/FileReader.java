package controller;

import java.util.Properties;

public class FileReader {

  //TODO: Remove this and property file and retrieve cardSymbol from either JSON or manually based on unique characters
  public Properties getPropertyFile(String fileName) {
    Properties propertyFile = new Properties();
    try {
      propertyFile
          .load(FileReader.class.getClassLoader().getResourceAsStream(fileName + ".properties"));

    } catch (Exception e) {
      e.printStackTrace();
    }
    return propertyFile;
  }

}
