import java.util.Scanner;

/**
 * Write a description of class WheelOfFortune here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class WheelOfFortune
{
    /**
    * An example of a method - replace this comment with your own
    *
    * @param  y  a sample parameter for a method
    * @return    the sum of x and y
    */
    public static void main(String[] args)
    {
        Scanner reader = new Scanner(System.in);
        char playGame = 'o';
        Game game;
        try {
            System.out.println("------------------------------------------------");
            System.out.println("|    Welcome to the virtual Wheel Of Fortune.  |"); 
            System.out.println("------------------------------------------------");
            System.out.println("Would you like to play?");
            while(playGame != 'n'){
                while(playGame != 'n' && playGame != 'y'){
                    System.out.println("Enter y or n.");
                    playGame = reader.nextLine().charAt(0);
                }
                if(playGame == 'y'){
                    game = new Game();
                    game.start();
                }
            }
            System.out.println("\n\n\n--------------------------------------------------");
            System.out.println("|    Goodbye from the virtual Wheel Of Fortune.  |"); 
            System.out.println("--------------------------------------------------");
            //System.out.println(crd.getValue());
            //System.out.println("Hello World");
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println("Game could not be started. Contact Administrator.");
        }finally{
            reader.close();
        }
        
    }


    /**
     * Constructor for objects of class WheelOfFortune
     */
    public WheelOfFortune()
    {
        // initialise instance variables
    }


}
