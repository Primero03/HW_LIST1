package LISTiterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    private static final ArrayList<String> tasks = new ArrayList<>();

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            printMenu();


            int choice = getValidInt("Выберите действие (1-6): ");


            switch (choice) {
                case 1:
                    addTask();
                    break;
                case 2:
                    printTasks();
                    break;
                case 3:
                    removeByIndex();
                    break;
                case 4:
                    removeByText();
                    break;
                case 5:
                    running = false;
                    System.out.println("Выход из программы. До свидания!");
                    break;
                default:
                    System.out.println("Неверный номер действия.");
            }

            if (running) {
                System.out.println("\n--- Текущий список дел ---");
                printTasks();
                System.out.println("-------------------------\n");
            }
        }

    }

    private static void printMenu() {
        System.out.println("=== МЕНЮ УПРАВЛЕНИЯ СПИСКОМ ДЕЛ ===");
        System.out.println("1. Добавить новое дело");
        System.out.println("2. Показать все дела");
        System.out.println("3. Удалить дело по номеру");
        System.out.println("4. Удалить дело по тексту");
        System.out.println("5. Выход");
    }

    private static void addTask() {
        System.out.print("Введите описание дела: ");
        sc.nextLine();
        String task = sc.nextLine().trim();
        System.out.println(task);

        if (task.isEmpty()) {
            System.out.println("Ошибка: описание дела не может быть пустым!");
            return;
        }


        if (tasks.contains(task)) {
            System.out.println("Такое дело уже есть в списке!");
            return;
        }

        tasks.add(task);
        System.out.println("Дело успешно добавлено!");
    }


    private static void printTasks() {
        if (tasks.isEmpty()) {
            System.out.println("Список дел пуст.");
            return;
        }

        Iterator<String> iterator = tasks.iterator();
        int index = 1;
        while (iterator.hasNext()) {
            String task = iterator.next();
            System.out.println(index + ". " + task);
            index++;
        }
    }


    private static void removeByIndex() {
        if (tasks.isEmpty()) {
            System.out.println("Список пуст, нечего удалять.");
            return;
        }

        int index = getValidInt("Введите номер дела для удаления: ");


        int realIndex = index - 1;

        if (realIndex >= 0 && realIndex < tasks.size()) {
            String removed = tasks.remove(realIndex);
            System.out.println("Удалено дело: " + removed);
        } else {
            System.out.println("Дела с номером " + index + " не существует.");
        }
    }

    
    private static void removeByText() {
        System.out.print("Введите точный текст дела для удаления: ");
        sc.nextLine();
        String target = sc.nextLine();

        boolean removed = false;
        Iterator<String> iterator = tasks.iterator();

        while (iterator.hasNext()) {
            String current = iterator.next();
            if (current.equalsIgnoreCase(target)) {
                iterator.remove(); // Безопасное удаление через итератор
                removed = true;
                break; // Так как дела уникальны, дальше искать не нужно
            }
        }

        if (!removed) {
            System.out.println("Дело с таким текстом не найдено.");
        } else {
            System.out.println("Дело удалено.");
        }
    }

    // Вспомогательный метод для безопасного ввода целого числа
    private static int getValidInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            if (sc.hasNextInt()) {
                return sc.nextInt();
            } else {
                System.out.println("Пожалуйста, введите корректное целое число.");
                sc.next(); // Очистка некорректного ввода
            }
        }
    }
}

