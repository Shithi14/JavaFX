import java.util.Scanner;

public class Hex2Dec {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a hexadecimal string: ");
        String hexString = scanner.nextLine();

        try {
            int decimalValue = hex2Dec(hexString);
            System.out.println("Decimal value: " + decimalValue);
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }

        scanner.close();
    }

    public static int hex2Dec(String hexString) throws NumberFormatException {
        if (!hexString.matches("[0-9A-Fa-f]+")) {
            throw new NumberFormatException("Invalid hex string: " + hexString);
        }

        int decimal = 0;
        for (int i = 0; i < hexString.length(); i++) {
            char hexChar = hexString.charAt(i);
            decimal = decimal * 16 + hexCharToDecimal(hexChar);
        }
        return decimal;
    }

    public static int hexCharToDecimal(char ch) {
        if (ch >= 'A' && ch <= 'F') {
            return 10 + ch - 'A';
        } else if (ch >= 'a' && ch <= 'f') {
            return 10 + ch - 'a';
        } else {
            return ch - '0';
        }
    }
}
