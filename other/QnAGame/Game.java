import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.Gson;

public class Game {

    public static void main(String[] args) throws Exception {

        // naredimo getRequest
        HttpRequest getRequest = HttpRequest.newBuilder()
            .uri(new URI("https://opentdb.com/api.php?amount=10&category=22&difficulty=medium&type=boolean"))
            .GET()
            .build();
        
        // naredimo httpClient
        HttpClient httpClient = HttpClient.newHttpClient();

        // posljemo Clientu ta request | 2. parameter je da povemo, da pricakujemo nekaj nazaj (nek string)
        // to shranimo v getResponse
        HttpResponse<String> getResponse = httpClient.send(getRequest, BodyHandlers.ofString()); 

        // damo to v Response class
        Gson gson = new Gson();
        Response response = gson.fromJson(getResponse.body(), Response.class);

        // ---
        // game code
        Scanner sc = new Scanner(System.in);
        ArrayList<Question> questions = response.getQuestions();

        int numCorrectAnswers = 0;

        for (Question q : questions) { // gremo cez vprasanja

            clearScreen();

            System.out.println("ANSWER WITH t/f");
            System.out.printf("%s\n", q.getQuestion());

            String answer = sc.nextLine();

            switch (answer) {
                case "t":
                    answer = "True";
                    break;
                case "f":
                    answer = "False";
                    break;
                default:
                    break;
            }

            if (answer.equals(q.getCorrect_answer())) {

                numCorrectAnswers++;
                System.out.println("CORRECT!");
            }
            else  System.out.println("INCORRECT!");

            Thread.sleep(1000);
        }

        clearScreen();
        System.out.printf("YOUR SCORE: %d/10", numCorrectAnswers);

        sc.close();
    }

    public static void clearScreen() {

        // clearamo screen (for prettiness)
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}