package utility;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

/**
 * This class writes to a properties file
 */
public final class PropertiesFileWriter {

  /**
   * Method writes to a properties file that corresponds to the name of the player
   * @param playerName - string of the name of the player that is cashing out
   * @param propertyFile - property object needed to write to a file
   */
  public static void cashOutToPlayerSaves(String playerName, Properties propertyFile) {
    try {
      FileWriter fileWriter = new FileWriter("PlayerSaveFiles/" + playerName + "Player.properties");
      propertyFile.store(fileWriter, null);
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }
}

