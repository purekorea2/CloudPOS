import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class PointOfSaleSystem {
    private Map<String, Product> products;
    private List<CartItem> cart;
    private double total;

    public PointOfSaleSystem() {
        products = new HashMap<>();
        cart = new ArrayList<>();
        total = 0.0;
    }

    public void addProduct(Product product) {
        products.put(product.getProductId(), product);
    }

    public void displayProducts() {
        System.out.println("---- Products ----");
        for (Product product : products.values()) {
            System.out.println(product.getProductId() + ". " + product.getName() + " - $" + product.getPrice());
        }
        System.out.println("------------------");
    }

    public void addToCart(String productId, int quantity) {
        Product product = products.get(productId);
        if (product == null) {
            System.out.println("Product not found.");
            return;
        }

        CartItem cartItem = new CartItem(product, quantity);
        cart.add(cartItem);
        total += cartItem.getTotalPrice();
        System.out.println(quantity + "x " + product.getName() + " added to cart.");
    }

    public void checkout() {
        System.out.println("---- Checkout ----");
        for (CartItem cartItem : cart) {
            System.out.println(cartItem.getProduct().getName() + " - $" + cartItem.getProduct().getPrice()
                    + " x " + cartItem.getQuantity() + " = $" + cartItem.getTotalPrice());
        }
        System.out.println("Total: $" + total);
        System.out.println("------------------");
    }

    public static void main(String[] args) {
        PointOfSaleSystem pos = new PointOfSaleSystem();

        // Adding products to the POS system
        pos.addProduct(new Product("P1", "Product 1", 10.99));
        pos.addProduct(new Product("P2", "Product 2", 5.49));
        pos.addProduct(new Product("P3", "Product 3", 7.79));

        Scanner scanner = new Scanner(System.in);

        // Main loop
        while (true) {
            System.out.println("Enter 1 to view products, 2 to add to cart, 3 to checkout, or 0 to exit:");
            int choice = scanner.nextInt();

            if (choice == 0) {
                System.out.println("Thank you for using the POS system. Goodbye!");
                break;
            } else if (choice == 1) {
                pos.displayProducts();
            } else if (choice == 2) {
                System.out.println("Enter the product ID and quantity (e.g., P1 2):");
                String productId = scanner.next();
                int quantity = scanner.nextInt();
                pos.addToCart(productId, quantity);
            } else if (choice == 3) {
                pos.checkout();
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}
