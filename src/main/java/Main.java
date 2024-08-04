import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int earnings = 0;    // доходы
        int spendings = 0;   // расходы

        Scanner scanner = new Scanner(System.in);
        //Цикл будет работать, пока пользователь не введет `end`
        while (true) {
            // Выводим информацию о возможных операциях пользователю
            System.out.println("Выберите операцию и введите её номер:");
            System.out.println("1. Добавить новый доход");
            System.out.println("2. Добавить новый расход");
            System.out.println("3. Выбрать систему налогообложения");
            System.out.println("Или введите end для выхода из программы");
            String input = scanner.nextLine();
            if ("end".equals(input))
                break;
            int operation = Integer.parseInt(input);
            switch (operation) {
                case 1:
                    System.out.println("Введите сумму дохода:");
                    String money1Str = scanner.nextLine();
                    int money1 = Integer.parseInt(money1Str);
                    earnings += money1;
                    break;
                case 2:
                    System.out.println("Введите сумму расхода:");
                    String money2Str = scanner.nextLine();
                    int money2 = Integer.parseInt(money2Str);
                    spendings += money2;
                    break;
                case 3:
                    String tax1Name = "УСН доходы";
                    String tax2Name = "УСН доходы минус расходы";
                    String equalTaxes = "Можете выбрать любую систему налогообложения";
                    try {
                        int tax1 = taxEarnings(earnings);
                        int tax2 = taxEarningsMinusSpendings(earnings, spendings);
                        if (tax1 == tax2)
                            System.out.println(equalTaxes);
                        if (tax1 < tax2)
                            printResult(tax1Name, tax1, tax2);
                        else
                            printResult(tax2Name, tax2, tax1);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    System.out.println("Такой операции нет");
            }
        }
        scanner.close();
        System.out.println("Программа завершена!");
    }

    public static void printResult(String niceTaxName, int niceTaxValue, int badTaxValue) {
        System.out.println("Мы советуем вам " + niceTaxName);
        System.out.println("Ваш налог составит: " + niceTaxValue + " рублей");
        System.out.println("Налог на другой системе: " + badTaxValue + " рублей");
        System.out.println("Экономия: " + (badTaxValue - niceTaxValue) + " рублей");
    }

    public static int taxEarningsMinusSpendings(int earnings, int spendings) throws Exception {
        if ((earnings < 0) || (spendings < 0)) {
            throw new Exception("Невалидное значение параметра");
        }
        int tax = (earnings - spendings) * 15 / 100;
        if (tax >= 0) {
            return tax;
        } else {
            return 0;
        }
    }

    public static int taxEarnings(int earnings) throws Exception {
        if (earnings < 0) {
            throw new Exception("Невалидное значение параметра");
        }
        int tax = earnings * 6 / 100;
        if (tax >= 0) {
            return tax;
        } else {
            return 0;
        }
    }
}
