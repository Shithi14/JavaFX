import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class GenerateBabyNameFiles {
    public static void main(String[] args) {
        int startYear = 2001;
        int endYear = 2010;
        String[] boyNames = {"Jacob", "Ethan", "Michael", "Jayden", "William", "Alexander", "Daniel", "Elijah", "Matthew", "Aiden"};
        String[] girlNames = {"Isabella", "Sophia", "Emma", "Olivia", "Ava", "Emily", "Abigail", "Madison", "Chloe", "Mia"};
        Random random = new Random();

        for (int year = startYear; year <= endYear; year++) {
            String fileName = "babynameranking" + year + ".txt";

            try (FileWriter writer = new FileWriter(fileName)) {
                for (int rank = 1; rank <= 1000; rank++) {
                    // Generate random boy and girl names and their counts
                    String boyName = boyNames[random.nextInt(boyNames.length)];
                    int boyCount = random.nextInt(20000) + 1000; // Random count between 1000 and 21000

                    String girlName = girlNames[random.nextInt(girlNames.length)];
                    int girlCount = random.nextInt(20000) + 1000; // Random count between 1000 and 21000

                    // Write the data to the file
                    writer.write(String.format("%d %s %d %s %d%n", rank, boyName, boyCount, girlName, girlCount));
                }

                System.out.println("Generated file: " + fileName);
            } catch (IOException e) {
                System.err.println("Error writing file: " + fileName + ". " + e.getMessage());
            }
        }
    }
}