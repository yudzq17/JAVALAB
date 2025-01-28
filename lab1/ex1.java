import java.util.Scanner;

public class ex1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();  // Ввод числа
        int steps = 0;

        while (n != 1) {  // Пока n не станет 1
            if (n % 2 == 0) {
                n = n / 2;  // Если четное, делим на 2
            } else {
                n = 3 * n + 1;  // Если нечетное, умножаем на 3 и прибавляем 1
            }
            steps++;  // Считаем шаги
        }

        System.out.println(steps);  // Выводим количество шагов
    }
}
