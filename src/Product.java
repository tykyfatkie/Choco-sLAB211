
public class Product {
    String id;
    String name;
    String brandId;
    String categoryId;
    int modelYear;
    double listPrice;

    public Product(String id, String name, String brandId, String categoryId, int modelYear, double listPrice) {
        this.id = id;
        this.name = name;
        this.brandId = brandId;
        this.categoryId = categoryId;
        this.modelYear = modelYear;
        this.listPrice = listPrice;
    }

    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", brandId=" + brandId + ", categoryId=" + categoryId + ", modelYear=" + modelYear + ", listPrice=" + listPrice + "]";
    }
}
