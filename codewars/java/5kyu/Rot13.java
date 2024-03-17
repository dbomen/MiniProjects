// changed name beacuse it was "Solution"
public class Rot13 {

    public static String rot13(String message) {

        String out = "";

        for (int i = 0; i < message.length(); i++) {
            char c = message.charAt(i);
            if (isLetter(c)) out += rot13(c);
            else             out += c;
        }

        return out;
    }

    public static boolean isLetter(char c) {
        return ((c >= 65 && c <= 90) || (c >= 97 && c <= 122));
    }

    public static char rot13(char c) {
        boolean isBig = (c >= 65 && c <= 90) ? true : false;
        int rotated = c + 13;

        if (isBig && rotated > 90)   return (char) (rotated - 26);
        if (!isBig && rotated > 122) return (char) (rotated - 26);
        return (char) rotated;
    }
}