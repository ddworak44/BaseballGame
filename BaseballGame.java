import java.util.Scanner;
import java.util.regex.Pattern;

public class BaseballGame {
    
    private static String getUserInput(Scanner scanner, String pattern, String errorMessage) {
        String input = scanner.nextLine().trim();
        while (!Pattern.matches(pattern, input)) {
            System.out.println(errorMessage);
            input = scanner.nextLine().trim();
        }
        return input;
    }

    private static int calculateDifference(String pitcherChoice, String batterChoice) {
        char pitcherNumber = pitcherChoice.charAt(0);
        char pitcherLetter = pitcherChoice.charAt(1);
        char batterNumber = batterChoice.charAt(0);
        char batterLetter = batterChoice.charAt(1);

        int numberDifference = Math.abs(pitcherNumber - batterNumber);
        int letterDifference = Math.abs(pitcherLetter - batterLetter);

        return numberDifference + letterDifference;
    }

    private static String getSwingOutcome(int difference, int power) {
        if (difference <= 2) {
            if (power >= 75) {
                return "Home run!";
            } else {
                return "Base hit!";
            }
        } else if (difference <= 4) {
            if (power >= 75) {
                return "Long fly ball, but it's caught!";
            } else {
                return "Weak hit, easily caught!";
            }
        } else {
            if (power >= 75) {
                return "Swing and a miss! Strike!";
            } else {
                return "Weak swing and a miss! Strike!";
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continuePlaying = true;
        int i = 0;
        while (i < 10) {
            // Get pitcher's choice
            System.out.println("Pitcher, choose a zone between [0-9] and [A-J]:");
            String pitcherChoice = getUserInput(scanner, "^[0-9][A-J]$", "Invalid input. Please choose a zone between [0-9] and [A-J]:");

            // Get batter's choice
            System.out.println("Batter, choose a zone between [0-9] and [A-J]:");
            String batterChoice = getUserInput(scanner, "^[0-9][A-J]$", "Invalid input. Please choose a zone between [0-9] and [A-J]:");

            // Get batter's power
            System.out.println("Batter, choose a power between [1-100]:");
            String powerStr = getUserInput(scanner, "^[1-9][0-9]{0,2}$", "Invalid input. Please choose a power between [1-100]:");
            int power = Integer.parseInt(powerStr);

            // Calculate difference between pitcher's and batter's choices
            int difference = calculateDifference(pitcherChoice, batterChoice);

            // Determine the outcome of the swing
            String outcome = getSwingOutcome(difference, power);
            System.out.println(outcome);

            // Check if players want to continue
            System.out.println("Do you want to continue playing? (yes/no)");
            String continueInput = getUserInput(scanner, "^(yes|no)$", "Invalid input. Please enter 'yes' or 'no':");
            continuePlaying = continueInput.equalsIgnoreCase("yes");
            i++; 
        }

        scanner.close();
    }

    // Implement getUserInput, calculateDifference, and getSwingOutcome methods
}