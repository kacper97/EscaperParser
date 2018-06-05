package Items;
import java.util.*;
public class Room extends RouteElement{
	public String name_;
	private Coordinates coordinates_;
	private Hashtable<Integer,Corridor> connectedBy_ = new Hashtable<>();
	public ConnectedBy_ adjacentList;	public Object room;
	public Object name; 
	
	public Room(String name, Coordinates c, int ID, int capacity, int fullness, boolean hasExit) {
		super(ID,capacity,fullness,hasExit);
		name_ = name;
		coordinates_ = c;
	}
	
	// Creating full/not-usable for escape room
	public Room(String name, Coordinates c, int ID, int capacity, boolean hasExit) {
		super(ID,capacity,hasExit);
		name_ = name;
		coordinates_ = c;
	}
	
	public Hashtable<Integer,Corridor> getCorridors() {
		return connectedBy_;
	}
	
	public String getName() {
		return name_;
	}
	
	public Coordinates getCoordinates() {
		return coordinates_;
	}
}
