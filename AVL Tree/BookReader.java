import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BookReader {
    //book
    public String book="";
    //words
    public MyLinkedList<String> words = new MyLinkedList<>();

    //BookReader
    public BookReader(String filename)  {

        readBook(filename);
        parseWords();
    }
    public void readBook(String filename) {
        System.out.print("Reading input file \"./" + filename + "\"... ");
        long time1 = System.nanoTime();
        try {
            book = Files.readString(Paths.get(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        long time2 = System.nanoTime();

        System.out.println(book.length() + " characters in " + ((time2 - time1) / 1000000) + " milliseconds.");
    }

    public MyLinkedList<String> getWords() {
        return words;
    }


    public void parseWords() {
        System.out.print("\nFinding words and adding them to a linked list... in ");
        char[] CH = {
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
                'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
                'X', 'Y', 'Z', '\'',
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
                'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
                'x', 'y', 'z',
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'

        };
        char[] C = book.toCharArray();
        String str = "";
        int z = 0;
        long time1 = System.nanoTime();
        for (char c : C) {
            boolean found = false;
            for (char character : CH) {
                if (c == character) {
                    found = true;
                    break;
                }
            }
            if (found) {
                str += String.valueOf(c);
                found = false;
            } else if (!str.isBlank()) {
                if (z == 0) {
                    words.addBefore(str);
                    words.first();
                    z++;
                } else {
                    words.addAfter(str);
                    words.next();
                }

                str = "";
            }
        }
        long time2 = System.nanoTime();
        System.out.println(((time2 - time1) / 1000000) + " milliseconds.");
        System.out.println("The linked list has a length of " + words.size() + ".");
    }


}

































