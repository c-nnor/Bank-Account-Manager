import java.text.DecimalFormat;

/**
 * Class representing a bank account.
 * Accessed from main to create a bank account system
 * @author Connor Charnock 50074655
 */
class Account {
    // Static variable to keep track of account number incrementation
    private static int accountNumInc = 10000001;

    // Instance variables
    private int accountNumber;
    private String accountName;
    private String accountType;
    private String address;
    private double accountBalance;
    private String sortCode;
    private double overdraftLimit;

    /**
     * Constructs an Account object with an auto-assigned account number.
     */
    public Account(){
        this.accountNumber = accountNumInc++;
    }

    /**
     * Constructs an Account object with specified details.
     * @param accountName The name associated with the account.
     * @param accountType The type of account.
     * @param address The address associated with the account.
     * @param accountBalance The current balance of the account.
     * @param sortCode The sort code of the account.
     * @param overdraftLimit The overdraft limit of the account.
     */
    public Account(String accountName, String accountType, String address, double accountBalance, String sortCode, double overdraftLimit) {
        this.accountNumber = accountNumInc++;
        this.accountName = accountName;
        this.accountType = accountType;
        this.address = address;
        this.accountBalance = accountBalance;
        this.sortCode = sortCode;
        this.overdraftLimit = overdraftLimit;
    }

    /**
     * Retrieves the account number.
     * @return The account number.
     */
    public int getAccountNumber() {
        return accountNumber;
    }

    /**
     * Retrieves the account name.
     * @return The account name.
     */
    public String getAccountName() {
        return accountName;
    }

    /**
     * Sets the account name.
     * @param accountName The account name to be set.
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    /**
     * Retrieves the account type.
     * @return The account type.
     */
    public String getAccountType() {
        return accountType;
    }

    /**
     * Sets the account type.
     * @param accountType The account type to be set.
     */
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    /**
     * Retrieves the address associated with the account.
     * @return The account's address.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address associated with the account.
     * @param address The address to be set.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Sets the account balance.
     * @param accountBalance The balance to be set.
     */
    public void setAccountBalance(double accountBalance){
        this.accountBalance = accountBalance;
    }

    /**
     * Retrieves the account balance.
     * @return The account balance.
     */
    public double getAccountBalance() {
        return accountBalance;
    }

    /**
     * Retrieves the sort code of the account.
     * @return The sort code.
     */
    public String getSortCode() {
        return sortCode;
    }

    /**
     * Sets the sort code of the account.
     * @param sortCode The sort code to be set.
     */
    public void setSortCode(String sortCode) {
        this.sortCode = sortCode;
    }

    /**
     * Retrieves the overdraft limit of the account.
     * @return The overdraft limit.
     */
    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    /**
     * Sets the overdraft limit of the account.
     * @param overdraftLimit The overdraft limit to be set.
     */
    public void setOverdraftLimit(double overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }

    /**
     * Deposits money into the account.
     * @param amount The amount to be deposited.
     */
    public void deposit(double amount) {
        if (amount > 0) {
            accountBalance += amount;
            System.out.println("Deposit successful. New balance: £" + accountBalance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    /**
     * Withdraws money from the account.
     * @param amount The amount to be withdrawn.
     */
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Please enter an amount to withdraw higher than 0");
        } else if (accountBalance + overdraftLimit >= amount) {
            accountBalance -= amount;
            System.out.println("Withdrawal successful. New balance: £" + accountBalance);
        } else {
            System.out.println("\n*!*\nWithdrawal amount exceeds overdraft limit.\n*!*");
        }
    }

    /**
     * Retrieves the formatted account balance.
     * @return The formatted account balance.
     */
    public String getFormattedAccountBalance() {
        DecimalFormat decimal = new DecimalFormat("#.00");
        return "£" + decimal.format(getAccountBalance());
    }

    /**
     * Retrieves the formatted overdraft limit.
     * @return The formatted overdraft limit.
     */
    public String getFormattedOverdraft() {
        DecimalFormat decimal = new DecimalFormat("#.00");
        return "£" + decimal.format(getOverdraftLimit());
    }

    /**
     * Displays all details of the account.
     */
    public void displayAllDetails() {
        System.out.println("---------------------------------------");
        System.out.println("Account Number: " + getAccountNumber());
        System.out.println("Account Name: " + getAccountName());
        System.out.println("Account Type: " + getAccountType());
        System.out.println("Address: " + getAddress());
        System.out.println("Account Balance: " + getFormattedAccountBalance());
        System.out.println("Sort Code: " + getSortCode());
        System.out.println("Overdraft Limit: " + getFormattedOverdraft());
        System.out.println("---------------------------------------");
        System.out.println();
    }
}
