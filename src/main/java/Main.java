import java.util.Scanner;

public class Main {
    /*
    Программа запрашивает у пользователя данные для калькулятора счета, вызывает методы и выводит итоговую информацию.
     */
    public static void main(String[] args) {
        byte amount = getValidAmount();

        double total= addProducts();
        String textRub = getTextRub(total, amount);

        System.out.format("Сумма которую должен заплатить каждый человек - %.2f %s",(total / amount), textRub);

    }
    /*
    Возвращает строку, описываюющую правильное окончание в слове "рубль" в зависимости от целочисленной части.
     */
    public static String getTextRub(double total, int amount) {
        String textRub;
        int rubles = (int) total / amount;
        if ( rubles % 10 == 1) {
            textRub = "рубль";
        } else if ( rubles % 10 >= 2 && rubles % 10 <=4) {
            textRub = "рубля";
        } else {
            textRub = "рублей";
        }
        return textRub;
    }
    /*
    Запрашивает количество людей для разделения счета и потом возвращает значение.
     */
    public static byte getValidAmount() {
        byte amount;

        while (true) {
            Scanner s = new Scanner(System.in);
            System.out.println("Пожалуйста, введите количество людей для разделения счета:");

            if (s.hasNextInt()) {
                amount = (byte) s.nextInt();
                if (amount > 1 && amount < 127) {
                    break;
                } else {
                    System.out.println(
                            "Некорректное значение, введите пожалуйста целое число > 1 и < 127"
                    );
                }
            } else {
                System.out.println(
                        "Недопустимое значение, введите пожалуйста число > 1 и < 127"
                );
                s.next();
            }
        }
        return amount;
    }

    /*
    Запрашивает у пользователя название товара и его стоимость, добавляет товар в общий расчет,
    выводит список добавленных товаров и возвращает общую стоимость
     */
    public static double addProducts() {

        String select = "";
        StringBuilder products = new StringBuilder();
        double price;
        double total = 0;
        String productName;

        while (!select.equalsIgnoreCase("Завершить")) {
            Scanner s = new Scanner(System.in);
            System.out.println("Введите название товара");
            productName = s.nextLine();

            System.out.println("Введите стоимость товара в формате рубли,копейки, например: 10,30");
            while (true) {
                if (s.hasNextDouble()) {
                    price = s.nextDouble();
                    if (price > 0) {
                        break;
                    } else {
                        System.out.println(
                                "Некорректное значение, введите пожалуйста цену больше 0"
                        );
                    }
                } else {
                    System.out.println(
                            "Недопустимое значение, введите пожалуйста число, например: 10,30"
                    );
                    s.next();
                }
            }
            Calculate product = new Calculate(productName, price);
            products.append(productName).append("\n");
            total += product.getPrice();
            System.out.println("Товар успешно добавлен.\nХотите добавить ещё один товар?");
            select = s.next();
        }

        System.out.println("Добавленные товары:\n" + products);
        return total;
    }
    /*
    Предоставляет информацию о товаре.
     */
    public static class Calculate {
        double price;
        String productName;
        /*
        Конструктор класса.
         */
        public Calculate(String productName, double price) {
            this.productName = productName;
            this.price = price;
        }

        /*
        Возвращает стоимость товара.
         */
        public double getPrice() {
            return price;
        }
    }
}