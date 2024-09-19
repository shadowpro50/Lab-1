package View;

import DTO.I_List;
import DTO.I_Menu;
import Controllers.Menu;
import Controllers.ProductList;
import Utils.Utils;

public class ProductManagement {

    public static void main(String args[]) {
        I_Menu menu = new Menu();
        menu.addItem("1. Add new a product");
        menu.addItem("2. Search a product by name");
        menu.addItem("3. Update a product");
        menu.addItem("4. Delete a product");
        menu.addItem("5. Save a product to file");
        menu.addItem("6. Print list products from the file");
        menu.addItem("0. Quit");
        int choice;
        boolean cont = false;
        boolean redo = false;
        I_List list = new ProductList();
        while (!cont) {

            do {
                menu.showMenu();
                choice = menu.getChoice();
                switch (choice) {
                    case 1:
                        while (!redo) {
                            list.add();
                            redo = Utils.askToGoBack();
                        }
                        redo = false;
                        break;
                    case 2:
                        while (!redo) {
                            list.search();
                            redo = Utils.askToGoBack();
                        }
                        redo = false;
                        break;

                    case 3:
                        while (!redo) {
                            list.update();
                            redo = Utils.askToGoBack();
                        }
                        redo = false;
                        break;
                    case 4:
                        while (!redo) {

                            list.remove();
                            redo = Utils.askToGoBack();
                        }
                        redo = false;
                        break;
                    case 5:
                        while (!redo) {

                            list.writeFile();
                            redo = Utils.askToGoBack();
                        }
                        redo = false;
                        break;
                    case 6:
                        while (!redo) {

                            list.readFile();
                            list.output();
                            redo = Utils.askToGoBack();
                        }
                        redo = false;
                        break;
                    case 0:
                        cont = menu.confirmYesNo("Do you want to quit?(Y/N)");
                        break;
                }
            } while (choice > 0 && choice <= 7);
            if (choice == 0) {
                System.out.println("End Program");
            }
        }

    }
}
