import java.util.Random;
import java.io.File; 
import java.io.FileNotFoundException; 
import java.util.Scanner;
 
/**
 * Write a description of class Wheel here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Wheel
{
    // instance variables - replace the example below with your own
    // private CardNode head;
    private CardNode current;
    private String wheelFileName = "Wheel.data";
    private boolean wheelinitialized;
    /**
     * Constructor for objects of class Wheel
     */
    public Wheel()
    {
        // initialise instance variables
        this.wheelinitialized = false;
        this.wheelinitialized = this.initWheel();
    }
    /**
     * 
     */
    private boolean initWheel(){
        Scanner scanner = null;
        File cardFile;
        try{
            cardFile = new File(this.wheelFileName);
            scanner = new Scanner(cardFile);
            //sc.useDelimiter("\\Z");
            while(scanner.hasNextLine()) {
                String cardString = new String(scanner.next());
                
                String[] cardData = cardString.split("-");
                Card newCard = new Card(cardData[0], Float.parseFloat(cardData[1]));
                this.push(newCard);

                //System.out.print(cardData[1]);
            }
            this.wheelinitialized = true;
        }catch(FileNotFoundException e){
            System.out.print("Error occured when initializing wheel.");
            this.wheelinitialized = false;
        }
        finally{
            scanner.close();
        }
        return this.wheelinitialized;
    }
    // simulate spin by cycling through cards for random number of turns 
    public Card spin(){
        //Card card; 
        Random rand = new Random();
        int moveby = (rand.nextInt(50) + 50);
        CardNode temp = this.current;

        int count = 0;
        while(temp.next != null){
            temp = temp.next;
            if(count == moveby){
                this.current = temp;
                break;
            }
            count++;
        }
        return this.current.getData();
    }

    public void push (Card data){
        CardNode newNode = new CardNode();
        CardNode temp = this.current;
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
        }
        this.current = newNode;
    }


    public void append (Card data){
        CardNode newNode = new CardNode();
        CardNode temp = this.current;
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
        }
        //this.current = newNode;
    }


    static class CardNode{
        Card data;
        CardNode next;
        
        public CardNode(){
            this.data = null;
            this.next = null;
        }

        public CardNode(String type, float value, CardNode next){
            this.data = new Card(type, value);
            this.next = next;
        }

        public Card getData(){
            return this.data;
        }
    }
}
