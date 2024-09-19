package Controllers;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import DTO.I_List;
import Models.Product;
import Utils.Utils;
import java.util.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import Utils.MyComparators;

public class ProductList extends ArrayList<Product> implements I_List {

    Set<String> processedIds = new HashSet<>();

    @Override
    public void add() {
        String id, brandId, categoryId;
        boolean checkId = true, existBrand = false, existCategory = false;
        do {
            id = Utils.getString("Enter Product ID: ");
            int index = this.searchById(id);
            if (index == -1) {
                checkId = false;
            }
        } while (checkId);
        String name = Utils.getString("Enter Product Name: ");
        do {
            brandId = Utils.getString("Enter Product Brand id: ");
            existBrand = Utils.checkBrand(brandId);
        } while (!existBrand);
        do {
            categoryId = Utils.getString("Enter Category id: ");
            existCategory = Utils.checkCategory(categoryId);
        } while (!existCategory);
        int modelYear = Utils.getInt("Enter Model Year (2000-2024): ", 2000, 2024);
        int listPrice = Utils.getInt("Enter List Price (100-1000): ", 100, 1000);
        Product newProduct = new Product(id, name, brandId, categoryId, modelYear, listPrice);
//        this.add(new Product("SE1", "A", "Gojek", "Bike", 2010, 300));
//        this.add(new Product("SE2", "Khoa", "Grab", "Car", 2001, 120));
//        this.add(new Product("SE3", "Hoang", "Gojek", "Bike", 2005, 180));
//        this.add(new Product("SE4", "D", "Be", "Car", 2020, 300));
        this.add(newProduct);
    }

    @Override
    public void search() {
        ArrayList<Product> list = searchByName();
        Collections.sort(list, MyComparators.compYear);
        for (Product product : list) {
            System.out.println(product);
        }
    }

    @Override
    public void update() {
        addFromFile();
        boolean checkId = true, existBrand = false, existCategory = false;
        String id1, brandId1, categoryId1;

        String id = Utils.getString("Enter Product ID want to update: ");
        int index = searchById(id);
        if (index == -1) {
            System.out.println("Product does not exist");
        } else {
            Product p = this.get(index);
            do {
                System.out.println(p);
                id1 = Utils.getString("Enter New Product ID: ", p.getId());
                int index1 = this.searchById(id1);
                if (index1 == -1) {
                    checkId = false;
                }
            } while (checkId);
            String name1 = Utils.getString("Enter new Product Name: ", p.getName());
            do {
                brandId1 = Utils.getString("Enter new Product Brand id: ", p.getBrandId());
                existBrand = Utils.checkBrand(brandId1);
            } while (!existBrand);
            do {
                categoryId1 = Utils.getString("Enter new Category id: ", p.getCategoryId());
                existCategory = Utils.checkCategory(categoryId1);
            } while (!existCategory);
            int modelYear1 = Utils.getInt("Enter new Model Year (2000-2024): ", 2000, 2024, p.getModelYear());
            int listPrice1 = Utils.getInt("Enter new List Price (100-1000): ", 100, 1000, p.getPrice());
            Product updatedProduct = new Product(id1, name1, brandId1, categoryId1, modelYear1, listPrice1);
//                Product q = (new Product("SE5", "Oanh", "Ba", "Car", 2060, 300));
            Product s = this.set(index, updatedProduct);
        }
    }

    @Override
    public void remove() {
        addFromFile();
        String id = Utils.getString("Enter Product ID: ");
        int index = this.searchById(id);
        if (index == -1) {
            System.out.println("Product does not exist");;
        } else {
            Product p = this.get(index);
            System.out.println(p);
            boolean confirm = Utils.confirmYesNo("Do you want to delete this product? (Y/N): ");
            if (confirm) {
                this.remove(p);
                System.out.println("Delete: Success");
            } else {
                System.out.println("Delete: Fail");
            }
        }
    }

    @Override
    public void output() {
        addFromFile();
        Collections.sort(this, MyComparators.compPriceAndName);
        for (Product product : this) {
            System.out.println(product);
        }
    }

    @Override
    public ArrayList<Product> readFile() {
        ArrayList<Product> list = new ArrayList();
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream("Product.txt");
            ois = new ObjectInputStream(fis);
        } catch (IOException ex) {
            System.out.println("File not found");
        }
        try {
            Product product = null;
            while (fis.available() > 0) {
                product = (Product) ois.readObject();
                list.add(product);
                if (true) {

                }
            }
        } catch (Exception e) {
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException e) {
            }
        }
        return list;
    }

    @Override
    public void writeFile() {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream("Product.txt");
            oos = new ObjectOutputStream(fos);;
        } catch (IOException e) {
            System.out.println("File not found");
        }
        try {
            for (Product thi : this) {
                oos.writeObject(thi);
            }
            System.out.println("Saved!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
                if (fos != null) {
                    oos.close();
                }
            } catch (IOException e) {
            }
        }
    }

    public void addFromFile() {
        for (Product product : readFile()) {
            String productId = product.getId();
            if (!processedIds.contains(productId)) {
                this.add(product);
                processedIds.add(productId);
            }
        }
    }

    public int searchById(String id) {
        Product product = new Product();
        product.setId(id);
        addFromFile();
        int index = this.indexOf(product);
        return index;
    }

    public ArrayList<Product> searchByName() {
        ArrayList<Product> list = new ArrayList();
        String name = Utils.getString("Enter Product Name: ");
        addFromFile();
        for (Product product : this) {
            if (product.getName().toLowerCase().contains(name.toLowerCase())) {
                list.add(product);
            }
        }
        if (list.isEmpty()) {
            System.out.println("Not found!");
        }
        return list;
    }

}
