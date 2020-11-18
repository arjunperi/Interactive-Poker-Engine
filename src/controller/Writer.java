package controller;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class Writer {

  public void cashOutToProperties(String playerName, Properties propertyFile) {
    try {
      FileWriter fileWriter = new FileWriter("properties/" + playerName + "Player.properties");
      propertyFile.store(fileWriter, null);
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

}

