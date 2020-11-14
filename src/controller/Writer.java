package controller;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class Writer {
    //cashing out cashes out yourself
    //when you go in as a new player and cash out you need to save the line of the other player who cashed out

    public void cashOutToProperties(FileWriter writer, Properties propertyFile) {
        try {
            propertyFile.store(writer, null);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}

