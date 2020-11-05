package view;

import javafx.scene.text.Text;

public class FrontEndPlayer extends GameDisplayRecipient{
    private String name;
    private int bankroll;
    private Text displayText;

    public FrontEndPlayer(int xLocation, int yLocation, String name, int bankroll){
        super(xLocation,yLocation);
        this.name= name;
        this.bankroll = bankroll;
        displayText = new Text();
        displayText.setText(name + " has $: " + bankroll);
    }

    public void foldDisplay(){
        displayText.setText(name + " has folded");
    }

    public void betDisplay(int bankroll){
        displayText.setText(name + " has $: " + bankroll);
    }

    public String getText(){
        return displayText.getText();
    }

    @Override
    public String toString(){
        return name;
    }
}
