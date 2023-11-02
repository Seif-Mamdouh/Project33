package rubank;

/**
 * Class for handling Checking Accounts.
 *
 * @author Seifeldeen Mohamed
 */
public class Checking extends Account{
    /**
     * Constant defining the amount necessary to waive the monthly fee.
     */
    public static final int FEE_WAIVED_AMT = 1000;

    /**
     * Constant defining monthly fee if balance is below 1000.
     */
    public static final int FEE = 12;

    /**
     * Constant defining annual interest rate for account.
     */
    public static final double APY = 0.001;

    /**
     * Creates instance of a Checking Account from Profile and balance.
     * @param profile Profile object of user to be associated with the Account
     * @param balance Starting amount of money deposited at Account Creation
     */
    public Checking(Profile profile, double balance)
    {
        super.holder = profile;
        super.balance = balance;
    }

    /**
     * Calculates the fee to be charged to a Checking Account.
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
     * @return The amount of interest for the Checking Account for the month.
     */
    public double monthlyInterest()
    {
        return super.balance * (APY / Date.MONTHS_IN_YEAR);
    }

    /**
     * Overrides equals method.
     * @return true if holder and type are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj){
        if (obj == this) return true;

        if (!(obj instanceof Checking) || (obj instanceof CollegeChecking)) return false;

        Account a = (Checking) obj;

        return (this.holder.equals(a.holder)) ;
    }


    /**
     * Returns type of class as String.
     * @return "Checking"
     */
    public String getType()
    {
        return "Checking";
    }

    /**
     * Overrides the default toString to return the account information.
     * @return Checking Account info in "accountType::holderInfo::Balance" format
     */
    @Override
    public String toString() {
        return this.getType() + super.toString();
    }
}
