package views;

public class MentorView extends View {


    @Override
    public int printMenu() {
        System.out.println("1. List all students");
        System.out.println("2. Add student");
        System.out.println("3. Remove student");
        System.out.println("4. Edit student");
        System.out.println("5. Get Assignments");
        System.out.println("6. Graduate Assignments");
        return takeInput("What would you like to do? ");
    }

}
