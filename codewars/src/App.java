import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.Set;
import java.util.HashSet;
import java.util.stream.IntStream;

public class App {
    static long[] fibo = new long[0];


    public static void main(String[] args) {
        // System.out.println(VowelCount("hello45789"));
        /*System.out.println(ClosestEnemy(new int[] {1, 0, 0, 0, 2, 2, 2}));
        System.out.println(ClosestEnemy(new int[] {2, 0, 0, 0, 2, 2, 1, 0}));
        System.out.println(ClosestEnemy(new int[] {1, 0, 0, 0, 0}));
        System.out.println(SumMultiplier(new int[] {2, 2, 2, 2, 4, 1}));
        System.out.println(SumMultiplier(new int[] {1, 1, 2, 10, 3, 1, 12}));
        System.out.println(SumMultiplier(new int[] {2, 5, 6, -6, 16, 2, 3, 6, 5, 3}));
        System.out.println(SumMultiplier(new int[] {4, 5, 5, 5, 12}));*/

        BigDecimal bd = new BigDecimal(100);
        System.out.println(bd.multiply(new BigDecimal(2.0005)));





    }

    /*public static String decode(String morseCode) {
        return "";
        // your brilliant code here, remember that you can access the preloaded Morse code table through MorseCode.get(code)
    }*/

    public static String SumMultiplier(int[] arr) {
        if(arr.length < 2) return "false";
        // int doubleSum = Arrays.stream(arr).sum() * 2;
        /*
        for(int i = 0; i < arr.length; i++) {
            for(int j = 0; j < arr.length; j++) {
                if(i != j && arr[i] * arr[j] > doubleSum) return "true";
            }
        }
        return "false";*/
        Arrays.sort(arr);
        return arr[arr.length - 1] * arr[arr.length - 2] > Arrays.stream(arr).sum() * 2 ? "true" : "false";


        /*long greaterThanDoubleSum = IntStream.range(0, arr.length).map(index ->
                IntStream.range(0, arr.length)
                        .filter(index2 -> index2 != index && arr[index] * arr[index2] > doubleSum)
                        .findFirst().orElse(-1))
                .filter(result -> result != -1).count();

        return greaterThanDoubleSum > 0 ? "true" : "false";*/

    }

    public static int ClosestEnemy(int[] arr) {
        List<Integer> intList = IntStream.of(arr).boxed().collect(Collectors.toList());
        if(!intList.contains(2)) {
            return 0;
        }
        int me = intList.indexOf(1);
        return IntStream.range(0, intList.size())
                .filter(index -> intList.get(index) == 2)
                .map(index -> Math.abs(me - index))
                .min().orElse(0);
    }

    public static String VowelCount(String str) {
        return String.valueOf(Arrays.stream(str.split("")).filter(letter -> letter.matches("[aeiou]")).count());
    }

    public static String FirstReverse(String str) {
        return new StringBuilder(str).reverse().toString();
    }

    public static int SimpleAdding(int num) {
        // code goes here
        return IntStream.rangeClosed(1, num).sum();
    }

    public static String LongestWord(String sen) {
        /*  my ugly stream
        return Arrays.stream(sen.split(" "))
                .filter(s -> s.matches("[a-zA-Z]+"))
                .collect(Collectors.toMap(String::valueOf, String::length))
                .entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors.toList()).get(0).getKey();

         */
        // beautiful reduce() inspired by Felix
        return Arrays.stream(sen.split(" "))
                .filter(s -> s.matches("[a-zA-Z0-9]+"))
                .reduce("", (word1, word2) -> word1.length() >= word2.length() ? word1 : word2);
    }





    public static String binaryToText(String binary) {
        /*if(binary.isEmpty()) return "";
        String result = "";
        for(int i = 0; i < binary.length() - 7; i += 8){
            result += (char) Integer.parseInt(binary.substring(i, i + 8), 2);
        }
        return result;*/
        return binary.isEmpty() ? "" : IntStream
                .iterate(0, i -> i + 8)
                .limit(binary.length()/8)
                .map(start -> Integer.parseInt(binary.substring(start, start + 8), 2))
                .mapToObj(Character::toString)
                .collect(Collectors.joining());
    }

