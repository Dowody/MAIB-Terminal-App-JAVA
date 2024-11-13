// HELLO WORLD

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class  App {

    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String GREEN = "\u001B[32m";
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";

    public static Scanner sc = new Scanner(System.in);

    public static List<User> usersDataList = DataSystem.readUsersData();
    public static List<Card> cardsDataList = DataSystem.readCardsData();
    public static List<Transaction> transactionDataList = DataSystem.readTransactionsData();

    public static String capitalizedFullName;
    public static String currentLoggedUser;

    public static void main(String[] args) {
        clearTerminal();
        LoginMenu();
        delayTime();
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
        String fullName = sc.nextLine().trim();
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
                System.out.println(RED + "The phone number should not contain any alphabetic characters." + RESET);
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

    public static void LoginUser() {
        boolean loginSuccesful = false;
        while (true) {
            clearTerminal();
            System.out.println(GREEN + "~~~~ MAIB ~~~~" + RESET);
            System.out.println(BLUE + "\n-> Login Into Your Account" + RESET);

            System.out.print("\nEnter Your Username: ");
            String username = sc.nextLine().toLowerCase();

            boolean usernameExists = false;
            String correctPassword = "";

            for (User user : usersDataList) {
                if (user.getUsername().equals(username)) {
                    usernameExists = true;
                    capitalizedFullName = user.getFullName();
                    currentLoggedUser = capitalizedFullName;
                    correctPassword = user.getPassword();
                    break;
                }
            }

            if (!usernameExists) {
                clearTerminal();
                System.out.println(RED + "Nonexistent username." + RESET);
                delayTime();
                break;
            }

            System.out.print("Enter Your Password: ");
            String password = sc.nextLine();

            if (!correctPassword.equals(password)) {
                clearTerminal();
                System.out.println(RED + "The password doesn't match." + RESET);
                delayTime();
                break;
            }

            clearTerminal();
            loginSuccesful = true;
            System.out.println(GREEN + "Login Successful! Welcome to MAIB! " + RESET);
            delayTime();
            clearTerminal();
            System.out.print(GREEN + sayHello() + " " + capitalizedFullName + "!" + RESET);
            delayTime();
            break;
        }

        if (loginSuccesful) {

            boolean cardFound = false;

            for (Card cardName: cardsDataList)
            {
                if (cardName.getCardHolderName().equals(currentLoggedUser))
                {
                    MainMenu();
                    cardFound = true;
                    break;
                }
            }

            if (!cardFound) {
                CardMenu();
            }
        }
    }

    public static void CardMenu()
    {
        clearTerminal();
        System.out.println(GREEN + "~~~~ MAIB ~~~~" + RESET);
        System.out.println(BLUE + "\n-> No Personal Cards!" + RESET);

        System.out.println("\n1. Create Your New Card\n2. Log Out");
        System.out.print(YELLOW + "\nEnter your choice: " + RESET);
        String choice = sc.nextLine();

        switch (choice)
        {
            case "1":
                CreateNewCard();
                break;
            case "2":
                break;

            default:
                clearTerminal();
                System.out.println(RED + "Invalid choice!" + RESET);
                delayTime();
                CardMenu();

        }
    }

    public static void CreateNewCard()
    {
        clearTerminal();
        System.out.println(GREEN + "~~~~ MAIB ~~~~" + RESET);
        System.out.println(BLUE + "\n-> Create a New Card" + RESET);

        System.out.println("\nPlease select the card type:\n");
        System.out.println("1. MAIB Daily -> Best everyday card");
        System.out.println("2. MAIB Freelance -> Flexible, mobile card");
        System.out.println("3. MAIB Gama Universal -> Free Cashback");
        System.out.println("4. MAIB Gama Premium -> Premium travel card");

        System.out.print(YELLOW + "\nChoose your card: " + RESET);
        String choice = sc.nextLine();

        String cardType = null;

        if (choice.equals("1")) { cardType = "MAIB Daily"; }
        else if (choice.equals("2")) { cardType = "MAIB Freelance"; }
        else if (choice.equals("3")) { cardType = "MAIB Gama Universal"; }
        else if (choice.equals("4")) { cardType = "MAIB Gama Premium"; }
        else
        {
            clearTerminal();
            System.out.println(RED + "Invalid card option" + RESET);
            delayTime();
            CardMenu();
            return;
        }

        String cardNumber = generateCardNumber();
        String cardExpiryDate = generateCardExpiryDate();
        String cardCVV = generateCardCVV();

        clearTerminal();
        System.out.println(GREEN + "~~~~ MAIB ~~~~" + RESET);
        System.out.println(BLUE + "\n-> Create a New Card" + RESET);
        System.out.println("\nPlease select the card currency:\n");
        System.out.println("1. EUR");
        System.out.println("2. USD");
        System.out.println("3. MDL");

        System.out.print(YELLOW + "\nChoose your card: " + RESET);
        String currency = sc.nextLine();

        String cardCurrency = null;

        if (currency.equals("1")) { cardCurrency = "EUR"; }
        else if (currency.equals("2")) { cardCurrency = "USD"; }
        else if (currency.equals("3")) { cardCurrency = "MDL"; }
        else
        {
            clearTerminal();
            System.out.println(RED + "Invalid card currency" + RESET);
            delayTime();
            CardMenu();
            return;
        }

        int cardInitialBalance = 0;

        Card newCard = new Card(currentLoggedUser, cardNumber, cardExpiryDate, cardCVV, cardType, cardCurrency, cardInitialBalance);

        boolean cardAlreadyExists = false;
        for (Card card: cardsDataList)
        {
            if (card.getCardHolderName().equals(currentLoggedUser))
            {
                cardAlreadyExists = true;
            }
        }

        if (!cardAlreadyExists)
        {
            if (DataSystem.createCard(newCard))
            {
                cardsDataList = DataSystem.readCardsData();
                MainMenu();
            }
        }
        else
        {
            System.out.println("Your card already exists!");
        }
    }

    public static void MainMenu()
    {
        clearTerminal();
        System.out.println(GREEN + "~~~~ MAIB ~~~~" + RESET);
        System.out.println(BLUE + "\n-> Main Menu" + RESET);

        for (Card card: cardsDataList)
        {
            if (currentLoggedUser.equals(card.getCardHolderName()))
            {

                cardsDataList = DataSystem.readCardsData();

                double cardBalance = card.getCardBalance();
                String formattedCurrency = null;

                if (card.getCardCurrency().equals("MDL"))
                {
                    NumberFormat currency = NumberFormat.getNumberInstance();
                    currency.setMinimumFractionDigits(2);
                    currency.setMaximumFractionDigits(2);

                    formattedCurrency = currency.format( cardBalance);
                    formattedCurrency = formattedCurrency + " MDL";
                }
                else if (card.getCardCurrency().equals("USD"))
                {
                    NumberFormat currency = NumberFormat.getCurrencyInstance(Locale.US);
                    formattedCurrency = currency.format( cardBalance);
                }
                else if (card.getCardCurrency().equals("EUR"))
                {
                    NumberFormat currency = NumberFormat.getCurrencyInstance(Locale.FRANCE);
                    formattedCurrency = currency.format( cardBalance);
                }

                System.out.println("\nCard Balance: " + formattedCurrency);
            }
        }

        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Previous Transactions\n");
        transactionDataList = DataSystem.readTransactionsData();
        GetLastTransactions();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        System.out.println("\n1. Fulfill Balance");
        System.out.println("2. Send Money");
        System.out.println("3. View All Transactions");
        System.out.println("4. See Card Details");

        System.out.print(YELLOW + "\nEnter your choice: " + RESET);
        String choice = sc.nextLine();

        switch (choice)
        {
            case "1":
                FulfilBalance();
                break;
            case "2":
                SendMoney();
                break;
            case "3":
                ViewAllTransactions();
                break;
            case "4":
                SeeCardDetails();
                break;

            default:
                clearTerminal();
                System.out.println(RED + "Invalid choice!" + RESET);
                delayTime();
                MainMenu();
        }

    }

    private static void SeeCardDetails()
    {
        clearTerminal();         System.out.print("\033[?25h");
        System.out.println(GREEN + "~~~~ MAIB ~~~~" + RESET);
        System.out.println(BLUE + "\n-> Card Details\n" + RESET);

        for (Card card: cardsDataList)
        {
            if (card.getCardHolderName().equals(currentLoggedUser))
            {
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
                System.out.println(BLUE + "§" + RESET + " Name Surname           " + card.getCardHolderName());
                System.out.println(BLUE + "§" + RESET + " Card Number            " + card.getCardNumber());
                System.out.println(BLUE + "§" + RESET + " Expires                " + card.getCardExpiryDate());
                System.out.println(BLUE + "§" + RESET + " CVV                    ***");
                System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

                System.out.println("\n1. View CVV");
                System.out.println("2. Main Menu");
                System.out.print(YELLOW + "\nEnter your choice: " + RESET);
                String choice = sc.nextLine();

                switch (choice)
                {
                    case "1":
                        if (VerifyUser("Card Details"))
                        {
                            System.out.print("\033[?25l");
                            clearTerminal();
                            System.out.println(GREEN + "~~~~ MAIB ~~~~" + RESET);
                            System.out.println(BLUE + "\n-> Card Details\n" + RESET);

                            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
                            System.out.println(BLUE + "§" + RESET + " Name Surname           " + card.getCardHolderName());
                            System.out.println(BLUE + "§" + RESET + " Card Number            **** **** " + maskCardNumber(card.getCardNumber()));
                            System.out.println(BLUE + "§" + RESET + " Expires                *****");
                            System.out.println(BLUE + "§ " + RESET + "CVV                    " + card.getCardCVV());
                            System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                            delayTime();
                            SeeCardDetails();
                        }
                        else
                        {
                            SeeCardDetails();
                        }

                        break;
                    case "2":
                        MainMenu();
                        break;
                    default:
                        clearTerminal();
                        System.out.println(RED + "Invalid choice!" + RESET);
                        delayTime();
                        SeeCardDetails();
                }
            }
        }
    }

    public static void SendMoney()
    {
        clearTerminal();
        System.out.println(GREEN + "~~~~ MAIB ~~~~" + RESET);
        System.out.println(BLUE + "\n-> Send Money\n" + RESET);


        System.out.print("Sender Full Name: ");
        String senderFullName = sc.nextLine();

        System.out.print("Sender Card Number: ");
        String senderCardNumber = sc.nextLine();

        System.out.println("\n1. MDL ");
        System.out.println("2. USD ");
        System.out.println("3. EUR ");

        System.out.print(YELLOW + "\nChoose wanted currency: " + RESET);
        String currencyChosen = sc.nextLine();

        switch (currencyChosen)
        {
            case "1":
                currencyChosen = "MDL";
                break;
            case "2":
                currencyChosen = "USD";
                break;
            case "3":
                currencyChosen = "EUR";
                break;
            default:
                clearTerminal();
                System.out.println(RED + "Invalid currency!" + RESET);
                delayTime();
                MainMenu();
        }

        clearTerminal();
        System.out.println(GREEN + "~~~~ MAIB ~~~~" + RESET);
        System.out.println(BLUE + "\n-> Send Money\n" + RESET);
        for (Card card: cardsDataList)
        {
            if (currentLoggedUser.equals(card.getCardHolderName()))
            {
                double cardBalance = card.getCardBalance();
                System.out.println(YELLOW + "Converted Balance: " + RESET + currencyFormatter(currencyChosen, convertCurrency(card.getCardCurrency(), currencyChosen, card.getCardBalance())));
                break;
            }
        }
        System.out.print(YELLOW + "\nEnter Sending Amount: " + RESET);
        double sendAmount = sc.nextDouble();
        sc.nextLine();

        if (sendAmount <= 0) {
            clearTerminal();
            System.out.println(RED + "Invalid amount entered!" + RESET);
            delayTime();
            MainMenu();
            return;
        }

        boolean senderCardFound = false;

        for (Card card : cardsDataList) {
            if (card.getCardHolderName().equals(senderFullName) && card.getCardNumber().equals(senderCardNumber)) {
                clearTerminal();
                senderCardFound = true;

                boolean isConfirmed = confirmTransaction(card, sendAmount, currencyChosen);

                if (isConfirmed) {
                    processTransaction(card, sendAmount, currencyChosen);
                }
            }
        }

        if (!senderCardFound) {
            clearTerminal();
            System.out.println(RED + "Incorrect data provided!" + RESET);
            delayTime();
            MainMenu();
        }
    }

    private static boolean confirmTransaction(Card card, double sendAmount, String currencyChosen)
    {
        while (true)
        {
            System.out.println(GREEN + "~~~~ MAIB ~~~~" + RESET);
            System.out.println(BLUE + "\n-> Send Money\n" + RESET);

            String formattedSendAmount = currencyFormatter(currencyChosen, sendAmount);

            System.out.println(formattedSendAmount + " will be sent to\n\n" + BLUE + card.getCardNumber() + "\n" + card.getCardHolderName() + RESET);

            System.out.println("\n1. Send Money");
            System.out.println("2. Cancel ");
            System.out.print(YELLOW + "\nPlease confirm your operation: " + RESET);

            String confirmation = sc.nextLine();

            if (confirmation.equals("1"))
            {
                return true;
            }
            else if (confirmation.equals("2"))
            {
                return false;
            }
            else
            {
                clearTerminal();
                System.out.println(RED + "Invalid choice!" + RESET);
                delayTime();
            }
        }
    }

    private static void processTransaction(Card senderCard, double sendAmount, String currencyChosen)
    {
        double convertedToSendAmount = sendAmount;

        for (Card currentUserCard : cardsDataList)
        {
            if (currentUserCard.getCardHolderName().equals(currentLoggedUser))
            {
                convertedToSendAmount = convertCurrency(currencyChosen, currentUserCard.getCardCurrency(), sendAmount);

                if (currentUserCard.getCardBalance() >= convertedToSendAmount)
                {
                    currentUserCard.setCardBalance(currentUserCard.getCardBalance() - convertedToSendAmount);
                    double finalAmountToSender = currencyChosen.equals(senderCard.getCardCurrency())
                            ? sendAmount
                            : convertCurrency(currentUserCard.getCardCurrency(), senderCard.getCardCurrency(), sendAmount);

                    senderCard.setCardBalance(senderCard.getCardBalance() + finalAmountToSender);
                    DataSystem.saveCardsData(cardsDataList);

                    String transactionTitleSender = "P2P to " + senderCard.getCardHolderName();
                    String transactionTitleReceiver = "P2P from " + currentUserCard.getCardHolderName();
                    LocalDateTime currentDateTime = LocalDateTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    DataSystem.writeTransaction(new Transaction(currentLoggedUser, "P2P", transactionTitleSender, currentDateTime, sendAmount, currencyChosen));
                    DataSystem.writeTransaction(new Transaction(senderCard.getCardHolderName(), "P2P", transactionTitleReceiver, currentDateTime, sendAmount, currencyChosen));

                    clearTerminal();
                    System.out.println(GREEN + "The amount was successfully sent!" + RESET);
                    delayTime();
                    MainMenu();
                }
                else
                {
                    clearTerminal();
                    System.out.println(RED + "Insufficient funds!" + RESET);
                    delayTime();
                    MainMenu();
                }
            }
        }
    }



    public static void GetLastTransactions()
    {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("d MMMM HH:mm");
        int count = 0;
        boolean transactionsFound = false;

        for (Transaction trans: transactionDataList)
        {
            if (count >= 3) break;

            if (currentLoggedUser.equals(trans.getAccountID()))
            {
                transactionsFound = true;

                LocalDateTime transactionTime = trans.getTransactionTime();
                String formattedTime = transactionTime.format(outputFormatter);

                double transactedAmount = trans.getTransactionAmount();
                String formattedCurrency = null;

                formattedCurrency = currencyFormatter(trans.getTransactionCurrency(), transactedAmount);

                if (trans.getTransactionType().equals("Supply"))
                {
                    System.out.printf(BLUE + "§ " + RESET + "%-25s" + GREEN + "%17s" + RESET + "%n", trans.getTransactionTitle(), "+" + formattedCurrency);
                }
                else if (trans.getTransactionType().equals("P2P"))
                {
                    if (trans.getTransactionTitle().startsWith("P2P to "))
                    {
                        System.out.printf(BLUE + "§ " + RESET + "%-25s" + RED + "%17s" + RESET + "%n", trans.getTransactionTitle(), "-" + formattedCurrency);
                    }
                    else
                    {
                        System.out.printf(BLUE + "§ " + RESET + "%-25s" + GREEN + "%17s" + RESET + "%n", trans.getTransactionTitle(), "+" + formattedCurrency);
                    }
                }
                System.out.println("  " + formattedTime + "\n");
                count++;
            }
        }

        if (!transactionsFound) {
            System.out.println("No previous transactions!");
        }
    }

    public static void ViewAllTransactions()
    {
        clearTerminal();
        System.out.println(GREEN + "~~~~ MAIB ~~~~" + RESET);
        System.out.println(BLUE + "\n-> All Transactions\n" + RESET);

        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("d MMMM HH:mm");
        boolean transactionsFound = false;

        for (Transaction trans: transactionDataList)
        {
            if (currentLoggedUser.equals(trans.getAccountID()))
            {
                transactionsFound = true;

                LocalDateTime transactionTime = trans.getTransactionTime();
                String formattedTime = transactionTime.format(outputFormatter);

                double transactedAmount = trans.getTransactionAmount();
                String formattedCurrency = null;

                formattedCurrency = currencyFormatter(trans.getTransactionCurrency(), transactedAmount);

                if (trans.getTransactionType().equals("Supply"))
                {
                    System.out.printf(BLUE + "§ " + RESET + "%-25s" + GREEN + "%17s" + RESET + "%n", trans.getTransactionTitle(), "+" + formattedCurrency);
                }
                else if (trans.getTransactionType().equals("P2P"))
                {
                    if (trans.getTransactionTitle().startsWith("P2P to "))
                    {
                        System.out.printf(BLUE + "§ " + RESET + "%-25s" + RED + "%17s" + RESET + "%n", trans.getTransactionTitle(), "-" + formattedCurrency);
                    }
                    else
                    {
                        System.out.printf(BLUE + "§ " + RESET + "%-25s" + GREEN + "%17s" + RESET + "%n", trans.getTransactionTitle(), "+" + formattedCurrency);
                    }
                }
                System.out.println("  " + formattedTime + "\n");
            }
        }

        if (!transactionsFound) {
            System.out.println("No previous transactions!");
        }

        System.out.println("\n1. Main Menu");

        System.out.print(YELLOW + "\nEnter your choice: " + RESET);
        String choice = sc.nextLine();

        switch (choice)
        {
            case "1":
                MainMenu();
                break;
            default:
                clearTerminal();
                System.out.println(RED + "Invalid choice!" + RESET);
                delayTime();
                ViewAllTransactions();
        }


    }

    public static void FulfilBalance()
    {
        clearTerminal();
        System.out.println(GREEN + "~~~~ MAIB ~~~~" + RESET);
        System.out.println(BLUE + "\n-> Fulfill Balance" + RESET);

        System.out.print(YELLOW + "\nEnter the deposit amount: " + RESET);
        double newAmount = sc.nextDouble();
        sc.nextLine();

        boolean balanceUpdated = false;

        for (Card card : cardsDataList)
        {
            if (currentLoggedUser.equals(card.getCardHolderName()))
            {
                card.setCardBalance(card.getCardBalance() + newAmount);
                balanceUpdated = true;
                break;
            }
        }

        if (balanceUpdated) {
            clearTerminal();

            for (Card card: cardsDataList)
            {
                if (currentLoggedUser.equals(card.getCardHolderName()))
                {

                    String cardNumber = card.getCardNumber();
                    String maskedCardNumber = maskCardNumber(cardNumber);

                    String transactionTitle = "Supply for " + maskedCardNumber;
                    LocalDateTime currentDateTime = LocalDateTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    DataSystem.writeTransaction(new Transaction(currentLoggedUser, "Supply", transactionTitle, currentDateTime, newAmount, card.getCardCurrency()));
                }

            }
            System.out.println(GREEN + "Your money was deposited successfully!" + RESET);
            delayTime();
            DataSystem.saveCardsData(cardsDataList);
            MainMenu();
        } else {
            clearTerminal();
            System.out.println(RED + "Error updating balance!" + RESET);
        }
    }

    public static boolean VerifyUser(String methodName)
    {
        clearTerminal();
        System.out.println(GREEN + "~~~~ MAIB ~~~~" + RESET);
        System.out.println(BLUE + "\n-> " + methodName + "\n" + RESET);

        System.out.println("We verify it is you...");
        System.out.print("\nEnter Your Password: ");
        String password = sc.nextLine();

        for (User user: usersDataList)
        {
            if (user.getFullName().equals(currentLoggedUser))
            {
                if (!user.getPassword().equals(password)) {
                    clearTerminal();
                    System.out.println(RED + "The password doesn't match." + RESET);
                    delayTime();
                    return false;
                }
            }
        }

        return true;
    }


    // Generator voids
    public static String generateCardNumber()
    {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 16; i++)
        {
            if (i > 0 && i % 4 == 0)
            {
                sb.append(" ");
            }
            sb.append(random.nextInt(10));
        }

        return sb.toString();
    }

    public static String generateCardExpiryDate()
    {
        LocalDate currentDate = LocalDate.now();
        LocalDate expiryDate = currentDate.plusYears(4);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");
        String formattedDate = expiryDate.format(formatter);

        StringBuilder sb = new StringBuilder();
        sb.append(formattedDate);
        String cardExpiryDate = sb.toString();

        return cardExpiryDate;
    }


    public static String generateCardCVV()
    {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 3; i++)
        {
            sb.append(random.nextInt(10));
        }

        return sb.toString();
    }


    // CONVERTORS
    public static double convertToMDL(String currencyChosen, double sendAmount)
    {
        double exchangeRate = switch (currencyChosen) {
            case "USD" -> 17.41;
            case "EUR" -> 19.45;
            default -> 1.0;
        };

        return sendAmount * exchangeRate;
    }

    public static double convertToEUR(String currencyChosen, double sendAmount)
    {
        double exchangeRate = switch (currencyChosen) {
            case "USD" -> 0.90;
            case "MDL" -> 0.052;
            default -> 1.0;
        };

        return sendAmount * exchangeRate;
    }

    public static double convertToUSD(String currencyChosen, double sendAmount)
    {
        double exchangeRate = switch (currencyChosen) {
            case "EUR" -> 1.12;
            case "MDL" -> 0.058;
            default -> 1.0;
        };

        return sendAmount * exchangeRate;
    }

    public static double convertCurrency(String currencyFrom, String currencyTo, double amount)
    {
        switch (currencyFrom)
        {
            case "EUR":
                return currencyTo.equals("MDL") ? convertToMDL(currencyFrom, amount):
                       currencyTo.equals("USD") ? convertToUSD(currencyFrom, amount):
                       amount;
            case "MDL":
                return currencyTo.equals("EUR") ? convertToEUR(currencyFrom, amount) :
                       currencyTo.equals("USD") ? convertToUSD(currencyFrom, amount) :
                       amount;
            case "USD":
                return currencyTo.equals("EUR") ? convertToEUR(currencyFrom, amount) :
                       currencyTo.equals("MDL") ? convertToMDL(currencyFrom, amount) :
                       amount;
            default:
                return amount;
        }
    }




    // UI voids
    private static String maskCardNumber(String cardNumber)
    {
        return "**** " + cardNumber.substring(cardNumber.length() - 4);
    }

    public static String currencyFormatter(String currencyChosen, double amount)
    {
        String formattedCurrency = "";

        for (Card card: cardsDataList)
        {
            if (currencyChosen.equals("MDL"))
            {
                NumberFormat currency = NumberFormat.getNumberInstance();
                currency.setMinimumFractionDigits(2);
                currency.setMaximumFractionDigits(2);

                formattedCurrency = currency.format( amount);
                formattedCurrency = formattedCurrency + " MDL";
            }
            else if (currencyChosen.equals("USD"))
            {
                NumberFormat currency = NumberFormat.getCurrencyInstance(Locale.US);
                formattedCurrency = currency.format( amount);
            }
            else if (currencyChosen.equals("EUR"))
            {
                NumberFormat currency = NumberFormat.getCurrencyInstance(Locale.FRANCE);
                formattedCurrency = currency.format( amount);
            }
        }

        return formattedCurrency;
    }

    public static String sayHello()
    {
        LocalTime currentTime = LocalTime.now();
        int hour = currentTime.getHour();

        String greetingMessage;

        if (hour >= 5 && hour <= 12)
        {
            greetingMessage = "Good morning";
        }
        else if (hour >= 12 && hour < 17)
        {
            greetingMessage = "Good afternoon";
        }
        else
        {
            greetingMessage = "Good evening";
        }

        return greetingMessage;
    }

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