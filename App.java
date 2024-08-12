// HELLO WORLD


import java.util.Scanner;

public class App {

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true)
        {
            System.out.println("~~~~ MAIB Terminal App ~~~~");

            System.out.println("\n1. Register");
            System.out.println("2. Log in");
            System.out.println("3. Exit");

            System.out.print("Enter your choice: ");
            String choice = sc.nextLine();

            switch (choice)
            {
                case "1":
//                    RegisterUser();
                default:
                    clearTerminal();
                    System.out.println("Invalid choice!");

            }
        }



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