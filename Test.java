/**
 * Created by mohammedawan on 4/3/17.
 */
  package cityInfo;
import java.util.*;

public class Test {

    public static void main(String[] args) {
        //Creating an adjacency list with all of the cities
        Startup start = new Startup();
        start.initialize();

        System.out.println("Welcome to City Info App");
        Scanner scan = new Scanner(System.in);

        boolean exit = false;
        while (!exit){
            System.out.println("Type a number to do something or exit if you don't care.");
            System.out.println("1. List all cities and their neighbors.");
            System.out.println("2. Calculate the shortest path between 2 cities.");
            System.out.println("3. Find all the cities that are neighbors with a certain amount of neighbors.");
            System.out.println("4. Exit the program");
            String input = scan.next();

            switch (input) {
                case "1":
                    start.listAll();

                    break;
                case "2":
                    System.out.println("Type the name of the city that you want to start in (No Spaces):");
                    String city1 = scan.next();
                    System.out.println("Type the name of the city that you want to end in (No Spaces):");
                    String city2 = scan.next();
                    start.shortestPath(city1, city2);

                    break;
                case "3":
                    System.out.println("Type in a number to see all the cities with that many neighbors");

                    try {
                        start.numNeighbors(scan.nextInt());
                    }
                    catch(InputMismatchException exception){
                        System.out.println("That's not a number :(");
                    }
                    break;

                case "4":
                    System.out.println("Bye-Bye");
                    exit = true;
                    break;
                default:
                    System.out.println("Pick a number 1 through 4");
                    break;
            }

        }


    }


}
