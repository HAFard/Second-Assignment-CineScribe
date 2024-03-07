import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // TODO --> complete main function
        runMenu();
    }
    public static void runMenu() {
        // TODO
        System.out.println("Choose from the options below and enter the number:");
        System.out.println("1. A movie's information");
        System.out.println("2. An Actor's information");

        Scanner in = new Scanner(System.in);
        int choiceNumber = in.nextInt();

        while (true) {
            if (choiceNumber == 1) {
                System.out.println("please enter movie's title!");
                Scanner In = new Scanner(System.in);
                String movieTitle = In.nextLine();
                Movie movie1 = new Movie(movieTitle);
                String movieJ = "";
                try {
                    movieJ = movie1.getMovieData(movieTitle);
                } catch (IOException e) {
                    System.out.println("Movie not found! please enter another movie name!");
                    //throw new RuntimeException(e);
                    continue;
                }
                movie1.getDirectorViaApi(movieJ);
                movie1.getGenreViaApi(movieJ);
                movie1.getLanguageViaApi(movieJ);
                movie1.getImdbVotesViaApi(movieJ);
                movie1.getActorListViaApi(movieJ);
                //movie1.getRatingViaApi(movieJ);
                System.out.println(movie1.toString());

                break;
            }
            else if (choiceNumber == 2) {


                break;
            }
            else {
                System.out.println("Invalid number.please enter 1 or 2.");
                choiceNumber = in.nextInt();
            }

        }
    }
}