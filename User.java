public class User {

    private String fullName;
    private String dateOfBirth;
    private String phoneNumber;
    private String username;
    private String password;

    public User (String firstName, String dateOfBirth, String phoneNumber, String username, String password)
    {
        this.fullName = firstName;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password = password;
    }

    public String getFullName() { return fullName; }

    public String getDateOfBirth() { return dateOfBirth; }

    public String getPhoneNumber() { return phoneNumber; }

    public String getUsername() { return username; }

    public String getPassword() { return password; }



}


