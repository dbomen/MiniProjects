public class MyFirstInterpreter {
    String input;
    byte currentMemoryCell;

    public MyFirstInterpreter(String code) {
        this.input = code;
        this.currentMemoryCell = -128;
    }

    public String interpret() {
        String output = "";
        for (int i = 0; i < this.input.length(); i++) {
            if (this.input.charAt(i) == '+') {
                currentMemoryCell++;
            } else if (this.input.charAt(i) == '.') {
                output += (char) (currentMemoryCell + 128);
            }
        }
        return output;
    }
}