import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataSystem {

    private static final Logger LOGGER = Logger.getLogger(DataSystem.class.getName());

    public static final String USERS_DATA_FILE = "users_data.txt";
    public static final String USERS_CARDS_FILE = "users_cards_data.txt";
    public static final String USERS_TRANSACTIONS_FILE = "transactions.txt";

    public static boolean registerUser(User user)
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USERS_DATA_FILE, true)))
        {
            writer.write(user.getFullName() + ", " + user.getDateOfBirth() + ", " + user.getPhoneNumber() + ", " + user.getUsername() + ", " + user.getPassword());
            writer.newLine();
            writer.flush();

            App.clearTerminal();
            System.out.println(App.GREEN + "You have successfully been registered!" + App.RESET);
            App.delayTime();

            return true;
        }
        catch (IOException e)
        {
            LOGGER.log(Level.SEVERE, "Error saving user data", e);
            App.clearTerminal();
            System.out.println(App.RED + "Error saving the data!" + App.RESET);
            App.delayTime();

            return false;
        }
    }

    public static List<User> readUsersData()
    {
        List<User> usersDataList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_DATA_FILE)))
        {
            String line;

            while ((line = reader.readLine()) != null)
            {
                String parts[] = line.split(",");

                String fullName = parts[0].trim();
                String dateOfBirth = parts[1].trim();
                String phoneNumber = parts[2].trim();
                String username = parts[3].trim();
                String password = parts[4].trim();

                usersDataList.add(new User(fullName, dateOfBirth, phoneNumber, username, password));
            }
        }
        catch (IOException e)
        {
            LOGGER.log(Level.SEVERE, "Error reading user data", e);
            App.clearTerminal();
            System.out.println(App.RED + "Error reading user data!" + App.RESET);
            App.delayTime();
        }
        return usersDataList;
    }

    public static boolean createCard(Card card)
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USERS_CARDS_FILE, true)))
        {
            writer.write(card.getCardHolderName() + ", " +
                    card.getCardNumber() + ", " +
                    card.getCardExpiryDate() + ", " +
                    card.getCardCVV() + ", " +
                    card.getCardType() + ", " +
                    card.getCardCurrency() + ", " +
                    card.getCardBalance());

            writer.newLine();
            writer.flush();

            App.clearTerminal();
            System.out.println(App.GREEN + "The card has been succesfully created!" + App.RESET);
            App.delayTime();

            return true;
        }
        catch (IOException e)
        {
            LOGGER.log(Level.SEVERE, "Error creating the card", e);
            App.clearTerminal();
            System.out.println(App.RED + "Error creating the card!" + App.RESET);
            App.delayTime();

            return false;
        }
    }

    public static List<Card> readCardsData()
    {
        List<Card> cardsDataList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_CARDS_FILE)))
        {
            String line;

            while ((line = reader.readLine()) != null)
            {
                String parts[] = line.split(",");

                String fullName = parts[0].trim();
                String cardNumber = parts[1].trim();
                String cardExpiryDate = parts[2].trim();
                String cardCVV = parts[3].trim();
                String cardType = parts[4].trim();
                String cardCurrency = parts[5].trim();
                float cardBalance = Float.parseFloat(parts[6].trim());

                cardsDataList.add(new Card(fullName, cardNumber, cardExpiryDate, cardCVV, cardType, cardCurrency, cardBalance));
            }
        }
        catch (IOException e)
        {
            LOGGER.log(Level.SEVERE, "Error reading cards data", e);
            App.clearTerminal();
            System.out.println(App.RED + "Error reading cards data!" + App.RESET);
            App.delayTime();
        }

        return cardsDataList;
    }

    public static void saveCardsData(List<Card> cardsList)
    {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(USERS_CARDS_FILE))) {
            for (Card card : cardsList) {
                String cardData = String.format("%s, %s, %s, %s, %s, %s, %.2f",
                        card.getCardHolderName(),
                        card.getCardNumber(),
                        card.getCardExpiryDate(),
                        card.getCardCVV(),
                        card.getCardType(),
                        card.getCardCurrency(),
                        card.getCardBalance()); // Use %.2f for double with 2 decimal places

                bw.write(cardData);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeTransaction(Transaction transaction)
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USERS_TRANSACTIONS_FILE, true)))
        {
            writer.write(transaction.getAccountID() + ", " +
                             transaction.getTransactionType() + ", " +
                             transaction.getTransactionTitle() + ", " +
                             transaction.getTransactionTime() + ", " +
                             transaction.getTransactionAmount() + "," +
                             transaction.getTransactionCurrency());

            writer.newLine();
            writer.flush();

        }
        catch (IOException e)
        {
            LOGGER.log(Level.SEVERE, "Error writing the transaction", e);
        }
    }

    public static List<Transaction> readTransactionsData()
    {
        List<String> lines = new ArrayList<>();
        List<Transaction> transactionList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_TRANSACTIONS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error reading transaction data", e);
            App.clearTerminal();
            System.out.println(App.RED + "Error reading transaction data!" + App.RESET);
            App.delayTime();
        }

        Collections.reverse(lines);

        for (String line : lines) {
            String[] parts = line.split(",");

            String accountID = parts[0].trim();
            String transactionType = parts[1].trim();
            String transactionTitle = parts[2].trim();
            LocalDateTime transactionTime = LocalDateTime.parse(parts[3].trim());
            double transactionAmount = Double.parseDouble(parts[4].trim());
            String transactionCurrency = parts[5].trim();

            transactionList.add(new Transaction(accountID, transactionType, transactionTitle, transactionTime, transactionAmount, transactionCurrency));
        }

        return transactionList;
    }
}
