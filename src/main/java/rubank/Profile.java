package rubank;

/**
 * Class for storing profile information for account holders: first name, last name,
 * and date of birth.
 *
 * @author Seifeldeen Mohamed
 */
public class Profile {
    /**
     * First name for profile.
     */
    private String fname;

    /**
     * Last name for profile.
     */
    private String lname;

    /**
     * Date of Birth for profile.
     */
    private Date dob;

    /**
     * Creates an instance of Profile.
     * @param fname First name of Profile object being created.
     * @param lname Last name of Profile object being created.
     * @param dob Date of birth of Profile object being created.
     */
    public Profile(String fname, String lname, Date dob) {
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
    }

    /**
     * Overrides equals method.
     * @return true if same first name, last name, and dob; false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) return true;

        if (!(o instanceof Profile)) return false;

        Profile p = (Profile) o;

        return this.fname.equalsIgnoreCase(p.fname) && this.lname.equalsIgnoreCase(p.lname)
                && this.dob.equals(p.dob);
    }

    /**
     * Overrides toString method.
     * @return String in "fname lname dob" format
     */
    @Override
    public String toString() {
        return this.fname + " " + this.lname + " " + this.dob;
    }
}
