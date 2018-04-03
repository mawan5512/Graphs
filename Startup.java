/**
 * Created by mohammedawan on 4/3/17.
 */
  package cityInfo;
public class Startup {


    //Initialize some arrays and variables

    NodeList[] arr = new NodeList[12];
    String[] usedNames = new String[12];
    String[] flightLog = new String[12];
    int[] distances = new int[12];
    int currIndex = 0;
    String city2FlightPath = "";
    String city2Name = "";
    int totalDistance = 0;

    public void initialize() {

        //Create all of the nodelists for each city

        NodeList Seattle = new NodeList();
        NodeList Minneapolis = new NodeList();
        NodeList Denver = new NodeList();
        NodeList Chicago = new NodeList();
        NodeList Boston = new NodeList();
        NodeList NewYork = new NodeList();
        NodeList SanFrancisco = new NodeList();
        NodeList LasVegas = new NodeList();
        NodeList LosAngeles = new NodeList();
        NodeList Dallas = new NodeList();
        NodeList DC = new NodeList();
        NodeList Miami = new NodeList();

        //Set each city nodelist to a spot in an array

        arr[0] = Seattle;
        arr[1] = Minneapolis;
        arr[2] = Denver;
        arr[3] = Chicago;
        arr[4] = Boston;
        arr[5] = NewYork;
        arr[6] = SanFrancisco;
        arr[7] = LasVegas;
        arr[8] = LosAngeles;
        arr[9] = Dallas;
        arr[10] = DC;
        arr[11] = Miami;

        //Setting the neighbors and distances for each of the cities

        Seattle.add("Seattle", 0, null);
        Seattle.add("Minneapolis", 2661, null);
        Seattle.add("Denver", 2161, null);
        Seattle.add("SanFrancisco", 1306, null);

        Minneapolis.add("Minneapolis", 0, null);
        Minneapolis.add("Seattle", 2661, null);
        Minneapolis.add("Dallas", 1532, null);
        Minneapolis.add("Chicago", 661, null);

        Denver.add("Denver", 0, null);
        Denver.add("Seattle", 2161, null);
        Denver.add("LasVegas", 1225, null);
        Denver.add("Dallas", 1258, null);
        Denver.add("Chicago", 1483, null);

        Chicago.add("Chicago", 0, null);
        Chicago.add("Minneapolis", 661, null);
        Chicago.add("DC", 2661, null);
        Chicago.add("Boston", 1613, null);
        Chicago.add("Denver", 1483, null);

        Boston.add("Boston", 0, null);
        Boston.add("Chicago", 1613, null);
        Boston.add("NewYork", 338, null);
        Boston.add("DC", 725, null);

        NewYork.add("NewYork", 0, null);
        NewYork.add("Boston", 338, null);
        NewYork.add("DC", 383, null);
        NewYork.add("Miami", 2145, null);

        SanFrancisco.add("SanFrancisco", 0, null);
        SanFrancisco.add("Seattle", 1306, null);
        SanFrancisco.add("LasVegas", 919, null);
        SanFrancisco.add("LosAngeles", 629, null);

        LasVegas.add("LasVegas", 0, null);
        LasVegas.add("Dallas", 1983, null);
        LasVegas.add("Denver", 1258, null);
        LasVegas.add("SanFrancisco", 919, null);
        LasVegas.add("LosAngeles", 435, null);

        LosAngeles.add("LosAngeles", 0, null);
        LosAngeles.add("SanFrancisco", 629, null);
        LosAngeles.add("LasVegas", 435, null);

        Dallas.add("Dallas", 0, null);
        Dallas.add("LasVegas", 1983, null);
        Dallas.add("Denver", 1258, null);
        Dallas.add("Minneapolis", 1532, null);
        Dallas.add("DC", 2113, null);
        Dallas.add("Miami", 2161, null);

        DC.add("DC", 0, null);
        DC.add("Dallas", 2113, null);
        DC.add("Miami", 1709, null);
        DC.add("NewYork", 383, null);
        DC.add("Chicago", 2661, null);
        DC.add("Boston", 725, null);

        Miami.add("Miami", 0, null);
        Miami.add("Dallas", 2161, null);
        Miami.add("DC", 1709, null);
        Miami.add("NewYork", 2145, null);
    }

    //This function lists all the cities and then all of their neighbors

