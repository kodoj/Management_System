package maskingThread;

import java.io.IOException;

public class CatchPassword {

    public String catchPasswordToHide() {
        String password = null;

        while(password == null) {
            password = PasswordField.readPassword("Enter your password: ");

            if(password == null ) {
                System.out.println("No password entered");
            }
        }
        return password;
    }
}