
class Month {

    static final String[] months = { "january", "february", "march", "april", "may", "june", "july", "august", "september", "october", "novembver", "december" };
    private int monthNumber;

    public void setMonthNumber(int m) { monthNumber = (m < 1 || m > 12) ? 1 : m; }
    public int getMonthNumber() { return monthNumber; }
    public String getMonthName() { 
        /* How it should be implemented 
        switch(monthNumber) {
            case 1:
                return "Janurary";
            case 2:
                return "February";
            case 3:
                return "March";
            case 4:
                return "April";
            case 5:
                return "May";
            case 6:
                reutrn "June";
            case 7:
                return "July";
            case 8:
                return "August";
            case 9:
                return "September";
            case 10:
                return "October";
            case 11:
                return "November";
            case 12:
                return "December";
            default:
                throw new IllegalStateException("Month Number is in an illegal state.");
        }
        
        How I wanted to do it */
        try {
            String ms = months[monthNumber - 1]; // Should not fail
            return ms.substring(0, 1) // Uppercase first character
                     .toUpperCase()
                     .concat(ms.substring(1)); 
        } catch (Exception e) {
            throw new IllegalStateException("Month Number is in an illegal state.");
        } 
    }

    @Override
    public String toString() {
        return getMonthName();
    }

    public boolean equals(Month m) {
        if (m == null) return false; // Handle null object
        return this.getMonthNumber() == m.getMonthNumber();
    }

    public Month() {
        monthNumber = 1;
    }

    public Month(int m)  {
        if (m < 1 || m > 12) {
            monthNumber = 1;
            throw new IllegalArgumentException("Argument Month is an integer that must satisfy: 1 <= Month <= 12");
        } else {
            monthNumber = m;
        }
    }

    public Month(String m) {
        if (m == null) throw new NullPointerException(); // Handle null object
        
        int mn = 1; // Month number 
        String mp = m.toLowerCase(); // Preprocessing (case insensitive)

        // Sequential search
        for (String month: months) {
            if (month.equals(mp)) break;
            else mn++;
        }
        
        if (mn <= 12) { // Validation & Assignment
            monthNumber = mn;
        } else { // Invalid Fallthrough case
            throw new IllegalArgumentException("Not a valid month string.");
        }
    } 
}