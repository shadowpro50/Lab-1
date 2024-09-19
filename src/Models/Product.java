package Models;

import java.io.Serializable;

public class Product implements Comparable, Serializable {

    private String id = "", name = "", brandId = "", categoryId = "";
    private int modelYear = 0, price = 0;

    public Product() {
    }

    public Product(String id) {
        this.id = id;
    }

    public Product(String id, String name, String brandId, String categoryId, int modelYear, int price) {
        this.id = id;
        this.name = name;
        this.brandId = brandId;
        this.categoryId = categoryId;
        this.modelYear = modelYear;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public int getModelYear() {
        return modelYear;
    }

    public void setModelYear(int modelYear) {
        this.modelYear = modelYear;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name=" + name + ", brandId=" + brandId + ", categoryId=" + categoryId + ", modelYear=" + modelYear + ", price=" + price + '}';
    }

    @Override
    public boolean equals(Object obj) {
        Product product = (Product) obj;
        if (this.id.equalsIgnoreCase(product.getId())) {
            return true;
        }
        return false;
    }

    @Override
    public int compareTo(Object o) {
        Product p = (Product) o;
        return this.getName().compareTo(p.getName());
    }
}
