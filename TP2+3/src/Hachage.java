import java.io.FileNotFoundException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Hachage {


    public static byte[] toSHA1(byte[] convertme) {
        final char[] HEX_CHARS = "0123456789ABCDEF".toCharArray();
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-1");
        }
        catch(NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] buf = md.digest(convertme);
        /*char[] chars = new char[2 * buf.length];
        for (int i = 0; i < buf.length; ++i) {
            chars[2 * i] = HEX_CHARS[(buf[i] & 0xF0) >>> 4];
            chars[2 * i + 1] = HEX_CHARS[buf[i] & 0x0F];
        }*/
        return buf;
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


        String word = "poisson";

        System.out.println(nombreEssais(dico, word, 7));

    }

    private static int nombreEssais(ArrayList<String> dico,  String word, int nbBit) {
        int indexFiveBit = 1030;

        int essais = 0;
        byte[] wordInByte = toSHA1(word.getBytes());
        int fiveBitWord = selectNbBit(wordInByte, nbBit);

        while (fiveBitWord!= indexFiveBit) {
            String wordIndex = dico.get(essais);
            byte[] wordIndexByte = toSHA1(wordIndex.getBytes());
            indexFiveBit = (selectNbBit(wordIndexByte, nbBit));
            essais +=1;
        }
        return essais;
    }

    private static int selectNbBit(byte[] b, int i) {
        if (i <= 8){
            return b[0] % (1 << i);
        }else {
            int huitpremierBit=b[0] % (1 << i);
            int neuviemeBit=b[1] % (1 << i);

        }
    }
}
