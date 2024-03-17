// changed name beacuse it was "Solution"
import java.lang.Math;

public class SquareError {
    
    public static double solution(int[] arr1, int[] arr2) {
        double[] solutions = new double[arr1.length];

        for (int i = 0; i < arr1.length; i++)   solutions[i] = Math.pow(Math.abs(arr1[i] - arr2[i]), 2);

        double out = 0;
        for (int i = 0; i < solutions.length; i++)   out += solutions[i];

        return out / solutions.length;
    }
}