    public static String reverseWords(final String original)
    {
        if(original.trim().isEmpty()) return original;
        return Arrays.stream(original.split(" ")).map(word -> new StringBuilder(word).reverse().toString()).collect(Collectors.joining(" "));
    }

    public static int findIt(int[] a) {
        int odd = 0;
        for(int i: a) {
            odd ^= i;
        }
        return odd;
    }

    public static boolean isValid(char[] walk) {
        if(walk.length == 1) return false;
        Map<Character, Integer> counts = new HashMap<>(Map.of('n', 0, 's', 0, 'w', 0, 'e', 0));
        for(Character c: walk) {
            counts.put(c, counts.get(c) + 1);
        }

        return counts.get('n') - counts.get('s') == 0 && counts.get('w') - counts.get('e') == 0;
    }

    public static String calculateSpecial(int lastDigit, int radix)
    {
        String result = "";
        

        return result;
    }


    static List<String> roman = List.of("M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I", " ");
    static List<Integer> arabic = List.of(1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1, 0);
    // static Map<String, Integer> mapping = IntStream.range(0, roman.size()).boxed().collect(Collectors.toMap(roman::get, arabic::get));
    private static String convert(int number) {
        String result = "";
        for(int i = 0; i < arabic.size() - 1; i++) {
            while(number >= arabic.get(i)) {
                result += roman.get(i);  // += M
                number -= arabic.get(i);
            }
        }

        return result;
    }


    private static int reverseConvert(String romanNumeral) {
        int result = 0;
        for(int i  = 0; i < roman.size(); i++) {
            while(romanNumeral.startsWith(roman.get(i))) {
                result += arabic.get(i);
                romanNumeral = romanNumeral.substring(roman.get(i).length());
            }
        }

        return result;
    }

    private static int convertBack(String romanText) {
        int result = 0;
        String[] letters = (romanText + " ").split("");

        for(int i = 0; i < letters.length - 1; i++) {
            if(roman.contains(letters[i] + letters[i+1])) {
                result += arabic.get(roman.indexOf(letters[i] + letters[i+1]));
                i++;
            } else {
                result += arabic.get(roman.indexOf(letters[i]));
            }
        }

        return result;
    }



    private static long getFibonacci(int n) {
        if(n == 1 || n == 2) {
            fibo[n] = 1;
            return 1;
        }
        if(fibo[n] != 0) return fibo[n];

        long nthFibonacci = getFibonacci(n-1) + getFibonacci(n-2);
        fibo[n] = nthFibonacci;
        return nthFibonacci;
    }

    public static int sumIntervals(int[][] intervals) {
        List<Integer[]> noOverlap = new ArrayList<>();
        for(int[] interval: intervals) {
            if(noOverlap.size() == 0) {
                noOverlap.add(new Integer[] {interval[0], interval[1]});
            } else {
                boolean hasOverlap = false;
                List<Integer[]> toBeMerged = new ArrayList<>();
                for(Integer[] inter: noOverlap) {
                    if((inter[0] < interval[1] && inter[0] >= interval[0]) ||
                            (inter[1] <= interval[1] && inter[1] > interval[0]) ||
                            (inter[0] >= interval[0] && inter[1] <= interval[1]) ||
                            (interval[0] >= inter[0] && interval[1] <= inter[1])) {
                        hasOverlap = true;
                        toBeMerged.add(inter);
                    }
                }
                if(hasOverlap) {
                    toBeMerged.add(new Integer[] {interval[0], interval[1]});
                    merge(toBeMerged, noOverlap);
                }
                else noOverlap.add(new Integer[] {interval[0], interval[1]});
            }
            noOverlap.forEach(x -> System.out.print("[" + x[0] + ", " + x[1] + "] "));
            System.out.println();
        }

        return noOverlap.stream().map(interval -> interval[1] - interval[0]).mapToInt(Integer::intValue).sum();
    }
    private static void merge(List<Integer[]> toBeMerged, List<Integer[]> noOverlap) {
        Integer[] merged = new Integer[2];
        merged[0] = toBeMerged.get(0)[0];
        merged[1] = toBeMerged.get(0)[1];
        for(Integer[] inter: toBeMerged) {
            if(inter[0] < merged[0]) merged[0] = inter[0];
            if(inter[1] > merged[1]) merged[1] = inter[1];
            noOverlap.remove(inter);
        }
        noOverlap.add(merged);
    }

