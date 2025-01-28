import java.util.Scanner;

public class ex2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Вводим натуральное число n
        System.out.print("Введите натуральное число n > 0: ");
        int n = scanner.nextInt();

        if (n <= 0) {
            System.out.println("Ошибка: число n должно быть больше 0.");
            return;
        }

        int sum = 0;

        // Вводим n чисел и вычисляем знакочередующуюся сумму
        System.out.println("Введите " + n + " чисел (каждое на новой строке):");
        for (int i = 1; i <= n; i++) {
            int num = scanner.nextInt();

            if (i % 2 == 0) {
                sum -= num; // Вычитаем, если номер числа чётный
            } else {
                sum += num; // Прибавляем, если номер числа нечётный
            }
        }
        // Выводим результат
        System.out.println("Знакочередующаяся сумма: " + sum);
    }
}
