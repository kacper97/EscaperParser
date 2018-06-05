package read;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import Items.ConnectedBy_;
import Items.Map;
import Items.Room;

import java.io.IOException;


public class Parser {
	 
    Room[] adjacentLists;  //array of adjacent lists
    ConnectedBy_ adjacentList;
    
    public Parser(String file) throws FileNotFoundException {
         
        Scanner sc = new Scanner(new File(file));
         
        String ParserType = sc.next();
        
        /*Undirected graphs have edges that do not have a direction.
         * The edges indicate a two-way relationship, in that each edge can be traversed in both directions.
         * Directed graphs have edges with direction.
         */
        
        boolean undirected=true;
        if (ParserType.equals("directed")) {
            undirected=false;
        }
         
        adjacentLists = new Room[sc.nextInt()];  //
 
        // read vertices
        for (int v=0; v < adjacentLists.length; v++) {
            adjacentLists[v] = new Room(sc.next(), null, sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.hasNext());
        }
 
        // read edges
        while (sc.hasNext()) {
             
            // read Room rooms and translate to Room numbers
            int v1 = indexForroom(sc.next());
            int v2 = indexForroom(sc.next());
             
            // add v2 to front of v1's adjacency list and
            // add v1 to front of v2's adjacency list
            adjacentLists[v1].adjacentList = new ConnectedBy_(v2, adjacentLists[v1].adjacentList);
            if (undirected) {
                adjacentLists[v2].adjacentList = new ConnectedBy_(v1, adjacentLists[v2].adjacentList);
            }
        }
    }
    
    /* Compares the room field against the target and if match 
     * returns current index of array
     */
	
    int indexForroom(String name) {
        for (int v=0; v < adjacentLists.length; v++) {
            if (adjacentLists[v].name_.equals(name)) {
                return v;
            }
        }
        return -1;
    }   
    
    public void updateFullnesses(Map map) {
        System.out.println();
        for (int v=0; v < adjacentLists.length; v++) {
            System.out.print(adjacentLists[v].name_);
            for (ConnectedBy_ nbr=adjacentLists[v].adjacentList; nbr != null;nbr=nbr.next) {
                System.out.print(" --> " + adjacentLists[nbr.vertexNum].name_);
            }
            System.out.println("\n");
        }
    }
    

    public static Map getMap() throws IOException{
		Scanner sc = new Scanner(System.in);
        System.out.print("Enter Parser input file room: "); //allows for different files to be read in
        String file = sc.nextLine();
        Parser Parser = new Parser(file);
        Map Map = new Map(file); // creates a new map 
		Parser.updateFullnesses(Map);
		return Map;
	}
}