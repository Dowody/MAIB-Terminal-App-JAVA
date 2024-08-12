// HELLO WORLD


import java.util.Scanner;

public class App {

    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String GREEN = "\u001B[32m";
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

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
                default:
                    clearTerminal();
                    System.out.println(RED + "Invalid choice!" + RESET);
                    delayTime();
            }
        }



    }

    // Log In Menu Methods
    public static void RegisterUser()
    {
        clearTerminal();
        System.out.println("~~~~ MAIB ~~~~");
        System.out.println("\n-> Register yourself");

        System.out.print("\nEnter Your First Name: ");
        String firstName = sc.nextLine();

        System.out.print("Enter Your Second: ");
        String secondName = sc.nextLine();

        System.out.print("Enter Phone Number: ");
        String phoneNumber = sc.nextLine();


    }


    // UI voids

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
}