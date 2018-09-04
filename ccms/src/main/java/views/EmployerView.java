package views;

import DAO.Model;

import java.util.List;

public class EmployerView extends View {

    @Override
    public void printMenu() {
        System.out.println("1. List all students");
        System.out.println("2. Logout");
    }

    public void printStudents(List<Model> models) {
        for(int i = 0; i < models.size(); i++) {
            System.out.println(models.get(i).toString());
        }
    }
}
