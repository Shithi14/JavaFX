import java.io.*;
import java.util.*;

public class BabyNameRanking {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the year: ");
        int year = scanner.nextInt();
        System.out.print("Enter the gender (M/F): ");
        char gender = scanner.next().charAt(0);
        System.out.print("Enter the name: ");
        String name = scanner.next();

        String fileName = "babynameranking" + year + ".txt";
        File file = new File(fileName);

        if (!file.exists()) {
            System.out.println("The file for year " + year + " does not exist.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            boolean found = false;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\t");
                int rank = Integer.parseInt(parts[0]);
                String boyGender = parts[1];
                String boyName = parts[2];
                String girlGender = parts[4];
                String girlName = parts[5];

                if (gender == 'M' && boyName.equalsIgnoreCase(name) && boyGender.equals("M")) {
                    System.out.println(name + " is ranked #" + rank + " in year " + year);
                    found = true;
                    break;
                } else if (gender == 'F' && girlName.equalsIgnoreCase(name) && girlGender.equals("F")) {
                    System.out.println(name + " is ranked #" + rank + " in year " + year);
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("The name " + name + " is not ranked in year " + year);
            }
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