    public static boolean check(int[][] sudoku) {
        Set<Integer> numbers = new HashSet<>();
        // check row
        for(int[] row: sudoku) {
            for(int cell: row) {
                if(cell < 1 || cell > 9) return false;
                numbers.add(cell);
            }
            if(numbers.size() != 9) return false;
            numbers.clear();
        }
        // check col
        for(int col = 0; col < sudoku[0].length; col++) {
            for(int row = 0; row < sudoku.length; row++) {
                numbers.add(sudoku[row][col]);
            }
            if(numbers.size() != 9) return false;
            numbers.clear();
        }

        // check subgrid
        int colStart = 0;
        int colEnd = 2;
        int rowCount = 0;
        while(colEnd < sudoku[0].length) {
            for(int row = 0; row < sudoku.length; row++) {
                if(rowCount == 3) {
                    if(numbers.size() != 9) return false;
                    numbers.clear();
                    rowCount = 0;
                }
                for(int col = colStart; col <= colEnd; col++) {
                    numbers.add(sudoku[row][col]);
                }
                rowCount++;
            }
            colStart += 3;
            colEnd += 3;
        }

        return true;
    }

    static List<String> alphabet = Arrays.asList("abcdefghijklmnopqrstuvwxyz".split(""));
    public static String decode(String r) {
        int number  = Integer.parseInt(r.replaceAll("[^0-9]", ""));
        String letters = r.replaceAll("[0-9]", "");
        int reducedNum = number % 26;
        String text = "";
        for(String letter: letters.split("")) {
            String l = getLetter(alphabet.indexOf(letter), reducedNum);
            if(l.equals("")) return "Impossible to decode";
            text += l;
        }


        return text;
    }

    private static String getLetter(int index, int reducedNum) {
        List<String> matchLetter = new ArrayList<>();
        for(int i = 0; i < 26; i++) {
            if(i * reducedNum % 26 == index) matchLetter.add(alphabet.get(i));
        }
        return matchLetter.size() == 1 ? matchLetter.get(0) : "";
    }

    public static char findMissingLetter(char[] array)
    {
        for(int i = 0; i < array.length - 1; i++) {
            if(array[i + 1] - array[i] != 1) return (char) (Character.hashCode(array[i]) + 1);
        }
        return ' ';
    }

    public static int squareDigits(int n) {
        String result = Arrays.stream(String.valueOf(n).split("")).mapToInt(Integer::parseInt).map(num -> num * num).mapToObj(num -> String.valueOf(num)).collect(Collectors.joining());
        return Integer.valueOf(result);

    }

    // 1st parameter is the stocklist (L in example),
    // 2nd parameter is list of categories (M in example)
    public static String stockSummary(String[] lstOfArt, String[] lstOf1stLetter) {
        if(lstOfArt.length == 0)  return "";
        String summary = "";
        Map<String, Integer> count = new HashMap<>();
        for(String letter: lstOf1stLetter) {
              for(String art: lstOfArt) {
                  String[] codeNumber = art.split(" ");
                  if(letter.equals(codeNumber[0].substring(0, 1))) {
                      if(count.containsKey(letter)) count.put(letter, count.get(letter) + Integer.parseInt(codeNumber[1]));
                      else count.put(letter, Integer.parseInt(codeNumber[1]));
                  }
              }
        }

        for(String letter: lstOf1stLetter) {
            System.out.println("count: " + count.get(letter));
            Integer c =   count.get(letter) == null ? 0 : count.get(letter);
            summary += "(" + letter + " : " + c + ") - ";
        }
        
        return summary.substring(0, summary.lastIndexOf(")") + 1);
    }


