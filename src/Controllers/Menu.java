package Controllers;

import java.util.ArrayList;
import DTO.I_Menu;
import Utils.Utils;

public class Menu extends ArrayList<String> implements I_Menu {

    public Menu() {
        super();
    }

    @Override
    public void addItem(String s) {
        this.add(s);

    }

    @Override
    public void showMenu() {
        for (String thi : this) {
            System.out.println(thi);
        }
    }

    @Override
    public boolean confirmYesNo(String welcome) {
        boolean result = false;
        String confirm = Utils.getString(welcome);
        if ("Y".equalsIgnoreCase(confirm)) {
            result = true;
        }
        return result;
    }

    @Override
    public int getChoice() {
        int choice = Utils.getInt("Input the choice: ", 0, 10);
        return choice;
    }

}
