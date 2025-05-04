import java.util.*;
import java.io.*;

class Entropy {
    //hashmap of characters and their respective bit pattern
    public static HashMap<Character, String> encoded; 
    //hashmap of characters and nodes containing the respective frequencies
    public static  HashMap<Character, Node> map; 
    //hashmap that is the opposite of 'map' (Key: Node, Value: Character)
    public static HashMap<Node, Character> oppMap;

    public static void main(String[] args) {
        String txt = readFile("text.txt");
        map = nodeCollection(frequencyCount(txt));
        encoded = new HashMap<Character, String>();
        ArrayList<Node> nodesList = new ArrayList<Node>(map.values());
        Node root = null;
        root = treeBuilder(root, nodesList);
        oppMap = new HashMap<Node, Character>();
        for(Map.Entry<Character, Node> pair: map.entrySet()) {
        	oppMap.put(pair.getValue(), pair.getKey());
        }
        encoder(root, "");
        System.out.println("Character Frequencies: ");
        for(Map.Entry<Character, Node> pair: map.entrySet()) {
        	if(pair.getKey() != null) {
        		System.out.println(pair.getKey() + ": " + pair.getValue());
        	}
        }
        System.out.println("Character Encoding:");
        for(Map.Entry<Character, String> pair: encoded.entrySet()) {
        	if(pair.getKey() != null) {
        		System.out.println(pair.getKey() + ": " + pair.getValue());
        	}
        }
        printBitCompressed(txt);
    }

    //pre: an existing text file with text in it
    //post: a string with the text that was in the file
    public static String readFile(String fileName) {
        String str = "";
        try {
            Scanner input = new Scanner(new FileReader(fileName));
            str = input.nextLine();
            input.close();
        } catch(Exception e) {
            return "ts file pmo";
        }
        return str;
    }

    //pre: a string
    /*post: counts the frequency of each character in the string and
    maps the characters to their respective frequency values*/
    public static HashMap<Character, Integer> frequencyCount(String str) {
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for(int i = 0; i < str.length(); i++) {
            if(map.containsKey(str.charAt(i))) {
                map.replace(str.charAt(i), map.get(str.charAt(i))+1);
            } else {
                map.put(str.charAt(i), 1);
            }
        }
        return map;
    }

    //pre: a <Character, Integer> map
    /*post: converts the <Character, Integer> map to a <Character, Node> map
    and returns it*/
    public static HashMap<Character, Node> nodeCollection(HashMap<Character, Integer> freqMap) {
        HashMap<Character, Node> map = new HashMap<Character, Node>();
        Iterator it = freqMap.entrySet().iterator();
        while(it.hasNext()) {
            @SuppressWarnings("unchecked")
            Map.Entry<Character, Integer> pair = (Map.Entry<Character, Integer>)it.next();
            map.put(pair.getKey(), new Node(pair.getValue()));
        }
        return map;
    }
    //pre: an ArrayList of node objects
    /*post: returns an array with the two Nodes with the
    smallest frequencies that are in the ArrayList*/
    public static Node[] twoMinValues(ArrayList<Node> nodesList) {
        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        Node[] vals = new Node[2];
        for(Node x: nodesList) {
            pq.add(x);
        }
        vals[0] = pq.poll();
        vals[1] = pq.poll();
        return vals;
    }

    //pre: a null Node object
    /*post: returns the root node for the tree 
    with leaves that represent character frequency*/
    public static Node treeBuilder(Node root, ArrayList<Node> nodesList) {
        Node temp = new Node(0);
        if(nodesList.size() > 1) {
            Node[] minVals = twoMinValues(nodesList);
            root = temp.add(minVals);
            nodesList.add(root);
            nodesList.remove(minVals[0]); nodesList.remove(minVals[1]);
            root = treeBuilder(root, nodesList);
        }
        return root;
    }

    //pre: the root node of a binary tree and an empty string
    /*post: adds the encoding of the letter to the encoded hash map*/
    public static void encoder(Node root, String thing) {
    	if(root.getLeft() != null){
    			encoder(root.getLeft(), thing + 1);
    	}
    	if(root.getRight() != null){
    		    encoder(root.getRight(), thing + 0);
    	}
    	if(root != null) {
    	encoded.put(oppMap.get(root), thing);
    	}
    }

    //pre: a string
    /*post: prints the string but with each character replaced by its
    respective bit pattern*/
    public static void printBitCompressed(String txt) {
    	System.out.println("Compressed Bit Pattern: ");
        for(int i = 0; i < txt.length(); i++) {
        	if(encoded.containsKey(txt.charAt(i))) {
        		System.out.print(encoded.get(txt.charAt(i)));
        	}
        }
    }
}
