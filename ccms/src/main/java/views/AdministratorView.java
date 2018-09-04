package views;

import DAO.Model;

import java.util.List;

public class AdministratorView extends View {

    @Override
    public void printMenu() {
        System.out.println("1. List all students");
        System.out.println("2. List all mentors");
        System.out.println("3. Add mentor");
        System.out.println("4. Remove mentor");
        System.out.println("5. Edit mentor");
        System.out.println("6. Logout");
    }


    public void printAllModels(List<Model> models) {
        for(int i = 0; i < models.size(); i++) {
            System.out.println(models.get(i).toString());
        }
    }
}
