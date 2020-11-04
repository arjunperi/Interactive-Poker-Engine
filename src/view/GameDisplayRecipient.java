package view;

import javafx.scene.text.Text;

public abstract class GameDisplayRecipient{
    protected int xLocation;
    protected int yLocation;

    public GameDisplayRecipient(int xLocation, int yLocation){
        this.xLocation = xLocation;
        this.yLocation = yLocation;
    }

    protected int getX(){
        return xLocation;
    }


}
