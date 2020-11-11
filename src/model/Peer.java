package model;

import java.util.ArrayList;

public class Peer implements Comparable<Peer>{
	private final Arc a;
	private final Node n1;
	private final Node n2;
	
	/**
	A Peer is the defined by a first node which is linked to a second one by an arc
	@param a : an Arc
	@param n1 : the first Node oriented to the second Node
	@param n2 : the second Node
	*/
	public Peer(Arc a, Node n1, Node n2) {
		this.a = a;
		this.n1 = n1;
		this.n2 = n2;
	}
	
	/**
    @return an ArrayList containing the two nodes of the peer
	*/
	@SuppressWarnings("serial")
	public ArrayList<Node> getNodes() {
		return new ArrayList<Node>() {{add(n1); add(n2);}};
	}
	
	/**
    @return the arc linking the two nodes of the peer
	*/
	public Arc getArc() {
		return a;
	}
	
	/**
    @return a String of the first node, the second and the arc linking both of them
	*/
	public String toString() {
		StringBuilder s = new StringBuilder();
		
        s.append("(" + n1 + ") -> " + a + " -> (" + n2 + ")");
        s.append("\n");
		
		return s.toString();
	}
	
	/**
	Compares this object with the specified object for order.
	@param p : the object to be compared.
    @return a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object.
	*/
	@Override
	public int compareTo(Peer p) {
		return this.n1.getId() - p.n1.getId();
	}
}
