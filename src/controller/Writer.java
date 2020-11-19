package controller;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class Writer {

  public void cashOutToPlayerSaves(String playerName, Properties propertyFile) {
    try {
      FileWriter fileWriter = new FileWriter("PlayerSaveFiles/" + playerName + "Player.properties");

      propertyFile.store(fileWriter, null);
      fileWriter.close();
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

}

