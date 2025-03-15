import java.util.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

class Cinema {
    String name;
    List<Hall> halls = new ArrayList<>();

    public Cinema(String name) {
        this.name = name;
    }

    public void addHall(Hall hall) {
        halls.add(hall);
    }
}

class Hall {
    String name;
    int rows, cols;
    List<Session> sessions = new ArrayList<>();

    public Hall(String name, int rows, int cols) {
        this.name = name;
        this.rows = rows;
        this.cols = cols;
    }

    public void addSession(Session session) {
        sessions.add(session);
    }
}

class Session {
    String movie;
    String time;
    int duration;
    char[][] seats;

    public Session(String movie, String time, int duration, int rows, int cols) {
        this.movie = movie;
        this.time = time;
        this.duration = duration;
        this.seats = new char[rows][cols];
        for (char[] row : seats) Arrays.fill(row, 'O');
    }

    public boolean hasFreeSeats() {
        for (char[] row : seats) {
            for (char seat : row) {
                if (seat == 'O') return true;
            }
        }
        return false;
    }

    public void printSeatPlan() {
        System.out.println("План зала для " + movie + " " + time + ":");
        for (char[] row : seats) {
            for (char seat : row) {
                System.out.print(seat + " ");
            }
            System.out.println();
        }
    }

    public boolean bookSeat(int row, int col) {
        if (row >= 0 && row < seats.length && col >= 0 && col < seats[0].length && seats[row][col] == 'O') {
            seats[row][col] = 'X';
            return true;
        }
        return false;
    }
}

