public class BirthDate {
    private int year;
    private int month;
    private int day;

    /** Constructor
     *
     * @param year  The year that you want to set it to
     * @param month The month that you want to set it to
     * @param day   The day that you want to set it to
     */
    public BirthDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    /** Setter for the year
     *
     * @param year The year that you want to set it to
     */
    public void setYear(int year) {
        this.year = year;
    }

    /** Setter for the month
     *
     * @param month The month that you want to set it to
     */
    public void setMonth(int month) {
        this.month = month;
    }

    /** Setter for the day
     *
     * @param day The day that you want to set it to
     */
    public void setDay(int day) {
        this.day = day;
    }


}
