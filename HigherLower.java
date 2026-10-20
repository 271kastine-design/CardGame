//--------------------------------------------------------------------
// HigherLower.java         by Dale/Joyce/Weems              Chapter 6
//
// Plays In-Between with the user through a command-line interface.
//--------------------------------------------------------------------
import java.util.Scanner;

public class HigherLower
{
    private static final int STARTING_CHIPS = 100;

    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        CardDeck deck = new CardDeck();
        deck.shuffle();
        int chips = STARTING_CHIPS;
        System.out.println("Welcome to In-Between! You start with " + chips + " chips.");

        while (chips > 0 && deck.cardsRemaining() >= 3)
        {
            System.out.println("\nYou have " + chips + " chips.");
            int risk = readInt(input, "How many chips do you want to risk? Enter 0 to cash out: ", 0, chips);
            if (risk == 0)
            {
                System.out.println("You chose to cash out.");
                break;
            }

            Card first = deck.nextCard();
        
            Card second = deck.nextCard();
            System.out.println("First two cards: " + first + " and " + second);

            if (first.compareTo(second) == 0)
            {
                chips += 2;
                System.out.println("A pair! You win 2 chips. You now have " + chips + " chips.");
                continue;
            }

            if (chips - risk >= risk && readYesNo(input, "Double your risk? (y/n) "))
                risk *= 2;

            Card third = deck.nextCard();
            System.out.println("Third card: " + third);

            boolean inBetween = third.compareTo(first) > 0 && third.compareTo(second) < 0
                    || third.compareTo(second) > 0 && third.compareTo(first) < 0;

            if (inBetween)
            {
                chips += risk;
                System.out.println("In between! You win " + risk + " chips.");
            }
            else
            {
                chips -= risk;
                System.out.println("Not in between. You lose " + risk + " chips.");
            }
        }

        if (chips == 0)
        {
            System.out.println("You are out of chips. Game over!");
        }
        else
        {
            System.out.println("Game over. You finish with " + chips + " chips.");
        }
    }

    private static int readInt(Scanner input, String prompt, int minimum, int maximum)
    {
        while (true)
        {
            System.out.print(prompt);
            if (input.hasNextInt())
            {
                int value = input.nextInt();
                if (value >= minimum && value <= maximum)
                    return value;
            }
            else
                input.next();

            System.out.println("Enter a number from " + minimum + " to " + maximum + ".");
        }
    }

    private static boolean readYesNo(Scanner input, String prompt)
    {
        while (true)
        {
            System.out.print(prompt);
            String answer = input.next().trim().toLowerCase();
            if (answer.equals("y") || answer.equals("yes"))
                return true;
            if (answer.equals("n") || answer.equals("no"))
                return false;
            System.out.println("Please answer y or n.");
        }
    }
}