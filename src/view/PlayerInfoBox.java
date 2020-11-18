package view;

import javafx.geometry.Insets;
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
  private Text playerAction;
  private ImageView playerAvatar;

  private static final int INFO_BOX_MIN_WIDTH = 200;
  private static final int INFO_BOX_MIN_HEIGHT = 30;

  private static final int AVATAR_WIDTH = 35;
  private static final int AVATAR_HEIGHT = 30;

  private static final int NAME_POSITION = 0;
  private static final int BANKROLL_POSITION = 1;
  private static final int AVATAR_POSITION = 2;
  private static final int ACTION_POSITION = 3;


  public PlayerInfoBox(String name, int bankroll, String avatar) {
    this.name = name;
    this.bankroll = bankroll;
    this.avatar = avatar;

    initializePlayerInfo();
    initializePlayerInfoSpacing();
    initializePlayerGridPane();
  }

  private void initializePlayerInfo() {
    playerAction = new Text();
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
    ColumnConstraints col1 = new ColumnConstraints();
    col1.setPercentWidth(40);
    ColumnConstraints col2 = new ColumnConstraints();
    col2.setPercentWidth(40);
    ColumnConstraints col3 = new ColumnConstraints();
    col3.setPercentWidth(20);
    this.getColumnConstraints().addAll(col1, col2, col3);
  }

  private void initializePlayerGridPane() {
    this.add(playerName, NAME_POSITION, 0);
    this.add(playerBankroll, BANKROLL_POSITION, 0);
    this.add(playerAvatar, AVATAR_POSITION, 0);
    this.add(playerAction, NAME_POSITION, 1);

    this.setHgap(5);
    this.setStyle("-fx-border-color: red");
    this.setPadding(new Insets(0, 0, 0, 5));
    this.setMinSize(INFO_BOX_MIN_WIDTH, INFO_BOX_MIN_HEIGHT);
  }


  public void setBankroll(int bankroll) {
    playerBankroll.setText("$" + bankroll);
  }

  public void setPlayerAction(String action) {
    playerAction.setText(action);
  }

  public Text getBankroll() {
    return playerBankroll;
  }

}
