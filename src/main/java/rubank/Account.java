package rubank;


import java.text.DecimalFormat;

/**
 * Class for storing information on accounts in bank: holder, open, and balance.
 *
 * @author Seifeldeen Mohamed
 */
public abstract class Account {
    /**
     * Stores the information of the account holder.
     */
    protected Profile holder;

    /**
     * Holds account balance, used to determine withdraw and deposit amount when
     * Account instance used for deposit and withdraw methods in subclasses.
     */
    protected double balance;

    /**
     * Default constructor generated by compiler.
     */
    public Account() {}

    /**
     * Withdraws amount from balance.
     * @param amount the money being withdrawn
     */
    public void withdraw(double amount) {
        this.balance -= amount;
    }

    /**
     * Deposits amount into account.
     * @param amount the money being deposited
     */
    public void deposit(double amount) {
        this.balance += amount;
    }

    /**
     * Returns fee for type of account.
     * @return fee specific to account type
     */
    public abstract double fee();

    /**
     * Returns monthly interest rate for type of account.
     * @return monthly interest rate to two decimal places specific to account type
     */
    public abstract double monthlyInterest();

    /**
     * Returns type of account.
     * @return Type of account (checking, savings, college checking, money market)
     */
    public abstract String getType();

    /**
     * Returns instance of holder associated to this account.
     * @return instance of holder for account
     */
    public Profile getHolder() {
        return holder;
    }


    /**
     * Overrides equals method.
     * @return true if holder and type are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;

        if (!(obj instanceof Account)) return false;

        Account a = (Account) obj;

        return (this.holder.equals(a.holder));
    }

    /**
     * Overrides toString method.
     * @return String in "accountType::holderInfo::Balance" format
     */
    @Override
    public String toString() {
        DecimalFormat formatter = new DecimalFormat("#,##0.00");
        String str = "::" + holder.toString() + "::Balance $" + formatter.format(balance);
        return str;
    }
}
