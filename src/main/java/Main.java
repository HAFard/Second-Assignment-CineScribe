import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        runMenu();
    }

    public static void runMenu() {

        Scanner input = new Scanner(System.in);

        while (true)
        {
            System.out.println("Press 1 To Find A Movie");
            System.out.println("Press 2 To Find An Actor");
            System.out.println("Press 0 To Close The Program");

            int Number = input.nextInt();

            if (Number == 1) {
                System.out.println("Enter The Movie Name");
                String MovieName = input.nextLine();
            }

            else if (Number == 2)
            {
                System.out.println("Enter The Actor Name");
                String ActorName = input.nextLine();
           }
            else if (Number == 0)
            {
                System.exit(0);
                break;
            }
        }
    }
}