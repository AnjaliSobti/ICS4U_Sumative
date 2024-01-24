package helper;

public class DayTypeCalculator {

    public static void main(String[] args) {


        // System.out.println("Enter the day (1-31): ");
        DayTypeCalculator.getDayType(15);
        
    }

public static int getDayType(int day) {
    int dayType = -1;
    if (isValidDate(day)) {
            if (day % 2 == 0) {
                System.out.println("The day is even.");
                dayType = 2;
            } else {
                System.out.println("The day is odd.");
                dayType = 1;
            }
    } else {
            System.out.println("Invalid date.");
    }
    return dayType;
}
    private static boolean isValidDate(int day) {
        // Basic validation for day and month ranges
        return (day >= 1 && day <= 31);
    }
}
