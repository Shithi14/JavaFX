import java.util.Random;
import java.util.Scanner;

public class ArrayIndexOutOfBoundsExceptionDemo {
    public static void main(String[] args) {
        int[] array = new int[100];
        Random rand = new Random();


        for (int i = 0; i < array.length; i++) {
            array[i] = rand.nextInt(1000);
        }

        Scanner input = new Scanner(System.in);
        System.out.print("Enter the index of the array: ");

        try {
            int index = input.nextInt();
            System.out.println("Element at index " + index + " is " + array[index]);
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
    }
}