      public static int[] sortArray(int[] array) {
        List<Integer> oddIndex = new ArrayList<>();
        for(int i = 0; i < array.length; i++) {
            if(array[i] % 2 != 0) oddIndex.add(i);
        }
        List<Integer> oddValues = new ArrayList<>();
        Arrays.stream(array).filter(x -> x%2 != 0).forEach(x -> oddValues.add(x));
        Collections.sort(oddValues);
        for(int index: oddIndex) {
            array[index] =  oddValues.remove(0);
        }
        return array;

      }
      public static String mix(String s1, String s2) {
        String result = "";
        s1 = s1.replaceAll("[^a-z]", "");
        s2 = s2.replaceAll("[^a-z]", "");
        String[] s1Arr = s1.split("");
        String[] s2Arr = s2.split("");
        Map<String, Long> countS1 = Arrays.stream(s1Arr).collect(Collectors.groupingBy(String::valueOf, Collectors.counting()));
        Map<String, Long> countS2 = Arrays.stream(s2Arr).collect(Collectors.groupingBy(String::valueOf, Collectors.counting()));
        Map<String, Long> sortedS1 = new HashMap<>();  // descending order
        Map<String, Long> sortedS2 = new HashMap<>();
        countS1.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).filter(x -> x.getValue() > 1).forEachOrdered(x -> sortedS1.put(x.getKey(), x.getValue()));
        countS2.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).filter(x -> x.getValue() > 1).forEachOrdered(x -> sortedS2.put(x.getKey(), x.getValue()));
        List<String> joined = new ArrayList<>();
        String toAdd = "";
        for(Map.Entry<String, Long> entry: sortedS1.entrySet()) {
            if(sortedS2.containsKey(entry.getKey())) {
                if(sortedS2.get(entry.getKey()) > entry.getValue()) continue;
                else if(sortedS2.get(entry.getKey()) < entry.getValue()) sortedS2.remove(entry.getKey());
            }
            toAdd = "1" + entry.getKey().repeat(entry.getValue().intValue());
            if(joined.isEmpty()) joined.add(toAdd);
            else {
                joined = insert(joined, toAdd);
            }
        }
        for(Map.Entry<String, Long> entry: sortedS2.entrySet()) {
            toAdd = "2" + entry.getKey().repeat(entry.getValue().intValue());
            joined = insert(joined, toAdd);
        }

        reorder(joined);
        reorder(joined);

        for(String s: joined) {
            if(Character.isDigit(s.charAt(0))) result += s.charAt(0) + ":" + s.substring(1) + "/";
            else result += "=:" + s.substring(1) + "/";
        }

        return result.substring(0, result.length() - 1);
    }

    private static void reorder(List<String> joined) {
        for(int i = 0; i < joined.size() - 1; i++) {
            if (joined.get(i).length() == joined.get(i + 1).length()) {
                if ((joined.get(i).charAt(0) == '=' && joined.get(i + 1).charAt(0) != '=') || (joined.get(i).charAt(0) == '2' && joined.get(i + 1).charAt(0) == '1'))
                    swapListElement(i, i + 1, joined);
            }
        }
    }

    private static void swapListElement(int i, int j, List<String> joined) {
           String temp = joined.get(i);
           joined.set(i, joined.get(j));
           joined.set(j, temp);
    }

    private static List<String> insert(List<String> joined, String toAdd) {
        for(int i = 0; i < joined.size(); i++) {
            if(joined.get(i).length() < toAdd.length() || (joined.get(i).length() == toAdd.length() && joined.get(i).codePointAt(1) > toAdd.codePointAt(1))) {
                joined.add(i, toAdd);
                return joined;
            } else if(joined.get(i).length() == toAdd.length() && joined.get(i).codePointAt(1) == toAdd.codePointAt(1)) {
                joined.set(i, joined.get(i).replaceAll("[^a-z]", "="));
                joined.get(i).codePointCount(0, joined.get(i).length());
                return joined;
            }
        }
        joined.add(toAdd);
        return joined;
    }

    public static int[] snail(int[][] array) {
        int[] flattened = new int[array.length * array[0].length];
        int count = 0;
        int row = 0;
        int col = 0;
        while(count < array.length * array[0].length) {
            flattened[count] = array[row][col];
            count++;

        }

        return flattened;
    }
    public static long nextBiggerNumber(long n)
    {
        String[] digits = String.valueOf(n).split("");
        String[] sorted = Arrays.stream(digits).mapToInt(Integer::valueOf).sorted().mapToObj(String::valueOf).toArray(String[]::new);
        Arrays.sort(sorted, Comparator.reverseOrder());  // in descending order
        if(Long.valueOf(String.join("", sorted)) == n) return -1;

        int startIndex = digits.length - 1;
        List<Long> bigger = new ArrayList<>();
        while(startIndex > 0) {
            int indexToBeExchanged = startIndex - 1;
            while(indexToBeExchanged >= 0) {
                String[] swapped = swap(indexToBeExchanged, startIndex, Arrays.copyOf(digits, digits.length));
                long newN = Long.valueOf(String.join("", swapped));
                if(newN > n) {
                    bigger.add(newN);
                }
                indexToBeExchanged--;
            }
            startIndex --;
        }

        long between = getBiggerLongBetween(n, Collections.min(bigger));

        return between == -1 ? Collections.min(bigger) : between;
    }
    private static String[] swap(int toBeExchanged, int start, String[] digits) {
        String temp = digits[toBeExchanged];
        digits[toBeExchanged] = digits[start];
        digits[start] = temp;
        return digits;
    }

    private static long getBiggerLongBetween(long min, long max) {
        for(long i = min + 1; i < max + 1; i++) {
            if(containSameDigits(min, i)) return i;
        }
        return -1;
    }

    private static boolean containSameDigits(long origin, long toBeCompare) {
        String[] o = String.valueOf(origin).split("");
        String[] t = String.valueOf(toBeCompare).split("");
        o = Arrays.stream(o).sorted().toArray(String[]::new);
        t = Arrays.stream(t).sorted().toArray(String[]::new);

        return Arrays.toString(o).equals(Arrays.toString(t));
    }

    public static String rangeExtraction(int[] arr) {
        String result = "";
        int start = arr[0];
        boolean consecutive = false;
        int consecutiveCount = 0;
        for(int i: arr) {
            if(i - start != 1) {
                if(consecutive) {
                    if(consecutiveCount > 1) result  += "-" + start + ",";
                    else result += "," +  start + ",";
                    consecutive = false;
                    consecutiveCount = 0;
                    start = i;
                    result += i + ",";
                } else {
                    result += i + ",";
                    start = i;
                }
            } else {
                consecutiveCount++;
                if(result.charAt(result.length() - 1) == ',') result = result.substring(0, result.length() - 1);
                start = i;
                consecutive = true;
            }
        }
        if(result.charAt(result.length() - 1) == ',') result = result.substring(0, result.length() - 1);
        if(consecutiveCount > 1) result  += "-" + start;
        else if(!result.contains(String.valueOf(arr[arr.length-1]))) result += "," +  start;
        return result;
    }

    public static long[] eqSumPowDig(long hmax, int exp) {
        List<Long> matches = new ArrayList<>();
        for (long i = 2; i <= hmax; i++) {
            String[] digits = String.valueOf(i).split("");
            double sum = Arrays.asList(digits).stream().mapToDouble(Double::valueOf).map(x -> Math.pow(x, (double) exp)).sum();
            if(sum == i) matches.add(i);
        }
        return matches.stream().mapToLong(Long::valueOf).toArray();
    }

    public String toString() {
        System.out.println("hello!");
        return "";
    }

}

class Day{

    public String countDays(Date d){
        Date current = new Date();
        long diffInMillies = Math.abs(d.getTime() - current.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        if (diff > 0) {
            if(d.before(current)) return "The day is in the past!";
            else return "" + diff + " days";
        }  else return "Today is the day!";
    }

}

class Boggle {
    /*
    *   {'E','A','R','A'},
        {'N','L','E','C'},
        {'I','A','I','S'},
        {'B','Y','O','R'}
    *
    *
    *
    * */
    private char[][] board;
    private String word;
    public Boggle(final char[][] board, final String word) {
        this.board = board;
        this.word = word;
    }

    public boolean check() {
        for(int row = 0; row < board.length; row++) {
            for(int col = 0; col < board[0].length; col++){
                if (board[row][col] == word.charAt(0)) {
                }
            }
        }
        return false;
    }
}