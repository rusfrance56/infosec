package arrayInterval;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {

        int[] arr = new int[]{2, 2, 6, 1, 5, 4, 5, 6, 9, 1, 66, 4};
        int requiredSum = 71;

        List<Integer> integers = searchSumElementsOfArray(arr, requiredSum);

        if (integers == null) {
            System.out.println("no results found.");
        } else if (integers.get(1) > integers.get(0)) {
            System.out.println("i = " + integers.get(0) + "; j = " + integers.get(1));
        } else {
            System.out.println("the required number of positions at: " + integers.get(0));
        }
    }

    private static List<Integer> searchSumElementsOfArray(int[] arr, int requiredSum) {
        List<Integer> result = new ArrayList<Integer>();

        int i = 0;
        int j = 0;
        int sum = 0;
        while (j < arr.length && sum != requiredSum) {
            if (sum < requiredSum) {
                sum = sum + arr[j];
                j++;
            }
            while (sum > requiredSum) {
                sum = sum - arr[i];
                i++;
            }
        }
        j--;

        if (sum != requiredSum) {
            return null;
        }

        result.add(i);
        result.add(j);
        return result;
    }
}
