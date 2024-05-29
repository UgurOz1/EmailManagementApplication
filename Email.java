package EmailManagementApplication;

import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Email {
    private String firstName;
    private String lastName;
    private String department;
    private String email;
    private String password;
    private String alternativeEmail;

    public Email() {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter your first name: ");
        this.firstName = input.nextLine();

        System.out.print("Enter your last name: ");
        this.lastName = input.nextLine();
        System.out.println("Hello, " + firstName + " " + lastName + "!");
        System.out.println("----------------------------------");

        getDepartment(input);
        System.out.println("Your department: " + department);
        System.out.println("----------------------------------");

        createPassword(input);
        createEmail();
        System.out.println("Your email address has been created: " + email);
        System.out.println("----------------------------------");

        addAlternativeEmail(input);
        System.out.println(this.toString());

        input.close();
    }

    private void getDepartment(Scanner input) {
        System.out.println("DEPARTMENTS: \n" +
                "1) Marketing\n" +
                "2) Development\n" +
                "3) Accounting\n" +
                "Please enter the code of your department:");
        System.out.println("------------------------------");

        int selectedDepartment = input.nextInt();
        input.nextLine(); // Consume the new line after input

        switch (selectedDepartment) {
            case 1:
                this.department = "Marketing";
                break;
            case 2:
                this.department = "Development";
                break;
            case 3:
                this.department = "Accounting";
                break;
            default:
                this.department = "General";
                break;
        }
    }

    private void createPassword(Scanner input) {
        int passwordLength = 6;
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder passwordBuilder = new StringBuilder(passwordLength);

        for (int i = 0; i < passwordLength; i++) {
            int randomIndex = random.nextInt(characters.length());
            passwordBuilder.append(characters.charAt(randomIndex));
        }
        System.out.println("Your temporary password: " + passwordBuilder);
        System.out.println("------------------------------");

        System.out.println("Press 1 to change your password\n" +
                "Press 2 to use the temporary password:");
        System.out.println("------------------------------");

        int choice = input.nextInt();
        input.nextLine(); // Consume the new line after input

        if (choice == 1) {
            System.out.print("Enter your new password: ");
            this.password = input.nextLine();
            System.out.println("Your new password has been successfully created: " + this.password);
        } else {
            this.password = passwordBuilder.toString();
            System.out.println("Temporary password will be used: " + this.password);
        }
    }

    private void createEmail() {
        this.email = firstName.toLowerCase() + "." + lastName.toLowerCase() + "@" + department.toLowerCase() + ".ozcanlar.com";
    }

    private void addAlternativeEmail(Scanner input) {
        Pattern emailPattern = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
        boolean validEmail = false;

        while (!validEmail) {
            System.out.print("Enter your alternative email address: ");

            String enteredEmail = input.nextLine();
            if (emailPattern.matcher(enteredEmail).matches()) {
                this.alternativeEmail = enteredEmail;
                validEmail = true;
            } else {
                System.out.println("Invalid email format. Please enter a valid email address.");
                System.out.println("------------------------------");
            }
        }
    }

    @Override
    public String toString() {
        return "\n-- Email Account Information --\n" +
                "First Name         : " + firstName + "\n" +
                "Last Name          : " + lastName + "\n" +
                "Department         : " + department + "\n" +
                "Email              : " + email + "\n" +
                "Password           : " + password + "\n" +
                "Alternative Email  : " + alternativeEmail + "\n";
    }
}
