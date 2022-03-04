import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Exo2 {

    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {

        String word = "Exercise2";
        final char[] HEX_CHARS = "0123456789ABCDEF".toCharArray();

        byte[] wordByte = word.getBytes("UTF-8");

        SecretKeySpec spec = new SecretKeySpec(wordByte, "HmacSHA512");

        Mac mac = Mac.getInstance("HmacSHA512");
        mac.init(spec);
        byte[] buf = mac.doFinal("Ceci est mon premier HMAC SHA1".getBytes("UTF-8"));


        char[] chars = new char[2 * buf.length];
        for (int i = 0; i < buf.length; ++i) {
            chars[2 * i] = HEX_CHARS[(buf[i] & 0xF0) >>> 4];
            chars[2 * i + 1] = HEX_CHARS[buf[i] & 0x0F];
        }


        System.out.println(new String(chars));

    }

}