public class CinemaSystem {
    static List<Cinema> cinemas = new ArrayList<>();
    static final String ADMIN_LOGIN = "admin";
    static final String ADMIN_PASSWORD = "1234";
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Выберите режим:\n1. Администратор\n2. Пользователь\n3. Выход");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> adminLogin();
                case 2 -> userMenu();
                case 3 -> System.exit(0);
                default -> System.out.println("Некорректный ввод");
            }
        }
    }

    static void adminLogin() {
        System.out.println("Введите логин: ");
        String login = scanner.next();
        System.out.println("Введите пароль: ");
        String password = scanner.next();
        if (login.equals(ADMIN_LOGIN) && password.equals(ADMIN_PASSWORD)) {
            adminMenu();
        } else {
            System.out.println("Неверные учетные данные.");
        }
    }

    static void adminMenu() {
        while (true) {
            System.out.println("1. Добавить кинотеатр\n2. Добавить зал\n3. Добавить сеанс\n4. Назад");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> addCinema();
                case 2 -> addHall();
                case 3 -> addSession();
                case 4 -> { return; }
                default -> System.out.println("Некорректный ввод");
            }
        }
    }

    static void userMenu() {
        while (true) {
            System.out.println("1. Найти ближайший сеанс\n2. Показать план зала\n3. Купить билет\n4. Назад");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> findNearestSession();
                case 2 -> showSeatPlan();
                case 3 -> buyTicket();
                case 4 -> { return; }
                default -> System.out.println("Некорректный ввод");
            }
        }
    }
    static void addSession() {
        if (cinemas.isEmpty()) {
            System.out.println("Нет доступных кинотеатров.");
            return;
        }
        System.out.println("Выберите кинотеатр:");
        for (int i = 0; i < cinemas.size(); i++) {
            System.out.println((i + 1) + ". " + cinemas.get(i).name);
        }
        int cinemaChoice = scanner.nextInt() - 1;
        scanner.nextLine();
        Cinema cinema = cinemas.get(cinemaChoice);

        if (cinema.halls.isEmpty()) {
            System.out.println("В выбранном кинотеатре нет залов.");
            return;
        }
        System.out.println("Выберите зал:");
        for (int i = 0; i < cinema.halls.size(); i++) {
            System.out.println((i + 1) + ". " + cinema.halls.get(i).name);
        }
        int hallChoice = scanner.nextInt() - 1;
        scanner.nextLine();
        Hall hall = cinema.halls.get(hallChoice);

        System.out.println("Введите название фильма:");
        String movie = scanner.nextLine();
        System.out.println("Введите время сеанса:");
        String time = scanner.nextLine();
        System.out.println("Введите продолжительность сеанса (в минутах):");
        int duration = scanner.nextInt();
        scanner.nextLine();

        hall.addSession(new Session(movie, time, duration, hall.rows, hall.cols));
        System.out.println("Сеанс добавлен.");
    }


    static void addCinema() {
        System.out.println("Введите название кинотеатра:");
        String name = scanner.nextLine();
        cinemas.add(new Cinema(name));
        System.out.println("Кинотеатр добавлен.");
    }

    static void addHall() {
        if (cinemas.isEmpty()) {
            System.out.println("Нет доступных кинотеатров.");
            return;
        }
        System.out.println("Выберите кинотеатр:");
        for (int i = 0; i < cinemas.size(); i++) {
            System.out.println((i + 1) + ". " + cinemas.get(i).name);
        }
        int cinemaChoice = scanner.nextInt() - 1;
        scanner.nextLine();
        System.out.println("Введите название зала:");
        String hallName = scanner.nextLine();
        System.out.println("Введите количество рядов:");
        int rows = scanner.nextInt();
        System.out.println("Введите количество мест в ряду:");
        int cols = scanner.nextInt();
        cinemas.get(cinemaChoice).addHall(new Hall(hallName, rows, cols));
        System.out.println("Зал добавлен.");
    }

    static void findNearestSession() {
        System.out.println("Введите название фильма:");
        String movie = scanner.nextLine();
        Session nearestSession = null;
        Cinema nearestCinema = null;
        Hall nearestHall = null;
        LocalTime earliestTime = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        for (Cinema cinema : cinemas) {
            for (Hall hall : cinema.halls) {
                for (Session session : hall.sessions) {
                    if (session.movie.equalsIgnoreCase(movie) && session.hasFreeSeats()) {
                        LocalTime sessionTime = LocalTime.parse(session.time, formatter);
                        if (earliestTime == null || sessionTime.isBefore(earliestTime)) {
                            nearestSession = session;
                            nearestCinema = cinema;
                            nearestHall = hall;
                            earliestTime = sessionTime;
                        }
                    }
                }
            }
        }

        if (nearestSession != null) {
            System.out.println("Ближайший сеанс: " + nearestSession.movie + " в кинотеатре " + nearestCinema.name + ", зал " + nearestHall.name + " в " + nearestSession.time);
        } else {
            System.out.println("Нет доступных сеансов для " + movie);
        }
    }

    static void showSeatPlan() {
        System.out.println("Выберите кинотеатр:");
        for (int i = 0; i < cinemas.size(); i++) {
            System.out.println((i + 1) + ". " + cinemas.get(i).name);
        }
        int cinemaChoice = scanner.nextInt() - 1;
        scanner.nextLine();
        Cinema cinema = cinemas.get(cinemaChoice);

        if (cinema.halls.isEmpty()) {
            System.out.println("В выбранном кинотеатре нет залов.");
            return;
        }

        System.out.println("Выберите зал:");
        for (int i = 0; i < cinema.halls.size(); i++) {
            System.out.println((i + 1) + ". " + cinema.halls.get(i).name);
        }
        int hallChoice = scanner.nextInt() - 1;
        scanner.nextLine();
        Hall hall = cinema.halls.get(hallChoice);

        if (hall.sessions.isEmpty()) {
            System.out.println("В выбранном зале нет сеансов.");
            return;
        }

        System.out.println("Выберите сеанс:");
        for (int i = 0; i < hall.sessions.size(); i++) {
            System.out.println((i + 1) + ". " + hall.sessions.get(i).movie + " " + hall.sessions.get(i).time);
        }
        int sessionChoice = scanner.nextInt() - 1;
        scanner.nextLine();
        Session session = hall.sessions.get(sessionChoice);
        session.printSeatPlan();
    }

    static void buyTicket() {
        System.out.println("Выберите кинотеатр:");
        for (int i = 0; i < cinemas.size(); i++) {
            System.out.println((i + 1) + ". " + cinemas.get(i).name);
        }
        int cinemaChoice = scanner.nextInt() - 1;
        scanner.nextLine();
        Cinema cinema = cinemas.get(cinemaChoice);

        System.out.println("Выберите зал:");
        for (int i = 0; i < cinema.halls.size(); i++) {
            System.out.println((i + 1) + ". " + cinema.halls.get(i).name);
        }
        int hallChoice = scanner.nextInt() - 1;
        scanner.nextLine();
        Hall hall = cinema.halls.get(hallChoice);

        System.out.println("Выберите сеанс:");
        for (int i = 0; i < hall.sessions.size(); i++) {
            System.out.println((i + 1) + ". " + hall.sessions.get(i).movie + " " + hall.sessions.get(i).time);
        }
        int sessionChoice = scanner.nextInt() - 1;
        scanner.nextLine();
        Session session = hall.sessions.get(sessionChoice);

        System.out.println("Введите номер ряда:");
        int row = scanner.nextInt() - 1;
        System.out.println("Введите номер места:");
        int col = scanner.nextInt() - 1;

        if (session.bookSeat(row, col)) {
            System.out.println("Билет успешно куплен!");
        } else {
            System.out.println("Место уже занято или неверные данные.");
        }
    }
}
