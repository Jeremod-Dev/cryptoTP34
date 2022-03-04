import java.io.FileNotFoundException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Hachage {


    public static char[] toSHA1STR(byte[] convertme) {
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
        return chars;
    }
    public static byte[] toSHA1(byte[] convertme) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-1");
        }
        catch(NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] buf = md.digest(convertme);;
        return buf;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, FileNotFoundException {
        // String message = "Salut";

        ArrayList<String> dico = new ArrayList<String>();
        java.util.Scanner lecteur ;
        java.io.File fichier = new java.io.File("TP2+3\\src\\dico.txt");
        lecteur = new java.util.Scanner(fichier);
        while (lecteur.hasNextLine()) {
            dico.add(lecteur.nextLine());
        }
        lecteur.close();


        String word = "poisson";
        System.out.println(toSHA1STR(word.getBytes()));
        System.out.println(nombreEssais(dico, word, 10));

    }

    private static int nombreEssais(ArrayList<String> dico,  String word, int nbBit) {
        int indexFiveBit;
        int essais = 0;
        boolean trouve = true;
        byte[] wordInByte = toSHA1(word.getBytes());
        int fiveBitWord = selectNbBit(wordInByte, nbBit);
        while (trouve) {
            String wordDico = dico.get(essais);
            byte[] wordDicoByte = toSHA1(wordDico.getBytes());
            indexFiveBit = (selectNbBit(wordDicoByte, nbBit));
            if (fiveBitWord!=indexFiveBit)
                essais +=1;
            else{
                System.out.println(wordDico);
                trouve = false;}
        }
        return essais;
    }
    private static int selectNbBit(byte[] wordByte, int i) {
        if (i <= 8)
            return wordByte[0] % (1 << i);
        else 
            return (wordByte[0]%(1 << i)) << (wordByte[1] % (1 << i-8));
    }
}
