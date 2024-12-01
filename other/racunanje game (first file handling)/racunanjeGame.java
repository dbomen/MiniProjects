import java.util.*;
import java.io.*;

public class racunanjeGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rd = new Random();

        int points = 0;
        
        try {
            File save = new File("save.txt");
            Scanner myReader = new Scanner(save);
            points = myReader.nextInt();
            myReader.close();
        } catch (IOException e) {
            System.out.println("An error occurred <READING>.");
            e.printStackTrace();
        }

        while (true) {
            Racun racun = new Racun(rd.nextInt(100), rd.nextInt(100));
            System.out.printf("Your points: %d%n", points);
            System.out.printf("%s: ", racun.pokaziRacun());
            int rezultat = sc.nextInt();

            if (rezultat == racun.getNum1() + racun.getNum2()) {
                points = addOnePoint(points);
            }
        }
    }

    public static int addOnePoint(int points) {
        try {
            FileWriter myWriter = new FileWriter("save.txt");
            myWriter.write(Integer.toString(points + 1));
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred <WRITING>.");
            e.printStackTrace();
        }

        return points + 1;
    }

    public static class Racun {
        private int num1;
        private int num2;

        public Racun(int num1, int num2) {
            this.num1 = num1;
            this.num2 = num2;
        }

        public String pokaziRacun() {
            return String.format("%d + %d", this.num1, this.num2);
        }

        public int getNum1() {
            return this.num1;
        }

        public int getNum2() {
            return this.num2;
        }
    }

}