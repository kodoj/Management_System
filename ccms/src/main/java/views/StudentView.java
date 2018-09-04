package views;

public class StudentView extends View {

    @Override
    public void printMenu() {
        System.out.println("1. Submit assignment");
        System.out.println("2. View grades");
        System.out.println("3. Take new assignment");
        System.out.println("4. Logout");
    }
}