    public void listAll(){
        NodeList tmp;
        for(int i = 0; i <= 11; i++){
            tmp = arr[i];
            tmp.display();
        }
        System.out.println("");
        System.out.println("Back to the main menu:");
    }

    //This function calculates the cities with X amount of neighbors

    public void numNeighbors(int input){
        NodeList tmp;
        System.out.println("The amount of cities with " + input + " neighbors are ... ");
        for(int i = 0; i <= 11; i++){
            tmp = arr[i];
            tmp.neigbors(input);
        }
        System.out.println("that's it.");
        System.out.println("");
        System.out.println("Back to the main menu:");
    }

    //This is the function to find the shortest path from city1 to city2
    //I implemented a version of dijkstra's algorithm to find it
    //I used three arrays to accomplish this
    //usedNames has the names of all the cities that have been visited in the algorithm
    //distances keep track of the shortest distance to that city from the source
    //flightLog keeps track of the shortest flight path to that city from the source

    public void shortestPath(String cityName1, String cityName2){

        //These variables help keep track of the end city's name and the flight path
        city2FlightPath = cityName2;
        city2Name = cityName2;

        //Here I just initialize the arrays again
        usedNames = new String[12];
        distances = new int[12];
        //Here I set the first values of each array to the start city
        flightLog[0] = cityName1;
        usedNames[0] = cityName1;
        distances[0] = 0;
        totalDistance = 0;

        //I'm setting the distance to each other city to the max integer value
        for(int i = 1; i <= distances.length - 1; i++){
            distances[i] = Integer.MAX_VALUE;
        }
        //This runs the distance calculation for each city in the array
        for(int i = 0; i <= distances.length - 1; i++){
            currIndex = i;
            calculateShortest(usedNames[i]);
        }


        //This just prints out the the shortest flightpath to the end city and the total distance
        if(totalDistance == 0){
            System.out.println("You might've typed in the name wrong.");
            System.out.println("Try Again.");
        } else {
            System.out.println("The best route for your trip would be to travel from:");
            System.out.println(city2FlightPath + ".");
            System.out.println("The total distance for this trip would be");
            System.out.println(totalDistance + " miles.");
            System.out.println("");
        }


    }

    public void calculateShortest(String name) {

        //Some temporary indexes to help find cities
        int tmpIndex = 0;
        int tmpIndex2 = 0;

        //This finds the neighbor nodelist for the current city in the array
        NodeList current = new NodeList();
        for (int i = 0; i <= 11; i++) {
            if (arr[i].cityName().equalsIgnoreCase(name)) {
                current = arr[i];
            }
        }

        //For each of the current city's neighbors it calculates whether or not the path would be shorter
        //by passing through the current city or using the path the neighbor already has already has
        for(int j = 1; j <= current.size; j++){

            //This checks whether or not we have already visited the city and if it's in useNames in our algorithm
            String neighbor = current.getNeighbors(j);
            boolean cityThere = false;
            for (int i = 0; i <= 11; i++) {
                if (neighbor.equalsIgnoreCase(usedNames[i])) {
                    tmpIndex = i;
                    cityThere = true;
                }
            }
            //If the city hasn't been visited then it will be inserted into the usedNames at the end
            while(!cityThere && usedNames[tmpIndex2] != null){
                tmpIndex2++;
            }
            if(!cityThere && tmpIndex2 <= 11){
                usedNames[tmpIndex2] = neighbor;
                tmpIndex = tmpIndex2;
            }

            //This is the actual calculation where it checks whether the neighbor's current flightpath is
            //shorter or if it would become shorter by going through the current city
            if(distances[tmpIndex] > distances[currIndex] + current.getDistance(current.getNeighbors(j))){

                //This just puts the flightPath and distance to the city we want to travel to into variables so
                //that we can print it in the console
                if(usedNames[tmpIndex].equalsIgnoreCase(city2Name)) {
                city2FlightPath = flightLog[currIndex] + " to " + current.getNeighbors(j);
                totalDistance = distances[currIndex] + current.getDistance(current.getNeighbors(j));
                }

                //This updates the flight log and distances array if the new path is shorter
                flightLog[tmpIndex] = flightLog[currIndex] + " to " + current.getNeighbors(j);
                distances[tmpIndex] = distances[currIndex] + current.getDistance(current.getNeighbors(j));
            }
        }
    }

}
