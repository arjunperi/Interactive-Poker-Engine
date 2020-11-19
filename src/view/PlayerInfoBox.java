package view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class PlayerInfoBox extends GridPane {

  private String name;
  private int bankroll;
  private String avatar;

  private Text playerName;
  private Text playerBankroll;
  private ImageView playerAvatar;

  private static final int INFO_BOX_MIN_WIDTH = 200;
  private static final int INFO_BOX_MIN_HEIGHT = 30;

  private static final int AVATAR_WIDTH = 35;
  private static final int AVATAR_HEIGHT = 30;

  private static final int NAME_POSITION = 0;
  private static final int BANKROLL_POSITION = 1;
  private static final int AVATAR_POSITION = 2;

  private static final int NAME_COLUMN_WIDTH_PERCENTAGE = 40;
  private static final int BANKROLL_COLUMN_WIDTH_PERCENTAGE = 40;
  private static final int AVATAR_COLUMN_WIDTH_PERCENTAGE = 20;



  public PlayerInfoBox(String name, int bankroll, String avatar) {
    this.name = name;
    this.bankroll = bankroll;
    this.avatar = avatar;

    initializePlayerInfo();
    initializePlayerInfoSpacing();
    initializePlayerGridPane();
  }

  private void initializePlayerInfo() {
    playerName = new Text(name);
    playerBankroll = new Text("$" + bankroll);
    try {
      playerAvatar = new ImageView(
          new Image(PlayerInfoBox.class.getResource(avatar).toExternalForm()));
    } catch (Exception e) {
      e.printStackTrace();
    }
    playerAvatar.setFitHeight(AVATAR_HEIGHT);
    playerAvatar.setFitWidth(AVATAR_WIDTH);
  }

  private void initializePlayerInfoSpacing() {
    ColumnConstraints nameColumn = new ColumnConstraints();
    nameColumn.setPercentWidth(NAME_COLUMN_WIDTH_PERCENTAGE);
    ColumnConstraints bankrollColumn = new ColumnConstraints();
    bankrollColumn.setPercentWidth(BANKROLL_COLUMN_WIDTH_PERCENTAGE);
    ColumnConstraints avatarColumn = new ColumnConstraints();
    avatarColumn.setPercentWidth(AVATAR_COLUMN_WIDTH_PERCENTAGE);
    this.getColumnConstraints().addAll(nameColumn, bankrollColumn, avatarColumn);
  }

  private void initializePlayerGridPane() {
    this.add(playerName, NAME_POSITION, 0);
    this.add(playerBankroll, BANKROLL_POSITION, 0);
    this.add(playerAvatar, AVATAR_POSITION, 0);

    this.getStyleClass().add("playerInfoBox");
    this.setMinSize(INFO_BOX_MIN_WIDTH, INFO_BOX_MIN_HEIGHT);
  }

  public Text getBankroll() {
    return playerBankroll;
  }

}
