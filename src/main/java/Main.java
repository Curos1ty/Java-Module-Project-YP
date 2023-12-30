import java.util.Scanner;

public class Main {
    /*
    Программа запрашивает у пользователя данные для калькулятора счета, вызывает методы и выводит итоговую информацию.
     */
    public static void main(String[] args) {
        int amount = getValidAmount();

        double total= addProducts();

        String textRub = getTextRub(total/amount);

        System.out.format("Сумма которую должен заплатить каждый человек - %.2f %s",(total / amount), textRub);

    }
    /*
    Возвращает строку, описывающую правильное окончание в слове "рубль" в зависимости от целочисленной части.
     */
    public static String getTextRub(double rubles) {
        if (rubles % 100 >= 11 && rubles % 100 <= 19) {
            return "рублей";
        } else if ( rubles % 10 == 1) {
            return "рубль";
        } else if ( rubles % 10 >= 2 && rubles % 10 <=4) {
            return "рубля";
        } else {
            return "рублей";
        }
    }
    /*
    Запрашивает количество людей для разделения счета и потом возвращает значение.
     */
    public static int getValidAmount() {
        int amount;
        Scanner s = new Scanner(System.in);
        while (true) {
            System.out.println("Пожалуйста, введите количество людей для разделения счета:");

            if (s.hasNextInt()) {
                amount = s.nextInt();
                if (amount > 1) {
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
            Product product = new Product(productName, price);
            products.append(product.getProductName()).append("\n");
            total += product.getPrice();
            System.out.println(
                    """
                            Товар успешно добавлен.
                            Хотите добавить ещё один товар? Чтобы продолжить, введите любой символ.
                            Введите 'завершить', если хотите получить результат подсчета."""
            );
            select = s.next();
        }

        System.out.println("Добавленные товары:\n" + products);
        return total;
    }
    /*
    Предоставляет информацию о товаре.
     */
    public static class Product {
        private final double price;
        private final String productName;
        /*
        Конструктор класса.
         */
        public Product(String productName, double price) {
            this.productName = productName;
            this.price = price;
        }

        /*
        Возвращает стоимость товара.
         */
        public double getPrice() {
            return price;
        }

        public String getProductName() {
            return productName;
        }
    }
}