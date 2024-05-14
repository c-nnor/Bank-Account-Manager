import java.util.ArrayList;
import java.util.Scanner;

/**
 * Program: OOP Bank NI
 * This program is a menu orientated for the Bank to create, view and manage single accounts at a time
 * to withdraw and deposit with a users account overdraft in mind.
 * @author Connor Charnock 50074566
 */

public class Main {
   // Main method to start the program
    public static void main(String[] args) {
        int capacity = 30; // sets the capacity, added as a variable in order to be expandable if needed.
        ArrayList<Account> accountsLst = new ArrayList<>(capacity); // initialises the list to store bank accounts created
        preFillList(accountsLst); // fills the list with a default preset.

        boolean menuRunning = true;
        while (menuRunning) {
            try {
                displayMenu(); // pulls the menu text from this method
                System.out.print("\n\t\t\t\tPlease enter menu choice = ");
                int choice = uiInt();
                // switch to allow users to make a decision within the menu choices
                switch (choice) {
                    case 1:
                        System.out.println("Displaying all account details\n");
                        displayAllAccountDetails(accountsLst); // passes through the accounts list to the display all method
                        break;
                    case 2:
                        System.out.println("Displaying Single Account Details\n");
                        displaySingleAccountDetails(accountsLst); // passes through the accounts list to display single method
                        break;
                    case 3:
                        addNewAccount(accountsLst); // takes user to add new account method, passes list
                        break;
                    case 4:
                        depositMoney(accountsLst); // takes user to the deposit money method, passes list
                        break;
                    case 5:
                        withdrawMoney(accountsLst); // takes user to the withdraw money method, passes list
                        break;
                    case 6:
                        // allows user exit the program within the menu
                        System.out.println("\nQuitting...\n\n....");
                        Thread.sleep(500);
                        System.out.println("\nBye!");
                        menuRunning = false;
                        System.exit(0);
                        break;
                    default:
                        // exception handling for any choices made outside the menu parameters
                        System.out.println("\n*!*\nInvalid Menu choice. Please enter a menu choice between 1-6.\n*!*");
                }

                boolean returnToMainMenu = true;
                while (returnToMainMenu) {
                    // user choice allows user to exit the program after a task in the menu choice has been completed
                    System.out.println("Do you want to return to the main menu?");
                    System.out.print("\n(Y = Yes, or N = No): ");

                    String goAgain = uiString().toLowerCase();

                    if (goAgain.equals("n")) {
                        System.out.println("\nQuitting...\n\n....");
                        Thread.sleep(500);
                        System.out.println("\nBye!");
                        menuRunning = false;
                        System.exit(0);
                        returnToMainMenu = false;
                    } else if (goAgain.equals("y")) {
                        returnToMainMenu = false;
                    } else {
                        System.out.println("\n*!*\nPlease enter a valid choice to return or not to return to the menu.\n*!*\n");
                    }
                }
                // Exception handling for any inputs that was not an integer
            } catch (Exception e) {
                System.out.println("\n*!*\nAn error occurred: please enter a valid integer\n*!*\n" + e.getMessage());
            }
        }
    }

    // menu choice, formatted to create a user-friendly interface
    public static void displayMenu() {
        System.out.println("\n\t\t\t\t\tOOP (NI) Bank\n");
        System.out.println("\t\t\t\t\tAccount Menu\n");
        System.out.println("1\tDisplay All Account Details\t\t\t4\tDeposit");
        System.out.println("2\tDisplay Single Account Details\t\t\t5\tWithdraw");
        System.out.println("3\tAdd New Account\t\t\t\t\t6\tQuit");
    }

    // User input method for integers
    public static int uiInt() {
        Scanner ui = new Scanner(System.in);
        return ui.nextInt();
    }

    // User input method for doubles
    public static double uiDouble() {
        Scanner ui = new Scanner(System.in);
        double kbd = ui.nextDouble();
        return kbd;
    }

    // User input method for Strings
    public static String uiString() {
        Scanner ui = new Scanner(System.in);
        return ui.nextLine();
    }

    // Method to iterate through all accounts stored in the list, calling the Account's method displayAllDetails
    public static void displayAllAccountDetails(ArrayList<Account> accountsLst) {
        // Checks that the account list isn't empty
        if (!accountsLst.isEmpty()) {
            for (Account i : accountsLst) { // for each loop iterating through the list
                i.displayAllDetails();
            }
        } else {
            System.out.println("\n*!*\nError: No account details stored\n*!*\n");
        }
    }

    // Method to display a single account
    public static void displaySingleAccountDetails(ArrayList<Account> accountsLst) {
        System.out.print("Enter account number: ");
        int accountNumber = uiInt();
        // fetches account, with the number entered
        Account fetchedAccount = filterByAccountNumber(accountsLst, accountNumber);

        if (fetchedAccount != null) {
            fetchedAccount.displayAllDetails();
        } else {
            System.out.println("\n*!*\nError: Account not found.\n*!*\n");
        }
    }

