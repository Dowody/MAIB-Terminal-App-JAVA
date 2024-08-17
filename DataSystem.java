import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataSystem {

    private static final Logger LOGGER = Logger.getLogger(DataSystem.class.getName());

    public static final String USERS_DATA_FILE = "users_data.txt";

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
}
