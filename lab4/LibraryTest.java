import java.util.*;

// Класс книги
class Book {
    private String title;
    private String author;
    private int year;

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getYear() { return year; }

    @Override
    public String toString() {
        return String.format("Книга: \"%s\", Автор: %s, Год: %d", title, author, year);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return year == book.year &&
                Objects.equals(title, book.title) &&
                Objects.equals(author, book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, year);
    }
}

// Класс библиотеки
class Library {
    private List<Book> books = new ArrayList<>();
    private Set<String> uniqueAuthors = new HashSet<>();
    private Map<String, Integer> authorBookCount = new HashMap<>();

    public void addBook(Book book) {
        books.add(book);
        uniqueAuthors.add(book.getAuthor());
        authorBookCount.put(book.getAuthor(), authorBookCount.getOrDefault(book.getAuthor(), 0) + 1);
    }

    public void removeBook(Book book) {
        if (books.remove(book)) {
            int count = authorBookCount.getOrDefault(book.getAuthor(), 0) - 1;
            if (count <= 0) {
                authorBookCount.remove(book.getAuthor());
                uniqueAuthors.remove(book.getAuthor());
            } else {
                authorBookCount.put(book.getAuthor(), count);
            }
        }
    }

    public List<Book> findBooksByAuthor(String author) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().equals(author)) {
                result.add(book);
            }
        }
        return result;
    }

    public List<Book> findBooksByYear(int year) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getYear() == year) {
                result.add(book);
            }
        }
        return result;
    }

    public void printAllBooks() {
        if (books.isEmpty()) {
            System.out.println("Библиотека пуста.");
        } else {
            books.forEach(System.out::println);
        }
    }

    public void printUniqueAuthors() {
        if (uniqueAuthors.isEmpty()) {
            System.out.println("Нет уникальных авторов.");
        } else {
            uniqueAuthors.forEach(System.out::println);
        }
    }

    public void printAuthorStatistics() {
        if (authorBookCount.isEmpty()) {
            System.out.println("Нет статистики по авторам.");
        } else {
            authorBookCount.forEach((author, count) ->
                    System.out.printf("Автор: %s — Книг: %d%n", author, count)
            );
        }
    }
}

// Тестовый класс
public class LibraryTest {
    public static void main(String[] args) {
        Library library = new Library();

        Book book1 = new Book("Война и мир", "Лев Толстой", 1869);
        Book book2 = new Book("Преступление и наказание", "Фёдор Достоевский", 1866);
        Book book3 = new Book("Анна Каренина", "Лев Толстой", 1877);
        Book book4 = new Book("Идиот", "Фёдор Достоевский", 1869);
        Book book5 = new Book("Ревизор", "Николай Гоголь", 1836);

        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);
        library.addBook(book4);
        library.addBook(book5);

        System.out.println("📚 Все книги:");
        library.printAllBooks();

        System.out.println("\n🔍 Книги Льва Толстого:");
        library.findBooksByAuthor("Лев Толстой").forEach(System.out::println);

        System.out.println("\n📆 Книги, изданные в 1869 году:");
        library.findBooksByYear(1869).forEach(System.out::println);

        System.out.println("\n👤 Уникальные авторы:");
        library.printUniqueAuthors();

        System.out.println("\n📊 Статистика по авторам:");
        library.printAuthorStatistics();

        System.out.println("\n❌ Удаление книги: Анна Каренина");
        library.removeBook(book3);

        System.out.println("\n📚 Книги после удаления:");
        library.printAllBooks();

        System.out.println("\n📊 Статистика после удаления:");
        library.printAuthorStatistics();
    }
}
