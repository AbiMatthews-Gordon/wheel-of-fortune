
/**
 * Write a description of class Card here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Card
{
    // instance variables - replace the example below with your own
    private float value;
    private String type;
    /**
     * Constructor for objects of class Card
     */
    public Card()
    {
        // initialise instance variables
        value = 0;
        type = "M";
    }
    
    /**
     * Constructor for objects of class Card
     */
    public Card(String type, float val)
    {
        // initialise instance variables
        this.value = val;
        this.type = type;
    }
    
        /**
     * Constructor for objects of class Card
     */
    public Card(Card card)
    {
        // initialise instance variables
        this.value = card.value;
        this.type = card.type;
    }
    
    //Start GetterSetterExtension Source Code
    /**GET Method Propertie value*/
    public float getValue(){
        return this.value;
    }//end method getValue

    /**SET Method Propertie value*/
    public void setValue(float value){
        this.value = value;
    }//end method setValue

    /**GET Method Propertie type*/
    public String getType(){
        return this.type;
    }//end method getType

    /**SET Method Propertie type*/
    public void setType(String type){
        this.type = type;
    }//end method setType

    //End GetterSetterExtension Source Code
//!
}
