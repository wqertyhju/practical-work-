import java.util.Scanner;

public class Main {

    static final int    MAX_QUANTITY   = 10_000;
    static final double MAX_PRICE      = 5_000_000.0;
    static final double MAX_DISCOUNT   = 30.0;
    static final double VAT_RATE       = 0.25;   // вариант 4
    static final double FREE_THRESHOLD = 1_000_000.0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите количество: ");
        int quantity = sc.nextInt();
        System.out.print("Введите цену за единицу: ");
        double price = sc.nextDouble();
        System.out.print("Введите скидку (%): ");
        double discount = sc.nextDouble();

        String error = validate(quantity, price, discount);
        if (error != null) {
            System.out.println("Ошибка: " + error);
            return;
        }

        double base = quantity * price;
        double total = calculateTotal(base, discount);
        System.out.printf("Итоговая сумма: %.2f руб.%n", total);
    }

    static String validate(int quantity, double price, double discount) {
        if (quantity <= 0 || quantity > MAX_QUANTITY)
            return "количество должно быть от 1 до " + MAX_QUANTITY;
        if (price <= 0 || price > MAX_PRICE)
            return "цена должна быть от 0 до " + MAX_PRICE;
        if (discount < 0 || discount > MAX_DISCOUNT)
            return "скидка должна быть от 0 до " + MAX_DISCOUNT + "%";
        return null;
    }

    static double calculateTotal(double base, double discount) {
        double afterDiscount = base * (1 - discount / 100.0);
        double vat = afterDiscount * VAT_RATE;
        return afterDiscount + vat;
    }
}