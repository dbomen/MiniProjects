import java.util.ArrayList;

public class Response {
    
    private int response_code;
    private ArrayList<Question> results;

    public int getResponse_code() {
        return response_code;
    }

    public void setResponse_code(int response_code) {
        this.response_code = response_code;
    }

    public void setQuestions(ArrayList<Question> results) {
        this.results = results;
    }

    public ArrayList<Question> getQuestions() {
        return results;
    }

}
