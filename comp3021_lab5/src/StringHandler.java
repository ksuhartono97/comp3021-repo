import java.util.Scanner;

public class StringHandler {
    public static String originalText;
    public static String modifiedText;

    public static void toUpper() {
        // TODO 2: Produce a string with the same characters as in the original string,
        //         but all in uppercase
        modifiedText = originalText.toUpperCase();

        System.out.println("Original String: " + originalText);
        System.out.println("String changed to upper case: " + modifiedText);
    }

    public static void reverse() {
        // TODO 3: Produce a string with the same characters as in the original string,
        //         but in reversed order, e.g. "Hello" become "olleH"

        modifiedText = new StringBuilder(originalText).reverse().toString();


        System.out.println("String before reverse: " + originalText);
        System.out.println("String after reverse: " + modifiedText);
    }

    public static void searchInString() {
        System.out.print("Please input a word/character that you are looking for: ");
        Scanner reader = new Scanner(System.in); // Scanner is used for Java input
        String target = reader.next();

        // TODO 4: Find whether the target string / character is inside the original string
        //         if it doesn't exist in the string, output "target is not found"
        //         if it does, output the index of the target
        int index = originalText.indexOf(target);
        if(index == -1) {
            System.out.println("'" + target + "' is not found");
        }
        else {
            System.out.println("Found '" + target + "' at index " + index);
        }
    }

    public static void removeChar() {
        System.out.print("Please input a character that you want to remove: ");
        Scanner reader = new Scanner(System.in); // Scanner is used for Java input
        String target = reader.next();

        System.out.println("String before removing \'" + target + "\': " + originalText);

        // TODO 5: Produce a string having all the characters in the original string,
        //         except those match with the target character

        String regex = "[" + target + "]";

        modifiedText =  originalText.replaceAll(regex, "");

        System.out.println("String after removing \'" + target + "\': " + modifiedText);
    }

    public static void split() {
        System.out.print("Please input a delimiter: ");
        Scanner reader = new Scanner(System.in); // Scanner is used for Java input
        String target = "";
        target = reader.next();

        // TODO 6: Split the string whenever there is a delimiter
        //         Output each substring in separate line

        String[] result = originalText.split(target);

        for(int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }

    }

    public static void shiftString() {
        System.out.print("Please input amount of shift: ");
        Scanner reader = new Scanner(System.in); // Scanner is used for Java input
        int shiftAmount = reader.nextInt();

        // TODO 7: Shift characters forward by the amount specified by shiftAmount
        //         e.g., Input string: "Hello World"
        //               shiftAmount: 3
        //               Result: "rldHello Wo"

        modifiedText = originalText;

        for (int i = 0; i < shiftAmount; i++) {
            modifiedText =  modifiedText.charAt(modifiedText.length() - 1) + modifiedText.substring(0, modifiedText.length() - 1);
        }

        System.out.println("After shifting \"" + originalText + "\" by " + shiftAmount + ": \"" + modifiedText + "\"");
    }

    public static void removeVowels() {
        // TODO 8: Produce a string having all the characters in the original string,
        //         except vowels, i.e. A, E, I, O, U, a, e, i, o, and u

        modifiedText =  originalText.replaceAll("[AEIOUaeiou]", "");

        System.out.println("String before removing vowels: " + originalText);
        System.out.println("After removing all vowels: " + modifiedText);
    }

    public static void main(String args[]){
        System.out.println("Welcome to the String Handling System!");
        System.out.print("Please input something: ");

        Scanner reader = new Scanner(System.in); // Scanner is used for Java input
        originalText = reader.nextLine();

        String option = "";

        boolean dummy = true;
        // TODO 1a: Replace dummy with a correct condition that quit when user input "Q"
        while (!option.equals("Q")) {
            System.out.println("=========== Options ============");
            System.out.println("1: Convert all characters into uppercase");
            System.out.println("2: Reverse the string");
            System.out.println("3: Search specific word in the string");
            System.out.println("4: Remove specfic character");
            System.out.println("5: Split the string");
            System.out.println("6: Shift the string");
            System.out.println("7: Remove all vowels");
            System.out.println("================================");
            System.out.println("Please choose an option (type in Q if you want to quit): ");
            option = reader.next();

            switch (option){
                case "1":
                    toUpper();
                    break;
                case "2":
                    reverse();
                    break;
                case "3":
                    searchInString();
                    break;
                case "4":
                    removeChar();
                    break;
                case "5":
                    split();
                    break;
                case "6":
                    shiftString();
                    break;
                case "7":
                    removeVowels();
                    break;
                default:
                    // TODO 1b: Replace dummy with a correct condition that say "Goodbye!" when user inputs "Q"
                    if(option.equals("Q"))
                        System.out.println("Goodbye!");
                    else
                        System.out.println("Invalid Option! Please try again: ");
                    break;
            }
        }
        reader.close();
    }
}