import java.io.*;
import java.util.Random;

public class GenerateFacultyData {
    public static void main(String[] args) {
        String fileName = "Salary.txt";

        String[] ranks = {"assistant", "associate", "full"};
        double[][] salaryRanges = {
                {50000, 80000},  // Assistant
                {60000, 110000}, // Associate
                {75000, 130000}  // Full
        };

        Random random = new Random();

        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (int i = 1; i <= 1000; i++) {
                // Generate first name and last name
                String firstName = "FirstName" + i;
                String lastName = "LastName" + i;

                int rankIndex = random.nextInt(ranks.length);
                String rank = ranks[rankIndex];

                double minSalary = salaryRanges[rankIndex][0];
                double maxSalary = salaryRanges[rankIndex][1];
                double salary = minSalary + (maxSalary - minSalary) * random.nextDouble();
                salary = Math.round(salary * 100.0) / 100.0;


                writer.printf("%s %s %s %.2f%n", firstName, lastName, rank, salary);
            }

            System.out.println("File created successfully: " + fileName);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}