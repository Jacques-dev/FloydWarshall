package model;

import java.util.ArrayList;

public class Peer implements Comparable<Peer>{
	private final Arc a;
	private final Node n1;
	private final Node n2;
	
	public Peer(Arc a, Node n1, Node n2) {
		this.a = a;
		this.n1 = n1;
		this.n2 = n2;
	}
	
	@SuppressWarnings("serial")
	public ArrayList<Node> getNodes() {
		return new ArrayList<Node>() {{add(n1); add(n2);}};
	}
	
	public Arc getArc() {
		return a;
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		
        s.append("(" + n1 + ") - " + a + " -> (" + n2 + ")");
        s.append("\n");
		
		return s.toString();
	}
	
	@Override
	public int compareTo(Peer p) {
		return this.n1.getId() - p.n1.getId();
	}
}
