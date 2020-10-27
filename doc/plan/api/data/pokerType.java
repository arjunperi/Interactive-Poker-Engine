package data;

import java.util.Properties;

/**
 * This class is an example of how the data will be processed in order to change some of the rules of how the poker is played
 */
public class pokerType {

    /**
     * This method shows how dealer rules might be changed based on values from a properties file. For example, the number of communal cards
     * to deal between rounds might be read in from a specific game type's properties file, and once reading this value from the file,
     * the changeRules() method would be be called and the number of communal cards could be passed in.
     */
    public void changeDealerRules(Properties dealerProperties){
        //This could pass in an int parameter that specifies the number of communal cards to deal, and the setCommunalCardsDealt would
        //be affected accordingly.
        //For example, if we read the property of Communal Cards from a file to be 2:
        int propertyRead  = 2;
        DealerRules.setCommunalCardsDealt(propertyRead);
    }
}
