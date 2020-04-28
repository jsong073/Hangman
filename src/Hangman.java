/*
This program will play a version of hangman by doing the following:
1. Initialize an ArrayList with a series of predetermined words
2. Randomly choose a word from the ArrayList for the user to guess
3. Prompt the user to guess a letter
    a. If the user guesses a letter correctly, reveal the letter in its correct spot, with blanks to fill in the
        remaining spaces
    b. If the user guesses the word correctly, inform the user that they win
    c. Otherwise, inform the user they are incorrect
4. Repeat step 3 until they've guessed the whole word or guessed incorrectly 6 times

Process:
1. Initialize variables:
    a. "ArrayList words" to store the words to be used in the game
    b. "Random random" to randomly select a word for the game
    c. "String[] letters" to divide the words in the list into their individual letters
    e. "Scanner input" to receive user guesses
    f. "boolean correctGuess" to check if the user has guessed correctly
    g. "String guess" to store the user's guess
    h. "int mistakes" to store the number of times the user guessed incorrectly
    i. "boolean winCondition" to check if the user has won
    j. "ArrayList blankSpaces" to store the letters of the selected word before any guesses have been made
2. Add the words to the ArrayList
3. Randomly select a word from the list and convert it to an ArrayList
4.Start the game by prompting the user for a guess and showing them the empty dashes representing the randomly chosen
    word
    a. The empty dashes can be created using a for loop:
        FOR int i = 0 to letters.length, exclusive
            Print "_"
        ENDFOR
5. Prompt the user for a letter
WHILE the number of mistakes is less than 6 AND the winCondition is false:
        IF user enters "quit" THEN
            break the loop
        ENDIF
    6. Compare the letter to each letter in the word, and replace the "_" with the letter if they match
        FOR int i = 0 to letters.length, exclusive
            IF guess is equal to letters[i] THEN
                Set blankSpaces(i) to letters[i]
                Print out the letter
                Set correctGuess equal to true
            ELSEIF the user guesses the word THEN
                Set winCondition equal to true
            ELSE
                Print out blankSpaces(i) (which should be "_" if the user has not guessed it yet, or the corresponding
                                            letter if the user has already guessed it)
            ENDIF
        ENDFOR
    7. If none of the letters match the guess, than increase mistakes by 1
        IF correctGuess is false THEN
            Increment mistakes
            Print out the number of mistakes
        ELSEIF blankSpaces doesn't contain any "_" (i.e. all letters have been guessed) THEN
            set winCondition to true
        ELSEIF winCondition is equal to true (because the user guessed the whole word at once) THEN
            Print out the guess
        ENDIF
    8. Reset correctGuess to false
ENDWHILE
9. Check if the user has won or not
    IF winCondition is true THEN
        Print "You Win"
    ELSEIF mistakes is greater than or equal to 6 (they incorrectly guessed 6 times) THEN
        Print "You Lose"

 */

import java.util.*;

public class Hangman {
    public static void main (String[] args) {
        //Variables
        ArrayList<String> words = new ArrayList<String>();
        ArrayList<String> blankSpaces = new ArrayList<String>();
        Random random = new Random();
        String[] letters = new String[0];
        String guess = "";
        Scanner input = new Scanner(System.in);
        boolean correctGuess = false;
        int mistakes = 0;
        int randNum = 0;
        boolean winCondition = false;

        words.add("tree");
        words.add("rain");
        words.add("bear");
        words.add("encourage");
        words.add("promise");
        words.add("soup");
        words.add("chess");
        words.add("insurance");
        words.add("pancakes");
        words.add("stream");

        randNum = random.nextInt(words.size());
        letters = words.get(randNum).split("");
        System.out.println(Arrays.toString(letters));
        for (int i = 0; i < letters.length; i++) {
            blankSpaces.add("_");
        }

        System.out.println("Let's play a game of hangman!");
        System.out.print("Here's the word I am thinking of: ");
        for (int i = 0; i < letters.length; i++) {
            System.out.print("_ ");
        }
        System.out.println();

        while (mistakes < 6 && winCondition == false) {
            System.out.print("Guess a letter or type \"quit\" to quit: ");
            guess = input.next();
            if (guess.equalsIgnoreCase("quit")) {       //Will quit loop if "quit" is typed
                break;
            }
            System.out.print("Your guess so far: ");
            for (int i = 0; i < letters.length; i++) {
                if (guess.equalsIgnoreCase(letters[i])) {
                    blankSpaces.set(i, letters[i]);
                    System.out.print(blankSpaces.get(i) + " ");
                    correctGuess = true;
                } else if (guess.equalsIgnoreCase(words.get(randNum))) {
                    correctGuess = true;
                    winCondition = true;
                }
                else {
                    System.out.print(blankSpaces.get(i) + " ");
                }
            }


            if (correctGuess == false) {
                System.out.println("You have guessed incorrectly " + (++mistakes) + "/6 times.");
            } else if (blankSpaces.contains("_") == false) {
                winCondition = true;
            } else if (winCondition == true) {
                System.out.println(guess);
            }
            correctGuess = false;
            System.out.println();
        }

        if (winCondition == true) {
            System.out.println("You Win!");
        } else if (mistakes >= 6) {
            System.out.println("You Lose.");
        }
        System.out.println("Thanks for playing. Goodbye");

    }
}
