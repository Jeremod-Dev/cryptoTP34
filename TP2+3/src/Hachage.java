import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Hachage {


    public static String toSHA1(byte[] convertme) {
        final char[] HEX_CHARS = "0123456789ABCDEF".toCharArray();
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-1");
        }
        catch(NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] buf = md.digest(convertme);
        char[] chars = new char[2 * buf.length];
        for (int i = 0; i < buf.length; ++i) {
            chars[2 * i] = HEX_CHARS[(buf[i] & 0xF0) >>> 4];
            chars[2 * i + 1] = HEX_CHARS[buf[i] & 0x0F];
        }
        return new String(chars);
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, FileNotFoundException {
        String message = "Salut";

        ArrayList<String> dico = new ArrayList<String>();
        java.util.Scanner lecteur ;
        java.io.File fichier = new java.io.File("src\\dico.txt");
        lecteur = new java.util.Scanner(fichier);
        while (lecteur.hasNextLine()) {
            dico.add(lecteur.nextLine());
        }
        System.out.println(dico);




        System.out.println(toSHA1(message.getBytes()));
    }
}
