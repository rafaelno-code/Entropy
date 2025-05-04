public class Node implements Comparable<Node>{
    
    private Node left;
    private Node right;
    private int frequency;

    //Constructor
    public Node(int frequency) {
        this.left = null;
        this.right = null;
        this.frequency = frequency;
    }

    //post: returns the node's left child
    public Node getLeft() {
        return this.left;
    }

    //post: returns the node's right cihld
    public Node getRight() {
        return this.right;
    }

    //pre: a non-empty array of length 2 with with two Node objects
    /*post: returns a Node that has been updated 
    to have the two nodes as children and a frequency that is the sum
    of the frequencies from the values of the children*/
    public Node add(Node[] vals) {
        if(vals[0].compareTo(vals[1]) == -1 || vals[0].compareTo(vals[1]) == 0) {
            this.left = vals[0]; this.right = vals[1];
        } else {
            this.right = vals[0]; this.left = vals[1];
        }
        this.frequency = vals[0].frequency + vals[1].frequency;
        return this;
    }
    //pre: a Node object
    /*post: returns a positive integer the Node's frequency is greater
    than the other Node's frequency, 0 if they are the same, and
    a negative integer if it is less than.*/
    @Override
    public int compareTo(Node other) {
        if(other == null) return 1;
        return Integer.compare(this.frequency, other.frequency);
    }

    //post: returns the frequency of the Node in the form of a string
    @Override
    public String toString() {
        return String.valueOf(this.frequency);
    }

    
}
