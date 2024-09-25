import java.util.*;

public class BikeStoreManagementSystem {
    public static void main(String[] args) {
        List<Product> products = FileManager.loadProducts();
        List<String> brands = FileManager.loadBrands();
        List<String> categories = FileManager.loadCategories();

        ProductManager productManager = new ProductManager(products, brands, categories);

        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Add a product");
            System.out.println("2. Search product by name");
            System.out.println("3. Update product");
            System.out.println("4. Delete product");
            System.out.println("5. Save products to file");
            System.out.println("6. Print products from file");
            System.out.println("7. Quit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    productManager.addProduct(sc);
                    break;
                case 2:
                    productManager.searchProductByName(sc);
                    break;
                case 3:
                    productManager.updateProduct(sc);
                    break;
                case 4:
                    productManager.deleteProduct(sc);
                    break;
                case 5:
                    FileManager.saveProducts(products);
                    break;
                case 6:
                    productManager.printProducts();
                    break;
                case 7:
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        } while (choice != 7);
    }
}
