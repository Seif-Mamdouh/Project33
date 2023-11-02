package rubank;

/**
 * Class for handling College type Checking Accounts.
 *
 * @author Seifeldeen
 */
public class CollegeChecking extends Checking{
    /**
     * Constant defining annual interest rate for account.
     */
    public static final double APY = 0.0025;

    /**
     * Constant for New Brunswick campus code.
     */
    public static final int NB = 0;

    /**
     * Constant for Newark campus code.
     */
    public static final int NEWARK = 1;

    /**
     * Constant for Camden campus code.
     */
    public static final int CAMDEN = 2;

    /**
     * Tracks which college campus the account belongs to.
     */
    private int campusCode;

    /**
     * Creates instance of a College Checking Account from Profile, balance, and Campus association.
     * @param profile Profile object of user to be associated with the Account
     * @param balance Starting amount of money deposited at Account Creation
     * @param campusCode Number corresponding to a university that the user is associated with
     */
    public CollegeChecking(Profile profile, double balance, int campusCode)
    {
        super(profile, balance);
        this.campusCode = campusCode;
    }

    /**
     * Returns campus code for instance of college checking account.
     * @return campus code field
     */
    public int getCampusCode()
    {
        return campusCode;
    }

    /**
     * Sets campus code.
     * @param camp 0(NB), 1(NEWARK), or 2(CAMDEN)
     */
    public void setCampusCode(int camp)
    {
        this.campusCode = camp;
    }

    /**
     * Calculates the fee to be charged to a CollegeChecking Account.
     * @return The fee to be charged to the account.
     */
    public double fee()
    {
        return 0;
    }

    /**
     * Calculates the interest value for the month based on current balance.
     * @return The amount of interest for the CC Account for the month.
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

        if (!(obj instanceof CollegeChecking)) return false;

        Account a = (CollegeChecking) obj;

        return (this.holder.equals(a.holder)) ;
    }


    /**
     * Returns type of class as String.
     * @return "College Checking"
     */
    public String getType()
    {
        return "College Checking";
    }

    /**
     * Overrides the default toString to return the account information.
     * @return CC Account info in "accountType::holderInfo::Balance::Campus" format
     */
    @Override
    public String toString() {
        String campus = "";
        switch (campusCode) {
            case NB:
                campus = "NEW_BRUNSWICK";
                break;
            case NEWARK:
                campus = "NEWARK";
                break;
            case CAMDEN:
                campus = "CAMDEN";
                break;
        }
        return super.toString() + "::" + campus;
    }
}
