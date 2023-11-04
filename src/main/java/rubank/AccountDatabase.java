package rubank;

/**
 * Class for storing all accounts at the bank.
 *
 * @author Seifeldeen Mohamed
 */
public class AccountDatabase {
    /**
     * Constant for the initial capacity of the database.
     */
    public static final int INIT_CAPACITY = 4;

    /**
     * Constant for the growth size everytime the database gets "full."
     */
    public static final int GROWTH = 4;

    /**
     * Constant for find() method when account is not in database.
     */
    public static final int NOT_FOUND = -1;

    /**
     * Array of all stored accounts in database.
     */
    private rubank.Account[] accounts;

    /**
     * Stores number of accounts in database including open accounts.
     */
    private int numAcct;


    /**
     * Creates instance of AccountDatabase setting the number of accounts to 0,
     * and accounts array to hold up to 4 accounts.
     */
    public AccountDatabase() {
        this.numAcct = 0;
        this.accounts = new rubank.Account[INIT_CAPACITY];
    }

    /**
     * Returns number of accounts in database.
     * @return number of accounts
     */
    public int getNumAcct() {
        return numAcct;
    }

    /**
     * Finds account in accounts array and returns its index.
     * @param account instance of Account to be looked up in database
     * @return index of account in accounts array, -1 if not found in array
     */
    private int find(rubank.Account account) {
        for (int i = 0; i < numAcct; i++) {
            if (account.equals(accounts[i])) {
                return i;
            }
        }

        return NOT_FOUND;
    }

    /**
     * Method to determine the status of an account in the database.
     * @param account instance of Account to check status of in database
     * @return true if account is found and false otherwise
     */
    public boolean checkStatus(rubank.Account account) {
        int loc = find(account);
        if(loc >= 0)
        {
            return true;
        }
        return false;
    }

    /**
     * Used by BankTeller for determining whether account was in database already before opening
     * or closing.
     * @param account instance of Account to be looked up in database
     * @return true if account in database, false otherwise
     */
    public boolean isInDatabaseAlready(rubank.Account account) {
        return find(account) >= 0;
    }

    /**
     * Grows accounts array by 4.
     */
    private void grow() {
        rubank.Account[] newAccounts = new rubank.Account[accounts.length + GROWTH];

        for (int i = 0; i < numAcct; i++) {
            newAccounts[i] = accounts[i];
        }

        accounts = newAccounts;
    }

    /**
     * Adds new account to accounts array,
     * and does nothing if it already exists in the accounts array.
     * @param account The information for an account to be opened in the database
     * @return true if successfully opened, false otherwise
     */
    public boolean open(rubank.Account account) {
        String accType = account.getType();
        for (int i = 0; i < numAcct; i++) {
            rubank.Account a = accounts[i];
            if (account.equals(a)) {
                return false;
            }
            if(account.holder.equals(a.holder))
            {
                if (accType.equals("College Checking") || accType.equals("Checking")) {
                    String aType = a.getType();
                    if ((aType.equals("College Checking") || aType.equals("Checking"))) {
                        return false;
                    }
                }
            }
        }

        if (numAcct + 1 > accounts.length) grow();
        accounts[numAcct++] = account;

        return true;
    }

    /**
     * Closes an account in the accounts array.
     * @param account The information for the account that is to be closed
     * @return true if closed a found account, false otherwise
     */
    public boolean close(rubank.Account account) {
        int accountIndex = find(account);
        if (accountIndex == NOT_FOUND) return false;
        while(accountIndex+1 < numAcct) {
            accounts[accountIndex] = accounts[accountIndex+1];
            accountIndex++;
        }
        accounts[accountIndex] = null;
        numAcct--;
        return true;
    }

    /**
     * Deposits amount passed in through account's balance to account.
     * @param account used to store account info and amount to be deposited
     */
    public void deposit(rubank.Account account) {
        double depositAmt = account.balance;
        int accountIndex = find(account);
        if (accountIndex == NOT_FOUND) return;

        rubank.Account a = accounts[accountIndex];
        a.deposit(depositAmt);
    }

    /**
     * Withdraws amount passed in through account's balance from account.
     * @param account used to store account info and amount to be withdrawn
     * @return true if successfully withdrew money, false otherwise
     */
    public boolean withdraw(rubank.Account account) {
        double withdrawAmt = account.balance;
        int accountIndex = find(account);
        if (accountIndex == NOT_FOUND) return false;

        rubank.Account a = accounts[accountIndex];
        if (a.balance >= withdrawAmt) {
            a.withdraw(withdrawAmt);
            return true;
        } else
            return false;
    }

    //TODO: Change JavaDoc for printing methods, as now they return String objects.

    /**
     * Returns String of all accounts in database.
     * @return accounts in database as formatted String
     */
    public String print() {
        String str = accounts[0].toString() + "\n";

        for (int i = 1; i < numAcct; i++) {
            rubank.Account a = accounts[i];
            str += a.toString() + "\n";
        }

        return str;
    }

    /**
     * Returns String of all accounts in order of account type in the array.
     * @return accounts in database ordered by type with formatting
     */
    public String printByAccountType() {
        String str = "by account type.\n";
        for (int i = 1; i < numAcct; i++) {
            rubank.Account curr = accounts[i];
            int j = i - 1;
            while (j >= 0 && (curr.getType().compareTo(accounts[j].getType()) < 0)) {
                accounts[j + 1] = accounts[j];
                j--;
            }
            accounts[j+1] = curr;
        }
        str += print();
        return str;
    }

    /**
     * Returns accounts in array with their calculated fee and interest with current order.
     * @return accounts in array with current order and fees and interest with formatting.
     */
    public String printFeeAndInterest() {
        String str = "with fee and monthly interest\n";

        for (int i = 0; i < numAcct; i++) {
            rubank.Account a = accounts[i];
            String fee = String.format("%.2f", a.fee());
            String monthlyInterest = String.format("%.2f", a.monthlyInterest());
            str += a + "::fee $" + fee + "::monthly interest $" + monthlyInterest + "\n";
        }

        return str;
    }

    /**
     * Updates balances of all accounts by subtracting fee from their balance
     * and adding their monthly interest to their balance, then retuns string of accounts in
     * current order.
     * @return all accounts as formatted string with updated balances.
     */
    public String updateBalances() {
        for (int i = 0; i < numAcct; i ++) {
            rubank.Account a = accounts[i];
            double fee = a.fee();
            double monthlyInterest = a.monthlyInterest();
            a.balance -= fee;
            a.balance += monthlyInterest;

        }

        String str = "with updated balance\n";
        str += print();
        return str;
    }
}
