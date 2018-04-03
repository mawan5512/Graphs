/**
 * Created by mohammedawan on 4/3/17.
 */
  package cityInfo;
public class NodeList {

    protected Node head;
    protected Node tail;
    public int size;


    public NodeList(){
        head = null;
        tail = null;
        size = -1;
    }

    //Use this to add neighbors to the nodelist for each city

    public void add(String name, int distance, Node next){
        Node node = new Node(name, distance, null);

        if (head == null){
            head = node;
            tail = node;
        }
        else{
            tail.setNext(node);
            tail = node;
        }
        size++;

    }

    //Displays the city and it's neighbors

    public void display(){
        Node nptr;
        nptr = head;
        int x = size;
        System.out.println("The city " + nptr.getName() + " ");
        System.out.print("Has the neighbors ");
        while (x != 0) {
            nptr = nptr.getNext();
            System.out.print(nptr.getName() + " and ");
            x--;
        }
        System.out.println("that's it.");

    }

    //This is used to check if a node has a certain amount of neighbors

    public boolean neigbors(int input){
        Node nptr = head;
        if(size == input){
            System.out.println(nptr.getName());
            return true;
        }
        else{
            return false;
        }
    }

    //return the city name

    public String cityName(){
        return head.getName();
    }

    //Get the distance from the current node and it's neighbor

    public int getDistance(String name){
        Node nptr = head;
        while(nptr != null) {
            if (name.equals(nptr.getName())) {
                return nptr.getDistance();
            } else {
                nptr = nptr.getNext();
            }
        }
        return 0;
    }

    //This gets the name of a neighbor

    public String getNeighbors(int i){
        Node nptr = head.getNext();
        int x = 0;
        String name = "";
        while (x != i){
            name = nptr.getName();
            x++;
            nptr = nptr.getNext();
        }
        return name;
    }




}
