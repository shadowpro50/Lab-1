package Utils;

import java.util.Comparator;
import Models.Product;

public class MyComparators {

    public static Comparator compYear = new Comparator<Product>() {
        @Override
        public int compare(Product x, Product y) {
            if (x.getModelYear() > y.getModelYear()) {
                return 1;
            } else if (x.getModelYear() == y.getModelYear()) {
                return 0;
            } else {
                return -1;
            }

        }
    };

    public static Comparator compPriceAndName = new Comparator<Product>() {
        @Override
        public int compare(Product x, Product y) {

            if (x.getPrice() < y.getPrice()) {
                return 1;
            } else if (x.getPrice() == y.getPrice()) {
                x.compareTo(y);
                return 0;
            } else {
                return -1;
            }

        }
    };
}
