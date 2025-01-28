import java.util.Scanner;

public class ex5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Ввод трехзначного числа
            System.out.print("Введите положительное трехзначное число (или 0 для выхода): ");
            int number = scanner.nextInt();

            // Проверка на выход
            if (number == 0) {
                System.out.println("Программа завершена.");
                break;
            }

            // Проверка на корректность ввода
            if (number < 100 || number > 999) {
                System.out.println("Ошибка: число должно быть положительным и трехзначным.");
                continue;
            }

            // Извлечение цифр числа
            int digit1 = number / 100;       // Сотни
            int digit2 = (number / 10) % 10; // Десятки
            int digit3 = number % 10;        // Единицы

            // Вычисление суммы и произведения цифр
            int sum = digit1 + digit2 + digit3;
            int product = digit1 * digit2 * digit3;

            // Проверка условий "дважды четного" числа
            if (sum % 2 == 0 && product % 2 == 0) {
                System.out.println("Число " + number + " является 'дважды четным'.");
            } else {
                System.out.println("Число " + number + " не является 'дважды четным'.");
            }
        }

        scanner.close();
    }
}