    // Filters accounts in the accounts list and returns the account object when it matches the account number matches the account in the list
    private static Account filterByAccountNumber(ArrayList<Account> accountsLst, int accountNumber) {
        for (Account account : accountsLst) {
            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }
        return null;
    }

    // Method to create new accounts
    public static void addNewAccount(ArrayList<Account> accountsLst) {
        Account newAccount = new Account(); // Initialise new object

        // Account name input, while loop to ensure user enters a correct name in accordance to our exceptions
        String accountName;
        while (true) {
            System.out.print("----------------------------------------------\n\tEnter account full name: ");
            accountName = uiString();

            if (isValidAccountName(accountName) && accountName.length() >= 7) {
                newAccount.setAccountName(accountName); // set account name method from Account class to declare name
                break;
            } else {
                System.out.println("\n*!*\nError: Invalid characters in the account name or the name is too short. Please enter a valid account name.\n*!*\n");
            }
        }

        // SortCode input, while loop to ensure user enters a correct SortCode in accordance to our exceptions
        String sortCode;
        while (true) {
            System.out.print("----------------------------------------------\n\tEnter sort code: ");
            sortCode = uiString();

            try {
                // Checks that input is a number between 0-9, and that is 6 numbers long
                if (sortCode.matches("[0-9]+") && sortCode.length() == 6) {
                    // formats the input with '-' in between each 2 numbers, xx-xx-xx
                    String sortCodeFormat = sortCode.substring(0, 2) + "-" + sortCode.substring(2, 4) + "-" + sortCode.substring(4, 6);
                    newAccount.setSortCode(sortCodeFormat); // set SortCode method from Account class to declare SortCode
                    break;
                } else {
                    // exception handling for inputs that aren't numbers, or 6 numbers long
                    System.out.println("\n*!*\nError: Please enter the Bank sort code (6 numeric digits).\n*!*\n");
                }
            } catch (Exception e) {
                System.out.println("\n*!*\nError: Please enter the Bank sort code (6 numeric digits).\n*!*\n");
            }
        }

        // Account type input, allows users to enter 'p' or 'b' instead of typing out the full word for user-friendly tasks
        String accountType;
        // while loop to ensure user enters a correct account type in accordance to our exceptions
        while (true) {
            String accountTypeCode;
            System.out.print("----------------------------------------------\n\tEnter account type \n\t(p for Personal, b for Business): ");
            accountTypeCode = uiString().toLowerCase();
            // account type achieved with an if statement
            if (accountTypeCode.equals("p")) {
                accountType = "Personal";
                newAccount.setAccountType(accountType);
                break;
            } else if (accountTypeCode.equals("b")) {
                accountType = "Business";
                newAccount.setAccountType(accountType);
                break;
            } else {
                // exception handling for inputs outside of parameters allowed
                System.out.println("\n*!*\nError: invalid account type code, Please enter 'p' for Personal or 'b' for Business.\n*!*\n");
            }
        }

        // address input, while loop ensuring that the input is long enough to be a valid address
        String address;
        while (true) {
            System.out.print("----------------------------------------------\n\tEnter home address: ");
            address = uiString();

            if (address.length() >= 5) {
                newAccount.setAddress(address); // set Address method from Account class to declare the accounts address
                break;
            } else {
                System.out.println("\n*!*\nError: Address entered not long enough to be valid, Please enter a valid home address.\n*!*\n");
            }
        }


    // Balance and overdraft input both contained in the same while loop because they both should be input in accordance to one another
        double initialBalance;
        double overdraft;
        while (true) {
            System.out.print("----------------------------------------------\n\tEnter initial balance: ");
            try {
                String userInput = uiString();
                double parsedBalance = Double.parseDouble(userInput); // balance input

                System.out.print("----------------------------------------------\n\tEnter overdraft limit (0 or 500-5000 range): ");
                userInput = uiString();
                double parsedOverdraft = Double.parseDouble(userInput); // overdraft input

                // ensure balance is in the allowed range given on the brief (-5000 - 5000)
                // and that the input is not a decimal number longer than 2 points
                if (parsedBalance >= -5000 && parsedBalance <= 50000 && decimalPlacesCounter(parsedBalance) <= 2) {
                    // checks that overdraft is within range (0 or 500 - 5000) and that the overdraft complies with the set balance
                    if ((parsedOverdraft == 0) || (parsedOverdraft >= 500 && parsedOverdraft <= 5000 && decimalPlacesCounter(parsedOverdraft) <= 2 && parsedBalance + parsedOverdraft >= 0)) {
                        initialBalance = parsedBalance;
                        overdraft = parsedOverdraft;
                        newAccount.setAccountBalance(initialBalance); // set initial balance method from Account class, to declare the accounts balance
                        newAccount.setOverdraftLimit(overdraft); // set overdraft limit method from Account class, to declare the accounts overdraft
                        break;
                    } else {
                        // exception handling for overdraft outside of range, decimal place exception or if the overdraft doesn't correspond with the balance
                        System.out.println("\n*!*\nError: Either Overdraft limit entered is out of the range available (should be in the range of 0 to 5000, or 0 if none is applied)\nInput should not have more than 2 decimal places.\nAlso, the sum of the initial balance shouldnt exeed the overdraft limit.\n*!*\n");
                    }
                } else {
                    // exception handling for initial balance outside of range, or decimal place exception
                    System.out.println("\n*!*\nError: Please enter a valid initial numeric account balance in the range of -5000 to 50000 and should not have more than 2 decimal places.\n*!*\n");
                }
            } catch (NumberFormatException e) {
                System.out.println("\n*!*\nError: Please enter valid numeric values for initial balance and overdraft limit.\n*!*\n");
            }
        }

        // calls addAccountToLst method, passes the account list, and the object created with it
        addAccountToLst(accountsLst, newAccount);
    }

