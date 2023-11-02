package rubank;

/**
 * Class for handling Savings Accounts.
 *
 * @author Seifeldeen Mohamed
 */
public class Savings extends Account{
    /**
     * Constant defining the amount necessary to waive the monthly fee.
     */
    public static final int FEE_WAIVED_AMT = 500;

    /**
     * Constant defining monthly fee if balance is below 300.
     */
    public static final int FEE = 6;

    /**
     * Constant defining annual interest rate for non loyal account.
     */
    public static final double BASIC_APY = 0.003;

    /**
     * Constant defining annual interest rate for loyal account.
     */
    public static final double LOYAL = 0.0045;

    /**
     * Stores whether account is loyal or not.
     */
    protected boolean isLoyal;

    /**
     * Creates instance of a Savings Account from Profile, balance, and loyalty status.
     * @param profile Profile object of user to be associated with the Account
     * @param balance Starting amount of money deposited at Account Creation
     * @param isLoyal The starting loyalty status of the user when creating a Savings Account
     */
    public Savings(Profile profile, double balance, boolean isLoyal)
    {
        super.holder = profile;
        super.balance = balance;
        this.isLoyal = isLoyal;
    }

    /**
     * Returns the loyality sstatus of a Savings Account.
     * @return loyal The  loyalty status of the Account
     */
    public boolean getLoyalty()
    {
        return isLoyal;
    }

    /**
     * Updates the loyality sstatus of a Savings Account.
     * @param loyal The new loyalty status of the user
     */
    public void setLoyalty(Boolean loyal)
    {
        isLoyal = loyal;
    }

    /**
     * Calculates the fee to be charged to a Savings Account.
     * @return The fee to be charged to the account.
     */
    public double fee()
    {
        int feeTot = 0;
        if(super.balance < FEE_WAIVED_AMT)
        {
            feeTot = FEE;
        }
        return feeTot;
    }

    /**
     * Calculates the interest value for the month based on current balance.
     * @return The amount of interest associated with the Savings Account for the month.
     */
    public double monthlyInterest()
    {
        if(this.isLoyal)
        {
            return super.balance * (LOYAL / Date.MONTHS_IN_YEAR);
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

        if (!(obj instanceof Savings) || (obj instanceof rubank.MoneyMarket)) return false;

        Account a = (Savings) obj;

        return (this.holder.equals(a.holder));
    }

    public String getType() {
        return "Savings";
    }

    /**
     * Overrides the default toString to return the account information.
     * @return Savings Account info in "accountType::holderInfo::Balance::LoyaltyStatus" format
     */
    @Override
    public String toString() {
        return "Savings" + super.toString() + (isLoyal ? "::Loyal" : "");
    }
}
