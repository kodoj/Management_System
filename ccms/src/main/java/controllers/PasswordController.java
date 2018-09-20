package controllers;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

public class PasswordController {
    private final int iterations = 1000;
    private final int saltLen = 8;
    private final int desiredKeyLen = 256;

    public static void main(String[] args) {
        PasswordController controller = new PasswordController();
        try {
            System.out.println(controller.getSaltedHash("admin"));
        } catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    /** Computes a salted PBKDF2 hash of given plaintext password
     suitable for storing in a database.
     Empty passwords are not supported. */
    public String getSaltedHash(String password) throws IllegalArgumentException, NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] salt = SecureRandom.getInstance("SHA1PRNG").generateSeed(saltLen);
        // stores salt with the password
        return Base64.encodeBase64String(salt) + "$" + hash(password, salt);
    }

    /** Checks whether given plaintext password corresponds
     to a stored salted hash of the password. */
    public boolean check(String password, String hashedPassword) throws IllegalArgumentException, NoSuchAlgorithmException, InvalidKeySpecException{
        String[] saltAndHash = hashedPassword.split("\\$");
        if (saltAndHash.length != 2) {
            throw new IllegalStateException(
                    "The stored password must have the form 'salt$hash'");
        }
        String hashOfInput = hash(password, Base64.decodeBase64(saltAndHash[0]));
        return hashOfInput.equals(saltAndHash[1]);
    }

    private String hash(String password, byte[] salt) throws IllegalArgumentException, NoSuchAlgorithmException, InvalidKeySpecException {
        if (password == null || password.length() == 0)
            throw new IllegalArgumentException("Empty passwords are not supported.");
        SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        SecretKey key = f.generateSecret(new PBEKeySpec( password.toCharArray(), salt, iterations, desiredKeyLen));
        return Base64.encodeBase64String(key.getEncoded());
    }
}
