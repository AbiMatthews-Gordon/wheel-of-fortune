import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
/**
 * Write a description of class Round here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Round
{
    // instance variables - replace the example below with your own
    public static final float vowelCost = 100;
    public static final String[] categories = new String[3];
    public static String[] puzzles = new String[3];
    /*
    public static final String[] categories = {"Person", "Place", "Thing", "Phrase"};
    public static String[][] puzzles = {{
            "HAPPY CUSTOMER", 
            "UNITED NATIONS AMBASSADOR", 
            "MY FATHER'S FATHER", 
            "CHRIS HEMSWORTH",
            "GRANDDAUGHTER", 
            "A GOOD LISTENER", 
            "MAN OF FEW WORDS", 
            "VALEDICTORIAN",
            "GAME SHOW CONTESTANT", 
            "WHISTLEBLOWER", 
            "TRACK-AND-FIELD STAR", 
            "FAMOUS WRITER"
        },{
            "AMPHITHEATERS", 
            "POPULAR TOURIST ATTRACTION", 
            "EARTH MOON SUN & STARS", 
            "BEACHFRONT PROPERTY",
            "SUPERMARKET", 
            "THE GREAT BARRIER REEF", 
            "FAST-FOOD RESTAURANTS", 
            "THE WEST END",
            "DESERT OASIS", 
            "ON THE WATER", 
            "DORMITORIES", 
            "GAME ROOM"
        },{
            "ATMOSPHERE OF TITAN", 
            "BEAUTIFULLY CARVED MOLDINGS", 
            "CARDIO WORKOUT PLAN", 
            "DESIGNER HANDBAG COLLECTION", 
            "ELEGANT FINE DINING", 
            "HIGH SPEED HDMI CABLES",
            "LETTERS OF THE ALPHABET",
            "OPPORTUNITY OF A LIFETIME",
            "REFERENCE MATERIALS",
            "PRODUCT REVIEW",
            "SPEAKERPHONE",
            "TOUCHSCREEN",
        },{
            "A MARTINI SHAKEN NOT STIRRED", 
            "HEY DIDDLE DIDDLE", 
            "I HAVE A DREAM", 
            "TO EACH HIS OWN",
            "DO YOU SOLEMNLY SWEAR", 
            "SHIVER ME TIMBERS", 
            "MARY HAD A LITTLE LAMB", 
            "YOU CAN'T HANDLE THE TRUTH",
            "SPACE THE FINAL FRONTIER", 
            "THE BUCK STOPS HERE", 
            "DOCTOR LAWYER INDIAN CHIEF", 
            "WINTER IS COMING!"
        }
    };*/
    public static final String puzzleFileName = "puzzles.data";
    public static boolean puzzleInitialized = false;
    private String category = "";
    private String puzzle;
    private String solution;
    private GuessQeue guesses; 
    private boolean hasEnded;
    
    /**
     * Constructor for objects of class Round
     */
    public Round()
    {
        // initialise instance variables
        this.puzzle = "";
        this.category = "";
        this.guesses = new GuessQeue();
        this.hasEnded = false;
    }
    
    
    public Round(String category, String puzzle) {
        this.category = category;
        this.guesses = new GuessQeue();
        this.hasEnded = false;
        this.puzzle = puzzle.toUpperCase();
        this.solution = this.puzzle.replaceAll("[a-zA-z]", "_");
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPuzzle() {
        return this.puzzle;
        
    }

    public void setPuzzle(String puzzle) {
        this.puzzle = puzzle;
        this.solution = this.puzzle.replaceAll("[a-zA-z]", "_");
    }

    public String getSolution() {
        return this.solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    /**
     * @return the categories
     */
    public boolean getHasEnded() {
        return this.hasEnded;
    }

    /**
     * @return the guesses
     */
    public GuessQeue getGuesses() {
        return this.guesses;
    }

    boolean solvePuzzle(String guess){
        boolean match = false;
        //System.out.println(this.puzzle.toUpperCase() + "solve" + guess.toUpperCase());
        if( this.puzzle.toUpperCase().equals(guess.toUpperCase()) ){
            this.hasEnded = true;
            match = true;
        }
        return match;
    }

    int processGuess(char guess){
        int occurences = 0;
        int length = this.puzzle.length();
        guess = Character.toUpperCase(guess);
        if(!this.guesses.search(guess)){
            this.guesses.add(guess);
            for(int i=0; i < length; i++){
                if(this.puzzle.charAt(i) == guess){
                    System.out.println(guess + " found at ln: " + i);
                    this.solution = this.solution.substring(0,i) + guess + this.solution.substring(i+1,length);
                    occurences++;
                }
            }
        }

        
        
        //}
        if(occurences > 0 && "AEIOUaeiou".indexOf(guess) != -1){
            occurences = 0 - occurences; //set the occurences to a negative number so that the cost of buting a vowel will be removed
        }

        this.solvePuzzle(this.solution);
        return occurences;
    }


    public static boolean initPuzzles(){
        Scanner scanner = null;
        File puzzlesFile;
        Random rand = new Random();
        int lineNumbers[] = new int[3];
        int count;
        try{
            int lines = (int)Files.lines(Paths.get(Round.puzzleFileName), Charset.defaultCharset()).count();
            puzzlesFile = new File(Round.puzzleFileName);
            scanner = new Scanner(puzzlesFile);
            lineNumbers[0] = rand.nextInt(lines);
            lineNumbers[1] = rand.nextInt(lines);
            lineNumbers[2] = rand.nextInt(lines);
            //sc.useDelimiter("\\Z");
            count = 0;
            while(scanner.hasNextLine()) {
                String puzzleString = new String(scanner.nextLine());
                String[] puzzleData = puzzleString.split("-", 2);
                System.out.print(puzzleData[1]);
                if(lineNumbers[0] == count){
                    Round.categories[0] = puzzleData[0];
                    Round.puzzles[0] = puzzleData[1];
                }else if(lineNumbers[1] == count){
                    Round.categories[1] = puzzleData[0];
                    Round.puzzles[1] = puzzleData[1];
                }else if (lineNumbers[2] == count){
                    Round.categories[2] = puzzleData[0];
                    Round.puzzles[2] = puzzleData[1];
                }

                count++;
                //System.out.print(cardData[1]);
            }
            Round.puzzleInitialized = true;
        }catch(FileNotFoundException e){
            System.out.print("Error occured when initializing wheel.");
            Round.puzzleInitialized = false;
        }catch(IOException e){
            System.out.print("Error occured when initializing wheel.");
            Round.puzzleInitialized = false;
        }
        finally{
            scanner.close();
        }
        return Round.puzzleInitialized;
    }


    class GuessQeue{
        GuessNode head;
        GuessNode tail;
        public boolean isEmpty(){
            return (head == null);
        }

        public char peek(){
            return head.data;
        }

        public void add(char data){
            GuessNode temp = new GuessNode(data);
            if (this.tail != null){
                this.tail.nextNode = temp;
            }
            this.tail = temp;
            System.out.println("x"); 
            if (this.head == null){
                this.head = temp; 
            }

        }
        public boolean search(char key){
            GuessNode temp = new GuessNode();
            if(!this.isEmpty()){
                temp = this.head;
                while(temp != null){
                    if(temp.getData() == key){
                        return true;
                    }
                    temp = temp.getNextNode();
                }
            }
            return false;
        }

        public boolean display(){
            GuessNode temp = new GuessNode();
            if(!this.isEmpty()){
                System.out.print("--------------------------\nLetters used: ");
                temp = this.head;
                while(temp != null){
                    System.out.print(" " + temp.data + " ");
                    
                    temp = temp.getNextNode();
                }
                System.out.println("\n--------------------------");
                return true;
            }
            return false;
        }
    }
    
    class GuessNode{
        char data;
        GuessNode nextNode;
        public GuessNode(){
            this.data = '\0';
            this.nextNode = null;
        }

        public GuessNode(char data){
            this.data = data;
            this.nextNode = null;
        }

        public GuessNode(GuessNode node){
            this.data = node.data;
            this.nextNode = node.nextNode;
        }
        /**
         * @return the nextNode
         */
        public GuessNode getNextNode() {
            return nextNode;
        }

        /**
         * @return the data
         */
        public char getData() {
            return data;
        }

        /**
         * @param data the data to set
         */
        public void setData(char data) {
            this.data = data;
        }

        /**
         * @param nextNode the nextNode to set
         */
        public void setNextNode(GuessNode nextNode) {
            this.nextNode = nextNode;
        }
    }
}
