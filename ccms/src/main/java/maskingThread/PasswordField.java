package maskingThread;

import java.io.*;


public class PasswordField {


    public static String readPassword (String prompt) {
        MaskingThread et = new MaskingThread(prompt);
        Thread mask = new Thread(et);
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String password = "";

        try {
            mask.start();
            password = in.readLine();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        et.stopMasking();

        return password;
    }
}