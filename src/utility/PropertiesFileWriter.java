package utility;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public final class PropertiesFileWriter {

  public static void cashOutToPlayerSaves(String playerName, Properties propertyFile) {
    try {
      FileWriter fileWriter = new FileWriter("PlayerSaveFiles/" + playerName + "Player.properties");
      propertyFile.store(fileWriter, null);
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

}

