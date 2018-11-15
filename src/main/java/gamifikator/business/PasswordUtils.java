package gamifikator.business;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;
import java.security.SecureRandom;

import java.util.Random;

public class PasswordUtils {

    public static final int DEFAULT_LENGTH = 20;

    public static final String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String lower = upper.toLowerCase(Locale.ROOT);
    public static final String digits = "0123456789";
    public static final String alphanum = upper + lower + digits;

    private final Random random;
    private final char[] symbols;
    private final char[] buf;



    public PasswordUtils(int length) {
        if (length < 1) throw new IllegalArgumentException();
        this.random = new SecureRandom();
        this.symbols = alphanum.toCharArray();
        this.buf = new char[length];
    }


    public PasswordUtils(int length, String symbols) {
        if (length < 1) throw new IllegalArgumentException();
        if (symbols.length() < 2) throw new IllegalArgumentException();
        this.random = new SecureRandom();
        this.symbols = symbols.toCharArray();
        this.buf = new char[length];
    }

	public static String hash_SHA256(String plaintext) throws NoSuchAlgorithmException {


        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashInBytes = md.digest(plaintext.getBytes(StandardCharsets.UTF_8));

        // bytes to hex
        StringBuilder sb = new StringBuilder();
        for (byte b : hashInBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();

    }

    /**
     * Generate a random string.
     */
    public String nextString() {
        for (int idx = 0; idx < buf.length; ++idx)
            buf[idx] = symbols[random.nextInt(symbols.length)];
        return new String(buf);
    }




}
