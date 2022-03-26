package utils;

import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ScannerUtils {
    static Scanner scanner = new Scanner(System.in);

    public static int scanInt() {
        return scanner.nextInt();
    }

    public static String scanString(String err) {
        while(true) {
            String s = scanner.nextLine();
            s = s.trim();
            s = s.replaceAll("\\s+", " ");

            return s;
        }
    }

    public static String scanEmail(String err) {
        while(true) {
            String email = ScannerUtils.scanString(err);
            Pattern p = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w]{2,4}$");
            if(!p.matcher(email).find()) {
                System.err.println(err);
                System.out.print("Nhập lại Email : ");
            } else {
                return email;
            }
        }
    }

    public static String scanPassword(String err) {
        while(true) {
            String password = ScannerUtils.scanString(err);
            if(password.length() >= 6 && password.length() <= 12) {
                return password;
            } else {
                System.err.println(err);
                System.out.print("Nhập lại Password : ");
            }
        }
    }

    public static String scanName(String err) {
        while(true) {
            String name = ScannerUtils.scanString(err);

            String[] elements = name.split(" ");
            name = "";
            for(String element : elements) {
                String fstChar = element.substring(0,1).toUpperCase();
                String lftChar = element.substring(1).toLowerCase();
                element = fstChar + lftChar;
                name += element + " ";
            }
            return name.trim();
        }
    }
}
