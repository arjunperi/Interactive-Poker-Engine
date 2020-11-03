package view;

public class GameDisplayRecipient {
    private int xLocation;
    private int yLocation;

    public GameDisplayRecipient(int xLocation, int yLocation){
        this.xLocation = xLocation;
        this.yLocation = yLocation;
    }

    public int getX(){
        return xLocation;
    }

}
