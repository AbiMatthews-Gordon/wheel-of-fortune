import java.util.Scanner;
import java.util.Random;

/**
 * Write a description of class Game here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Game {
    // instance variables - replace the example below with your own
    private Wheel wheel;
    private PlayerList players;
    private RoundStack gameRounds;
    private Round currentRound;
    private Player currentPlayer;
    private boolean roundInitialized;

    /**
     * Constructor for objects of class Game
     */
    public Game() {
        // initialise instance variables
        this.gameRounds = new RoundStack();
        this.wheel = new Wheel();
        this.players = new PlayerList();
        this.roundInitialized = false;
    }
    
    Game(RoundStack gameRounds, String playerGuess) {
        this.gameRounds = gameRounds;
        this.wheel = new Wheel();
        this.players = new PlayerList();
        this.roundInitialized = false;
    }

    Game(Game g) {
        this.gameRounds = g.getGameRounds();
        this.wheel = g.getWheel();
        this.players = g.getPlayerList();
        this.roundInitialized = g.getRoundInitialized();
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param y a sample parameter for a method
     * @return the sum of x and y
     */
    public boolean start() {
        // create players and
        this.players = new PlayerList();
        this.wheel = new Wheel();
        Player player1 = new Player("", 1, 0, 0, null);
        Player player2 = new Player("", 2, 0, 0, null);
        Player player3 = new Player("", 3, 0, 0, null);
        Scanner reader = new Scanner(System.in);
        
        // input player data
        System.out.println("--------------------------------------------------");
        System.out.println("WELCOME TO WHEEL OF FORTUNE");
        
        player1 = enterPlayerDetails(player1, 1);
        this.players.push(player1);
        
        // input player two data
        player2 = enterPlayerDetails(player2, 2);
        this.players.push(player2);
        
        // input player three data
        player3 = enterPlayerDetails(player3, 3);
        this.players.push(player3);

        Round.initPuzzles();
        this.roundInitialized = this.gameRounds.initRounds(); 
        if(this.roundInitialized){
            while(!this.getGameRounds().isEmpty()){
                this.currentRound = this.getGameRounds().pop();
                this.currentPlayer = this.getPlayerList().getCurrentPlayer();
                Card currentCard = null;
                char guess = ' ';
                // int option = 0;
                // give each player a turn indefinatly
                System.out.println("Puzzle: "+this.currentRound.getPuzzle().toUpperCase());
                while(!this.getPlayerList().isEmpty()){
                    boolean TurnEnd = false;
                    currentCard = this.wheel.spin();
                    System.out.println("--------------------------------------------------");
                    System.out.println(this.currentPlayer.getPlayerName()+" your turn has begun.");
                    System.out.println("--------------------------------------------------");
                    System.out.println(this.currentPlayer.getPlayerName()+" you Got a $" +currentCard.getValue()+" card. ");
                    System.out.println("--------------------------------------------------");
                    System.out.println("Puzzle: "+this.currentRound.getSolution().toUpperCase());
                    System.out.println("--------------------------------------------------");
                    
                    if(currentCard.getValue() == 0){
                        TurnEnd = true;
                        this.handleSpecialCard(currentCard);
                    }else{
                        //else if(this.currentPlayer.getPlayerRoundTotal() > 0){
                        
                        // get user guess to determine how score will be moved 
                        int multiplier = this.currentRound.processGuess(
                            this.guess(true)// get user guess
                        );
                        //System.out.println("Multipler: " + multiplier);
                        if(multiplier == 0 || this.currentRound.getHasEnded()){
                            TurnEnd = true;
                        }else {
                            this.calculateScore(multiplier, currentCard);
                        }
                        // reset card and multiplier
                        currentCard = null;
                        multiplier = 0;
                        System.out.println(this.currentPlayer.getPlayerName()+" your score is: " + this.currentPlayer.getPlayerRoundTotal());
                        System.out.println("--------------------------------------------------\n\n");
                        System.out.println("--------------------------------------------------");
                        System.out.println("Puzzle: "+this.currentRound.getSolution().toUpperCase());
                        System.out.println("--------------------------------------------------");
                        //}
                        
                        // If guess is incorrect  end round
                        while(!TurnEnd){
                            guess = 0;
                            System.out.println("Puzzle: "+this.currentRound.getSolution().toUpperCase());
                            // ask the user whether they want to spin
                            while(guess != 'y' && guess != 'n'){
                                System.out.println(this.currentPlayer.getPlayerName() + " would you like to spin the wheel?\nEnter y or n");
                                guess = reader.nextLine().charAt(0);
                            }

                            if(guess == 'y'){
                                currentCard = this.wheel.spin();
                                if(currentCard.getValue() == 0){
                                    this.handleSpecialCard(currentCard);
                                    break;
                                }
                                System.out.println(this.currentPlayer.getPlayerName()+" you Got $" +currentCard.getValue()+". ");
                                multiplier = this.currentRound.processGuess(this.guess(true));
                            }else{
                                multiplier = this.currentRound.processGuess(this.guess(false));
                            }
                            if(multiplier == 0){
                                TurnEnd = true;
                            }else{
                                this.calculateScore(multiplier, currentCard);
                            }
                            System.out.println("--------------------------------------------------");
                            System.out.println(this.currentPlayer.getPlayerName()+" your score is: " + this.currentPlayer.getPlayerRoundTotal());
                            System.out.println("--------------------------------------------------");
                            if(this.currentRound.getHasEnded()){
                                TurnEnd = true;
                            }
                            
                        }
                    }
                    if(this.currentRound.getHasEnded()){
                        System.out.println("_______________________________________________");
                        System.out.println("Round has ended");
                        System.out.println("_______________________________________________\n\n\n\n");
                        // add player to total
                        Player roundWinner = this.players.tallyRound();
                        //TODO: print player scores
                        System.out.println("Congratulations! " + roundWinner.getPlayerName() + ", you won round ");
                        System.out.println(roundWinner.getPlayerName() + " you now have $" + roundWinner.getPlayerGrandTotal() );
                        this.players.makePlayerOneCurrent();
                        break;
                    }
                    //System.out.println(currentCard.getType());
                    System.out.println(this.currentPlayer.getPlayerName() + " your turn has ended");
                    System.out.println("_______________________________________________\n\n");
                    this.currentPlayer = this.getPlayerList().nextPlayer();
                }
            }
            //TODO: tally game
            Player gameWinner = this.players.tallyGame();
            System.out.println("Congratulations! " + gameWinner.getPlayerName() + ", you won round ");
            //TODO: print winner and totals
            // System.out.println(this.getGameRounds().pop().getPuzzle());
        }
        reader.close();


        // put your code here
        return true;
    }

    // Start GetterSetterExtension Source Code
    /** GET Method Propertie gameRound */
    public RoundStack getGameRounds() {
        return this.gameRounds;
    }// end method getGameRound

    /** SET Method Propertie gameRound */
    public void setGameRound(RoundStack gameRounds) {
        this.gameRounds = gameRounds;
    }// end method setGameRound

    /** GET Method Propertie Wheel */
    public Wheel getWheel() {
        return this.wheel;
    }

    /** SET Method Propertie Wheel */
    public void setWheel(Wheel wheel) {
        this.wheel = wheel;
    }

    /** GET Method Propertie players */
    public PlayerList getPlayerList() {
        return this.players;
    }

    /** SET Method Propertie playerList */
    public void setPlayerList(PlayerList playerList) {
        this.players = playerList;
    }

    /** GET Method Propertie roundInitialized */
    public boolean getRoundInitialized() {
        return this.roundInitialized;
    }

    /** SET Method Propertie playerList */
    public void setRoundInitialized(boolean roundInitialized) {
        this.roundInitialized = roundInitialized;
    }

    // End GetterSetterExtension Source Code

    void handleSpecialCard(Card currentCard){
        if(currentCard.getType().equals("L")){
            System.out.println(this.currentPlayer.getPlayerName()+" you lose a turn");
        }else if(currentCard.getType().equals("B")){
            this.currentPlayer.setPlayerRoundTotal(0);
            System.out.println(this.currentPlayer.getPlayerName()+" you are bankrupt.");
        }
    }

    //
    private Player enterPlayerDetails(Player player, int playerNumber){
        Scanner reader = new Scanner(System.in);
        String tempName = "";
        System.out.println("--------------------------------------------------");
        System.out.println("Enter details for player " + playerNumber);
        System.out.println("Name: ");
        while(tempName.equals("")){
            System.out.println("Please enter a valid name: ");
            tempName = reader.nextLine();
        }
        player.setPlayerName(tempName);
        player.setPlayerNumber(playerNumber);
        System.out.println("--------------------------------------------------\n\n");
        reader.close();
        return player;
    }
    // Allows a user to enter a choice or guess 
    char guess(boolean SpinDone){
        Scanner reader = new Scanner(System.in);
        char guess = '1';
        this.currentRound.getGuesses().display();
        System.out.println("What would you like to do?");
        if(SpinDone){
            System.out.println("To make a guess enter the letter.");
        }
        if(this.currentPlayer.getPlayerRoundTotal() > 0){
            //System.out.println("Round total: "+this.currentPlayer.getPlayerRoundTotal());
            System.out.println("To buy a vowel enter the vowel.");
            System.out.println("Press 3 to solve the puzzle");
        }
        while(guess == '1'){           
            System.out.println("Enter a valid choice.");
            try{
                guess = Character.toUpperCase(reader.nextLine().charAt(0));
            }catch(StringIndexOutOfBoundsException e){
                System.out.println("You must enter a valid character");
            }
            if(guess == '3' && this.currentPlayer.getPlayerRoundTotal() > 0){
                System.out.println("You chose to solve the puzzle. Enter your guess");
                String puzzle_guess = reader.nextLine();
                //System.out.println(puzzle_guess);
                if(this.currentRound.solvePuzzle(puzzle_guess)){
                    System.out.println("Congratulations! You guessed correctly!");
                }else{
                    System.out.println("Sorry! That was an incorrect guess.");
                }
                break;
            }else if( "AEIOUaeiou".indexOf(guess) != -1 ){ //If the guess is a vowel
                if(this.currentPlayer.getPlayerRoundTotal() > 100){
                    break;
                }
                //System.out.println("You do not have enough money to buy a vowel ");
            }else if( (Character.isLetter(guess) && SpinDone == true) || guess == '3' ){
                //System.out.println("You entered: " + guess);
                break;
            }
            
            System.out.println("The character you enter is invalid.");
            guess = 1;
        }
        reader.close();
        return guess;
    }

    void calculateScore(int multiplier, Card currentCard){
        if(multiplier > 0){
            this.currentPlayer.setPlayerRoundTotal(
                this.currentPlayer.getPlayerRoundTotal()+(multiplier * currentCard.getValue())
            );
        }else{
            this.currentPlayer.setPlayerRoundTotal(
                this.currentPlayer.getPlayerRoundTotal()+(multiplier * Round.vowelCost)
            );
        }
    }


    class RoundStack {
        private RoundNode top;

        public boolean isEmpty() {
            if (this.top == null) {
                return true;
            } else {
                return false;
            }
        }

        public void push(Round data) {
            RoundNode newNode = new RoundNode(data);

            if (this.top == null) {
                this.top = newNode;
            } else {
                RoundNode temp = top;
                top = newNode;
                newNode.next = temp;
            }
        }

        public Round pop() 
        { 
            Round popped = null; 
            if (this.top == null) { 
                System.out.println("Round stack is empty"); 
            } 
            else { 
                popped = this.top.data; 
                top = this.top.next; 
            } 
            return popped; 
        } 
        public Round peek() 
        { 
            if (this.top == null) { 
                System.out.println("Round stack is empty."); 
                return null; 
            } 
            else { 
                return this.top.data; 
            } 
        }

        public boolean initRounds(){
            Random rand = new Random();
            for(int i=0; i<3; i++){
                //int categoryToUse = (rand.nextInt(3));
                //int puzzleToUse = (rand.nextInt(11));
                //System.out.println(categoryToUse);
                //System.out.println(puzzleToUse);
                Round temp = new Round(Round.categories[i], Round.puzzles[i]);
                this.push(temp);
            }
            if(!this.isEmpty()){
                return true;
            }else{
                return false;
            }
        }
    }

    class RoundNode {
        Round data;
        RoundNode next;

        public RoundNode() {
            this.data = null;
        }

        public RoundNode(Round round) {
            this.data = round;
        }
    }
    // !
}
