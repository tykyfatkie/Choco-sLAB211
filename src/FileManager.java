import java.io.*;
import java.util.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    static final String PRODUCT_FILE = "Product.txt";
    static final String BRAND_FILE = "Brand.txt";
    static final String CATEGORY_FILE = "Category.txt";

    public static List<Product> loadProducts() {
        List<Product> products = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(PRODUCT_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                products.add(new Product(data[0], data[1], data[2], data[3], Integer.parseInt(data[4]), Double.parseDouble(data[5])));
            }
        } catch (IOException e) {
            System.out.println("Error loading products.");
        }
        return products;
    }

    public static List<String> loadBrands() {
        return loadListFromFile(BRAND_FILE);
    }

    public static List<String> loadCategories() {
        return loadListFromFile(CATEGORY_FILE);
    }

    private static List<String> loadListFromFile(String fileName) {
        List<String> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                list.add(line.trim());
            }
        } catch (IOException e) {
            System.out.println("Error loading file: " + fileName);
        }
        return list;
    }

    public static void saveProducts(List<Product> products) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(PRODUCT_FILE))) {
            for (Product product : products) {
                bw.write(product.toString());
                bw.newLine();
            }
            System.out.println("Products saved to file successfully.");
        } catch (IOException e) {
            System.out.println("Error saving products.");
        }
    }
}
