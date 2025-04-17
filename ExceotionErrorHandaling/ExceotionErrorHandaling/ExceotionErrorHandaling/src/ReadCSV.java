import java.io.*;
public class ReadCSV {
    public static final String delimiter = ",";

    public static void read(String csvFile) {
        try {
            File file = new File(csvFile);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            String[] tempArr;
            while ((line = br.readLine()) != null) {
                tempArr = line.split(delimiter);
                for (String tempStr : tempArr) {
                    System.out.print(tempStr + " ");
                }
                System.out.println();
            }
            br.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // csv file to read
        String csvFile = "D:\\CSE-14\\ACADEMIC\\SEMESTER 2-1\\OOP\\ExceotionErrorHandaling\\ExceotionErrorHandaling\\House_Rent_Dataset.csv";

        ReadCSV.read(csvFile);
    }
}