    // exception handling method, to check that all the characters in the name are alphabetical
    private static boolean isValidAccountName(String accountName) {
        for (char ch : accountName.toCharArray()) {
            if (!((ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z') || ch == ' ')) {
                return false;
            }
        }
        return true;
    }
    // exception handling decimal places exceeding over 2 decimal places
    private static int decimalPlacesCounter(double i) {
        if (i % 1 == 0) {
            return 0;
        } else {
            String stringValue = Double.toString(i);
            return stringValue.length() - stringValue.indexOf('.') - 1;
        }
    }

    // Method to adding the object 'newAccount' created in the previous method to the arrayList class Account accounts
    private static void addAccountToLst(ArrayList<Account> accountsLst, Account newAccount) {

        accountsLst.add(newAccount);
        System.out.println("\n\n\n\n\n\n\n* New account added successfully.\n* Account number: " + newAccount.getAccountNumber() + "\n");
    }

    // Method to deposit money to an account object
    public static void depositMoney(ArrayList<Account> accountsLst) {
        while (true) {
            try {
                System.out.print("\nEnter account number for deposit: ");
                int accountNumber = uiInt();
                // fetches account into a single object through filterByAccountNumber method
                Account fetchedAccount = filterByAccountNumber(accountsLst, accountNumber);
                if (fetchedAccount != null) { // if the account number is found in the accounts list
                    System.out.println("\n\n* Current account balance for " + fetchedAccount.getAccountName() + ": " + fetchedAccount.getFormattedAccountBalance() + " \n* with an overdraft limit of: " + fetchedAccount.getFormattedOverdraft());
                    System.out.print("Enter deposit amount: ");
                    double amount = uiDouble();
                    fetchedAccount.deposit(amount); // Calls method from Account class, to add to the accounts balance
                    break;
                } else { // exception for account not found
                    System.out.println("\n*!*\nError: Account not found.\n*!*\n");
                }
            } catch (Exception e){ // exception for anything else other than whole numbers being entered
                System.out.println("\n*!*\nPlease try again: ensure to enter valid integers for account number and deposit amount\n*!*\n");
        }
        }
    }

    // Method to withdraw money from an account object
    public static void withdrawMoney(ArrayList<Account> accountsLst) {
        while (true) {
            try {
                System.out.print("\nEnter account number for withdrawal: ");
                int accountNumber = uiInt();
                Account fetchedAccount = filterByAccountNumber(accountsLst, accountNumber);
                if (fetchedAccount != null) {
                    System.out.println("\n\n* Current account balance for " + fetchedAccount.getAccountName() + ": " + fetchedAccount.getFormattedAccountBalance() + " \n* with an overdraft limit of: " + fetchedAccount.getFormattedOverdraft());
                    System.out.print("Enter withdrawal amount: ");
                    double amount = uiDouble();
                    fetchedAccount.withdraw(amount); // Calls method from Account class, takes amount away from balance
                    break;
                } else {
                    System.out.println("\n*!*\nError: Account not found.\n*!*\n");
                }
            } catch (Exception e) { // exception for anything else other than whole numbers being entered
                System.out.println("\n*!*\nPlease try again: ensure to enter valid integers for account number and withdraw amount\n*!*\n");
            }
        }
    }

    // method that creates the default entries so that the program has entries in the list when the program starts
    public static void preFillList(ArrayList<Account> accountsLst) {
        Account account1 = new Account("Connor Charnock", "Personal", "Belfast", 1000000, "11-42-41", 1337);
        Account account2 = new Account("Mark Zuckerberg", "Business", "California", 5002350, "40-44-04", 1500);
        Account account3 = new Account("Fraser Charnock", "Personal", "Dromore", -1000, "78-23-12", 1100);
        Account account4 = new Account("Elon Musk", "Personal", "New York", 7800000, "32-12-52", 500);
        Account account5 = new Account("Thomas Morgan", "Personal", "Lisburn", 10, "16-24-62", 1000);
        accountsLst.add(account1);
        accountsLst.add(account2);
        accountsLst.add(account3);
        accountsLst.add(account4);
        accountsLst.add(account5);
    }
}