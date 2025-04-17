import java.io.*;
import java.net.URL;
import java.util.*;

public class SalaryProcessor {
    public static void main(String[] args) {
        String url = "https://raw.githubusercontent.com/Mubasshir14/score-data/refs/heads/main/Salary.txt";

        // Variables to store total salaries and counts
        double totalAssistantSalary = 0, totalAssociateSalary = 0, totalFullSalary = 0, totalSalary = 0;
        int assistantCount = 0, associateCount = 0, fullCount = 0;

        try {
            URL salaryURL = new URL(url);
            Scanner input = new Scanner(salaryURL.openStream());

            while (input.hasNextLine()) {
                String line = input.nextLine();
                // Split by whitespace
                String[] data = line.split("\\s+");

                if (data.length >= 4) {
                    String rank = data[2];
                    double salary = Double.parseDouble(data[3]);

                    // Process salaries based on rank
                    switch (rank.toLowerCase()) {
                        case "assistant":
                            totalAssistantSalary += salary;
                            assistantCount++;
                            break;
                        case "associate":
                            totalAssociateSalary += salary;
                            associateCount++;
                            break;
                        case "full":
                            totalFullSalary += salary;
                            fullCount++;
                            break;
                    }

                    // Add to total salary
                    totalSalary += salary;
                }
            }

            input.close();

            // Display total and average salaries
            System.out.println("Total Salaries:");
            System.out.printf("Assistant Professors: $%.2f%n", totalAssistantSalary);
            System.out.printf("Associate Professors: $%.2f%n", totalAssociateSalary);
            System.out.printf("Full Professors: $%.2f%n", totalFullSalary);
            System.out.printf("All Faculty: $%.2f%n", totalSalary);

            System.out.println("\nAverage Salaries:");
            System.out.printf("Assistant Professors: $%.2f%n",
                    assistantCount > 0 ? totalAssistantSalary / assistantCount : 0);
            System.out.printf("Associate Professors: $%.2f%n",
                    associateCount > 0 ? totalAssociateSalary / associateCount : 0);
            System.out.printf("Full Professors: $%.2f%n",
                    fullCount > 0 ? totalFullSalary / fullCount : 0);
            System.out.printf("All Faculty: $%.2f%n",
                    (assistantCount + associateCount + fullCount) > 0 ? totalSalary / (assistantCount + associateCount + fullCount) : 0);

        } catch (IOException e) {
            System.out.println("Error reading data: " + e.getMessage());
        }
    }
}
