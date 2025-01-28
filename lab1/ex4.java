import java.util.Scanner;

public class ex4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Считываем количество дорог
        int numberOfRoads = scanner.nextInt();

        int maxHeight = 0;
        int bestRoad = 0;

        // Перебираем дороги
        for (int road = 1; road <= numberOfRoads; road++) {
            int numberOfTunnels = scanner.nextInt(); // Считываем количество туннелей на дороге
            int minTunnelHeight = Integer.MAX_VALUE; // Инициализируем минимальную высоту туннеля

            // Считываем высоты туннелей и находим минимальную
            for (int i = 0; i < numberOfTunnels; i++) {
                int tunnelHeight = Integer.parseInt(scanner.next());
                if (tunnelHeight < minTunnelHeight) {
                    minTunnelHeight = tunnelHeight;
                }
            }

            // Проверяем, является ли эта дорога лучшей
            if (minTunnelHeight > maxHeight) {
                maxHeight = minTunnelHeight;
                bestRoad = road;
            }
        }

        // Выводим номер лучшей дороги и её максимальную высоту
        System.out.println(bestRoad);
        System.out.println(maxHeight);

        scanner.close();
    }
}
