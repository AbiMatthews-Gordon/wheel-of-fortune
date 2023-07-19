
/**
 * Write a description of class Player here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Player
{
    // instance variables - replace the example below with your own
    private String playerName;
    private int playerNumber;
    private float playerGrandTotal = 0;
    private float playerRoundTotal;
    private String playerCard;

    /**
     * Constructor for objects of class Player
     */
    public Player()
    {
        // initialise instance variables
        this.playerName = " ";
        this.playerNumber = 0;
        this.playerGrandTotal = 0;
        this.playerRoundTotal = 0;
        this.playerCard = " ";
    }

    Player(String playerName, int playerNumber, float playerGrandTotal, float playerRoundTotal, String playerCard)
    {
        this.playerName = playerName;
        this.playerNumber = playerNumber;
        this.playerGrandTotal = playerGrandTotal;
        this.playerRoundTotal = playerRoundTotal;
        this.playerCard = playerCard;
    }
    Player(Player p)
    {
        this.playerName = p.playerName;
        this.playerNumber = p.playerNumber;
        this.playerGrandTotal = p.playerGrandTotal;
        this.playerRoundTotal = p.playerRoundTotal;
        this.playerCard = p.playerCard;
    }

    //Start GetterSetterExtension Source Code
    /**GET Method Propertie playerName*/
    public String getPlayerName(){
        return this.playerName;
    }//end method getPlayerName

    /**SET Method Propertie playerName*/
    public void setPlayerName(String playerName){
        this.playerName = playerName;
    }//end method setPlayerName

    /**GET Method Propertie playerNumber*/
    public int getPlayerNumber(){
        return this.playerNumber;
    }//end method getPlayerNumber

    /**SET Method Propertie playerNumber*/
    public void setPlayerNumber(int playerNumber){
        this.playerNumber = playerNumber;
    }//end method setPlayerNumber

    /**GET Method Propertie playerGrandTotal*/
    public float getPlayerGrandTotal(){
        return this.playerGrandTotal;
    }//end method getPlayerGrandTotal

    /**SET Method Propertie playerGrandTotal*/
    public void setPlayerGrandTotal(float playerGrandTotal){
        this.playerGrandTotal = playerGrandTotal;
    }//end method setPlayerGrandTotal

    /**GET Method Propertie playerRoundTotal*/
    public float getPlayerRoundTotal(){
        return this.playerRoundTotal;
    }//end method getPlayerRoundTotal

    /**SET Method Propertie playerRoundTotal*/
    public void setPlayerRoundTotal(float playerRoundTotal){
        this.playerRoundTotal = playerRoundTotal;
    }//end method setPlayerRoundTotal

    /**GET Method Propertie playerCard*/
    public String getPlayerCard(){
        return this.playerCard;
    }//end method getPlayerCard

    /**SET Method Propertie playerCard*/
    public void setPlayerCard(String playerCard){
        this.playerCard = playerCard;
    }//end method setPlayerCard

    //End GetterSetterExtension Source Code
    
    float spinTheWheel()
    {
         return 0;
    }
    String buyAVowel()
    {
        return "";
    }
    boolean solveThePuzzle(String Solution)
    {
        return false;
    }
//!
}
