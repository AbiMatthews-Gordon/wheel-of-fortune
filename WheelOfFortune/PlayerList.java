
/**
 * Write a description of class PlayerList here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class PlayerList
{
    // instance variables - replace the example below with your own
    private PlayerNode current;

    /**
     * Constructor for objects of class PlayerList
     */
    public PlayerList()
    {
        // initialise instance variables
        this.current =  null;
    }

    public boolean isEmpty(){
        if (this.current == null) {
            return true;
        } else {
            return false;
        }
    }

    public void push (Player data){
        PlayerNode newNode = new PlayerNode();
        PlayerNode temp = this.current;
        newNode.data = data;
        newNode.next = this.current;
        /* If linked list is not null  
        then set the next of last node */
        if(this.current != null){
            while(temp.next != this.current){
                temp = temp.next;
            }
            temp.next = newNode;
        }else{
            newNode.next = newNode;
            this.current = newNode;
        }
    }

    void makePlayerOneCurrent(){
        PlayerNode temp = this.current;
        while(temp.next != null){
            if(temp.getData().getPlayerNumber() == 1){
                break;
            }
            temp = temp.next;
        }
    }

    public Player tallyRound(){
        PlayerNode temp = new PlayerNode();
        float highest = 0 ;
        if(!this.isEmpty()){
            temp = this.current;
            //tempHighest = this.current;
            for(int i = 0; i < 3; i++){
                if(temp.getData().getPlayerRoundTotal() > highest ){
                    highest = temp.getData().getPlayerRoundTotal();
                }
                temp = temp.next;
            }
            for(int i = 0; i < 3; i++){
                if(temp.getData().getPlayerRoundTotal() == highest ){
                    temp.getData().setPlayerGrandTotal(
                        temp.getData().getPlayerGrandTotal()+temp.getData().getPlayerRoundTotal()
                    );
                }
                temp.getData().setPlayerRoundTotal(0);
                temp = temp.next;
            }
        }
        return temp.getData();
    } 

    public Player tallyGame(){
        PlayerNode temp = new PlayerNode();
        float highest = 0 ;
        if(!this.isEmpty()){
            temp = this.current;
            //tempHighest = this.current;
            for(int i = 0; i < 3; i++){
                if(temp.getData().getPlayerGrandTotal() > highest ){
                    highest = temp.getData().getPlayerGrandTotal();
                }
                temp = temp.next;
            }
            for(int i = 0; i < 3; i++){
                if(temp.getData().getPlayerGrandTotal() == highest ){
                    break;
                }
                temp = temp.next;
            }
        }
        return temp.getData();
    } 

    // get current player 
    public Player getCurrentPlayer(){
        return this.current.getData();
    }

    // get next player 
    public Player nextPlayer(){
        //Player player; 
        //Node temp = this.current;

        if(this.current.next != null){
            //temp = temp.next;
            this.current = this.current.next;
        }
        return this.current.getData();
    }

    /**
     * Node class
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    static class PlayerNode{
        private Player data;
        private PlayerNode next;
        public PlayerNode(){
            this.data = null;
            this.next = null;
        }
        public PlayerNode(String playerName, int playerNumber, float playerGrandTotal, float playerRoundTotal, String playerCard, PlayerNode next){
            this.data = new Player(playerName, playerNumber, playerGrandTotal, playerRoundTotal, playerCard);
            this.next = next;
        }

        public Player getData(){
            return this.data;
        }
        public void setData(Player data){

        }
        /**
         * @return the next
         */
        public PlayerNode getNext() {
            return next;
        }
        /**
         * @param next the next to set
         */
        public void setNext(PlayerNode next) {
            this.next = next;
        }
    }
}
