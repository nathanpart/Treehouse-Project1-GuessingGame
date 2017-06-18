import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

class Prompter {
  private BufferedReader inputReader;               //Reader for input I/O
  private static Prompter prompterInstance = null;  //Store the one and only instance
 
  // We generally only want one of these floating around so make constructor private
  // and provide a public static factory method for clients to get the instance
  private Prompter() {
    inputReader = new BufferedReader(new InputStreamReader(System.in));
  }
  
  public static Prompter getPrompter() {
    if (prompterInstance == null) {
      prompterInstance = new Prompter();
    }
    return prompterInstance;
  }
  
  //Get a the type of items in jar from user  
  public String getItemType() {
    System.out.print("What type of item?  ");
    try {
      return inputReader.readLine();
    }
    catch ( IOException ioe ) {
      System.out.printf("I/O Error: %s %n",
             ioe.getMessage());
      System.exit(1);
    }
    return "";  //Make compiler happy, runtime should be looking for another exception handler
  }
  
  //Get maximum amount of items from user
  public int getMaxItems(String itemType) {

    // Loop until succefuly returned the count, or console I/O blew up for some reason
    while (true) {
      System.out.printf("What is the maximum amount of %s?  ",
                      itemType);
      try {
        return Integer.parseInt(inputReader.readLine());
      }
      catch (IOException ioe) {
        System.out.printf("I/O Error: %s %n",
                          ioe.getMessage());
        System.exit(1);
      }
      catch (NumberFormatException nfe) {
        System.out.printf("%s: Input must be an integer. %nTry agian.%n%n",
                          nfe.getMessage());
        // While loop sends us back to prompt
      }
    }
  }
  
  //Get a guess from the user
  public int getGuess(Jar jar, int tries) {
    int guess = 0;
    while (true) {
      System.out.printf("Guess #%d:%n", tries);
      System.out.printf("How many %s are in the jar? Pick a number between 1 and %s: ", 
             jar.getItemName(),
             jar.getMaxCount() );
      try {
        guess = Integer.parseInt(inputReader.readLine());
      }
      catch (IOException ioe) {
        System.out.printf("I/O Error: %s %n",
                          ioe.getMessage());
        System.exit(1);
      }
      catch (NumberFormatException nfe) {
        System.out.printf("%s: Input must be an integer. %nTry agian.%n%n",
                          nfe.getMessage());
        continue;
      }
      //If guess is in range break out of input loop
      if (guess > 0 && guess <= jar.getMaxCount()) {
        break;
      }
      System.out.printf("Your guess must be less than %d. %nPlease try again.%n%n", 
                       jar.getMaxCount()+1);
    }
    return guess;
  }
  
  public void printGuessResult(Jar jar, int guess, int tries) {
    if (guess != jar.getCount()) {
      if (guess < jar.getCount()) {
        System.out.println("Sorry, your guess is too low.");
      } else {
        System.out.println("Sorry, your guess is too high.");
      }
      System.out.println("Please try again.");
    } else {
      System.out.printf("CONGRATULATIONS - You got it in %d attempt(s).", tries);
    }
    System.out.println();
  }
  
  public void printSetupHeading() {
    System.out.println();
    System.out.println("GUESSING GAME SETUP");
    System.out.println("-------------------");
  }
  
  public void printPlayerHeading(Jar jar) {
    System.out.println();
    System.out.println("PLAYER GUESSES");
    System.out.println("-------------------");
    System.out.printf("The object of this game is to guess how many %s are in the jar. %n",
                      jar.getItemName());
    System.out.printf("Your guess should be between 1 and %d. %n",
                      jar.getMaxCount());
    System.out.println();
  }
    
}