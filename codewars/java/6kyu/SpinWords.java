public class SpinWords {

    public String spinWords(String sentence) {

        String[] words = sentence.split(" ");

        for (int i = 0; i < words.length; i++) {
            if (words[i].length() >= 5) {

                StringBuilder nWord = new StringBuilder(words[i]);
                nWord.reverse();
                words[i] = nWord.toString();
            }
        }

        String out = "";
        for (int i = 0; i < words.length; i++) {
            out += words[i];
            out += " ";
        }
        out = out.substring(0, out.length() - 1);

        return out;
    }
}