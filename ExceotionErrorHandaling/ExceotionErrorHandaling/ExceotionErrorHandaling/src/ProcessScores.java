import java.io.*;
import java.net.URL;
import java.util.Scanner;

public class ProcessScores {
    public static void main(String[] args) {
        try {
            // Replace the URL below with your desired URL
            URL url = new URL("https://raw.githubusercontent.com/Mubasshir14/score-data/refs/heads/main/Scores.txt");

            // Open a connection to the URL
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

            // Read the content of the file
            String line = reader.readLine();
            reader.close();

            // Split the line by spaces to get individual scores and convert them to integers
            Scanner scanner = new Scanner(line);
            int total = 0;
            int count = 0;
            while (scanner.hasNextInt()) {
                int score = scanner.nextInt();
                total += score;
                count++;
            }
            scanner.close();

            // Calculate the average
            double average = (double) total / count;

            // Display the total and average
            System.out.println("Total: " + total);
            System.out.println("Average: " + average);
            System.out.println("Count: " + count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
