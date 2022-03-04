import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Exo2 {

    private static char[] convert(byte[] buf1) {
        final char[] HEX_CHARS = "0123456789ABCDEF".toCharArray();
        char[] chars = new char[2 * buf1.length];
        for (int i = 0; i < buf1.length; ++i) {
            chars[2 * i] = HEX_CHARS[(buf1[i] & 0xF0) >>> 4];
            chars[2 * i + 1] = HEX_CHARS[buf1[i] & 0x0F];
        }
        return chars;
    }

    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {

        String word = "Exercise2";

        byte[] wordByte = word.getBytes("UTF-8");

        // Hash avec HmacSHA1
        SecretKeySpec spec1 = new SecretKeySpec(wordByte, "HmacSHA1");
        Mac mac1 = Mac.getInstance("HmacSHA1");
        mac1.init(spec1);
        byte[] buf1 = mac1.doFinal("Ceci est mon premier HMAC SHA1".getBytes("UTF-8"));

        // Hash avec HmacSHA512
        SecretKeySpec spec512 = new SecretKeySpec(wordByte, "HmacSHA512");
        Mac mac512 = Mac.getInstance("HmacSHA512");
        mac512.init(spec512);
        byte[] buf512 = mac512.doFinal("Ceci est mon premier HMAC SHA1".getBytes("UTF-8"));

        // Convert in hexadecimal
        char[] chars1 = convert(buf1);
        char[] chars512 = convert(buf512);


        System.out.println("Premier message a haché (avec HmacSHA1) : " + new String(chars1));
        System.out.println("Deuxième message a haché (avec HmacSHA512) : " + new String(chars512));

    }

}
