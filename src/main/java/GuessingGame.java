import java.io.IOException;

public class GuessingGame {
  
    public static void main(String[] args) {
      Prompter prompter = Prompter.getPrompter();

      prompter.printSetupHeading();
      
      String itemType = prompter.getItemType();
      int itemCount = prompter.getMaxItems(itemType);
      
      Jar jar = new Jar(itemType, itemCount);
      jar.fillJar();

      prompter.printPlayerHeading(jar);
      
      int guess = 0;
      int tries = 1;
      do {
        guess = prompter.getGuess(jar, tries);
        prompter.printGuessResult(jar, guess, tries); 
        tries += 1;
      } while (guess != jar.getCount());
    }
      
}
