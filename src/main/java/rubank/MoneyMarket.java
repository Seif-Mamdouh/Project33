package rubank;

/**
 * Class for handling MoneyMarket type Savings Accounts.
 *
 * @author Seifeldeen Mohamed
 */
public class MoneyMarket extends Savings{
    /**
     * Constant defining the minimum amount required to open a Money Market account.
     */
    public static final int MIN_OPEN_AMT = 2000;

    /**
     * Constant defining the amount necessary to waive the monthly fee.
     */
    public static final int FEE_WAIVED_AMT = 2000;

    /**
     * Constant defining the max withdrawals allowed to waive monthly fee.
     */
    public static final int MAX_WITHDRAW = 3;

    /**
     * Constant defining annual interest rate for non-loyal account.
     */
    public static final double BASIC_APY = 0.045;

    /**
     * Constant defining annual interest rate for loyal account.
     */
    public static final double LOYAL_APY = 0.0475;

    /**
     * Constant defining monthly fee if balance is below 2500.
     */
    public static final int FEE = 10;

    /**
     * Stores number of withdrawals performed on instance of Money Market account.
     */
    private int withdrawals;

    /**
     * Creates instance of a MoneyMarket Savings Account from Profile, balance, and loyalty.
     * @param profile Profile object of user to be associated with the Account
     * @param balance Starting amount of money deposited at Account Creation
     * @param isLoyal Starting Loyalty status of user opening an account
     */
    public MoneyMarket(Profile profile, double balance, boolean isLoyal)
    {
        super(profile, balance, isLoyal);
        this.withdrawals = 0;
    }

    /**
     * Overrides the withdrawal method and Withdraws amount from balance; updating loyalty and withdrawal count.
     * @param amount the money being withdrawn
     */
    @Override
    public void withdraw(double amount)
    {
        super.balance -= amount;
        if(super.balance < MIN_OPEN_AMT)
        {
            super.isLoyal = false;
        }
        withdrawals++;

    }

    /**
     * Resets the value of withdrawals to 0 for use when an account is being closed.
     */
    public void withdrawalReset()
    {
        this.withdrawals = 0;
    }

    /**
     * Calculates the fee to be charged to a MoneyMarket Account.
     * @return The fee to be charged to the account.
     */
    public double fee()
    {
        int feeTot = 0;
        if(super.balance < FEE_WAIVED_AMT || this.withdrawals > MAX_WITHDRAW)
        {
            feeTot = FEE;
        }
        return feeTot;
    }



    /**
     * Calculates the interest value for the month based on current balance.
     * @return The amount of interest for the MM Account for the month.
     */
    public double monthlyInterest()
    {
        if(super.isLoyal)
        {
            return super.balance * (LOYAL_APY / Date.MONTHS_IN_YEAR);
        }
        return super.balance * (BASIC_APY / Date.MONTHS_IN_YEAR);
    }
    /**
     * Overrides equals method.
     * @return true if holder and type are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj){
        if (obj == this) return true;

        if (!(obj instanceof MoneyMarket)) return false;

        Account a = (MoneyMarket) obj;

        return (this.holder.equals(a.holder));
    }

    public String getType()
    {
        return "Money Market";
    }

    /**
     * Overrides the default toString to return the account information.
     * @return MM Account info in "accountType::holderInfo::Balance::withdrawalCount" format
     */
    @Override
    public String toString() {
        return this.getType() + " " + super.toString() + "::withdrawal: " + withdrawals ;
    }
}
