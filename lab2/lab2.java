import java.util.*;

class ArrayAndStringTasks {

    // 1. Найти наибольшую подстроку без повторяющихся символов
    public static String longestUniqueSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int left = 0, maxLength = 0, start = 0;
        for (int right = 0; right < s.length(); right++) {
            while (set.contains(s.charAt(right))) {
                set.remove(s.charAt(left++));
            }
            set.add(s.charAt(right));
            if (right - left + 1 > maxLength) {
                maxLength = right - left + 1;
                start = left;
            }
        }
        return s.substring(start, start + maxLength);
    }

    // 2. Объединить два отсортированных массива
    public static int[] mergeSortedArrays(int[] arr1, int[] arr2) {
        int[] merged = new int[arr1.length + arr2.length];
        int i = 0, j = 0, k = 0;
        while (i < arr1.length && j < arr2.length) {
            merged[k++] = (arr1[i] < arr2[j]) ? arr1[i++] : arr2[j++];
        }
        while (i < arr1.length) merged[k++] = arr1[i++];
        while (j < arr2.length) merged[k++] = arr2[j++];
        return merged;
    }

    // 3. Найти максимальную сумму подмассива (Алгоритм Кадана)
    public static int maxSubarraySum(int[] nums) {
        int maxSum = nums[0], currentSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            maxSum = Math.max(maxSum, currentSum);
        }
        return maxSum;
    }

    // 4. Повернуть массив на 90 градусов по часовой стрелке
    public static int[][] rotateMatrixClockwise(int[][] matrix) {
        int n = matrix.length;
        int[][] rotated = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                rotated[j][n - 1 - i] = matrix[i][j];
            }
        }
        return rotated;
    }

    // 5. Найти пару элементов, сумма которых равна target
    public static int[] findPairWithSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            int complement = target - num;
            if (map.containsKey(complement)) {
                return new int[]{complement, num};
            }
            map.put(num, 1);
        }
        return null;
    }

    // 6. Найти сумму всех элементов в двумерном массиве
    public static int sum2DArray(int[][] matrix) {
        int sum = 0;
        for (int[] row : matrix) {
            for (int num : row) {
                sum += num;
            }
        }
        return sum;
    }

    // 7. Найти максимальный элемент в каждой строке двумерного массива
    public static int[] maxInEachRow(int[][] matrix) {
        int[] maxValues = new int[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            maxValues[i] = Arrays.stream(matrix[i]).max().orElse(Integer.MIN_VALUE);
        }
        return maxValues;
    }

    // 8. Повернуть двумерный массив на 90 градусов против часовой стрелке
    public static int[][] rotateMatrixCounterClockwise(int[][] matrix) {
        int n = matrix.length;
        int[][] rotated = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                rotated[n - 1 - j][i] = matrix[i][j];
            }
        }
        return rotated;
    }

    // Метод для тестирования
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. Ввод строки
        System.out.print("Введите строку: ");
        String inputString = scanner.nextLine();
        System.out.println("Наибольшая подстрока без повторов: " + longestUniqueSubstring(inputString));

        // 2. Ввод двух отсортированных массивов
        System.out.print("Введите элементы первого отсортированного массива через пробел: ");
        int[] arr1 = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.print("Введите элементы второго отсортированного массива через пробел: ");
        int[] arr2 = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.println("Объединенный отсортированный массив: " + Arrays.toString(mergeSortedArrays(arr1, arr2)));

        // 3. Ввод массива для поиска максимальной суммы подмассива
        System.out.print("Введите элементы массива для поиска максимальной суммы подмассива через пробел: ");
        int[] arr3 = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        System.out.println("Максимальная сумма подмассива: " + maxSubarraySum(arr3));

        // 4. Ввод двумерного массива и его обработка
        System.out.print("Введите размер двумерного массива: ");
        int n = scanner.nextInt();
        scanner.nextLine(); // Читаем оставшийся \n после nextInt()

        int[][] matrix = new int[n][n];
        System.out.println("Введите элементы матрицы построчно: ");
        for (int i = 0; i < n; i++) {
            matrix[i] = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        System.out.println("Поворот на 90 градусов по часовой стрелке:");
        System.out.println(Arrays.deepToString(rotateMatrixClockwise(matrix)));

        // 5. Ввод массива и target для поиска пары
        System.out.print("Введите массив для поиска пары (через пробел): ");
        int[] arr4 = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.print("Введите целевую сумму target: ");
        int target = scanner.nextInt();
        scanner.nextLine(); // Читаем оставшийся \n после nextInt()

        int[] pair = findPairWithSum(arr4, target);
        System.out.println(pair != null ? "Найденная пара: " + Arrays.toString(pair) : "Пара не найдена.");

        // 6. Ввод новой матрицы для суммы элементов
        System.out.println("Введите размер новой матрицы для суммы элементов: ");
        int m = scanner.nextInt();
        scanner.nextLine();

        int[][] matrix2 = new int[m][m];
        System.out.println("Введите элементы матрицы построчно:");
        for (int i = 0; i < m; i++) {
            matrix2[i] = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        System.out.println("Сумма всех элементов матрицы: " + sum2DArray(matrix2));

        // 7. Максимальный элемент в каждой строке
        System.out.println("Максимальные элементы в каждой строке: " + Arrays.toString(maxInEachRow(matrix2)));

        // 8. Поворот на 90 градусов против часовой стрелки
        System.out.println("Матрица после поворота на 90 градусов против часовой стрелки:");
        System.out.println(Arrays.deepToString(rotateMatrixCounterClockwise(matrix2)));

        scanner.close();
    }
}
