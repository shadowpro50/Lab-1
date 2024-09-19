package DTO;

import java.util.ArrayList;
import Models.Product;

public interface I_List {

    void add();

    void search();

    void update();

    void remove();

    void output();

    ArrayList<Product> readFile();

    void writeFile();
}
