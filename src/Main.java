import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        int countUpper = 0;
        int countLower = 0;
        int countDigit= 0;
        int countSpecialChar = 0;

        System.out.println("Enter The Password: ");
        Scanner scanner = new Scanner(System.in);
        String password = scanner.nextLine();
        double entropy = calculateTheEntropy(password);
        double variance = calculateTheVariance(password);

        for (int i = 0; i <password.length() ; i++) {
            char ch = password.charAt(i);
            if (Character.isUpperCase(ch)) {
                countUpper++;
            } else if (Character.isLowerCase(ch)) {
                countLower++;
            } else if (Character.isDigit(ch)) {
                countDigit++;
            } else {
                countSpecialChar++;
            }
        }

        if (password.length() >= 8 && countUpper > 0 && countLower > 0 && countDigit > 0 && countSpecialChar > 0) {
            if (entropy >= 60 && variance >= 80.0) {
                System.out.println("The  password is : Strong :) ");
            } else {
                System.out.println(" The  password is : Medium :| ");
            }
        } else {
            System.out.println("The  password is : Weak :( ");
        }
        System.out.println("The Entropy is : " + entropy);
        System.out.println("The Variance is : " + variance);
        System.out.println("Hints for creating a strong password:");
        System.out.println("- Use a mix of uppercase, lowercase, numbers, and special characters.");
        System.out.println("- Avoid using common words or sequences.");
        System.out.println("- Consider adding special characters.");

    }

    public static double calculateTheEntropy(String password) {
            if (password == null || password.length() == 0) {
                throw new IllegalArgumentException("Error: The password will not be empty or null !!");
            }
            int Range = characterRange(password);
            double entropy = Math.log(Range) / Math.log(2) * password.length();

            return entropy;
        }

        private static int characterRange(String password) {
            boolean[] chars = new boolean[128];
            int uniqueCharsCount = 0;
            for (char ch : password.toCharArray()) {
                int asciiValue = (int) ch;
                if (!chars[asciiValue]) {
                    chars[asciiValue] = true;
                    uniqueCharsCount++;
                }
            }
            return uniqueCharsCount;
        }

    public static double calculateTheVariance(String password) {
        if (password == null || password.length() == 0) {
            throw new IllegalArgumentException("Error : The password will not be empty or null !!");
        }
        double Mean = calculateTheMean(password);
        double sumOfSquares = 0;
        for (char ch : password.toCharArray()) {
            int asciiValue = (int) ch;
            sumOfSquares += Math.pow(asciiValue - Mean, 2);
        }
        double variance = sumOfSquares / password.length();

        return variance;
    }
    private static double calculateTheMean(String password) {
        double Sum = 0;
        for (char ch : password.toCharArray()) {
            Sum += (int) ch;
        }

        return Sum / password.length();
    }

}