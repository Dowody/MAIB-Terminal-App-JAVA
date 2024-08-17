// HELLO WORLD


import javax.xml.crypto.Data;
import java.util.List;
import java.util.Scanner;

public class App {

    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String GREEN = "\u001B[32m";
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";

    public static Scanner sc = new Scanner(System.in);

    public static List<User> usersDataList = DataSystem.readUsersData();

    public static String capitalizedFullName;

    public static void main(String[] args) {

        LoginMenu();

    }

    // Log In Menu Methods
    public static void LoginMenu()
    {
        while (true)
        {
            clearTerminal();
            System.out.println(GREEN + "~~~~ Welcome to MAIB ~~~~" + RESET);

            System.out.println("\n1. Register");
            System.out.println("2. Log in");
            System.out.println("3. Exit");

            System.out.print(YELLOW + "\nEnter your choice: " + RESET);
            String choice = sc.nextLine();

            switch (choice)
            {
                case "1":
                    RegisterUser();
                    break;
                case "2":
                    LoginUser();
                    break;
                case "3":
                    clearTerminal();
                    System.out.print(GREEN + "Closing the app. Have a nice day!");
                    delayTime();
                    clearTerminal();
                    dotsAnimation();
                    System.out.println(RESET);
                    clearTerminal();
                    System.exit(1);

                default:
                    clearTerminal();
                    System.out.println(RED + "Invalid choice!" + RESET);
                    delayTime();
            }
        }
    }

    public static void RegisterUser()
    {
        boolean usernameExists;
        boolean hasDigit = false;
        boolean hasAlphabet = false;

        clearTerminal();
        System.out.println(GREEN + "~~~~ MAIB ~~~~" + RESET);
        System.out.println(BLUE + "\n-> Register Yourself" + RESET);

        System.out.print("\nEnter Your Full Name: ");
        String fullName = sc.nextLine();
        capitalizedFullName = capitalizeWords(fullName);

        for (int i = 0; i < fullName.length(); i++)
        {
            if (Character.isDigit(fullName.charAt(i)))
            {
                hasDigit = true;
                break;
            }
        }
        if (hasDigit)
        {
            clearTerminal();
            System.out.println(RED + "The full name should not contain any digits." + RESET);
            delayTime();
            RegisterUser();
        }

        System.out.print("Enter Your Date of Birth: ");
        String dateOfBirth = sc.nextLine();

        System.out.print("Enter Phone Number: ");
        String phoneNumber = sc.nextLine();
        do {
            hasAlphabet = false;
            for (int i = 0; i < phoneNumber.length(); i++)
            {
                if (Character.isAlphabetic(phoneNumber.charAt(i)))
                {
                    hasAlphabet = true;
                    break;
                }
            }
            if (hasAlphabet) {
                clearTerminal();
                System.out.println(RED + "The phone number should not contain any alphabetic characters." + RESET);  // Corrected error message
                delayTime();

                clearTerminal();
                System.out.print("Enter Phone Number: ");
                phoneNumber = sc.nextLine();
            }
        } while (hasAlphabet);

        clearTerminal();
        System.out.print("How should we call you?: ");
        String username = sc.nextLine();
        username = username.toLowerCase();

        do
        {
            usernameExists = false;

            for (User user: usersDataList)
            {
                if (user.getUsername().equals(username))
                {
                    clearTerminal();
                    System.out.println(RED + "This username already exists! Choose another username." + RESET);
                    delayTime();
                    clearTerminal();
                    System.out.print("How should we call you?: ");
                    username = sc.nextLine();
                    usernameExists = true;
                    break;
                }
            }

        } while (usernameExists);

        System.out.print("Create your new password: ");
        String password = sc.nextLine();

        User newUser = new User(capitalizedFullName, dateOfBirth, phoneNumber, username, password);

        if (DataSystem.registerUser(newUser))
        {
            usersDataList = DataSystem.readUsersData();
        }

    }

    public static void LoginUser()
    {
        boolean usernameExists = false;
        boolean passwordMatch = false;

        clearTerminal();
        System.out.println(GREEN + "~~~~ MAIB ~~~~" + RESET);
        System.out.println(BLUE + "\n-> Login Into Your Account" + RESET);

        System.out.print("\nEnter Your Username: ");
        String username = sc.nextLine();
        username = username.toLowerCase();

        for (User user: usersDataList)
        {
            if (user.getUsername().equals(username))
            {
                usernameExists = true;
                break;
            }
        }

        if (!usernameExists)
        {
            clearTerminal();
            System.out.println(RED + "There are no accounts registered with this name." + RESET);
            delayTime();

            LoginMenu();
        }

        System.out.print("Enter Your Password: ");
        String password = sc.nextLine();

        for (User user: usersDataList)
        {
            if (user.getUsername().equals(username))
            {
                if (user.getPassword().equals(password))
                {
                    passwordMatch = true;
                    break;
                }
            }
        }

        if (!passwordMatch)
        {
            clearTerminal();
            System.out.println(RED + "The password doesn't match. Try again." + RESET);
            delayTime();

            LoginMenu();
        }
        else
        {
            clearTerminal();
            System.out.println(GREEN + "Login Successful! Welcome to MAIB! " + RESET);
            delayTime();
            clearTerminal();
            System.out.println(GREEN + "say hello" + RESET);
            // Go to MainMenu();
        }


    }

    // UI voids
    public static String capitalizeWords(String input)
    {
        String words[] = input.split(" ");
        StringBuilder capitalizedString = new StringBuilder();

        for (String word: words)
        {
            if (word.length() > 0)
            {
                capitalizedString.append(Character.toUpperCase(word.charAt(0)))
                                 .append(word.substring(1).toLowerCase())
                                 .append(" ");
            }
        }

        return capitalizedString.toString().trim();
    }


    public static void clearTerminal()
    {
        System.out.println("\033[H\033[2J");
    }

    public static void delayTime()
    {
        for (int i = 0; i < 3; i++)
        {
            try
            {
                Thread.sleep(500);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void dotsAnimation()
    {
        for (int i = 0; i < 3; i++)
        {
            try
            {
                Thread.sleep(500);
                System.out.print(". ");

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}