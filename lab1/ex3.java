import java.util.Scanner;

public class ex3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Чтение координат клада
        int treasureX = Integer.parseInt(scanner.nextLine().trim());
        int treasureY = Integer.parseInt(scanner.nextLine().trim());

        // Текущая позиция и счетчик шагов
        int currentX = 0, currentY = 0, steps = 0;

        while (true) {
            String direction = scanner.nextLine().trim();
            if (direction.equals("стоп")) break;

            int distance = Integer.parseInt(scanner.nextLine().trim());

            // Движение по направлению
            if (direction.equals("север") && currentY < treasureY) {
                currentY = Math.min(currentY + distance, treasureY);
                steps++;
            } else if (direction.equals("юг") && currentY > treasureY) {
                currentY = Math.max(currentY - distance, treasureY);
                steps++;
            } else if (direction.equals("восток") && currentX < treasureX) {
                currentX = Math.min(currentX + distance, treasureX);
                steps++;
            } else if (direction.equals("запад") && currentX > treasureX) {
                currentX = Math.max(currentX - distance, treasureX);
                steps++;
            }
        }

        // Вывод результата
        System.out.println(steps);
    }
}