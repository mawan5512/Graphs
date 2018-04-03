/**
 * Created by mohammedawan on 4/3/17.
 */
 package cityInfo;
public class Node {

    private String name;
    private int distance;

    private Node next;

    public Node(){
        this.name = "";
        this.distance = 0;
        this.next = null;
    }

    public Node(String name, int distance, Node next){
        this.name = name;
        this.distance = distance;
        this.next = next;
    }

    public String getName(){
        return name;
    }


    public int getDistance(){
        return distance;
    }

    public Node getNext(){
        return next;
    }

    public void setNext(Node next){
        this.next = next;
    }


}
