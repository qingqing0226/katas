import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CoderByte {
    public static void main(String[] args) {
        //System.out.println(ClosestEnemyII(new String[] {"0000", "1000", "0002", "0002"}));
        System.out.println(ClosestEnemyII(new String[] {"0000", "2010", "0000", "2002"}));

    }

    public static String NumberSearch(String str) {
        int sum = Arrays.stream(str.split(""))
                .filter(s -> s.matches("[0-9]"))
                .mapToInt(Integer::parseInt).sum();
        long countLetters = Arrays.stream(str.split(""))
                .filter(s -> s.matches("[a-zA-Z]"))
                .count();

        return String.valueOf(Math.round(sum/countLetters));
    }

    public static String ClosestEnemyII(String[] strArr) {
        Arrays.stream(strArr).forEach(x -> {
            System.out.println(x);
        });
        if(Arrays.stream(strArr).filter(row -> row.contains("2")).count() == 0) return "0";
        int closest = closestDistance(convertToMatrix(strArr));
        while(strArr.length > 1) {
            List<String> temp = new ArrayList<>();
            for(int i = 0; i < strArr.length - 1; i=i+2) {
                temp.add(strArr[i] + strArr[i+1]);
            }
            strArr = temp.toArray(new String[0]);
            int newDistance = closestDistance(convertToMatrix(strArr));
            if(newDistance < closest) closest = newDistance;
        }

        return String.valueOf(closest);
    }

    private static List<List<String>> convertToMatrix(String[] input) {
        List<List<String>> matrix = new ArrayList<>();
        Arrays.stream(input).forEach(row -> matrix.add(Arrays.stream(row.split("")).collect(Collectors.toList())));
        return matrix;
    }

    private static int closestDistance (List<List<String>> matrix) {
        int[] oneCoordi = {0, 0};
        int closest = matrix.size() * matrix.get(0).size();
        for(int i = 0; i < matrix.size(); i++) {
            for(int j = 0; j < matrix.get(0).size(); j++) {
                if(matrix.get(i).get(j).equals("1")) {
                    oneCoordi[0] = i;
                    oneCoordi[1] = j;
                }
            }
        }

        for(int i = 0; i < matrix.size(); i++) {
            for(int j = 0; j < matrix.get(0).size(); j++) {
                if(matrix.get(i).get(j).equals("2")) {
                    int distance = Math.abs(i - oneCoordi[0]) + Math.abs(j - oneCoordi[1]);
                    if(distance < closest) closest = distance;
                }
            }
        }

        return closest;
    }




}
