import java.util.*;


public class ProductManager {
    List<Product> products;
    List<String> brands; // Sử dụng List thay vì Map :))
    List<String> categories; // cái này cũng z :)))
    
    public ProductManager(List<Product> products, List<String> brands, List<String> categories) {
        this.products = products;
        this.brands = brands;
        this.categories = categories;
    }

    public void addProduct(Scanner sc) {
        System.out.print("Enter product name: ");
        String name = sc.nextLine();
        if (name.isEmpty()) {
            System.out.println("Product name cannot be empty.");
            return;
        }

        System.out.print("Enter brand ID: ");
        String brandId = sc.nextLine();
        if (!brands.contains(brandId)) {
            System.out.println("Invalid brand ID.");
            return;
        }

        System.out.print("Enter category ID: ");
        String categoryId = sc.nextLine();
        if (!categories.contains(categoryId)) {
            System.out.println("Invalid category ID.");
            return;
        }

        System.out.print("Enter model year: ");
        int modelYear = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter list price: ");
        double listPrice = sc.nextDouble();
        sc.nextLine();
        if (listPrice <= 0) {
            System.out.println("List price must be positive.");
            return;
        }

        String id = UUID.randomUUID().toString();
        products.add(new Product(id, name, brandId, categoryId, modelYear, listPrice));
        System.out.println("Product added successfully.");
    }

    public void searchProductByName(Scanner sc) {
        System.out.print("Enter product name to search: ");
        String searchStr = sc.nextLine();
        List<Product> foundProducts = new ArrayList<>();
        for (Product product : products) {
            if (product.name.toLowerCase().contains(searchStr.toLowerCase())) {
                foundProducts.add(product);
            }
        }

        if (foundProducts.isEmpty()) {
            System.out.println("No products found.");
        } else {
            foundProducts.sort(Comparator.comparingInt(p -> p.modelYear));
            foundProducts.forEach(System.out::println);
        }
    }

    public void updateProduct(Scanner sc) {
        System.out.print("Enter product ID to update: ");
        String id = sc.nextLine();
        Product productToUpdate = null;

        for (Product product : products) {
            if (product.id.equals(id)) {
                productToUpdate = product;
                break;
            }
        }

        if (productToUpdate == null) {
            System.out.println("Product not found.");
            return;
        }

        System.out.print("Enter new name (leave blank to keep current): ");
        String name = sc.nextLine();
        if (!name.isEmpty()) {
            productToUpdate.name = name;
        }

        System.out.print("Enter new brand ID (leave blank to keep current): ");
        String brandId = sc.nextLine();
        if (!brandId.isEmpty() && brands.contains(brandId)) {
            productToUpdate.brandId = brandId;
        }

        System.out.print("Enter new category ID (leave blank to keep current): ");
        String categoryId = sc.nextLine();
        if (!categoryId.isEmpty() && categories.contains(categoryId)) {
            productToUpdate.categoryId = categoryId;
        }

        System.out.print("Enter new model year (leave blank to keep current): ");
        String modelYearStr = sc.nextLine();
        if (!modelYearStr.isEmpty()) {
            productToUpdate.modelYear = Integer.parseInt(modelYearStr);
        }

        System.out.print("Enter new list price (leave blank to keep current): ");
        String listPriceStr = sc.nextLine();
        if (!listPriceStr.isEmpty()) {
            productToUpdate.listPrice = Double.parseDouble(listPriceStr);
        }

        System.out.println("Product updated successfully.");
    }

    public void deleteProduct(Scanner sc) {
        System.out.print("Enter product ID to delete: ");
        String id = sc.nextLine();
        boolean removed = products.removeIf(p -> p.id.equals(id));
        if (removed) {
            System.out.println("Product deleted successfully.");
        } else {
            System.out.println("Product not found.");
        }
    }

    public void printProducts() {
        products.sort((p1, p2) -> {
            int priceComparison = Double.compare(p2.listPrice, p1.listPrice);
            if (priceComparison != 0) {
                return priceComparison;
            }
            return p1.name.compareTo(p2.name);
        });

        for (Product product : products) {
            String brandName = brands.get(product.brandId);
            String categoryName = categories.get(product.categoryId);
            
            if (brandName == null || categoryName == null) {
                System.out.println("Error: Invalid brand or category ID.");
                continue;
            }

            System.out.println(product.id + ", " + product.name + ", " + brandName + ", " + categoryName + ", " + product.modelYear + ", " + product.listPrice);
        }
    }
}